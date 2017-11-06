package ModelImpl;

import model.Client;

public class ClientImpl implements Client {

  private String name;

  private String password;

  private Long id;

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
