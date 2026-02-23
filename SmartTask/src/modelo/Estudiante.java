package modelo;

/**
 * Representa un usuario de tipo Estudiante.
 */

public class Estudiante extends Usuario {

	public Estudiante(int id, String nombre) {
		super(id, nombre, TipoUsuario.ESTUDIANTE);
	}

}
