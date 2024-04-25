package concurrente.snapshots.snapshotsImpl;

public class StampedSnap<T>{
    
    private long stamp;
    private T value;
    private T[] snap;

    public StampedSnap(T value){
        this.stamp = 0;
        this.value = value;
        this.snap = null;
    }

    public StampedSnap(long stamp, T value, T[] snap){
        this. stamp = stamp;
        this.value = value;
        this.snap = snap;
    }

    public long getStamp(){
        return stamp;
    }

    public T getValue(){
        return value;
    }

    public T[] getSnap(){
        return snap;
    }
}
