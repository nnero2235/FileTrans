package nnero.filetrans.bean;

import java.io.File;

/**
 * Created by NNERO on 15/12/1.
 * 加N 只为区别系统File类
 */
public class NFile implements Item{

  private String name;
  private File entity;

  public NFile(){}

  public NFile(String name,File entity){
    this.name = name;
    this.entity = entity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public File getEntity() {
    return entity;
  }

  public void setEntity(File entity) {
    this.entity = entity;
  }

  @Override
  public int getType() {
    return Item.TYPE_FILE;
  }
}
