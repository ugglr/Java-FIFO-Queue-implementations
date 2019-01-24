package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		if(last==null) {
			last = new QueueNode<E>(e);
			last.next = last;
		} else {
			QueueNode<E> tmp = new QueueNode<E>(e);
			tmp.next = last.next;
			last.next = tmp;
			last = tmp;	
		}
		size++;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return this.size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(last == null) {
			return null;
		} else {
			return last.next.element;
		}
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if(last == null) {
			return null;			
		} else {
			QueueNode<E> tmp = last.next;
			
			if(this.size==1) {
				last = null;
			} else {
				last.next = tmp.next;	
			}
			
			size--;			
			return tmp.element;
		}
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		
		private QueueIterator() {
			if(last == null) {
				pos = null;
			} else {
				pos = last.next;
			}
		}
		
		@Override
		/**
		 * Returns true if the current position have element after
		 */
		public boolean hasNext() {
			return pos != null;
		}

		@Override
		/**
		 * Returns the current element and moves one down in the Queue.
		 */
		public E next() {
			if(hasNext()) {
				E e = pos.element;
				pos = pos.next;
				
				if(pos == last.next) {
					pos=null;
				}
				return e;
			} else {
				throw new NoSuchElementException();
			}
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			this.element = x;
			next = null;
		}
	}

}
