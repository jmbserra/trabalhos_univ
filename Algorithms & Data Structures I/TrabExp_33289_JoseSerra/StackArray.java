package expressoes;


public class StackArray<U> implements StackMet<U> {

	public U[] stack;
	public static int capacity;
	private int currentSize = -1;

	@SuppressWarnings("unchecked")
	public StackArray(int c){
		capacity = c;
		stack = (U[]) new Object[capacity];
	}

	public U top() {

		return stack[currentSize];
	}


	public U pop(){
		if (!isEmpty()){
			U temp = stack[currentSize];
			stack[currentSize]= null;
			currentSize--;

			return temp;
		}

		throw new IllegalStateException("Invalid Expression: Stack is empty");
	}


	public void push(U o) {
		if(currentSize + 1 < capacity){

			currentSize++;
			stack[currentSize] = o;
		}
		else{
			throw new IllegalStateException("Invalid Expression: Stack is full");

		}
		//System.out.println(o + "Foi adicionado à Pilha");
	}



	public boolean isEmpty() {

		return currentSize == -1 ;
	}


	public int size() {

		return currentSize + 1 ;
	}


}

