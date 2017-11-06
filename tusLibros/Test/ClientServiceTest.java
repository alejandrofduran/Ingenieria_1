import static org.junit.Assert.assertEquals;

import exceptions.InvalidArgumentException;
import java.util.HashSet;
import java.util.Set;
import mock.MockFactory;
import model.Client;
import modelImpl.ClientImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.ClientService;
import serviceImpl.ClientServiceImpl;

public class ClientServiceTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  Set<Client> listaClientes;
  Client clienteUno;
  Set<Client> listaClientesVacia = new HashSet<Client>();

  @Before
  public void setUp() {
    listaClientes = new HashSet<Client>();
    clienteUno = new ClientImpl("NombreUno", "123456", 1L);
    listaClientes.add(clienteUno);
  }

  @Test
  public void testValidClient() {
    ClientService clientService = new ClientServiceImpl(listaClientes);
    Client cliente = clientService.findClient(clienteUno.id(), clienteUno.password());

    assertEquals(clienteUno.id(), cliente.id());
    assertEquals(clienteUno.name(), cliente.name());
    assertEquals(clienteUno.password(), cliente.password());
  }

  @Test
  public void testCreateInvalid() {
    Set<Client> invalidListaClientes = new HashSet<Client>();
    invalidListaClientes.add(MockFactory.clientWithNullId());
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage("Id de cliente invalido");
    ClientService clientService = new ClientServiceImpl(invalidListaClientes);
  }

  @Test
  public void testNotValidClient() {
    ClientService clientService = new ClientServiceImpl(listaClientesVacia);
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage("Cliente no encontrado");
    clientService.findClient(clienteUno.id(), clienteUno.password());
  }

  @Test
  public void testNotValidPassword() {
    ClientService clientService = new ClientServiceImpl(listaClientes);
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage("Password Invalida");
    clientService.findClient(clienteUno.id(), clienteUno.password() + "notValidPassword");
  }
}
