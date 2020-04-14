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
        Node currNode = pHead;
    
        if(pHead == null || pHead.getValue().getVt() > vt) {
            newNode.setNext(pHead);
            pHead = newNode;
        } else {
            while(currNode.getNext() != null && currNode.getNext().getValue().getVt() <= vt) {
                currNode = currNode.getNext();
            }
            newNode.setNext(currNode.getNext());
            currNode.setNext(newNode);
        }
        pLength++;
    } // end of enqueue()


    @Override
    public String dequeue() {
        if (pLength == 0) {
            return "";
        }
    
        Node currNode = pHead;
        Node prevNode = null;
        Proc dequeue = findMin(pHead);
    
        if(currNode.getValue() == dequeue) {
            pHead = currNode.getNext();
            currNode = null;
            pLength--;
            return dequeue.getProcLabel();
        }
    
        prevNode = currNode;
        currNode = currNode.getNext();
    
        while(currNode != null) {
            if(currNode.getValue() == dequeue) {
                prevNode.setNext(currNode.getNext());
                currNode = null;
                pLength--;
                return dequeue.getProcLabel();
            }
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
        Node currNode = pHead;
        int time = 0;
        
        if(findProcess(procLabel)) {
            while(!currNode.getValue().getProcLabel().equals(procLabel)) {
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
        Node currNode = pHead;
        int time = 0;
        
        if(findProcess(procLabel)) {
            for(int i = 0; i < pLength; ++i) {
                if(currNode.getValue().getProcLabel().equals(procLabel)) {
                    currNode = currNode.getNext();
                    while(currNode != null) {
                        time += currNode.getValue().getVt();
                        currNode = currNode.getNext();
                    }
                    return time;
                } else {
                    currNode = currNode.getNext();
                }
            }
            return time;
        } else {
            return -1;
        }
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        Node currNode = pHead;
    
        StringBuffer str = new StringBuffer();
    
        while(currNode != null) {
            str.append(currNode.getValue().getProcLabel()+ " ");
            currNode = currNode.getNext();
        }
        
        str.setLength(str.length() - 1);
        
        os.println(str.toString());
    } // end of printAllProcess()
    
    
    public Proc findMin(Node head) {
        Proc currMin = head.getValue();
        Node currNode = head.getNext();
        
        while (currNode != null) {
            if(currNode.getValue().getVt() < currMin.getVt()) {
                currMin = currNode.getValue();
            }
            currNode = currNode.getNext();
        }
        return currMin;
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

class Node {
    
    protected Proc pValue;
    protected Node pNext;
    protected Node pPrev;
    
    public Node(Proc value) {
        pValue = value;
        pNext = null;
        pPrev = null;
    }
    
    public Proc getValue() {
        return pValue;
    }
    
    public Node getNext() {
        return pNext;
    }
    
    public Node getPrev() {
        return pPrev;
    }
    
    public void setValue(Proc value) {
        pValue = value;
    }
    
    public void setNext(Node next) {
        pNext = next;
    }
    
    public void setPrev(Node prev) {
        pPrev = prev;
    }
}