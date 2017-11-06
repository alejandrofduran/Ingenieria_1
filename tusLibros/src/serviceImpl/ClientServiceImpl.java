package serviceImpl;

import static utils.Utils.checkArgument;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import model.Client;
import service.ClientService;

public class ClientServiceImpl implements ClientService {

  private Map<Long, Client> clientsMap;


  public ClientServiceImpl(Set<Client> clients) {
    clientsMap = new HashMap<>();
    for (Client client : clients) {
      clientsMap.put(client.id(), client);
    }
  }

  @Override
  public Client findClient(Long clientId, String password) {
    Client client = clientsMap.get(clientId);
    checkArgument(client != null, "Cliente no encontrado");
    checkArgument(client.password().equals(password), "Password Invalida");
    return client;
  }
}
