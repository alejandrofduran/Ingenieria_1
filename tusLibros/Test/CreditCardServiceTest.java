import exceptions.InvalidArgumentException;
import java.util.Date;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.CreditCardService;
import serviceImpl.CreditCardServiceImpl;

public class CreditCardServiceTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  private CreditCardService creditCardService = new CreditCardServiceImpl();

  @Test
  public void testCreditCardValidation() {
    creditCardService.verifyCrediteCard("1234123412341234", "122050", "John Doe", new Date());
  }

  @Test
  public void testCreditCardValidationWithInvalidFormat() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CreditCardServiceImpl.FORMATO_DE_FECHA_INVALIDO);
    creditCardService.verifyCrediteCard("1234123412341234", "92050", "John Doe", new Date());
  }

  @Test
  public void testCreditCardValidationWithInvalidFormatNumberLetters() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CreditCardServiceImpl.NUMERO_DE_TARJETA_INVALIDO);
    creditCardService.verifyCrediteCard("asdasdas", "092050", "John Doe", new Date());
  }

  @Test
  public void testCreditCardValidationWithInvalidFormatNumberLength() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CreditCardServiceImpl.NUMERO_DE_TARJETA_INVALIDO);
    creditCardService.verifyCrediteCard("123412341234123", "092050", "John Doe", new Date());
  }

  @Test
  public void testCreditCardValidationWithInvalidFormatOwner() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CreditCardServiceImpl.FORMATO_DE_OWNER_INVALIDO);
    creditCardService.verifyCrediteCard("123412341234123", "092050",
        "John Doe algo muy largo que no entra en el nombre", new Date());
  }

}
