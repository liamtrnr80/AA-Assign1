public class Node {
    
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
