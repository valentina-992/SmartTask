package modelo;

/**
 * Representa los distintos tipos de usuario del sistema.
 */

public enum TipoUsuario {
	PROFESOR, ESTUDIANTE, TRABAJADOR;
	
	/**
	 * Devuelve una representación formateada del tipo de usuario.
	 * 
	 * @return nombre formateado (primera letra en mayúscula)
	 */
	
	public String getNombreFormateado() {
		return this.name().charAt(0) + this.name().substring(1).toLowerCase();
	}
}
