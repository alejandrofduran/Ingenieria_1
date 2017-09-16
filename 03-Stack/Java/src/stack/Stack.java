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

import java.util.ArrayDeque;
import java.util.Deque;

public class Stack {

    public static final String STACK_EMPTY_DESCRIPTION = "Stack is Empty";
    //private final Deque<Object> stack;    
    private final Deque<ObjectStack> stack;
    
    public Stack() {
        //stack = new ArrayDeque<Object>();
        stack = new ArrayDeque<ObjectStack>();
        stack.push(new NullObject());
    }

    public void push(Object anObject) {
        //stack.push(anObject);
        stack.push(new ValueObjectStack(anObject));
    }

    public Object pop() {
        /*if (stack.isEmpty()) {
            throw new RuntimeException(Stack.STACK_EMPTY_DESCRIPTION);
        } else {
            return stack.pop();
        }*/
        return stack.pop().valor();
    }

    public Object top() {
        /*if (stack.isEmpty()) {
            return throwExeptionIsEmpty();
        } else {
            return stack.getFirst();
        }*/
        return stack.getFirst().valor();
    }

    public Boolean isEmpty() {
        //return stack.isEmpty();
        return stack.getFirst().isEmpty();
    }

    public Integer size() {
        return stack.size()-1;
    }

    /*private Object throwExeptionIsEmpty() {
        throw new RuntimeException(ObjectStack.STACK_EMPTY_DESCRIPTION);
    }*/

}
