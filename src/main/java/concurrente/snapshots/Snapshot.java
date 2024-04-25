package concurrente.snapshots;

public interface Snapshot<T>{
    
    /**
     * Agrega un valor en el registro del proceso que llama al método
     * @param value Valor que se agregara
     */
    public void update(T value);

    /**
     * Genera una vista del arreglo
     * @return Arreglo con los últimos valores de los registros
     */
    public T[] scan();
}
