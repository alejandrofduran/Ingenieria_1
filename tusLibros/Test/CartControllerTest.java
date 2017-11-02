import org.junit.Before;
import org.junit.Test;
import rest.CartController;

public class CartControllerTest {

  private CartController cartController;

  @Before
  public void construct() {
    cartController = new CartController();
  }

  @Test
  public void testListar() {
    cartController.listCart(1L);
  }

}

