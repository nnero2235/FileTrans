package nnero.filetrans.ui;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
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


  public ItemAdapter(Activity activity,List<Item> items){
    this.mContext = activity;
    this.mItems = items;
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

  private void refreshItems(String path){
    mItems.clear();
    mItems.addAll(FileManager.getInstance().getLevelAllFiles(path));
    notifyDataSetChanged();
  }

  public void resetLastLevelItems() {
    String lastPath = FileManager.getInstance().getLastLevelPath();
    CommonUtil.log(lastPath);
    refreshItems(lastPath);
  }

  static class ViewHolder extends RecyclerView.ViewHolder{


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
