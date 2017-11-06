import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import exceptions.InvalidArgumentException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import mock.MockFactory;
import model.Cart;
import model.Client;
import model.Sale;
import modelImpl.CartImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.PurchaseService;
import serviceImpl.PurchaseServiceImpl;

public class PurchaseServiceTest {

  private final static String ISBN_EN_CATALGO_UNO = "ISBN 1";
  private final static String ISBN_EN_CATALGO_DOS = "ISBN 2";
  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  private PurchaseService purchaseService;
  private Set<String> catalogueIsbn;
  private Client client;


  @Before
  public void setup() {
    client = MockFactory.validClient();
    Set<Client> clients = new HashSet<>();
    clients.add(client);
    Long nextId = 1L;
    purchaseService = new PurchaseServiceImpl(clients, nextId);
    catalogueIsbn = new HashSet<>();
    catalogueIsbn.add(ISBN_EN_CATALGO_UNO);
    catalogueIsbn.add(ISBN_EN_CATALGO_DOS);
  }

  @Test
  public void testCreateWithEmtpyClients() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(PurchaseServiceImpl.CONJUNTO_DE_CLIENTES_VACIO);
    purchaseService = new PurchaseServiceImpl(new HashSet<>(), 1L);
  }


  @Test
  public void testCreateWithNullClients() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(PurchaseServiceImpl.CONJUNTO_DE_CLIENTES_INVALIDO);
    purchaseService = new PurchaseServiceImpl(null, 1L);
  }

  @Test
  public void testCreateWithNullFirstId() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(PurchaseServiceImpl.NEXT_ID_INVALIDO);
    purchaseService = new PurchaseServiceImpl(MockFactory.clients(), null);
  }

  @Test
  public void testAddSale() {
    Set<Sale> sales = purchaseService.findSales(client);
    assertTrue(sales.isEmpty());
    Cart cart = new CartImpl(100L, catalogueIsbn, new Date(), client);
    cart.add(ISBN_EN_CATALGO_UNO, 10, new Date());
    double amount = 100.5;
    Long saleId = purchaseService.addSale(cart, amount);
    sales = purchaseService.findSales(client);
    assertTrue(!sales.isEmpty());
    assertTrue(sales.size() == 1);
    Sale before = null;
    for (Sale sale : sales) {
      assertTrue(sale.id() == saleId);
      assertTrue(sale.amount() == amount);
      assertThat(sale.client(), is(client));
      before = sale;
    }
    Long saleId2 = purchaseService.addSale(cart, amount + 10);
    assertTrue(saleId == saleId2 - 1);
    sales = purchaseService.findSales(client);
    assertTrue(sales.size() == 2);
    assertTrue(sales.contains(before));
    sales.remove(before);
    for (Sale sale : sales) {
      assertTrue(sale.id() == saleId2);
      assertTrue(sale.amount() == amount + 10);
      assertThat(sale.client(), is(client));
      before = sale;
    }
  }


  @Test
  public void testFindSale() {
    Set<Sale> sales = purchaseService.findSales(client);
    assertTrue(sales.isEmpty());
  }

  @Test
  public void testFindSaleWithNoUser() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(PurchaseServiceImpl.COMPRAS_DEL_CLIENTE_NO_ENCONTRADAS);
    purchaseService.findSales(MockFactory.validClient());
  }
}
