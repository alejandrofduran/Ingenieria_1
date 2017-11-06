package mock;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import model.Cart;
import model.Client;
import model.CreditCard;
import modelImpl.ClientImpl;
import processor.MerchantProcessor;

public class MockFactory {


  public static Client clientWithNullId() {
    return new Client() {
      @Override
      public String name() {
        return null;
      }

      @Override
      public String password() {
        return null;
      }

      @Override
      public Long id() {
        return null;
      }
    };
  }

  public static MerchantProcessor validMerchatProcessor() {
    return new MerchantProcessor() {
      @Override
      public void debit(double price, Long number, String expirationDate, String owner) {

      }
    };
  }

  public static Client validClient() {
    return new Client() {
      @Override
      public String name() {
        return "John doe";
      }

      @Override
      public String password() {
        return "123456";
      }

      @Override
      public Long id() {
        return 100L;
      }
    };
  }

  public static Cart cartWithBooks(Set<String> books) {
    return new Cart() {
      @Override
      public Long id() {
        return 20L;
      }

      @Override
      public void add(String isbn, Integer quantity, Date current) {

      }

      @Override
      public Map<String, Integer> itemsList() {
        Map<String, Integer> map = new HashMap();
        for (String s : books) {
          map.put(s, 10);
        }
        return map;
      }

      @Override
      public Date lastOperation() {
        return new Date();
      }

      @Override
      public Client client() {
        return validClient();
      }
    };
  }

  public static CreditCard validCreditCard() {
    return new CreditCard() {
      @Override
      public String owner() {
        return "Jonh Doe";
      }

      @Override
      public String creditCardExpiration() {
        return "012050";
      }

      @Override
      public Long number() {
        return 1234123412341234L;
      }
    };
  }

  public static Cart cartWithNoBooks() {
    return new Cart() {
      @Override
      public Long id() {
        return null;
      }

      @Override
      public void add(String isbn, Integer quantity, Date current) {

      }

      @Override
      public Map<String, Integer> itemsList() {
        return new HashMap<>();
      }

      @Override
      public Date lastOperation() {
        return null;
      }

      @Override
      public Client client() {
        return null;
      }
    };
  }

  public static Set<String> books() {
    Set<String> books = new HashSet<>();
    books.add("Libro 1");
    books.add("Libro 2");
    return books;
  }

  public static Set<Client> clients() {
    Set<Client> clients = new HashSet<>();
    clients.add(new ClientImpl("John doe", "123456", 1l));
    return clients;
  }

}
