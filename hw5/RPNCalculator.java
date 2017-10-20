package hw5;



/**
 * RPN calculator supporting addition, subtraction and multiplication
 * of anything that implements the CalculatorOperand interface
 */

public class RPNCalculator<T extends CalculatorOperand<T>> {


  ListStack<T> stack; // the stack holding the operands

  /**
   * Defines the operations the calculator can perform 
   */
  static public enum OperationType {ADD, SUBTRACT, MULTIPLY}; //enum: lets the user decide its own type. 

  /**
   * Constructor
   */
  public RPNCalculator () {
    stack = new ListStack<T>();
  }

  /**
   * Pushes the operand on the calculator stack
   */
  public void operand (T value) {
    // TODO: IMPLEMENT
    stack.push(value);
  }

  /**
   * Performs an operation on the two topmost elements of the stack
   * If t2 is topmost and t1 is second topmost, then t1 and t2 will be removed
   * from the stack and (t1 op t2) will be placed on top of the stack.
   * Does not modify the stack if it contains fewer than two elements.
   */

  public void operation (OperationType operation) {
    // TODO: IMPLEMENT

    if(stack.isEmpty()){
      return; //no need to return anything since its void.
    }
    T operand2= stack.pop();
    if(stack.isEmpty()) {
      stack.push(operand2);
        return;
      }
      T operand1= stack.pop();
      T result = null;
      if (operation== OperationType.ADD) {
        result = operand1.add(operand2);
      }
      else if(operation== OperationType.SUBTRACT) {
        result= operand1.subtract(operand2);
      }
      else if(operation== OperationType.MULTIPLY) {
        result= operand1.multiply(operand2);
      }
      stack.push(result);
    }
    /**
     * Prints the calculator stack
     */
    public String toString() {
      if (stack.isEmpty()) {
        return "Empty stack\n";
      }
      else {
        return stack.toString();
      }
    }
  }


