package modelo;

public class Trabajador extends Usuario {

	public Trabajador(int id, String nombre) {
		super(id, nombre, TipoUsuario.TRABAJADOR);
	}

}
