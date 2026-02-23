package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Estudiante;
import modelo.Usuario;

class UsuarioTest {

	@Test
	void constructorDeberiaLanzarExcepcionSiNombreEsInvalido() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new Estudiante(1, "");
	    });
	}

	@Test
	void setNombreDeberiaLanzarExcepcionSiEsInvalido() {
	    Usuario usuario = new Estudiante(1, "Vale");
	    assertThrows(IllegalArgumentException.class, () -> {
	        usuario.setNombre("   ");
	    });
	}

	@Test
	void agregarTareaNoDeberiaAgregarSiEsNull() {
	    Usuario usuario = new Estudiante(1, "Vale");
	    usuario.agregarTarea(null);

	    assertEquals(0, usuario.getTareas().size());
	}

	@Test
	void buscarTareaDeberiaRetornarNullSiNoExiste() {
	    Usuario usuario = new Estudiante(1, "Vale");
	    assertNull(usuario.buscarTarea(99));
	}

	@Test
	void eliminarTareaDeberiaRetornarFalseSiNoExiste() {
	    Usuario usuario = new Estudiante(1, "Vale");
	    assertFalse(usuario.eliminarTarea(99));
	}

	@Test
	void equalsUsuarioDeberiaSerTrueSiIdEsIgual() {
	    Usuario u1 = new Estudiante(1, "A");
	    Usuario u2 = new Estudiante(1, "B");

	    assertEquals(u1, u2);
	}

	@Test
	void toStringUsuarioNoDeberiaSerNull() {
	    Usuario usuario = new Estudiante(1, "Vale");
	    assertNotNull(usuario.toString());
	}

}
