package nnero.filetrans.bean;

/**
 * Created by NNERO on 15/12/1.
 */
public interface Item {
  int TYPE_DIR = 1;
  int TYPE_FILE = 2;

  int getType();
}
