import java.io.PrintWriter;
import java.lang.String;


/**
 * Implementation of the Runqueue interface using an Ordered Array.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan
 */
public class OrderedArrayRQ implements Runqueue {

    /**
     * Constructs empty queue
     */
    /** Head Process */
    protected Proc pHead;
    /** Length of array*/
    protected int pLength;
    
    public OrderedArrayRQ() {
        // Implement Me
        Proc[] processes = null;
    }  // end of OrderedArrayRQ()


    @Override
    public void enqueue(String procLabel, int vt) {
        // Implement me
        Proc[] oldProcs = new Proc[pLength];
        Proc[] newProcs = new Proc[pLength + 1];
        

    } // end of enqueue()


    @Override
    public String dequeue() {
        // Implement me

        return ""; // placeholder,modify this
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel){
        // Implement me

        return false; // placeholder, modify this
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        // Implement me
        
        if(true) {
            return true;
        } else {
            return false;
        }
    } // end of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
        // Implement me

        return -1; // placeholder, modify this
    }// end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        // Implement me

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        //Implement me

    } // end of printAllProcesses()

} // end of class OrderedArrayRQ

class DynamicArray {
    
    protected Proc array[];
    protected int length;
    protected int cap;
    
    public DynamicArray() {
        array = new Proc[2];
        length = 0;
        cap = 2;
    }
    
    public void add(Proc proc) {
        
        array[length] = proc;
        length++;
    }
    
    
}