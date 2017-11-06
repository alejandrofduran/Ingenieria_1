import static org.junit.Assert.assertEquals;

import exceptions.InvalidArgumentException;
import model.Client;
import modelImpl.ClientImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ClientTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  public void testCreateClient() {
    Long clientId = 1L;
    Client cliente = new ClientImpl("Nombre", "123456", clientId);
    assertEquals(cliente.id(), clientId);
    assertEquals(cliente.name(), "Nombre");
    assertEquals(cliente.password(), "123456");
  }

  @Test
  public void testCreateClientInvalidPassword() {
    expectedException.expectMessage(ClientImpl.PASSWORD_INVALIDA);
    expectedException.expect(InvalidArgumentException.class);
    Client cliente = new ClientImpl("Nombre", null, 1L);
  }

  @Test
  public void testCreateClientInvalidId() {
    expectedException.expectMessage(ClientImpl.ID_DE_CLIENTE_INVALIDO);
    expectedException.expect(InvalidArgumentException.class);
    Client cliente = new ClientImpl("Nombre", "123456", null);
  }

}
