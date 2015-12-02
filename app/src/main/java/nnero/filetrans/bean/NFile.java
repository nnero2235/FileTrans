package nnero.filetrans.bean;

import java.io.File;

/**
 * Created by NNERO on 15/12/1.
 * 加N 只为区别系统File类
 */
public class NFile implements Item{

  private String name;
  private String path;

  public NFile(){}

  public NFile(String name,String path){
    this.name = name;
    this.path = path;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  @Override
  public int getType() {
    return Item.TYPE_FILE;
  }
}
