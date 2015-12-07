package nnero.filetrans.ui;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nnero.filetrans.App;
import nnero.filetrans.R;
import nnero.filetrans.bean.Dir;
import nnero.filetrans.bean.Item;
import nnero.filetrans.bean.NFile;
import nnero.filetrans.file.FileManager;
import nnero.filetrans.util.CommonUtil;

/**
 * Created by NNERO on 15/12/1.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

  private Activity mContext;
  private List<Item> mItems;
  private OnLevelChangeListener mLevelListener;
//  private boolean isAnimateView; //是否播动画
  private int lastAnimateViewPos; //上滑的时候不应该播动画


  public ItemAdapter(Activity activity,List<Item> items){
    this.mContext = activity;
    this.mItems = items;
    this.lastAnimateViewPos = -1;
  }

  private void runInAnimation(View v,int pos){
    if(pos > lastAnimateViewPos && pos < App.getAnimateItemNumbers()) {
      lastAnimateViewPos = pos;
      v.setTranslationY(CommonUtil.getScreenHeight(mContext));
      v.animate()
              .translationY(0)
              .setStartDelay(100 * pos)
              .setInterpolator(new DecelerateInterpolator(3.f))
              .setDuration(1000)
              .start();
    }
  }

  public void setOnLevelChangeListener(OnLevelChangeListener l){
    this.mLevelListener = l;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    runInAnimation(holder.itemView,position);
    if(mItems.get(position).getType() == Item.TYPE_DIR){
      Dir dir = (Dir) mItems.get(position);
      holder.itemView.setTag(dir);
      holder.itemView.setOnClickListener(mDirClickListener);
      holder.iconView.setImageResource(R.drawable.ic_dir);
      holder.nameView.setText(dir.getName());
    } else {
      NFile nFile = (NFile) mItems.get(position);
      holder.iconView.setImageResource(R.drawable.ic_file);
      holder.nameView.setText(nFile.getName());
      holder.itemView.setOnClickListener(null);
    }
  }

  private View.OnClickListener mDirClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      Dir dir = (Dir) v.getTag();
      if(mLevelListener != null)
        mLevelListener.onLevelChange(dir);
      refreshItems(dir.getPath());
    }
  };

  @Override
  public int getItemCount() {
    return mItems.size();
  }

  public void refreshItems(String path){
    mItems.clear();
    mItems.addAll(FileManager.getInstance().getLevelAllFiles(path));
    lastAnimateViewPos = -1;
    notifyDataSetChanged();
  }

  public void resetLastLevelItems() {
    String lastPath = FileManager.getInstance().getLastLevelPath();
    CommonUtil.log(lastPath);
    refreshItems(lastPath);
  }

  class ViewHolder extends RecyclerView.ViewHolder{


    @Bind(R.id.item_tv) TextView nameView;
    @Bind(R.id.icon) ImageView iconView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }

  public interface OnLevelChangeListener{
    public void onLevelChange(Item item);
  }
}
