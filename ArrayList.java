
public class ArrayList<E> implements IList<E> {
	E[] data;
	int size;
	E current;

	public ArrayList() {
		data = (E[]) new Object[10];
		size = 0;
	}
	/** Insert a new list item just before the current item
	 *  <p>If the list is empty, the new element becomes the first item in the list
	 *  and the current item is set to the new item. In all other cases, the current item
	 *  will be the same before and after the insert call.
	 *@param e element to add to the list
	 */
	public void insert(E e) {
		if (size == 0) {
			data[0] = e;
			current = e;
			size++;
			return;
		}
		else {
			int cidx = this.indexOf(current);
			// If current is the first element 
			if (cidx == 0) {
				// Move other elements down
				for (int i=size;i>=0;i--) {
					data[i+1]=data[i];
				}
				data[cidx] = e;
				cidx++;
				current = data[cidx];
			}
			else {
				// If current is in the middle of the list
				for (int i=size;i>=cidx;i--) {
					data[i+1]=data[i];
				}
				data[cidx] = e;
				cidx++;
				current = data[cidx];
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
		if (size == 0) {
			data[0] = e;
			current = e;
			size++;
		}
		else {
			int idx = this.indexOf(current);
			idx++; 
			if (data[idx]==null) {
				data[idx] = e;
			}
			else {
				for(int i = size; i > idx; i--) {
					data[i] = data[i-1];
				}
				data[idx] = e;
			}
			current = e;
			size++;
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
	public E delete() throws InvalidLocationException {
		E temp;
		if (current==null) {
			throw new InvalidLocationException();
		}
		else {
			temp = current;
			if (size == 1) {
				data[0] = null;
				size--;
			}
			else if(this.indexOf(current)==0) {
				// If current is the first element
				data[0] = null;
				for (int i = 0; i < size; i++) {
					data[i] = data[i+1];
				}
				current = data[0];
				size--;
			}
			else {
				if (this.indexOf(current)==size-1) {
					// If current is the last element in the list
					int idx = this.indexOf(temp);
					data[idx] = null;
					idx--;
					current = data[idx];
					size--;
				}
				else {
					// If current is in the middle of the list
					int idx= this.indexOf(current);
					data[this.indexOf(current)] = null;
					for (int i = (idx + 1); i <= size; i++) {
						data[i-1]=data[i];
					}
					current = data[idx-1];
					size--;
				}
			}
		}
		return temp;
	}

	/** Retrieves the current list item without removing it
	 *@return the current element
	 *@throws InvalidLocationException when get is called on an empty list
	 */
	public E get() throws InvalidLocationException {
		if (current == null) {
			throw new InvalidLocationException();
		}
		else {
			return current;
		}
	}

	/** Rewinds to the beginning of the list
	 *  <p>After this method executes, the current element will be first element in the
	 *  list.
	 *@return the first element in the list
	 *@throws InvalidLocationException when head is called on an empty list
	 */
	public E head() throws InvalidLocationException {
		if (current == null) {
			throw new InvalidLocationException();
		}
		else {
			current = data[0];
			return current;
		}
	}

	/** Fast forwards to the end of the list
	 *  <p>After this method executes, the current element will be last element in the
	 *  list.
	 *@return the last element in the list
	 *@throws InvalidLocationException when tail is called on an empty list
	 */
	public E tail() throws InvalidLocationException {
		if(current == null) {
			throw new InvalidLocationException();
		}
		else {
			current = data[size-1];
			return data[size-1];
		}
	}

	/** Advances to the next element of the list.
	 *  <p>Also returns the element in the next list position.
	 *  <p>If advancing would escape the list, the InvalidLocationException should
	 *  be thrown and the current position should not be changed.
	 *@return the next element in the list
	 *@throws InvalidLocationException when next is past the end of the list
	 */
	public E next() throws InvalidLocationException {
		int idx=0;
		for(int i = 0; i < (size + 1); i++) {
			if (data[i] == current) {
				idx=i;
				break;
			}
		}
		int next = idx + 1;
		if (next > size - 1) {
			throw new InvalidLocationException();
		}
		else {
			current = data[next];
			return current;
		}
	}

	/** Steps to the previous element of the list.
	 *  <p>Also returns the element in the previous list position.
	 *  <p>If rewinding would escape the list, the InvalidLocationException should
	 *  be thrown and the current position should not be changed.
	 *@return the previous element in the list
	 *@throws InvalidLocationException when prev is past the beginning of the list
	 */
	public E prev() throws InvalidLocationException{
		int idx=0;
		for(int i = 0; i < (size + 1); i++) {
			if (data[i] == current) {
				idx = i;
				break;
			}
		}
		int prev = idx-1;
		if (prev < 0) {
			throw new InvalidLocationException();
		}
		else {
			current = data[prev];
			return current;
		}
	}

	/** Insert a new list item just before the element at index idx
	 *  <p>Regardless of where the new element is placed within the list, the current
	 *  element should be the same before and after the absolute insert is called.
	 *  <p>If the requested index is not in the list, the BadIndexException will be 
	 *  thrown.
	 *@param idx where to insert at
	 *@param e element to add to the list
	 *@throws BadIndexException when idx is outside the list
	 */
	public void insert(int idx, E e) throws BadIndexException{
		// insert new element just before idx

		if (idx < 0 || idx > size-1) {
			throw new BadIndexException(idx);
		}
		else {
			for (int i = size; i >= idx; i--) {
				data[i+1]= data[i];
			}
			data[idx] = e;
			size++;
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
	public void append(int idx, E e) throws BadIndexException {
		if (idx < 0 || idx > size-1) {
			throw new BadIndexException(idx);
		}
		else {
			for (int i = size; i >= (idx + 1); i--) {
				data[i+1]= data[i];
			}
			data[idx+1] = e;
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
	public E delete(int idx) {
		if (idx < 0 || idx > size-1) {
			throw new BadIndexException(idx);
		}
		else {
			E temp = data[idx];
			if (size == 1) {
				// Make list empty
				data[0] = null;
				size--;

			}
			else if(idx == 0) {
				// Move elements after 0 down
				data[idx] = null;
				for (int i = 0; i < size; i++) {
					data[i] = data[i+1];
				}
				current = data[0];
				size--;
			}
			else if (this.indexOf(current)==idx) {
				// If idx is last element in list
				if (idx == size-1) {
					data[idx] = null;
					current = data[idx-1];
					size--;
				}
				else {
					// If removing from middle of list 
					data[idx] = null;
					for (int i = (idx + 1); i <= size; i++) {
						data[i-1]=data[i];
					}
					size--;
					current = data[idx-1];
				}
			}
			else {
				// If idx != current and it is last
				if (idx == size-1) {
					data[idx] = null;
					size--;

				}
				else {
					// If idx != current and it is in the middle 
					data[idx] = null;
					for (int i = (idx + 1); i <= size; i++) {
						data[i-1]=data[i];
					}
					size--;
				}
			}
			return temp;
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
		if (idx < 0 || idx > size - 1) {
			throw new BadIndexException(idx);
		}
		else {
			return data[idx];
		}
	}

	/** Number of elements currently in the list
	 *@return the number of elements in the list
	 */
	public int size() {
		return size;
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
		if (size == 1) {
			return 0;
		}
		else {
			int idx = -1;
			for(int i = 0; i < size; i++) {
				if (data[i] == e) {
					idx = i;
					break;
				}
			}
			if (idx == -1) {
				throw new ElementNotFoundException();
			}
			return idx;
		}

	}
}
