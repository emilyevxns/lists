/**
 * Implementation of a linked list
 * @author ea_ev
 *
 * @param <E>
 */
public class LinkedList<E> implements IList<E> {
	Link cur;
	int size;

	/** 
	 * Initializes the instance variables 
	 */

	public LinkedList() {
		size = -1;
	}
	
	/** Insert a new list item just before the current item
	 *  <p>If the list is empty, the new element becomes the first item in the list
	 *  and the current item is set to the new item. In all other cases, the current item
	 *  will be the same before and after the insert call.
	 *@param e element to add to the list
	 */
	public void insert(E e) {
		if (cur == null) {
			cur = new Link(e);
			size++;
		}
		else {
			// First element in list
			if (cur.getPrevious()==null) {
				Link temp = new Link(e);
				// Link together new and old links
				cur.setPrevious(temp);
				temp.setNext(cur);
			}
			// Anywhere else in list 
			else if(cur.getPrevious()!=null) {
				Link temp = new Link(e);
				// Link together new and old links 
				cur.getPrevious().setNext(temp);
				temp.setPrevious(cur.getPrevious());
				cur.setPrevious(temp);
				temp.setNext(cur);
			}
			size++;
		}
	}

	/** Appends a new list item just after the current item
	 *  <p>If the list is empty, the new element becomes the first item in the list. In 
	 * all cases, the current element will be the element added by the append call.
	 *@param e element to add to the list
	 */
	public void append(E e) {
		Link temp = new Link(e);
		if (cur == null) {
			// Make first element the new and current element
			cur = temp;
			size++;
			return;
		}
		else {
			// If current is last element 
			if (cur.getNext() == null) {
				cur.setNext(temp);
				temp.setPrevious(cur);
				cur = temp;
				size++;
			}
			else {
				// Current element is in the middle of the list 
				cur.getNext().setPrevious(temp);
				temp.setNext(cur.getNext());
				cur.setNext(temp);
				temp.setPrevious(cur);
				cur = temp;
				size++;
			}
		}
	}

	/** Removes the current element from the list
	 *  <p>If the element being removed is the first element in the list, after removal 
	 *  the current element will be the new first element in the list. For any other 
	 *  element in the list, after removal the current item will be the item just 
	 *  before the one removed.
	 *@return the element being deleted
	 *@throws InvalidLocationException when delete is called on an empty list
	 */
	public E delete() {
		if (cur == null) {
			throw new InvalidLocationException();
		}
		else {
			// Get return value
			E temp = (E) cur.getValue();
			if (cur.getPrevious()==null) {
				Link new1 = cur.getNext();
				new1.setPrevious(null);
				cur=null;
				cur = new1;
				size--;
				return temp;
			}
			else {
				// Set current to the previous link 
				Link old = cur;
				cur = cur.getPrevious();
				if (old.getNext()!=null) {
					// Connect old and new links 
					old.getNext().setPrevious(cur);
					cur.setNext(old.getNext());
				}
				else if (old.getNext() == null) {
					old = null;
				}
				size--;
				return temp;
			}
		}
	}

	/** Retrieves the current list item without removing it
	 *@return the current element
	 *@throws InvalidLocationException when get is called on an empty list
	 */
	public E get() throws InvalidLocationException {
		if (cur == null) {
			throw new InvalidLocationException();
		}
		else {
			return (E) cur.getValue();
		}
	}

	/** Rewinds to the beginning of the list
	 *  <p>After this method executes, the current element will be first element in the
	 *  list.
	 *@return the first element in the list
	 *@throws InvalidLocationException when head is called on an empty list
	 */
	public E head() throws InvalidLocationException {
		if(size < 0) {
			throw new InvalidLocationException();
		}
		else {
			while (cur.getPrevious()!=null) {
				cur = cur.getPrevious();
			}
			return (E) cur.getValue();
		}
	}

	/** Fast forwards to the end of the list
	 *  <p>After this method executes, the current element will be last element in the
	 *  list.
	 *@return the last element in the list
	 *@throws InvalidLocationException when tail is called on an empty list
	 */
	public E tail() throws InvalidLocationException {
		if (size < 0) {
			throw new InvalidLocationException();
		}
		else {
			while (cur.getNext() != null) {
				cur = cur.getNext();
			}
			E temp = (E) cur.getValue();
			return temp;
		}
	}

	/** Advances to the next element of the list.
	 *  <p>Also returns the element in the next list position.
	 *  <p>If advancing would escape the list, the InvalidLocationException should
	 *  be thrown and the current position should not be changed.
	 *@return the next element in the list
	 *@throws InvalidLocationException when next is past the end of the list
	 */
	public E next() throws InvalidLocationException{
		if (size < 0 || cur.getNext() == null) {
			throw new InvalidLocationException();
		}
		else {
			cur = cur.getNext();
			E temp = (E) cur.getValue();
			return temp;
		}
	}

	/** Steps to the previous element of the list.
	 *  <p>Also returns the element in the previous list position.
	 *  <p>If rewinding would escape the list, the InvalidLocationException should
	 *  be thrown and the current position should not be changed.
	 *@return the previous element in the list
	 *@throws InvalidLocationException when prev is past the beginning of the list
	 */
	public E prev() throws InvalidLocationException {
		if (size < 0 || cur.getPrevious()==null) {
			throw new InvalidLocationException();
		}
		else {
			cur = cur.getPrevious();
		}
		return (E) cur.getValue();
	}

	/***** Absolute List Functions *****/
	/** Insert a new list item just before the element at index idx
	 *  <p>Regardless of where the new element is placed within the list, the current
	 *  element should be the same before and after the absolute insert is called.
	 *  <p>If the requested index is not in the list, the BadIndexException will be 
	 *  thrown.
	 *@param idx where to insert at
	 *@param e element to add to the list
	 *@throws BadIndexException when idx is outside the list
	 */
	public void insert(int idx, E e) throws BadIndexException {
		// throw 
		if (idx < 0 || idx > size) {
			throw new BadIndexException(idx);
		}
		else {
			if (idx == 0 && cur.getPrevious() == null) {
				Link temp = new Link(e);
				temp.setNext(cur);
				cur.setPrevious(temp);
				size++;
			}
			else {
				Link temp = cur;
				// Rewind to the end of the list
				if (this.indexOf((E)cur.getValue()) != this.size()) {
					while(temp.getNext() != null) {
						temp = temp.getNext();
					}
				}
				// Count backwards to intended insertion index
				for (int i = (size - 1); i >= idx; i--) {
					temp = temp.getPrevious();

				}
				// Put the new link before that index 
				Link new1 = new Link (e);
				if (temp.getPrevious() != null) {
					temp.getPrevious().setNext(new1);
					new1.setPrevious(temp.getPrevious());
				}
				new1.setNext(temp);
				temp.setPrevious(new1);
				size++;
			}
		}
	}
	
	/** Append a new list item just after the element at index idx
	 *  <p>Regardless of where the new element is placed within the list, the current
	 *  element should be the same before and after the absolute append is called.
	 *  <p>If the requested index is not in the list, the BadIndexException will be 
	 *  thrown.
	 *@param idx where to append at
	 *@param e element to add to the list
	 *@throws BadIndexException when idx is outside the list
	 */
	public void append(int idx, E e) {
		if (idx > size || idx < 0) {
			throw new BadIndexException(idx);
		}
		else {
			Link temp = cur;
			while (temp.getPrevious() != null) {
				temp = temp.getPrevious();
			}
			// Get to the index
			for (int i = 0; i < idx; i++) {
				temp = temp.getNext();
			}
			// Append the item after the index 
			Link new1 = new Link(e);
			// If idx is last element 
			if (temp.getNext() == null) {
				temp.setNext(new1);
				new1.setPrevious(temp);
			}			
			else {
				// Connect links 
				temp.getNext().setPrevious(new1);
				new1.setNext(temp.getNext());
				new1.setPrevious(temp);
				temp.setNext(new1);
			}
			size++;
		}
	}

	/** Remove the element at index idx
	 *  <p>If the current element is the element being deleted, the behavior should be 
	 *  consistent with the relative delete method. For all other elements, use of the
	 *  absolute delete should not change which element the current element is.
	 *  <p>If the requested index is not in the list, the BadIndexException will be 
	 *  thrown.
	 *@param idx which element to remove
	 *@return element being removed
	 *@throws BadIndexException when idx is outside the list
	 */
	public E delete(int idx) throws BadIndexException{
		if (idx > size || idx < 0) {
			throw new BadIndexException(idx);
		}
		else {
			E e;
			// If idx == current 
			e = (E) cur.getValue();
			if(this.indexOf(e) == idx){
				if (cur.getNext() != null) {
					if (cur.getPrevious() == null) {
						Link temp = cur;
						cur = cur.getNext();
						temp = null;
						cur.setPrevious(temp);
						size--;
					}
					else {
						Link temp = cur;
						cur = cur.getPrevious();
						cur.setNext(temp.getNext());
						temp = null;
						size--;
					}
				}
				else if (cur.getNext() == null){
					Link temp = cur.getPrevious();
					temp.setNext(null);
					cur = null;
					cur = temp;
					size--;
				}
			}
			else {
				// Rewind to front of list
				Link temp = cur;
				while (temp.getPrevious() != null) {
					temp = temp.getPrevious();
				}
				// Find link at the index 
				for (int i = 0; i < idx; i++) {
					temp = temp.getNext();
				}
				// If link is first in list
				if (temp.getPrevious() == null && temp.getNext() != null) {
					e = (E) temp.getValue();
					temp = temp.getNext();
					temp.getPrevious().setNext(null);
					temp.setPrevious(null);
				}
				// If link is last in list
				else if(temp.getNext() == null && temp.getPrevious() != null) {
					e = (E) temp.getValue();
					temp = temp.getPrevious();
					temp.getNext().setPrevious(null);
					temp.setNext(null);
				}
				else {
					e = (E) temp.getValue();
					// Connect links
					temp.getNext().setPrevious(temp.getPrevious());
					temp.getPrevious().setNext(temp.getNext());
					temp.setNext(null);
					temp.setPrevious(null);
				}
				size--;
			}
			return e;
		}	
	}

	/** Retrieves the element at index idx without removing it
	 *  <p>The absolute get will update the current element to be the one retrieved using
	 *  the absolute get.
	 *  <p>If the requested index is not in the list, the BadIndexException will be 
	 *  thrown. If the BadIndexException is thrown, the current element should not be 
	 *  changed by the call to absolute get.
	 *@param idx which element to remove
	 *@return the current element
	 *@throws BadIndexException when idx is outside the list
	 */
	public E get(int idx) throws BadIndexException {
		if (idx > size || idx < 0) {
			throw new BadIndexException(idx);
		}
		else {
			Link temp;
			if (size == 0) {
				temp = cur;
			}
			else {
				while (cur.getPrevious() != null) {
					cur = cur.getPrevious();
				}
				temp = cur;
				if (idx == 0) {
					return (E) temp.getValue();
				}
				else if (idx != 0) {
					for (int i = 0; i < idx; i++) {
						temp = temp.getNext();
					}
				}
			}
			cur = temp;
			return (E) temp.getValue();
		}
	}

	/** Number of elements currently in the list
	 *@return the number of elements in the list
	 */
	public int size() {
		int s = size + 1;
		return s;
	}

	/** Finds a matching element in the list
	 *  <p>If a matching element exist is in the list, return the index of that element
	 *  and update the current element to the found element. "Matching" is determined
	 *  using the equals method of the element to match.
	 *  <p>If there is no matching element, throw an ElementNotFound exception.
	 *@param e which element to find
	 *@return the index of element in the list
	 *@throws ElementNotFoundException when there is no matching element
	 */
	public int indexOf(E e) throws ElementNotFoundException {
		Link temp = cur;
		Link idx;
		int count = -1;
		// Rewind to beginning of list
		while (temp.getPrevious() != null) {
			temp = temp.getPrevious();
		}
		while(temp != null) {
			if (temp.getValue()==e) {
				count++;
				break;
			}
			else {
				temp = temp.getNext();
				count++;
			}
		}
		if (count == -1) {
			throw new ElementNotFoundException();
		}
		return count;
	}
}
