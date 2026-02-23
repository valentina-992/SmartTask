package interfaces;

import java.util.List;

import modelo.Prioridad;
import modelo.Tarea;
import modelo.TipoUsuario;
import modelo.Usuario;

/**
 * Define las operaciones principales del sistema gestor de tareas.
 * 
 * <p>
 * Separa la lógica del sistema de su implementación, permitiendo
 * aplicar principios como desacoplamiento y polimorfismo.
 */

public interface Accionable {
	
	// Gestión de usuarios
	
	/**
	 * Crea un nuevo usuario en el sistema
	 * 
	 * @param nombre nombre del usuario
	 * @param tipo tipo de usuario
	 * @return usuario creado
	 */
	Usuario crearUsuario(String nombre, TipoUsuario tipo);
	
	/**
	 * Busca un usuario por su identificador.
	 * 
	 * @param id identificador del usuario
	 * @return usuario encontrado o null si no existe
	 */
	Usuario buscarUsuario(int id);
	
	/**
	 * Devuelve la lista de usuarios registrados.
	 * 
	 * @return lista de usuarios
	 */
	List<Usuario> listarUsuarios();
	
	// Gestión de tareas 
	
	/**
	 * Crea una nueva tarea con prioridad especificada.
	 * 
	 * <p>
	 * El identificador de la tarea debe ser generado automáticamente.
	 * </p>
	 * 
	 * @param descripcion descripción de la tarea
	 * @param prioridad prioridad de la tarea
	 * @return tarea creada
	 */
	Tarea crearTarea(String descripcion, Prioridad prioridad);
	
	/**
	 * Asocia una tarea a un usuario.
	 * 
	 * @param usuarioId identificador del usuario
	 * @param tarea tarea a asociar
	 */
	void agregarTareaAUsuario(int usuarioId, Tarea tarea);
	
	/**
	 * Devuelve las tareas asociadas a un usuario.
	 * 
	 * @param usuarioId identificador del usuario
	 * @return lista de tareas o null si el usuario no existe
	 */
	List<Tarea> listarTareasDeUsuario(int usuarioId);
	
	/**
	 * Marca una tarea como completada.
	 * 
	 * @param usuarioId identificador del usuario
	 * @param tareaId identificador de la tarea
	 * @return true si se marcó correctamente, false si no se encontró
	 */
	boolean marcarTareaComoCompletada(int usuarioId, int tareaId);
	
	/**
	 * Elimina una tarea de un usuario.
	 * 
	 * @param usuarioId identificador del usuario
	 * @param tareaId identificador de la tarea
	 * @return true si la eliminó correctamente, false si no se encontró
	 */
	boolean eliminarTareaDeUsuario(int usuarioId, int tareaId);
	
	/**
	 * Obtiene todas las tareas asociadas a usuarios de un tipo específico.
	 *
	 * @param tipo Tipo de usuario a filtrar
	 * @return lista de tareas asociadas a ese tipo
	 */
	List<Tarea> listarTareasPorTipoUsuario(TipoUsuario tipo);
	

}
