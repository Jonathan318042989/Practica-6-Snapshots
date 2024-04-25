package concurrente.candados.candadosImpl;

import concurrente.candados.Lock;

/**
 * Clase que implementa el candado Peterson
 */
public class PetersonLock implements Lock{

    private Boolean[] flag = new Boolean[2];

    private volatile int victima;

    @Override
    public void lock() {
        int id = Integer.parseInt(Thread.currentThread().getName());
        flag[id] = true;
        this.victima = id;
        while (Boolean.TRUE.equals(flag[1 - id]) && this.victima == id) {
        }
    }

    @Override
    public void unlock() {
        int i = Integer.parseInt(Thread.currentThread().getName());
        flag[i] = false;
    }

}
