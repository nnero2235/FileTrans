package nnero.filetrans.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import nnero.filetrans.App;

/**
 * Created by NNERO on 15/12/1.
 */
public class CommonUtil {

  private static boolean debug = true;
  private static String TAG = "NNERO";

  public static void log(String msg){
    if(debug) Log.d(TAG,msg);
  }

  public static void toastOnShort(String msg){
    Toast.makeText(App.getContext(),msg,Toast.LENGTH_SHORT).show();
  }

  public static void toastOnLong(String msg){
    Toast.makeText(App.getContext(),msg,Toast.LENGTH_LONG).show();
  }

  public static int getScreenHeight(Activity activity){
    DisplayMetrics displayMetrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    return displayMetrics.heightPixels;
  }
}

