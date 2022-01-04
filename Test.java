/**
 * Simple, non-exhaustive, test program to check that lists can be constructed.
 * This test does not attempt to test the list methods in any meaningful way.
 */
public class Test {
    /**
     * Instantiates two lists
     *@param args ignored
     */
    public static void main(String[] args) {
        // Make the lists
        IList<String> ll = new LinkedList<String>();
        IList<String> al = new ArrayList<String>();
        // Add some strings
        ll.append("Hi");
        ll.append("Bye");
        al.append("Hi");
        al.append("Bye");
        for(int i=0; i<ll.size(); i++) {
            System.out.print(ll.get(i)+",");
        }
        System.out.println("");
        for(int i=0; i<al.size(); i++) {
            System.out.print(al.get(i)+",");
        }
        System.out.println("");
        
    }
}
