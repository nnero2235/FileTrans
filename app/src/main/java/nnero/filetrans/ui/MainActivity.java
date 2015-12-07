package nnero.filetrans.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nnero.filetrans.App;
import nnero.filetrans.R;
import nnero.filetrans.bean.Dir;
import nnero.filetrans.bean.Item;
import nnero.filetrans.file.FileManager;
import nnero.filetrans.util.CommonUtil;
import nnero.filetrans.views.DirectoryView;

public class MainActivity extends AppCompatActivity {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.dir) DirectoryView dirView;
  @Bind(R.id.list) RecyclerView listView;

  private List<Item> mItems;
  private ItemAdapter mItemAdapter;

  private long mExitTime;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initData();
    initViews();
    initListeners();
    App.calculateItemNumbers(CommonUtil.getScreenHeight(this),toolbar.getHeight(),dirView.getHeight());
  }

  private void initData() {
    mItems = new ArrayList<>();
    mItems.addAll(FileManager.getInstance().getRootAllFiles());
  }

  private void initViews() {
    setSupportActionBar(toolbar);
    mItemAdapter = new ItemAdapter(this, mItems);
    listView.setItemAnimator(new DefaultItemAnimator());
    listView.setLayoutManager(new LinearLayoutManager(this));
//    listView.addItemDecoration(new ItemDecotation(this, ItemDecotation.VERTICAL_LIST));
    listView.setAdapter(mItemAdapter);
  }

  private void initListeners() {
    dirView.setOnDirClickListener(path -> {
      mItemAdapter.refreshItems(path);
    });
    mItemAdapter.setOnLevelChangeListener(item -> {
      if(item.getType() == Item.TYPE_DIR){
        Dir dir = (Dir)item;
        dirView.increaseLevelDir(dir.getName());
        FileManager.getInstance().levelPlus(dir.getPath());
      } else {
        //TODO:support normal files
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onBackPressed() {
    CommonUtil.log(FileManager.getInstance().getLevel() + "");
    if(FileManager.getInstance().getLevel() <= 1){ //退出应用
      if(System.currentTimeMillis() - mExitTime < 2000){
        super.onBackPressed();
      }
      mExitTime = System.currentTimeMillis();
      CommonUtil.toastOnShort("再按一次退出");
    } else { //退回上一级
      dirView.reduceLevelDir();
      FileManager.getInstance().levelReduce();
      mItemAdapter.resetLastLevelItems();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    FileManager.getInstance().clearInfos();
  }
}
