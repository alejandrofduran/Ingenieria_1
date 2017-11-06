package service;

import model.Client;

public interface ClientService {

  Client findClient(Long clientId, String password);
}
