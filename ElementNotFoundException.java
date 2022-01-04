
/** Indicates that the element does not exist in the list
 * <p>ElementNotFoundException is not expected to be thrown during normal use
 * of a list. As such, it is declared as a RuntimeException, which
 * is not checked at compile time.
 */
public class ElementNotFoundException extends RuntimeException { 
    private static final long serialVersionUID = 0L; // Serialization requirement

    /** Generate instance
     */
    public ElementNotFoundException() {
        super("Desired element is not in the list.");
    }
}
