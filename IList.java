

/**
 * Interface for list implementations
 */
public interface IList<E> {
    /***** Relative List Functions *****/
    
    /** Insert a new list item just before the current item
     *  <p>If the list is empty, the new element becomes the first item in the list
     *  and the current item is set to the new item. In all other cases, the current item
     *  will be the same before and after the insert call.
     *@param e element to add to the list
     */
    public void insert(E e);
    
    /** Appends a new list item just after the current item
     *  <p>If the list is empty, the new element becomes the first item in the list. In 
     * all cases, the current element will be the element added by the append call.
     *@param e element to add to the list
     */
    public void append(E e);
    
    /** Removes the current element from the list
     *  <p>If the element being removed is the first element in the list, after removal 
     *  the current element will be the new first element in the list. For any other 
     *  element in the list, after removal the current item will be the item just 
     *  before the one removed.
     *@return the element being deleted
     *@throws InvalidLocationException when delete is called on an empty list
     */
    public E delete();
    
    /** Retrieves the current list item without removing it
     *@return the current element
     *@throws InvalidLocationException when get is called on an empty list
     */
    public E get();
    
    /** Rewinds to the beginning of the list
     *  <p>After this method executes, the current element will be first element in the
     *  list.
     *@return the first element in the list
     *@throws InvalidLocationException when head is called on an empty list
     */
    public E head();
    
    /** Fast forwards to the end of the list
     *  <p>After this method executes, the current element will be first element in the
     *  list.
     *@return the last element in the list
     *@throws InvalidLocationException when tail is called on an empty list
     */
    public E tail();
    
    /** Advances to the next element of the list.
     *  <p>Also returns the element in the next list position.
     *  <p>If advancing would escape the list, the InvalidLocationException should
     *  be thrown and the current position should not be changed.
     *@return the next element in the list
     *@throws InvalidLocationException when next is past the end of the list
     */
    public E next();
    
    /** Steps to the previous element of the list.
     *  <p>Also returns the element in the previous list position.
     *  <p>If rewinding would escape the list, the InvalidLocationException should
     *  be thrown and the current position should not be changed.
     *@return the previous element in the list
     *@throws InvalidLocationException when prev is past the beginning of the list
     */
    public E prev();
    
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
    public void insert(int idx, E e);
    
    /** Append a new list item just after the element at index idx
     *  <p>Regardless of where the new element is placed within the list, the current
     *  element should be the same before and after the absolute append is called.
     *  <p>If the requested index is not in the list, the BadIndexException will be 
     *  thrown.
     *@param idx where to append at
     *@param e element to add to the list
     *@throws BadIndexException when idx is outside the list
     */
    public void append(int idx, E e);
    
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
    public E delete(int idx);
    
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
    public E get(int idx);
    
    /** Number of elements currently in the list
     *@return the number of elements in the list
     */
    public int size();
    
    /** Finds a matching element in the list
     *  <p>If a matching element existis in the list, return the index of that element
     *  and update the current element to the found element. "Matching" is determined
     *  using the equals method of the element to match.
     *  <p>If there is no matching element, throw an ElementNotFound exception.
     *@param e which element to find
     *@return the index of element in the list
     *@throws ElementNotFoundException when there is no matching element
     */
    public int indexOf(E e);
}
