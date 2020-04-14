import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the Runqueue interface using a Binary Search Tree.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan
 */
public class BinarySearchTreeRQ implements Runqueue {

    protected BSTNode pRoot;
    
    /**
     * Constructs empty queue
     */
    public BinarySearchTreeRQ() {
        pRoot = null;
    }  // end of BinarySearchTreeRQ()


    @Override
    public void enqueue(String procLabel, int vt) {
        // Implement me
        BSTNode newNode = new BSTNode(new Proc(procLabel, vt));
        
        if(pRoot == null) {
            pRoot = newNode;
        }

    } // end of enqueue()


    @Override
    public String dequeue() {
        // Implement me

        return ""; // placeholder, modify this
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
        // Implement me

        return false; // placeholder, modify this
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        // Implement me

        return false; // placeholder, modify this
    } // end of removeProcess()


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
        // Implement me

    } // end of printAllProcess()

} // end of class BinarySearchTreeRQ

class BSTNode {
    public Proc pKey;
    public Node pLeftChild;
    public Node pRightChild;
    
    public BSTNode(Proc key) {
        pKey = key;
        pLeftChild  = null;
        pRightChild = null;
    }
    
    public Proc key(){
        return pKey;
    }
    
    public Node leftChild() {
        return pLeftChild;
    }
    
    public Node rightChild() {
        return pRightChild;
    }
    
}