package concurrente.candados;

/**
 * Interfaz que modela un candado
 */
public interface Lock {
    
    /**
     * Metodo que bloquea el acceso a la seccion critica.
     */
    public void lock();

    /**
     * Metodo que desbloque el acceso a la seccion critica.
     */
    public void unlock();
    
}
