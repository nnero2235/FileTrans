package nnero.filetrans;

import android.app.Application;
import android.content.Context;

/**
 * Created by nnero on 15/12/2.
 */
public class App extends Application{

    private static Context instance;
    private static int animateItemNumbers; //播放动画item个数 通过计算获得

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getContext(){
        return instance;
    }

    public static int getAnimateItemNumbers(){
        return animateItemNumbers;
    }

    public static void calculateItemNumbers(int screenHeight,int toolbarHeight,int dirHeight){
        animateItemNumbers = (int) ((screenHeight - toolbarHeight - dirHeight)
                        / (getContext().getResources().getDimension(R.dimen.list_item_height)
                        + getContext().getResources().getDimension(R.dimen.card_item_margin_bottom) * 2));
    }
}
