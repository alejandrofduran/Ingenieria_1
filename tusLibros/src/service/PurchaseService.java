package service;

import java.util.Set;
import model.Cart;
import model.Client;
import model.Sale;

public interface PurchaseService {

  Set<Sale> findSales(Client client);

  Long addSale(Cart cart);
}
