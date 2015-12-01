package nnero.filetrans.bean;

import java.io.File;

/**
 * Created by NNERO on 15/12/1.
 */
public class Dir implements Item {

  private String name;
  private File entity;

  public Dir(){}

  public Dir(String name,File entity){
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
    return Item.TYPE_DIR;
  }
}
