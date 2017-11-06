package modelImpl;

import static utils.Utils.checkArgument;

import model.Client;

public class ClientImpl implements Client {

  public static final String ID_DE_CLIENTE_INVALIDO = "Id de cliente invalido";
  public static final String PASSWORD_INVALIDA = "Password invalida";
  private String name;
  private String password;
  private Long id;

  public ClientImpl(String name, String password, Long id) {
    checkArgument(id != null, ID_DE_CLIENTE_INVALIDO);
    checkArgument(password != null, PASSWORD_INVALIDA);
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
