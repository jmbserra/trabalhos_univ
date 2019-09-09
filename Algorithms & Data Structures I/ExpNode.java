package expressoes;

public class ExpNode {

    private ExpNode left, right;  //Filhos
    private int value;     // Se Leaf, é um operando
    private char op;       // Se nao for Leaf, é um operador
    private int size;

    // Construtor para um Node Operando
    public ExpNode(int value){
      left = null;
      right = null;
      this.value = value;
      size = 1;
    }

    // Contrutor para um Node Operador
    public ExpNode(char op, ExpNode left, ExpNode right){
      this.left = left;
      this.right = right;
      this.op = op;
      size = 1+left.size()+right.size();
    }

    public boolean isLeaf(){
      return left == null && right == null;
    }

    public void setLeft(ExpNode left){
      this.left = left;
    }

    public ExpNode getLeft(){
      return left;
    }

    public void setRight(ExpNode right){
      this.right = right;
    }

    public ExpNode getRight(){
      return right;
    }

    public char getOp(){
      return op;
    }

    public int getValue(){
      return value;
    }

    public int size(){
      return size;
    }

    // avalia o nó e desce 
    public int eval(){
      if(isLeaf())//caso encontre um no com um numero devolve-o
        return value;

      //caso o no seja dum operador verifica qual a operação a fazer
      switch(op){
        case '+':
          return left.eval() + right.eval();
        case '-':
          return left.eval() - right.eval();
        case '*':
          return left.eval() * right.eval();
        case '/':
          return left.eval() / right.eval();
      }

      return 0;
    }

    public String toString(){
      if(isLeaf())
        return ""+value;
      else
        return ""+op;
    }
}
