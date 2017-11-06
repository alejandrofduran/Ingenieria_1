package service;

import java.util.Map;
import java.util.Set;
import model.Sale;

public interface TusLibrosService {

  public Long createCart(Long clientId, String password);

  public void addToCart(Long cartId, String bookIsn, Integer bookQuantity);

  public Map<String, Integer> listCart(Long id);

  public Set<Sale> listPurchases(Long id, String password);

  public Long checkOut(Long cartId, String ccn, String cced, String cco);
}
