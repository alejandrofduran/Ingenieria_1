package model;

import java.util.Date;
import java.util.Map;

public interface Cart {

  public Long id();

  public void add(String isbn, Integer quantity, Date current);

  public Map<String, Integer> itemsList();

  public Date lastOperation();

  public Client client();
}
