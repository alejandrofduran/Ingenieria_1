package stack;

public class NodoVacio implements Nodo {

  @Override
  public Object objeto() {
    throw new RuntimeException(Stack.STACK_EMPTY_DESCRIPTION);
  }

  @Override
  public Nodo anterior() {
    throw new RuntimeException(Stack.STACK_EMPTY_DESCRIPTION);
  }

  @Override
  public Boolean esVacio() {
    return true;
  }
}
