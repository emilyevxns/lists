
/** Indicates that an invalid index was asked for
 * <p>BadIndexException is not expected to be thrown during normal use
 * of a list. As such, it is declared as a RuntimeException, which
 * is not checked at compile time.
 */
public class BadIndexException extends RuntimeException { 
    private static final long serialVersionUID = 0L; // Serialization requirement

    /** Generate instance
     *@param idx index that caused the error
     */
    public BadIndexException(int idx) {
        super("Index "+idx+" does not exist.");
    }
}
