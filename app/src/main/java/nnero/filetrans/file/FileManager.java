package nnero.filetrans.file;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nnero.filetrans.bean.Dir;
import nnero.filetrans.bean.Item;
import nnero.filetrans.bean.NFile;
import nnero.filetrans.util.CommonUtil;

/**
 * Created by NNERO on 15/12/1.
 */
public class FileManager {
  private static final String ROOT = "";

  private static FileManager sFileManager;

  private FileManager(){}

  public static FileManager getInstance(){
    if(sFileManager == null)
      sFileManager = new FileManager();

    return sFileManager;
  }

  /**
   * 获取根目录所有files
   * @return
   */
  public List<Item> getRootAllFiles(){
    String path = Environment.getExternalStoragePublicDirectory(ROOT).getAbsolutePath();
    CommonUtil.log(path);
    return getLevelAllFiles(path);
  }

  /**
   * 获取某一层级的所有file
   * @param name
   * @return
   */
  public List<Item> getLevelAllFiles(String name){
    File file = new File(name);
    if(file.exists()){
      File[] files = file.listFiles();
//      CommonUtil.log(files.length+"");
      return createItemsByFileArray(files);
    }
    return null;
  }

  private List<Item> createItemsByFileArray(File[] files){
    Dir dir = null;
    NFile nFile = null;
    List<Item> items = new ArrayList<>();
    for(File file: files){
      if(file.isDirectory()){
        dir = new Dir(file.getName(),file.getAbsolutePath());
        items.add(dir);
      } else {
        nFile = new NFile(file.getName(),file.getAbsolutePath());
        items.add(nFile);
      }
    }
    return items;
  }
}
