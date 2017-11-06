import static org.junit.Assert.assertEquals;

import exceptions.InvalidArgumentException;
import model.CreditCard;
import modelImpl.CreditCardImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CreditCardTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  private String expirationDate = "012019";
  private Long number = 1234123412341234L;
  private String owner = "John doe";

  @Test
  public void testCrearTarjeta() {
    CreditCard tarjeta = new CreditCardImpl(number, expirationDate, owner);
    assertEquals(tarjeta.creditCardExpiration(), expirationDate);
    assertEquals(tarjeta.number(), number);
    assertEquals(tarjeta.owner(), owner);
  }

  @Test
  public void testCrearTarjetaConNumberNull() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CreditCardImpl.NUMERO_DE_TARJETA_INVALIDO);
    CreditCard tarjeta = new CreditCardImpl(null, expirationDate, owner);
  }

  @Test
  public void testCrearTarjetaConOwnerNull() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CreditCardImpl.FECHA_DE_EXPIRACION_INVALIDA);
    CreditCard tarjeta = new CreditCardImpl(number, null, owner);
  }

  @Test
  public void testCrearTarjetaConCreditCardExpirationNull() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CreditCardImpl.OWNER_INVALIDO);
    CreditCard tarjeta = new CreditCardImpl(number, expirationDate, null);
  }


}
