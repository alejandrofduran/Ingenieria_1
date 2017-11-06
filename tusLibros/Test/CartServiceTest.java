import exceptions.InvalidArgumentException;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.CartService;
import serviceImpl.CartServiceImpl;

public class CartServiceTest {

  private final static String ISBN_EN_CATALGO_UNO = "ISBN 1";
  private final static String ISBN_EN_CATALGO_DOS = "ISBN 2";
  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  private CartService cartService;
  private Set<String> catalogueIsbn;

  @Before
  public void setup() {
    catalogueIsbn = new HashSet<>();
    catalogueIsbn.add(ISBN_EN_CATALGO_UNO);
    catalogueIsbn.add(ISBN_EN_CATALGO_DOS);
    cartService = new CartServiceImpl(1L, catalogueIsbn);
  }

  @Test
  public void voidTestCreateWithEmptyCatalogue() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartServiceImpl.CATALOGO_VACIO);
    CartService service = new CartServiceImpl(1L, new HashSet<String>());
  }

  @Test
  public void voidTestCreateWithNullCatalogue() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartServiceImpl.CATALOGO_INVALIDO);
    CartService service = new CartServiceImpl(1L, null);
  }

  @Test
  public void voidTestCreateWithNullFirstID() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartServiceImpl.FIRST_ID_INVALIDO);
    CartService service = new CartServiceImpl(null, catalogueIsbn);
  }


}
