
/** Indicates that the requested action would escape the list
 * <p>InvalidLocationException is not expected to be thrown during normal use
 * of a list. As such, it is declared as a RuntimeException, which
 * is not checked at compile time.
 */
public class InvalidLocationException extends RuntimeException { 
    private static final long serialVersionUID = 0L; // Serialization requirement

    /** Generate instance
     */
    public InvalidLocationException() {
        super("Operation would escape the list.");
    }
}
