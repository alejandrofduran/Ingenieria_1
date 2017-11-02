package utils;

import exceptions.InvalidArgumentException;
import java.util.Date;
import java.util.Random;

public class Utils {

  static Random random = new Random(new Date().getTime());

  public static int random() {
    return random.nextInt();
  }

  public static int randomAbove(int a) {
    return random.nextInt(a) + a + 1;
  }

  public static int randomMod(int a) {
    return random.nextInt(a) + 1;
  }

  public static int randomBetween(int a, int b) {
    if (a <= b) {
      throw new RuntimeException("Parametros invalidos");
    }
    int bet = a - b;
    int mod = random.nextInt(bet);
    return b + mod;
  }

  public static void checkArgument(boolean condition, String message) {
    if (!condition) {
      throw new InvalidArgumentException(message);
    }
  }

}
