package stack;

public class NodoNoVacio implements Nodo {

  private Object o;

  private Nodo anterior;

  public NodoNoVacio(Object object, Nodo anterior) {
  this.o = object;
  this.anterior = anterior;
  }


  @Override
  public Object objeto() {
    return o;
  }

  @Override
  public Nodo anterior() {
    return anterior;
  }

  @Override
  public Boolean esVacio() {
    return false;
  }
}
