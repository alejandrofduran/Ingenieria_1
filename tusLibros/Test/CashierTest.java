import exceptions.InvalidArgumentException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import mock.MockFactory;
import model.Cart;
import model.Cashier;
import modelImpl.CashierImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import processor.MerchantProcessor;

public class CashierTest {

  public static final String BOOK_2 = "Book 2";
  public static final String BOOK_1 = "Book 1";
  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  private Cashier cashier;
  private Map<String, Integer> prices;
  private MerchantProcessor merchantProcessor;
  private Cart cart;

  @Before
  public void setup() {
    prices = new HashMap();
    prices.put(BOOK_1, 10);
    prices.put(BOOK_2, 20);
    merchantProcessor = MockFactory.validMerchatProcessor();
    cashier = new CashierImpl(prices, MockFactory.validMerchatProcessor());
  }

  @Test
  public void testCreateCashierWithNullPrices() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CashierImpl.LISTA_DE_PRECIOS_INVALIDA);
    cashier = new CashierImpl(null, merchantProcessor);
  }

  @Test
  public void testCreateCashierWithEmtpyPrices() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CashierImpl.LA_LISTA_DE_PRECIOS_ESTA_VACIA);
    cashier = new CashierImpl(new HashMap<>(), merchantProcessor);
  }

  @Test
  public void testCreateCashierWithNullMerchantProcessor() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CashierImpl.MERCHANT_PROCESSOR_INVALIDO);
    cashier = new CashierImpl(new HashMap<>(), null);
  }

  @Test
  public void testCreateCashierWithPricesInvalid() {
    prices.put("Libro 3", -10);
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CashierImpl.PRECIO_MAYOR_A_0);
    cashier = new CashierImpl(prices, merchantProcessor);
  }

  @Test
  public void testCheckOut() {
    cashier.checkOut(MockFactory.cartWithBooks(prices.keySet()), MockFactory.validCreditCard());
  }

  @Test
  public void testCheckOutWithEmptyCart() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CashierImpl.CARRITO_VACIO_ERR);
    cashier.checkOut(MockFactory.cartWithNoBooks(), MockFactory.validCreditCard());
  }

  @Test
  public void testCheckOutWithBookOutOfThePriceCatalog() {
    HashSet<String> books = new HashSet<>();
    books.add("Libro que no esta");
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CashierImpl.LIBRO_FUERA_DEL_CATALOGO_DE_PRECIOS);
    cashier.checkOut(MockFactory.cartWithBooks(books), MockFactory.validCreditCard());
  }
}
