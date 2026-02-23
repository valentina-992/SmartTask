package modelo;

/**
 * Representa una tarea con prioridad urgente.
 * 
 * <p>
 * Esta clase hereda de {@link Tarea} e implementa el método
 * {@link #getPrioridad()} devolviendo {@link Prioridad#URGENTE}.
 * </p>
 */

public class TareaUrgente extends Tarea{
	
	/**
	 * Construye una nueva tarea urgente.
	 * 
	 * @param id identificador único asignado externamente
	 * @param descripcion descripción de la tarea
	 */
	
	public TareaUrgente(int id, String descripcion) {
		super(id, descripcion);
	}
	
	/**
	 * Devueve la prioridad de la tarea.
	 * 
	 * @return {@link Prioridad#URGENTE}
	 */
	@Override
	public Prioridad getPrioridad() {
		return Prioridad.URGENTE;
	}

}
