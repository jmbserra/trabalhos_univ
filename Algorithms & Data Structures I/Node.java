package expressoes;

public class Node<T> {
	  private T item;
	  private Node<T> next;
	  private Node<T> prev;

	  public Node(){
	    this(null);
	  }

	  public Node(T e){
	    item = e;
	    next = null;
	    prev = null;
	  }

	  public Node(T e , Node<T> n){
	    item = e;
	    next = n;
	  }

	  public Node(T e, Node<T> p, Node<T> n){
	    item = e;
	    next = n;
	    prev = p;
	  }

	  public T element(){
	    return item;
	  }

	  public void setElement(T e){
			item = e;
		}

	  public void setNext(Node<T> n){
	    next = n;
	  }

	  public Node<T> getNext(){
	    return next;
	  }

	  public void setPrevious(Node<T> n){
	    prev = n;
	  }

	  public Node<T> getPrevious(){
	    return prev;
	  }

	}
