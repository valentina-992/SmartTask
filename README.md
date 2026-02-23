# SmartTask

Gestor de tareas por consola desarrollado en Java aplicando principios de Programación Orientada a Objetos y pruebas unitarias con alta cobertura.

---

## Descripción

SmartTask es una aplicación de consola que permite gestionar usuarios y sus tareas asociadas.  
El proyecto fue desarrollado con un enfoque en:

- Buenas prácticas de diseño
- Encapsulamiento
- Herencia y polimorfismo
- Uso de interfaces y clases abstractas
- Manejo adecuado de excepciones
- Pruebas unitarias con cobertura superior al 90%

---

## Funcionalidades

### Gestión de usuarios
- Creación de usuarios con ID autogenerado
- Tipos de usuario:
  - Profesor
  - Estudiante
  - Trabajador

### Gestión de tareas
- Crear tareas normales o urgentes
- Marcar tareas como completadas
- Listar tareas activas y completadas
- Eliminar tareas por ID
- Buscar tareas por usuario
- Filtrar tareas por tipo de usuario

---

## Conceptos aplicados

- Clases abstractas (`Tarea`, `Usuario`)
- Interfaces (`Accionable`)
- Herencia (`Profesor`, `Estudiante`, `Trabajador`)
- Polimorfismo
- Enumeraciones (`EstadoTarea`, `Prioridad`, `TipoUsuario`)
- Encapsulamiento con colecciones inmutables
- Override de `equals()` y `hashCode()`
- JavaDoc en clases y métodos públicos
- Arquitectura separando lógica de negocio y capa de presentación

---

## Testing

El proyecto incluye pruebas unitarias con JUnit 5.

- Cobertura total superior al 90% (se excluye la app principal)
- Pruebas de:
  - Constructores válidos e inválidos
  - Manejo de excepciones
  - Métodos de negocio
  - equals() y hashCode()
  - Casos límite
 
 <img width="747" height="146" alt="Captura de pantalla 2026-02-23 a la(s) 5 17 57 p m" src="https://github.com/user-attachments/assets/1101b2a4-3b91-4bbb-95f2-e549cccbff87" />

  
---

## Tecnologías utilizadas

- Java 17+
- JUnit 5
- Eclipse IDE
- Git & GitHub

---

## Documentación

La documentación del proyecto fue generada con JavaDoc.

Para visualizarla, abrir:

doc/index.html

