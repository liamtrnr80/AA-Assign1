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
        BSTNode newNode = new BSTNode(new Proc(procLabel, vt));
        
        if(pRoot == null) {
            pRoot = newNode;
            return;
        }
        
        BSTNode currNode = pRoot;
        BSTNode prevNode = null;
        
        while(true) {
            prevNode = currNode;
            if(vt < currNode.pKey.getVt()) {
                currNode = currNode.pLeftChild;
                if(currNode == null) {
                    prevNode.pLeftChild = newNode;
                    newNode.pParent = prevNode;
                    return;
                }
            } else {
                currNode = currNode.pRightChild;
                if(currNode == null) {
                    prevNode.pRightChild = newNode;
                    newNode.pParent = prevNode;
                    return;
                }
            }
        }
        
    } // end of enqueue()


    @Override
    public String dequeue() {
        Proc minProc = min();
        
        if(removeNode(minProc)) {
            return minProc.getProcLabel();
        } else {
            return "";
        }
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
        return search(pRoot, procLabel);
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        if(findProcess(procLabel)) {
            Proc delete = findNode(pRoot, procLabel).pKey;
            return removeNode(delete);
        } else {
            return false;
        }
    } // end of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
        int time = 0;
        BSTNode currNode = pRoot;
        BSTNode index = null;
        
        if(findProcess(procLabel)) {
            index = findNode(pRoot, procLabel);
            
           while (currNode != pRoot && currNode != null) {
                time += currNode.pKey.getVt();
                currNode = currNode.pParent;
            }
            
            while(currNode != null) {
                    time += currNode.pKey.getVt();
                    currNode = currNode.pLeftChild;
            }
            
            return time;
        } else {
            return -1;
        }
    } // end of precedingProcessTime()

    @Override
    public int succeedingProcessTime(String procLabel) {
        int time = 0;
        BSTNode currNode = null;
        
        if(findProcess(procLabel)){
            currNode = findNode(pRoot, procLabel).pRightChild;
            while(currNode != null) {
                time += currNode.pKey.getVt();
                currNode = currNode.pRightChild;
            }
            return time;
        } else {
            return -1;
        }
    } // end of precedingProcessTime()

    @Override
    public void printAllProcesses(PrintWriter os) {
        StringBuffer str = new StringBuffer();
        str = print(pRoot, str);
        
        str.setLength(str.length() - 1);
        os.println(str.toString());
    } // end of printAllProcess()
    
    public BSTNode getNextChild(BSTNode deleteNode) {
        BSTNode child = null;
        BSTNode childParent = null;
        BSTNode currNode = deleteNode.pRightChild;
        
        while(currNode != null) {
            childParent = child;
            child = currNode;
            currNode = currNode.pLeftChild;
        }
        
        if(child != deleteNode.pRightChild) {
            childParent.pLeftChild = child.pRightChild;
            child.pRightChild = deleteNode.pRightChild;
        }
        
        return child;
    }
    
    public Proc min() {
        BSTNode minNode = min(pRoot);
        
        return minNode.pKey;
    }
    
    protected BSTNode min(BSTNode root) {
        if(root.pLeftChild == null) {
            return root;
        }
        
        return min(root.pLeftChild);
    }
    
    protected boolean search(BSTNode root, String label) {
        if(root == null) {
            return false;
        }
        
        if(root.pKey.getProcLabel().equals(label)) {
            return true;
        }
        boolean resLeft = search(root.pLeftChild, label);
        if(resLeft) {
            return true;
        }
        boolean resRight = search(root.pRightChild, label);
        
        return resRight;
    }
    
    protected boolean removeNode(Proc delete) {
        BSTNode prevNode = pRoot;
        BSTNode currNode = pRoot;
        boolean isChildLeft = false;
    
        while(!currNode.pKey.getProcLabel().equals(delete.getProcLabel())) {
            prevNode = currNode;
            if(currNode.pKey.getVt() > delete.getVt()) {
                isChildLeft = true;
                currNode =currNode.pLeftChild;
            } else {
                isChildLeft = false;
                currNode = currNode.pRightChild;
            }
        
            if(currNode == null) {
                return false;
            }
        }
    
        if(currNode.pLeftChild == null && currNode.pRightChild == null) {
            if(currNode == pRoot) {
                pRoot = null;
            }
        
            if(isChildLeft) {
                prevNode.pLeftChild = null;
            } else {
                prevNode.pRightChild = null;
            }
        } else if(currNode.pRightChild == null) {
            if(currNode == pRoot) {
                pRoot = currNode.pLeftChild;
            } else if(isChildLeft) {
                prevNode.pLeftChild = currNode.pLeftChild;
            } else {
                prevNode.pRightChild = currNode.pLeftChild;
            }
        } else if(currNode.pLeftChild == null) {
            if(currNode == pRoot) {
                pRoot = currNode.pRightChild;
            } else if(isChildLeft) {
                prevNode.pLeftChild = currNode.pRightChild;
            } else {
                prevNode.pRightChild = currNode.pRightChild;
            }
        } else if(currNode.pLeftChild != null && currNode.pRightChild != null) {
            BSTNode child = getNextChild(currNode);
            if(currNode == pRoot) {
                pRoot = child;
            } else if(isChildLeft) {
                prevNode.pLeftChild = child;
            } else {
                prevNode.pRightChild = child;
            }
            child.pLeftChild = currNode.pLeftChild;
        }
        return true;
    }
    
    protected BSTNode findNode(BSTNode root, String label) {
        if(root == null) {
            return null;
        }
    
        if(root.pKey.getProcLabel().equals(label)) {
            return root;
        }
        BSTNode resLeft = findNode(root.pLeftChild, label);
        if(resLeft != null) {
            return resLeft;
        }
        BSTNode resRight = findNode(root.pRightChild, label);
        
        return resRight;
    }

    /** Trying to traverse BST until reaching index, however time does not increase
    public Integer findPreceeding(BSTNode root, BSTNode index, Integer time) {
        if(root != null && root != index) {
            time += root.pKey.getVt();
            findPreceeding(root.pLeftChild, index, time);
            System.out.println(String.format("Adding %s with value %d", root.pKey.getProcLabel(), root.pKey.getVt()));
            findPreceeding(root.pRightChild, index, time);
        }
        return time;
    }*/
    
    public StringBuffer print(BSTNode root, StringBuffer str) {
        if(root != null) {
            print(root.pLeftChild, str);
            str.append(root.pKey.getProcLabel() + " ");
            print(root.pRightChild, str);
        }
        
        return str;
    }
} // end of class BinarySearchTreeRQ

class BSTNode {
    public Proc pKey;
    public BSTNode pLeftChild;
    public BSTNode pRightChild;
    public BSTNode pParent;
    
    public BSTNode(Proc key) {
        pKey = key;
        pParent = null;
        pLeftChild  = null;
        pRightChild = null;
    }
    
    public Proc key(){
        return pKey;
    }
    
    public BSTNode leftChild() {
        return pLeftChild;
    }
    
    public BSTNode rightChild() {
        return pRightChild;
    }
    
    public BSTNode parent() {
        return pParent;
    }
}