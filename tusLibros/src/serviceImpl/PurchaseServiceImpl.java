package serviceImpl;

import static utils.Utils.checkArgument;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import model.Cart;
import model.Client;
import model.Sale;
import modelImpl.SaleImpl;
import service.PurchaseService;

public class PurchaseServiceImpl implements PurchaseService {

  private Map<Client, Set<Sale>> clientsSales;

  private Long nextId;

  public PurchaseServiceImpl(Set<Client> clients, Long nextId) {
    clientsSales = new HashMap<>();
    for (Client client : clients) {
      clientsSales.put(client, new HashSet<>());
    }
    this.nextId = nextId;
  }

  @Override
  public Set<Sale> findSales(Client client) {
    Set<Sale> sales = clientsSales.get(client);
    checkArgument(sales != null, "Compras del cliente no encontradas");
    return sales;
  }

  @Override
  public Long addSale(Cart cart, double amount) {
    Client client = cart.client();
    Set<Sale> sales = clientsSales.get(client);
    checkArgument(sales != null, "Carrito invalido");
    Sale sale = new SaleImpl(cart, amount, nextId);
    nextId++;
    sales.add(sale);
    return sale.id();
  }
}
