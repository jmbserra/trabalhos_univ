package expressoes;


public class DoublyLinkedListIterator<E> implements java.util.Iterator<E> {
	private Node<E> current;

	public DoublyLinkedListIterator(Node<E> c){
		current = c;
	}

  public boolean hasNext(){
		return current != null;
	}

  public boolean hasPrevious(){
    return current != null && current.getPrevious() != null;
  }

	public E next(){
		if (!hasNext())
			throw new java.util.NoSuchElementException("No element");

		E nextItem = current.element() ;
		current = current.getNext();
		return nextItem;
	}

  public E previous(){
    if (!hasPrevious())
      throw new java.util.NoSuchElementException("No element");

    E nextItem = current.element() ;
    current = current.getPrevious();
    return nextItem;
  }

	public void remove(){
		throw new UnsupportedOperationException();
	}

}
