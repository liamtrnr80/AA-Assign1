import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the run queue interface using an Ordered Link List.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan.
 */
public class OrderedLinkedListRQ implements Runqueue {

    /** Reference to head Process */
    protected Proc pHead;
    
    /** Length of List */
    protected int pLength;
    
    /**
     * Constructs empty linked list
     */
    public OrderedLinkedListRQ() {
        pHead = null;
        pLength = 0;
    }  // end of OrderedLinkedList()


    @Override
    public void enqueue(String procLabel, int vt) {
        Proc newProc = new Proc(procLabel, vt);
    
        if (pHead != null) {
            newProc.setNext(pHead);
        }
        pHead = newProc;
    
        pLength++;
    } // end of enqueue()


    @Override
    public String dequeue() {
        if(pLength == 0) {
            return "";
        }
        
        
        Proc currMin = findMin(pHead);
        Proc currProc = pHead;
        Proc prevProc = null;
        
        if(currProc == currMin) {
            pHead = currProc.getNext();
            currProc = null;
            pLength--;
            return currMin.getProcLabel();
        }
        
        prevProc = currProc;
        currProc = currProc.getNext();
        
        while (currProc != null) {
            if(currProc == currMin) {
                prevProc.setNext(currProc.getNext());
                currProc = null;
                pLength--;
                return currMin.getProcLabel();
            }
            
            prevProc = currProc;
            currProc = currProc.getNext();
        }
        
        return "";
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
        Proc currProc = pHead;
        for(int i = 0; i < pLength; ++i) {
            if(currProc.getProcLabel().equals(procLabel)){
                return true;
            }
            currProc = currProc.getNext();
        }
        
        return false;
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        Proc currProc = pHead;
        Proc prevProc = null;
        
        if(currProc.getProcLabel().equals(procLabel)){
            pHead = currProc.getNext();
            currProc = null;
            pLength--;
            return true;
        }
        
        prevProc = currProc;
        currProc = currProc.getNext();
        
        while(currProc != null) {
            if(currProc.getProcLabel().equals(procLabel)){
                prevProc.setNext(currProc.getNext());
                currProc = null;
                pLength--;
                return true;
            }
            prevProc = currProc;
            currProc = currProc.getNext();
        }
        
        return false;
    } // End of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
        // Implement me
        
        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        // Implement me

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        //Implement me

    } // end of printAllProcess()

    /** Find the smallest vt from current node */
    public Proc findMin(Proc head) {
        Proc currMin  = head;
        Proc currProc = head.getNext();
    
        while(currProc != null) {
            if(currProc.getVt() <= currMin.getVt()) {
                currMin = currProc;
            }
            currProc = currProc.getNext();
        }
        
        return currMin;
    }
    
} // end of class OrderedLinkedListRQ
