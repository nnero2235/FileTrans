package nnero.filetrans;

import android.app.Application;
import android.content.Context;

/**
 * Created by nnero on 15/12/2.
 */
public class App extends Application{

    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getContext(){
        return instance;
    }

}
