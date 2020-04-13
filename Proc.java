public class Proc {
    /** Stored label of process */
    private String procLabel;
    /** Stored value of vruntime */
    private int vt;
    /** Reference to next process */
    private Proc pNext;
    /** Reference to previous process */
    private Proc pPrev;
    
    public Proc(String label, int time) {
        procLabel = label;
        vt = time;
        pNext = null;
        pPrev = null;
    }
    
    public String getProcLabel() {
        return procLabel;
    }
    
    public int getVt() {
        return vt;
    }
    
    public Proc getNext() {
        return pNext;
    }
    
    public Proc getPrev() {
        return pPrev;
    }
    
    public void setNext(Proc next) {
        pNext = next;
    }
    
    public void setPrev(Proc prev) {
        pPrev = prev;
    }
}
