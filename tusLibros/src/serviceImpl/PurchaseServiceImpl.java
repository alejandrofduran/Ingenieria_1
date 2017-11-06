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

  public static final String CONJUNTO_DE_CLIENTES_VACIO = "Conjunto de clientes vacio";
  public static final String NEXT_ID_INVALIDO = "Next id invalido";
  public static final String CONJUNTO_DE_CLIENTES_INVALIDO = "Conjunto de clientes invalido";
  public static final String COMPRAS_DEL_CLIENTE_NO_ENCONTRADAS = "Compras del cliente no encontradas";
  public static final String CARRITO_INVALIDO = "Carrito invalido";
  private Map<Client, Set<Sale>> clientsSales;

  private Long nextId;

  public PurchaseServiceImpl(Set<Client> clients, Long nextId) {
    clientsSales = new HashMap<>();
    checkArgument(clients != null, CONJUNTO_DE_CLIENTES_INVALIDO);
    checkArgument(!clients.isEmpty(), CONJUNTO_DE_CLIENTES_VACIO);
    checkArgument(nextId != null, NEXT_ID_INVALIDO);
    for (Client client : clients) {
      clientsSales.put(client, new HashSet<>());
    }
    this.nextId = nextId;
  }

  @Override
  public Set<Sale> findSales(Client client) {
    Set<Sale> sales = clientsSales.get(client);
    checkArgument(sales != null, COMPRAS_DEL_CLIENTE_NO_ENCONTRADAS);
    return sales;
  }

  @Override
  public Long addSale(Cart cart, double amount) {
    Client client = cart.client();
    Set<Sale> sales = findSales(client);
    checkArgument(sales != null, CARRITO_INVALIDO);
    Sale sale = new SaleImpl(cart, amount, nextId);
    nextId++;
    sales.add(sale);
    return sale.id();
  }
}
