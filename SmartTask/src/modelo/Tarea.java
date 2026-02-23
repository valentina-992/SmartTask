package modelo;

import java.util.Objects;

/**
 * Clase abstracta que representa una tarea genérica.
 * 
 * <p>
 * Define atrubutos y comportamientos comunes a todas las tareas
 * (incluyendo normales y urgentes, estan activas o completadas),
 * como id, descripción y estado.
 * </p>
 * 
 * <p>
 * Las clases concretas deberán implementar el método {@link #getPrioridad()}
 * para especificar la prioridad de la tarea.
 * </p>
 * 
 * @author Valentina Villarroel
 */

public abstract class Tarea {
	
	/** Identificador único de la tarea */
	private final int id;
	
	/** Descripción de la tarea */
	private String descripcion;
	
	/** Estado actual de la tarea */
	private EstadoTarea estado;
	
	/**
	 * Constructor de la tarea 
	 * 
	 * @param id identificador único de la tarea asignado externamente
	 * @param descripcion descripción de la tarea (no puede ser null ni vacía)
	 * @throws IllegalArgumentException si la descripción es inválida
	 */
	
	public Tarea(int id, String descripcion) {
		if (descripcion == null || descripcion.trim().isEmpty()) {
			throw new IllegalArgumentException("La descripción no puede estar vacía.");
		}
		
		this.id = id;
		this.descripcion = descripcion.trim();
		this.estado = EstadoTarea.ACTIVA; // Las tareas inician activas por defecto
	}
	
	/**
	 * Obtiene el ID de la tarea.
	 * 
	 * @return identificador de la tarea
	 */
	
	public int getId() {
		return id;
	}
	
	/**
	 * Obtiene la descripción de la tarea.
	 * 
	 * @return descripción actual
	 */
	
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Modifica la descripción de la tarea.
	 * 
	 * @param descripcion nueva descripción (no puede ser null ni vacía)
	 * @throws IllegalArgumentException si la descripción es inválida
	 */
	public void setDescripcion(String descripcion) {
		if (descripcion == null || descripcion.trim().isEmpty()) {
			throw new IllegalArgumentException("La descripcion no puede estar vacía.");
		}
		this.descripcion = descripcion.trim();
	}
	
	/**
	 * Obtiene el estado actual de la tarea.
	 * 
	 * @return estado de la tarea
	 */
	public EstadoTarea getEstado() {
		return estado;
	}
	
	/**
	 * Marca la tarea como completada.
	 */
	public void marcarComoCompletada() {
		this.estado = EstadoTarea.COMPLETADA;
	}
	
	/**
	 * Devuelve la prioridad de la tarea.
	 * 
	 * <p>
	 * Este método debe ser implementado por las subclases.
	 * </p>
	 * 
	 * @return prioridad de la tarea
	 */
	public abstract Prioridad getPrioridad();
	
	/**
	 * Mostrar información de la tarea.
	 * 
	 * @return información formateada de la tarea
	 */
	@Override
	public String toString() {
		return String.format(
				"ID: %d | Descripción: %s | Prioridad: %s | Estado: %s",
				id,
				descripcion,
				getPrioridad(),
				estado
		);
	}
	
	/**
	 * Determina si dos tareas son iguales usando el ID.
	 * 
	 * @param o objeto a comparar
	 * @return true si los IDs coinciden
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if(!(o instanceof Tarea)) return false;
		Tarea tarea = (Tarea) o;
		return id == tarea.id;
	}
	
	/**
	 * Genera el hash basado en el ID.
	 * 
	 * @return hash code
	 */
	@Override 
	public int hashCode() {
		return Objects.hash(id);
	}

}
