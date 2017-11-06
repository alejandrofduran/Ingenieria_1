package modelImpl;

import model.Client;

public class ClientImpl implements Client {

  private String name;
  private String password;
  private Long id;

  public ClientImpl(String name, String password, Long id) {
    this.name = name;
    this.password = password;
    this.id = id;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public String password() {
    return password;
  }

  @Override
  public Long id() {
    return id;
  }
}
