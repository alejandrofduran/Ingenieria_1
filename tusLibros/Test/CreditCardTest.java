import model.CreditCardImpl;
import org.junit.Test;

public class CreditCardTest {

  @Test
  public void testCrearTarjeta() {
    CreditCardImpl tarjeta = new CreditCardImpl(123,123,"alguien");
  }

}
