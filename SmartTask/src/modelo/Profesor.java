package modelo;

/**
 * representa un usuario de tipo Profesor.
 */

public class Profesor extends Usuario {
	
	public Profesor(int id, String nombre) {
		super(id, nombre, TipoUsuario.PROFESOR);
	}

}
