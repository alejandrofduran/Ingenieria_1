import model.CreditCard;
import modelImpl.CreditCardImpl;
import org.junit.Test;

public class CreditCardTest {

  @Test
  public void testCrearTarjeta() {
    CreditCard tarjeta = new CreditCardImpl(123L, "012019", "alguien");
  }

}
