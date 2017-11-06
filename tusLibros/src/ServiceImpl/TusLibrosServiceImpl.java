package serviceImpl;

import java.util.Map;
import java.util.Set;
import model.Sale;
import service.TusLibrosService;

public class TusLibrosServiceImpl implements TusLibrosService {

  @Override
  public Long createCart(Long clientId, String password) {
    return null;
  }

  @Override
  public void addToCart(Long cartId, String bookIsn, Integer bookQuantity) {

  }

  @Override
  public Map<String, Integer> listCart(Long id) {
    return null;
  }

  @Override
  public Set<Sale> listPurchases(Long id, String password) {
    return null;
  }
}
