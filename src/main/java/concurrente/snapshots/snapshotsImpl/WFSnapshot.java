package concurrente.snapshots.snapshotsImpl;

import concurrente.snapshots.Snapshot;
import concurrente.candados.Lock;
import concurrente.candados.candadosImpl.PetersonLock;

public class WFSnapshot<T> implements Snapshot<T>{

    private StampedSnap<T>[] aTable;
    protected Lock candado;

    public WFSnapshot(int tamano, T valorInicial){
        aTable = (StampedSnap<T>[]) new StampedSnap[tamano];
        candado = new PetersonLock();
        for(int i = 0; i < aTable.length; i++)
            aTable[i] = new StampedSnap<>(valorInicial);
    }

    @Override
    public void update(T value) {
        int id = Integer.parseInt(Thread.currentThread().getName());
        T[] snap = scan();
        StampedSnap<T> oldValue = aTable[id];
        StampedSnap<T> newValue = new StampedSnap<>(oldValue.getStamp()+1,value,snap);
        aTable[id] = newValue;
    }

    @Override
    public T[] scan() {
        StampedSnap<T>[] oldCopy;
        StampedSnap<T>[] newCopy;
        boolean[] moved = new boolean[aTable.length];
        oldCopy = collect();
        collect : while(true){
            newCopy = collect();
            for(int j = 0; j < aTable.length; j++){
                if(oldCopy[j].getStamp() != newCopy[j].getStamp()){
                    if(moved[j])
                        return oldCopy[j].getSnap();
                    else{
                        moved[j] = true;
                        oldCopy = newCopy;
                        continue collect;
                    }
                }
            }
            T[] result = (T[]) new Object[aTable.length];
            for(int j = 0; j < aTable.length; j++)
                result[j] = newCopy[j].getValue();
            return result;
        }
    }
    
    /**
     * Crea una copia del aTable
     * @return Copia de aTable
     */
    private StampedSnap<T>[] collect(){
        StampedSnap<T>[] copy = (StampedSnap<T>[]) new StampedSnap[aTable.length];
        for(int j = 0; j < aTable.length; j++){
            copy[j] = aTable[j];
        }
        return copy;
    }
}