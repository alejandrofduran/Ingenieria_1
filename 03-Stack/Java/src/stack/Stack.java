/*
 * Developed by 10Pines SRL
 * License: 
 * This work is licensed under the 
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ 
 * or send a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, 
 * California, 94041, USA.
 *  
 */
package stack;

public class Stack {

  public static final String STACK_EMPTY_DESCRIPTION = "Stack is Empty";
  private Nodo actual;
  private int size = 0;

  public Stack() {
    this.actual = new NodoVacio();
  }

  public void push(Object anObject) {
    Nodo nuevo = new NodoNoVacio(anObject,actual);
    actual = nuevo;
    size++;
  }

  public Object pop() {
    size--;
    Object o = actual.objeto();
    actual = actual.anterior();
    return o;
  }

  public Object top() {
    return actual.objeto();
  }

  public Boolean isEmpty() {
    return actual.esVacio();
  }

  public Integer size() {
    return size;
  }


}
