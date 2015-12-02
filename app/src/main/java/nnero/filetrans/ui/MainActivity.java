package nnero.filetrans.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nnero.filetrans.R;
import nnero.filetrans.bean.Item;
import nnero.filetrans.bean.NFile;
import nnero.filetrans.file.FileManager;
import nnero.filetrans.util.CommonUtil;
import nnero.filetrans.views.DirectoryView;

public class MainActivity extends AppCompatActivity {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.dir) DirectoryView dirView;
  @Bind(R.id.list) RecyclerView listView;

  private List<Item> mItems;
  private ItemAdapter mItemAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initData();
    initViews();
  }

  private void initData(){
    mItems = new ArrayList<>();
    mItems.addAll(FileManager.getInstance().getRootAllFiles());
  }

  private void initViews(){
    setSupportActionBar(toolbar);
    mItemAdapter = new ItemAdapter(this,mItems);
    listView.setItemAnimator(new DefaultItemAnimator());
    listView.setLayoutManager(new LinearLayoutManager(this));
    listView.addItemDecoration(new ItemDecotation(this,ItemDecotation.VERTICAL_LIST));
    listView.setAdapter(mItemAdapter);
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
}
