package modelo;

/**
 * Representa un usuario de tipo Trabajador.
 */

public class Trabajador extends Usuario {

	public Trabajador(int id, String nombre) {
		super(id, nombre, TipoUsuario.TRABAJADOR);
	}

}
