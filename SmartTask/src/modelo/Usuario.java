package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Clase abstracta que representa un usuario del sistema.
 *
 * <p>
 * Define la estructura y comportamiento común para todos los tipos
 * de usuario (Profesor, Estudiante, Trabajador).
 * </p>
 */

public abstract class Usuario {

	/** Identificador único del usuario */
	private final int id;
	
	/** Nombre del usuario */
	private String nombre;
	
	/** Tipo de usuario */
	private final TipoUsuario tipo;
	
	/** Lista de tareas asociadas al usuario */
	private final List<Tarea> tareas;
	
	/**
	 * Construye un nuevo usuario.
	 * 
	 * @param id idetificador único asignado externamente
	 * @param nombre nombre del usuario (no puede ser null ni vacío)
	 * @throws IllegalArgumentException si el nombre es inválido
	 * @param tipo tipo de usuario
	 */
	
	public Usuario(int id, String nombre, TipoUsuario tipo) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede estar vacío.");
		}
		
		this.id = id;
		this.nombre = nombre.trim();
		this.tipo = tipo;
		this.tareas = new ArrayList<>();
	}
	
	/**
	 * Obtiene el identificador del usuario.
	 * 
	 * @return id del usuario
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Obtiene el nombre del usuario.
	 * 
	 * @return nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Obtiene el tipo de usuario
	 * @return tipo de usuario
	 */
	public TipoUsuario getTipo() {
		return tipo;
	}
	
	/**
	 * Modifica el nombre del usuario.
	 * 
	 * @param nombre nuevo nombre (no puede ser null ni vacío)
	 * @throws IllegalArgumentException si el nombre es inválido
	 */
	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede estar vacío.");
		}
		this.nombre = nombre.trim();
	}
	
	/**
	 * Devuelve una lista no modificable de las tareas del usuario.
	 * 
	 * <p>
	 * Se retrona una lista inmodificable para proteger el encapsulamiento.
	 * </p>
	 * 
	 * @return lista de tareas
	 */
	public List<Tarea> getTareas(){
		return Collections.unmodifiableList(tareas);
	}
	
	/**
	 * Agrega una tarea al usuario.
	 * 
	 * @param tarea tarea por agregar
	 */
	
	public void agregarTarea(Tarea tarea) {
		if (tarea != null) {
			tareas.add(tarea);
		}
	}
	
	/** 
	 * Busca una tarea por su identificador.
	 * 
	 * @param id identificador de la tarea
	 * @return la tarea si existe, o null si no se encuentra
	 */
	public Tarea buscarTarea(int id) {
        return tareas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }
	
	/**
	 * Elimina una tarea por su identificador.
	 * 
	 * @param id identificador de la tarea
	 * @return true si la tarea fue eliminada, false si no se encontró
	 */
	public boolean eliminarTarea(int id) {
		return tareas.removeIf(tarea -> tarea.getId() == id);
	}
	
	/**
     * Representación textual del usuario.
     *
     * @return información básica del usuario
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Tipo: %s| Total tareas: %d",
                id, 
                nombre, 
                tipo.getNombreFormateado(),
                tareas.size());
    }

    /**
     * Dos usuarios son iguales si tienen el mismo ID.
     *
     * @param obj objeto a comparar
     * @return true si los IDs coinciden
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Usuario)) return false;
        Usuario other = (Usuario) obj;
        return this.id == other.id;
    }

    /**
     * Genera hash basado en el ID.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
	

}
