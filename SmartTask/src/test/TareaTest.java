package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.EstadoTarea;
import  modelo.Tarea;
import modelo.TareaNormal;

class TareaTest {

	@Test
	void constructorDeberiaLanzarExcepcionSiDescripcionEsNull() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new TareaNormal(1, null);
	    });
	}

	@Test
	void constructorDeberiaLanzarExcepcionSiDescripcionEsVacia() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        new TareaNormal(1, "   ");
	    });
	}

	@Test
	void setDescripcionDeberiaLanzarExcepcionSiEsInvalida() {
	    Tarea tarea = new TareaNormal(1, "Test");
	    assertThrows(IllegalArgumentException.class, () -> {
	        tarea.setDescripcion("");
	    });
	}

	@Test
	void marcarComoCompletadaDeberiaCambiarEstado() {
	    Tarea tarea = new TareaNormal(1, "Test");
	    tarea.marcarComoCompletada();
	    assertEquals(EstadoTarea.COMPLETADA, tarea.getEstado());
	}

	@Test
	void equalsDeberiaSerTrueSiIdEsIgual() {
	    Tarea t1 = new TareaNormal(1, "A");
	    Tarea t2 = new TareaNormal(1, "B");

	    assertEquals(t1, t2);
	}

	@Test
	void equalsDeberiaSerFalseSiIdEsDistinto() {
	    Tarea t1 = new TareaNormal(1, "A");
	    Tarea t2 = new TareaNormal(2, "A");

	    assertNotEquals(t1, t2);
	}

	@Test
	void toStringNoDeberiaSerNull() {
	    Tarea tarea = new TareaNormal(1, "Test");
	    assertNotNull(tarea.toString());
	}

}
