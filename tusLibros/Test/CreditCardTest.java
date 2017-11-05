import ModelImpl.CreditCardImpl;
import org.junit.Test;

public class CreditCardTest {

  @Test
  public void testCrearTarjeta() {
    CreditCardImpl tarjeta = new CreditCardImpl(123, "012019", "alguien");
  }

}
