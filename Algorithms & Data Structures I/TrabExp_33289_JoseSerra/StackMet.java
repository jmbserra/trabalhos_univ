package expressoes;

public interface StackMet<U> {

	public U top();
	public U pop();
	public void push(U o);
	public boolean isEmpty();
	public int size();

}
