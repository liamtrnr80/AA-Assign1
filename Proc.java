public class Proc {
    /** Stored label of process */
    private String procLabel;
    /** Stored value of vruntime */
    private int vt;
    
    public Proc(String label, int time) {
        procLabel = label;
        vt = time;
    }
    
    public String getProcLabel() {
        return procLabel;
    }
    
    public int getVt() {
        return vt;
    }
}
