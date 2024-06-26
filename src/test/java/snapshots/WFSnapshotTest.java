package snapshots;

import concurrente.snapshots.snapshotsImpl.WFSnapshot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Test para el snapshot wait-free
 */
public class WFSnapshotTest {

    static final int HILOS = 7;
    WFSnapshot<Integer> snapshot = new WFSnapshot<>(HILOS, 0);

    @Test
    void testUpdateAndScan() throws InterruptedException{
        Thread[] threads = new Thread[HILOS];
        for(int i = 0; i < HILOS; i++){
            final int aux = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run(){
                    snapshot.update(aux+1);
                }
            }, ""+i);
        }
        for(Thread t : threads)
            t.start();
        for(Thread t : threads)
            t.join();
        
        Object[] scan = snapshot.scan();
        Integer[] result = new Integer[HILOS];
        for (int i = 0; i < HILOS; i++)
            result[i] = (int) scan[i];
        for (int i = 0; i < HILOS; i++)
            assertEquals(i + 1, result[i]);
    }
}