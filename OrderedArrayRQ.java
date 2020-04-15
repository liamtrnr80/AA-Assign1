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
    protected DynamicArray array;
    
    public OrderedArrayRQ() {
        array = new DynamicArray();
    }  // end of OrderedArrayRQ()


    @Override
    public void enqueue(String procLabel, int vt) {
        Proc newProc = new Proc(procLabel, vt);
        array.add(newProc);

        array.sortArray();
    } // end of enqueue()


    @Override
    public String dequeue() {
        Proc temp = array.getProc(0);

        if(temp != null) {
            array.remove(0);
            return temp.getProcLabel();
        } else {
            return "";
        }
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel){
        return array.search(procLabel);
    } // end of findProcess()

    @Override
    public boolean removeProcess(String procLabel) {
        if(findProcess(procLabel)) {
            array.remove(array.find(procLabel));
            return true;
        } else {
            return false;
        }
    } // end of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
        int time = 0;
        if(findProcess(procLabel)) {
            for(int i = 0; i < array.find(procLabel); i++) {
                time += array.getProc(i).getVt();
            }
            return time;
        } else {
            return -1;
        }
    }// end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        int time = 0;
        if(findProcess(procLabel)) {
            for(int i = array.find(procLabel) + 1; i < array.length; i++) {
                time += array.getProc(i).getVt();
            }
            return time;
        } else {
            return -1;
        }
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        StringBuffer str = array.print();

        str.setLength(str.length() - 1);

        os.println(str.toString());
    } // end of printAllProcesses()

} // end of class OrderedArrayRQ

/**
 * Code for DynamicArray was referenced/taken from:
 * https://examples.javacodegeeks.com/dynamic-array-java-example/
 */
class DynamicArray {
    
    protected Proc[] array;
    protected int length;
    protected int cap;
    
    public DynamicArray() {
        array = new Proc[2];
        length = 0;
        cap = 2;
    }
    
    public void add(Proc proc) {
        if(length == cap) {
            increaseCapacity(2);
        }

        array[length] = proc;
        length++;
    }

    public Proc getProc(int index) {
        return array[index];
    }

    public void remove(int index) {
        if(index >= length || index < 0) {
            System.out.println("No element at this index");
        } else {
            if (length - 1 - index >= 0) System.arraycopy(array, index + 1, array, index, length - 1 - index);
            array[length - 1] = null;
            length--;
            reduceCapacity();
        }
    }

    public void increaseCapacity(int minCapacity) {
        Proc[] temp = new Proc[cap*minCapacity];
        if (cap >= 0) System.arraycopy(array, 0, temp, 0, cap);
        array = temp;
        cap = cap * minCapacity;

    }

    public void reduceCapacity() {
        Proc[] temp = new Proc[length];
        System.arraycopy(array, 0, temp, 0, length);
        array = temp;
        cap = array.length;
    }

    public boolean search(String index) {
        for(int i = 0; i < length; i++) {
            if(array[i].getProcLabel().equals(index)) {
                return true;
            }
        }
        return false;
    }

    public int find(String label) {
        for(int i = 0; i < length; i++) {
            if(array[i].getProcLabel().equals(label)) {
                return i;
            }
        }
        return -1;
    }

    public void sortArray() {
        boolean swapped = false;
        int lengthRemain = length;
        
        // Code taken from labs
        do {
            swapped = false;
            for(int i = 0; i < lengthRemain - 1; i++) {
                if(array[i].getVt() > array[i + 1].getVt()) {
                    Proc temp = array[i];
                    array[i] = array[i + 1];
                    array[i+1] = temp;
                    swapped = true;
                }
            }

            lengthRemain--;

        } while (swapped);
    }

    public StringBuffer print() {
        StringBuffer str = new StringBuffer();
        for(int i = 0; i < length; i++) {
            str.append(array[i].getProcLabel()).append(" ");
        }

        return str;
    }
}