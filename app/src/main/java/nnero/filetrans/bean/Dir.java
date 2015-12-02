package nnero.filetrans.bean;

import java.io.File;

/**
 * Created by NNERO on 15/12/1.
 */
public class Dir implements Item {

  private String name;
  private String path;

  public Dir(){}

  public Dir(String name,String path){
    this.name = name;
    this.path = path;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public int getType() {
    return Item.TYPE_DIR;
  }
}
