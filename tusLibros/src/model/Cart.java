package model;

import java.util.Map;

public interface Cart {

  public Long id();

  public void add(String isbn, Integer quantity);

  public Map<String, Integer> itemsList();
}
