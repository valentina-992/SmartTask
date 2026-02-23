package test;

import modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import servicio.GestorTareasServicio;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase GestorTareas.
 */
class GestorTareasTest {

    private GestorTareasServicio gestor;

    @BeforeEach
    void setUp() {
        gestor = new GestorTareasServicio();
    }

    @Test
    void deberiaCrearUsuarioCorrectamente() {
        Usuario usuario = gestor.crearUsuario("Vale", TipoUsuario.ESTUDIANTE);

        assertNotNull(usuario);
        assertEquals("Vale", usuario.getNombre());
        assertEquals(TipoUsuario.ESTUDIANTE, usuario.getTipo());
    }
    
    @Test
    void deberiaCrearProfesor() {
        Usuario usuario = gestor.crearUsuario("Ana", TipoUsuario.PROFESOR);
        assertTrue(usuario instanceof Profesor);
    }

    @Test
    void deberiaCrearTrabajador() {
        Usuario usuario = gestor.crearUsuario("Luis", TipoUsuario.TRABAJADOR);
        assertTrue(usuario instanceof Trabajador);
    }
    
    @Test
    void deberiaCrearTareaNormal() {
        Tarea tarea = gestor.crearTarea("Leer", Prioridad.NORMAL);
        assertTrue(tarea instanceof TareaNormal);
    }

    @Test
    void deberiaCrearTareaUrgente() {
        Tarea tarea = gestor.crearTarea("Pagar cuentas", Prioridad.URGENTE);
        assertTrue(tarea instanceof TareaUrgente);
    }

    @Test
    void deberiaAgregarTareaAUsuario() {
        Usuario usuario = gestor.crearUsuario("Carlos", TipoUsuario.PROFESOR);
        Tarea tarea = gestor.crearTarea("Preparar clase", Prioridad.NORMAL);

        gestor.agregarTareaAUsuario(usuario.getId(), tarea);

        List<Tarea> tareas = gestor.listarTareasDeUsuario(usuario.getId());

        assertEquals(1, tareas.size());
        assertEquals("Preparar clase", tareas.get(0).getDescripcion());
    }
    
    @Test
    void noDeberiaAgregarTareaAUsuarioInexistente() {
        Tarea tarea = gestor.crearTarea("Algo", Prioridad.NORMAL);
        assertThrows(IllegalArgumentException.class, () -> {
            gestor.agregarTareaAUsuario(999, tarea);
        });
    }

    @Test
    void deberiaMarcarTareaComoCompletada() {
        Usuario usuario = gestor.crearUsuario("Ana", TipoUsuario.TRABAJADOR);
        Tarea tarea = gestor.crearTarea("Enviar informe", Prioridad.URGENTE);

        gestor.agregarTareaAUsuario(usuario.getId(), tarea);

        boolean resultado = gestor.marcarTareaComoCompletada(usuario.getId(), tarea.getId());

        assertTrue(resultado);
        assertEquals(EstadoTarea.COMPLETADA, tarea.getEstado());
    }
    
    @Test
    void noDeberiaMarcarTareaInexistente() {
        Usuario usuario = gestor.crearUsuario("Lucia", TipoUsuario.PROFESOR);

        boolean resultado = gestor.marcarTareaComoCompletada(usuario.getId(), 999);

        assertFalse(resultado);
    }

    @Test
    void deberiaEliminarTarea() {
        Usuario usuario = gestor.crearUsuario("Pedro", TipoUsuario.ESTUDIANTE);
        Tarea tarea = gestor.crearTarea("Estudiar Java", Prioridad.NORMAL);

        gestor.agregarTareaAUsuario(usuario.getId(), tarea);

        boolean eliminado = gestor.eliminarTareaDeUsuario(usuario.getId(), tarea.getId());

        assertTrue(eliminado);
        assertTrue(gestor.listarTareasDeUsuario(usuario.getId()).isEmpty());
    }
    
    @Test
    void noDeberiaEliminarTareaInexistente() {
        Usuario usuario = gestor.crearUsuario("Vale", TipoUsuario.ESTUDIANTE);
        boolean resultado = gestor.eliminarTareaDeUsuario(usuario.getId(), 999);
        assertFalse(resultado);
    }
    
    @Test
    void deberiaListarTareasPorTipoUsuario() {
        Usuario profesor = gestor.crearUsuario("Ana", TipoUsuario.PROFESOR);
        Usuario estudiante = gestor.crearUsuario("Vale", TipoUsuario.ESTUDIANTE);

        Tarea t1 = gestor.crearTarea("Clase", Prioridad.NORMAL);
        Tarea t2 = gestor.crearTarea("Estudiar", Prioridad.NORMAL);

        gestor.agregarTareaAUsuario(profesor.getId(), t1);
        gestor.agregarTareaAUsuario(estudiante.getId(), t2);

        List<Tarea> tareasProfesores =
                gestor.listarTareasPorTipoUsuario(TipoUsuario.PROFESOR);

        assertEquals(1, tareasProfesores.size());
        assertEquals("Clase", tareasProfesores.get(0).getDescripcion());
    }


    
}
