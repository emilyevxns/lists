
public class Link<E> {
	// the data in this link 
	private E data;
	// previous link
	private Link prev;
	// next link 
	private Link next;
	
	/**
	 * Constructs a shiny new link
	 */
	public Link(E d) {
		data = d;
	}
	
	/**
	 * Set the next link
	 */
	public void setNext(Link n) {
		next = n;
	}
	/**
	 * Set previous link
	 */
	public void setPrevious(Link n) {
		prev = n;
	}
	/**
	 * Get value from this link
	 * @return link value
	 */
	public E getValue() {
		return data;
	}
	/**
	 * Gets next link
	 */
	public Link getNext() {
		return next;
	}
	/**
	 * Gets the previous link
	 */
	public Link getPrevious() {
		return prev;
	}
}
