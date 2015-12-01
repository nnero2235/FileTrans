package nnero.filetrans.ui;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nnero.filetrans.R;
import nnero.filetrans.bean.Dir;
import nnero.filetrans.bean.Item;
import nnero.filetrans.bean.NFile;

/**
 * Created by NNERO on 15/12/1.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

  private Activity mContext;
  private List<Item> mItems;

  public ItemAdapter(Activity activity,List<Item> items){
    this.mContext = activity;
    this.mItems = items;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ViewHolder viewHolder = new ViewHolder(View.inflate(mContext, R.layout.item,null));
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    if(mItems.get(position).getType() == Item.TYPE_DIR){
      Dir dir = (Dir) mItems.get(position);
      holder.nameView.setText(dir.getName());
    } else {
      NFile nFile = (NFile) mItems.get(position);
      holder.nameView.setText(nFile.getName());
    }
  }

  @Override
  public int getItemCount() {
    return mItems.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.item_tv) TextView nameView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }
}
