package servicio;

import java.util.ArrayList;
import java.util.List;

import interfaces.Accionable;
import modelo.Estudiante;
import modelo.Prioridad;
import modelo.Profesor;
import modelo.Tarea;
import modelo.TareaNormal;
import modelo.TareaUrgente;
import modelo.TipoUsuario;
import modelo.Trabajador;
import modelo.Usuario;

/**
 * Implementación concreta del sistema gestor de tareas.
 * 
 * <p>
 * Esta clase se encarga de:
 * </p>
 * <ul>
 * 	<li>Gestionar usuarios</li>
 * 	<li>Generar identificadores auto-incrementales</li>
 * 	<li>Crear tareas indicando su prioridad</li>
 * 	<li>Asociar tareas a usuarios</li>
 * </ul>
 */

public class GestorTareasServicio implements Accionable {
	
	/** Lista de usuarios registrados */
	private final List<Usuario> usuarios;
	
	/** Contador auto-incremental para usuarios */
	private int contadorUsuarios;
	
	/** Contador auto-incremental para tareas */
	private int contadorTareas;
	
	/**
	 * Constructor del gestor.
	 */
	public GestorTareasServicio() {
		this.usuarios = new ArrayList<>();
		this.contadorUsuarios = 1;
		this.contadorTareas = 1;
	}
	
	// Gestión de usuarios

	@Override
	public Usuario crearUsuario(String nombre, TipoUsuario tipo) {
		int idGenerado = contadorUsuarios++;
		Usuario usuario;
		
		switch (tipo) {
			case PROFESOR:
				usuario = new Profesor(idGenerado, nombre);
				break;
			case ESTUDIANTE:
				usuario = new Estudiante(idGenerado, nombre);
				break;
			case TRABAJADOR:
				usuario = new Trabajador(idGenerado, nombre);
				break;
			default:
				throw new IllegalArgumentException("Tipo de usuario no válido.");
		}
		
		usuarios.add(usuario);
		return usuario;
	}

	@Override
	public Usuario buscarUsuario(int id) {
		return usuarios.stream()
				.filter(u -> u.getId() == id)
				.findFirst()
				.orElse(null);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return new ArrayList<>(usuarios); // copia defensiva
	}
	
	// Gestión de tareas

	@Override
	public Tarea crearTarea(String descripcion, Prioridad prioridad) {
		
		int idGenerado = contadorTareas++;
		Tarea tarea;
		
		switch (prioridad) {
			case NORMAL:
				tarea = new TareaNormal(idGenerado, descripcion);
				break;
			case URGENTE:
				tarea = new TareaUrgente(idGenerado, descripcion);
				break;
			default:
				throw new IllegalArgumentException("Prioridad no válida.");
		}
		
		return tarea;
	}

	@Override
	public void agregarTareaAUsuario(int usuarioId, Tarea tarea) {
		
		Usuario usuario = buscarUsuario(usuarioId);
		
		if (usuario == null) {
			throw new IllegalArgumentException("Usuario no encontrado.");
		}
		
		usuario.agregarTarea(tarea);

	}

	@Override
	public List<Tarea> listarTareasDeUsuario(int usuarioId) {
		
		Usuario usuario = buscarUsuario(usuarioId);
		
		if (usuario == null) {
			return null;
		}
		
		return usuario.getTareas();
	}

	@Override
	public boolean marcarTareaComoCompletada(int usuarioId, int tareaId) {
		
		Usuario usuario = buscarUsuario(usuarioId);
		
		if (usuario == null) {
			return false;
		}
		
		Tarea tarea = usuario.buscarTarea(tareaId);
		
		if (tarea == null) {
			return false;
		}
		
		tarea.marcarComoCompletada();
		return true;
	}

	@Override
	public boolean eliminarTareaDeUsuario(int usuarioId, int tareaId) {
		
		
		Usuario usuario = buscarUsuario(usuarioId);
		
		if (usuario == null) {
			return false;
		}
		return usuario.eliminarTarea(tareaId);
	}
	
	@Override
	public List<Tarea> listarTareasPorTipoUsuario(TipoUsuario tipo) {

	    return usuarios.stream()
	            .filter(u -> u.getTipo() == tipo)
	            .flatMap(u -> u.getTareas().stream())
	            .toList();
	}

}
