package modelo;

public class Estudiante extends Usuario {

	public Estudiante(int id, String nombre) {
		super(id, nombre, TipoUsuario.ESTUDIANTE);
	}

}
