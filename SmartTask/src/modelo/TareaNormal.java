package modelo;

/**
 * Representa una tarea con prioridad normal.
 * 
 * <p>
 * Esta clase hereda de {@link Tarea} e implementa el método
 * {@link #getPrioridad()} devolviendo {@link Prioridad#NORMAL}.
 * </p>
 */

public class TareaNormal extends Tarea {
	
	/**
	 * Construye una nueva tarea normal.
	 * 
	 * @param id identificador único asignado externamente
	 * @param descripcion descripción de la tarea
	 */
	
	public TareaNormal(int id, String descripcion) {
		super(id, descripcion);
	}
	
	/**
	 * Devuelve la prioridad de la tarea.
	 * 
	 * @return {@link Prioridad#NORMAL}
	 */
	@Override
	public Prioridad getPrioridad() {
		return Prioridad.NORMAL;
	}

}
