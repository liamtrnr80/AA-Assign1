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
    protected Node pHead;
    
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
        Node newNode = new Node(new Proc(procLabel, vt));
        
        if(pHead != null) {
            newNode.setNext(pHead);
        }
        pHead = newNode;
        
        pLength++;
        sortProcess();
    } // end of enqueue()


    @Override
    public String dequeue() {
        if(pLength == 0) {
            return "";
        }
        
        Proc minValue = findMin(pHead);
        Node currNode = pHead;
        Node prevNode = null;
        
        if(currNode.getValue().equals(minValue)){
            pHead = currNode.getNext();
            currNode = null;
            pLength--;
            return minValue.getProcLabel();
        }
        
        prevNode = currNode;
        currNode = currNode.getNext();
        
        while(currNode != null) {
            if(currNode.getValue() == minValue) {
                prevNode.setNext(currNode.getNext());
                currNode = null;
                pLength--;
                return minValue.getProcLabel();
            }
            prevNode = currNode;
            currNode = currNode.getNext();
        }
        return "";
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
        Node currNode = pHead;
        
        for(int i = 0; i < pLength; ++i) {
            if(currNode.getValue().getProcLabel().equals(procLabel)) {
                return true;
            }
            currNode = currNode.getNext();
        }
        
        return false;
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        Node currNode = pHead;
        Node prevNode = null;
        
        if(currNode.getValue().getProcLabel().equals(procLabel)) {
            pHead = currNode.getNext();
            currNode = null;
            pLength--;
            return true;
        }
        prevNode = currNode;
        currNode = currNode.getNext();
        
        while(currNode != null) {
            if(currNode.getValue().getProcLabel().equals(procLabel)) {
                prevNode.setNext(currNode.getNext());
                currNode = null;
                pLength--;
                return true;
            }
            prevNode = currNode;
            currNode = currNode.getNext();
        }
        
        return false;
    } // End of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
        // Implement me
        Node currNode = pHead;
        int time = 0;
        print();
        
        if(findProcess(procLabel)) {
            while(!currNode.getValue().getProcLabel().equals(procLabel)) {
                System.out.println(String.format("Current : %s", currNode.getValue().getProcLabel()));
                time += currNode.getValue().getVt();
                currNode = currNode.getNext();
            }
            return time;
        } else {
            return -1;
        }
    } // end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        // Implement me

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        //Implement me
        Node currNode = pHead;
    
        StringBuffer str = new StringBuffer();
    
        while(currNode != null) {
            str.append(currNode.getValue().getProcLabel()+ " ");
            currNode = currNode.getNext();
        }
        
        os.println(str.toString());
    } // end of printAllProcess()
    
    /** Sort Processes by Vruntime */
    public void sortProcess() {
        Node currNode = pHead;
        Node index = null;
        Proc temp;
        
        if(pHead == null) {
            return;
        } else {
            while(currNode != null) {
                index = currNode.getNext();
                while(index != null) {
                    if(currNode.getValue().getVt() > index.getValue().getVt()) {
                        temp = currNode.getValue();
                        currNode.setValue(index.getValue());
                        index.setValue(temp);
                    }
                    index = index.getNext();
                }
                currNode = currNode.getNext();
            }
        }
    }
    
    public Proc findMin(Node head) {
        Proc currMin = head.getValue();
        Node currNode = head.getNext();
        
        while (currNode != null) {
            if(currNode.getValue().getVt() <= currMin.getVt()) {
                currMin = currNode.getValue();
            }
            currNode = currNode.getNext();
        }
        return currMin;
    }
    
    public void print() {
        System.out.println(toString());
    }
    
    public String toString() {
        Node currNode = pHead;
    
        StringBuffer str = new StringBuffer();
    
        while(currNode != null) {
            str.append(currNode.getValue().getProcLabel()+ " ");
            currNode = currNode.getNext();
        }
        return str.toString();
    }
} // end of class OrderedLinkedListRQ
