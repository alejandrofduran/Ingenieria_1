import ModelImpl.CreditCardImpl;
import model.CreditCard;
import org.junit.Test;

public class CreditCardTest {

  @Test
  public void testCrearTarjeta() {
    CreditCard tarjeta = new CreditCardImpl(123, "012019", "alguien");
  }

}
