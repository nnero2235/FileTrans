package nnero.filetrans.util;

import android.util.Log;

/**
 * Created by NNERO on 15/12/1.
 */
public class CommonUtil {

  private static boolean debug = true;
  private static String TAG = "NNERO";

  public static void log(String msg){
    if(debug) Log.d(TAG,msg);
  }
}
