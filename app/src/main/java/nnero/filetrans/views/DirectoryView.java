package nnero.filetrans.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import nnero.filetrans.R;

/**
 * Created by NNERO on 15/12/1.
 */
public class DirectoryView extends LinearLayout{

  private float mTextSize;
  private int mTextColor;

  private int mItemWidth;
  private int mItemHeight;
  private int mLineWidth;

  private LinkedList<View> views; //增加顺序 是一个tv 一个lineview

  public DirectoryView(Context context) {
    super(context);
    init();
  }

  public DirectoryView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public DirectoryView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init(){
    initData();
    setPadding(10,10,10,10);
    setOrientation(HORIZONTAL);
    setBackgroundColor(getResources().getColor(R.color.White));
    addView(creatItemView(R.string.sd_card));
    addView(creatLineView());
  }

  private void initData(){
    views = new LinkedList<>();
    mTextSize = 16.0f;
    mTextColor = getResources().getColor(R.color.Black);
    mItemHeight = (int) getResources().getDimension(R.dimen.dir_item_height);
    mItemWidth = (int) getResources().getDimension(R.dimen.dir_item_width);
    mLineWidth = (int) getResources().getDimension(R.dimen.dir_line_width);
  }

  private View creatLineView(){
    View v = new View(getContext());
    LayoutParams params = new LayoutParams(mLineWidth, ViewGroup.LayoutParams.MATCH_PARENT);
    params.setMargins(0,3,0,3);
    v.setBackgroundColor(getResources().getColor(R.color.Gray));
    v.setLayoutParams(params);
    views.add(v);
    return v;
  }

  private TextView creatItemView(int resId){
    TextView tv = creatItemView();
    tv.setText(resId);
    return tv;
  }

  private TextView creatItemView(String text){
    TextView tv = creatItemView();
    tv.setText(text);
    return tv;
  }

  private TextView creatItemView(){
    TextView tv = new TextView(getContext());
    tv.setTextSize(mTextSize);
    tv.setTextColor(mTextColor);
    tv.setSingleLine(true);
    LayoutParams params = new LayoutParams(mItemWidth,mItemHeight);
    params.setMargins(5,5,5,5);
    tv.setLayoutParams(params);
    tv.setGravity(Gravity.CENTER);
    views.add(tv);
    return tv;
  }

  /**
   * 增加目录层级
   * @param name
   */
  public void increaseLevelDir(String name){
    addView(creatItemView(name));
    addView(creatLineView());
  }

  /**
   * 减掉 目录层级
   */
  public void reduceLevelDir(){
    removeView(views.pollLast());
    removeView(views.pollLast());
  }

}
