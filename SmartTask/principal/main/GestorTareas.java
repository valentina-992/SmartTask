package main;

import java.util.List;
import java.util.Scanner;

import servicio.GestorTareasServicio;
import interfaces.Accionable;
import modelo.Prioridad;
import modelo.Tarea;
import modelo.TipoUsuario;
import modelo.Usuario;

/**
 * Clase principal del sistema Gestor de Tareas.
 * 
 * <p>Se encarga de:
 * <ul>
 *     <li>Mostrar el menú interactivo en consola</li>
 *     <li>Capturar la entrada del usuario</li>
 *     <li>Delegar operaciones con la interfaz {@link Accionable}</li>
 * </ul>
 * 
 * <p>Aplica separación de responsabilidades, delegando la lógica de negocio
 * a la capa de servicio.
 * 
 * @author Valentina Villarroel
 */

public class GestorTareas {
	
	private static final Scanner scanner = new Scanner(System.in);
	private static final Accionable gestor = new GestorTareasServicio();
;
	public static void main(String[] args) {
		
		int opcion;
		
		do {
			mostrarMenu();
			opcion = leerEntero("Seleccione una opción: ");
			
			switch (opcion) {
			case 1:
				crearUsuario();
				break;
			case 2:
				listarUsuarios();
				break;
			case 3:
				agregarTarea();
				break;
			case 4:
				listarTareas();
				break;
			case 5: 
				marcarTarea();
				break;
			case 6:
				eliminarTarea();
				break;
			case 7:
				filtrarUsuariosPorTipo();
				break;
			case 8:
				listarTareasPorTipoUsuario();
				break;
			case 0:
				System.out.println("Saliendo del sistema...");
				break;
			default:
				System.out.println("Ingrese una opción válida.");
			}
		} while (opcion != 0);
		
		scanner.close();

	}
	
	private static void mostrarMenu() {
		System.out.println("\nGestor de tareas:");
        System.out.println("1. Crear usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Agregar tarea a usuario");
        System.out.println("4. Listar tareas de usuario");
        System.out.println("5. Marcar tarea como completada");
        System.out.println("6. Eliminar tarea");
        System.out.println("7. Filtrar usuarios por tipo");
        System.out.println("8. Listar tareas por tipo de usuario");
        System.out.println("0. Salir");
	}
	
	private static void crearUsuario() {
		System.out.println("Ingrese nombre: ");
		String nombre = scanner.nextLine();
		
		System.out.println("Seleccione el tipo: ");
		for (TipoUsuario tipo: TipoUsuario.values()) {
			System.out.println(tipo.ordinal() + 1 + ". " + tipo.getNombreFormateado());
		}
		
		int opcionTipo = leerEntero("Opción: ");
		TipoUsuario tipoSeleccionado = TipoUsuario.values()[opcionTipo - 1];
		
		Usuario usuario = gestor.crearUsuario(nombre, tipoSeleccionado);
		System.out.println("Usuario creado: " + usuario);
	}
	
	private static void listarUsuarios() {
		List<Usuario> usuarios = gestor.listarUsuarios();
		
		if (usuarios.isEmpty()) {
			System.out.println("No hay usuarios registrados.");
			return;
		}
		
		usuarios.forEach(System.out::println);
	}
	
	private static void agregarTarea() {
		
		int usuarioId = leerEntero("Ingrese ID del usuario: ");
		Usuario usuario = gestor.buscarUsuario(usuarioId);
		
		if (usuario == null ) {
			System.out.println("Usuario no encontrado.");
			return;
		}
		
		System.out.println("Descripción de la tarea: ");
		String descripcion = scanner.nextLine();
		
		System.out.println("Seleccione prioridad: ");
		for (Prioridad p : Prioridad.values()) {
			System.out.println(p.ordinal() + 1 + ". " + p);
		}
		
		int opcionPrioridad = leerEntero("Opción: ");
		Prioridad prioridad = Prioridad.values()[opcionPrioridad - 1];
		
		Tarea tarea = gestor.crearTarea(descripcion, prioridad);
		gestor.agregarTareaAUsuario(usuarioId, tarea);
		
		System.out.println("Tarea agregada correctamente.");
	}
	
	private static void listarTareas() {
		
		int usuarioId = leerEntero("Ingrese ID del usuario: ");
		List<Tarea> tareas = gestor.listarTareasDeUsuario(usuarioId);
		
		if (tareas == null || tareas.isEmpty()) {
			System.out.println("No hay tareas para este usuario.");
			return;
		}
		
		tareas.forEach(System.out::println);
	}
	
	private static void marcarTarea() {
		
		int usuarioId = leerEntero("Ingrese ID del usuario: ");
		int tareaId = leerEntero("Ingrese ID de la tarea: ");
		
		boolean resultado = gestor.marcarTareaComoCompletada(usuarioId, tareaId);
		
		if (resultado) {
			System.out.println("Tarea marcada como completada.");
		} else {
			System.out.println("No se encontró la tarea.");
		}
	}
	
	private static void eliminarTarea() {
		int usuarioId = leerEntero("Ingrese ID del usuario: ");
		int tareaId = leerEntero("Ingrese ID de la tarea. ");
		
		boolean resultado = gestor.eliminarTareaDeUsuario(usuarioId, tareaId);
		
		if(resultado) {
			System.out.println("Tarea eliminada correctamente.");
		} else {
			System.out.println("No se encontró la tarea");
		}
	}
	
	private static void filtrarUsuariosPorTipo() {
		
		System.out.println("Seleccione tipo: ");
		for (TipoUsuario tipo: TipoUsuario.values()) {
			System.out.println(tipo.ordinal() + 1 + ". " + tipo.getNombreFormateado());
		}
		
		int opcionTipo = leerEntero("Opción: ");
		TipoUsuario tipoSeleccionado = TipoUsuario.values()[opcionTipo - 1];
		
		gestor.listarUsuarios().stream()
				.filter(u -> u.getTipo() == tipoSeleccionado)
				.forEach(System.out::println);
	}
	
	private static void listarTareasPorTipoUsuario() {

	    System.out.println("Seleccione tipo:");
	    for (TipoUsuario tipo : TipoUsuario.values()) {
	        System.out.println(tipo.ordinal() + 1 + ". " + tipo.getNombreFormateado());
	    }

	    int opcionTipo = leerEntero("Opción: ");
	    TipoUsuario tipoSeleccionado = TipoUsuario.values()[opcionTipo - 1];

	    List<Tarea> tareas = gestor.listarTareasPorTipoUsuario(tipoSeleccionado);

	    if (tareas.isEmpty()) {
	        System.out.println("No hay tareas para este tipo de usuario.");
	        return;
	    }

	    tareas.forEach(System.out::println);
	}

	
	private static int leerEntero(String mensaje) {
		
		while (true) {
			try {
				System.out.println(mensaje);
				int numero = Integer.parseInt(scanner.nextLine());
				return numero;
			} catch (NumberFormatException e) {
				System.out.println("debe ingresar un número válido.");
			}
		}
	}

}
