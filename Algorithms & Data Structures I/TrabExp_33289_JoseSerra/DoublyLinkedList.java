package expressoes;


import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T>{
	private Node<T> header, tail;
	private int size;

	public DoublyLinkedList(){
		header = new Node<T>();
		tail = new Node<T>();
		header.setNext(tail);
		tail.setPrevious(header);
		size = 0;
	}

	public java.util.Iterator<T> iterator(){
		return (Iterator<T>)new DoublyLinkedListIterator<T>(header.getNext());
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size==0;
	}

	public Node<T> header(){
		return header;
	}

	public Node<T> tail(){
		return tail;
	}

	public void addFront(T data){
		add(header, data);
	}

	public void addRear(T data) {
		if(size == 0)
			add(header, data);
		else{
			Node<T> last = tail.getPrevious();

			add(last, data);
		}
	}

	public void add(Node<T> current, T x){
		Node<T> newNode= new Node<T>(x, current, current.getNext());
		current.getNext().setPrevious(newNode);
		current.setNext(newNode);
		size++;
	}

	public void insertAt(T data, int index) throws IndexOutOfBoundsException{
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("insertAt index:"+ index +"; size:"+size);
		else if(index == 0)
			addFront(data);
		else if(index == size)
			addRear(data);
		else{
			
			Node<T> current = header.getNext();

			int i = 0;
			while(i < index){
				current = current.getNext();
				i++;
			}

			add(current, data);

		}
	}

	public void add(T x){
		addRear(x);
	}

	public void removeFront() throws IndexOutOfBoundsException{
		if(size == 0)
			throw new IndexOutOfBoundsException("removeFront size:"+size);

		Node<T> n = header.getNext();
		n.getNext().setPrevious(header);
		header.setNext(n.getNext());

		size--;
	}

	public void removeRear() throws IndexOutOfBoundsException{
		if(size == 0)
			throw new IndexOutOfBoundsException("removeRear size:"+size);

		Node<T> n = tail.getPrevious();
		tail.setPrevious(n.getPrevious());
		n.getPrevious().setNext(tail);

		size--;

	}

	public void removeAt(int index) throws IndexOutOfBoundsException{
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("removeAt index:"+ index +"; size:"+size);
		else if(index == 0)
			removeFront();
		else if(index == size)
			removeRear();
		else{
			
			Node<T> current = header.getNext();

			int i = 0;
			while(i < index){
				current = current.getNext();
				i++;
			}

			remove(current);
		}
	}

	public void remove(Node<T> current){
		current.getNext().setPrevious(current.getPrevious());
		current.getPrevious().setNext(current.getNext());
		size--;
	}


	

	public Node<T> findPrevious(T x){
		Node<T> p = header();
		for(T v:this){
			if (v.equals(x))
				return p;
			else
				p = p.getNext();
		}
		throw new java.util.NoSuchElementException("No element");
	}


	public String toString(){
		Node<T> current = header.getNext();
		StringBuilder sb= new StringBuilder("");

		while(current != null & !current.equals(tail)){
			sb.append(current.element() + " ");
			current = current.getNext();
		}

		return new String(sb);
	}

	public void set(int index, T x) throws IndexOutOfBoundsException{
		getNode(index).setElement(x);
	}

	public T get(int ind) throws IndexOutOfBoundsException{
		return getNode(ind).element();
	}

	public Node<T> getNode(int index) throws IndexOutOfBoundsException{
		Node<T> current = header;
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("getNode index:"+ index +"; size:"+size);
		else if(index == 0)
			return header().getNext();
		else if(index == size-1)
			return tail().getPrevious();
		else{
			
			current = header.getNext();

			int i = 0;
			while(i < index){
				current = current.getNext();
				i++;
			}

		}

		return current;

	}

}
