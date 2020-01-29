package org.iesalandalus.programacion.tutorias.mvc.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.Controlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public class Vista {

	private Controlador controlador;

	public Vista() {
		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void comenzar() {
		Consola.mostrarCabecera("Gestión de tutorias para FP Semipresencial del IES Al-Ándalus");
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOridnal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	public void terminar() {
		controlador.terminar();
	}

	public void insertarAlumno() {
		Consola.mostrarCabecera("Insertar Alumno");
		try {
			controlador.insertar(Consola.leerAlumno());
			System.out.println("Alumno insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarAlumno() {
		Consola.mostrarCabecera("Buscar Alumno");
		Alumno alumno;
		try {
			alumno = controlador.buscar(Consola.leerAlumnoFicticio());
			String mensaje = (alumno != null) ? alumno.toString() : "No existe ese alumno.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAlumno() {
		Consola.mostrarCabecera("Borrar Alumno");
		try {
			controlador.borrar(Consola.leerAlumnoFicticio());
			System.out.println("Alumno borrado satisfactoriamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAlumnos() {
		Consola.mostrarCabecera("Listado de Alumnos");
		Alumno[] alumnos = controlador.getAlumnos();
		if (alumnos[0] != null) {
			for (Alumno alumno : alumnos) {
				if (alumno != null)
					System.out.println(alumno);
			}
		} else {
			System.out.println("No hay alumnos que mostrar.");
		}
	}

	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar Profesor");
		try {
			controlador.insertar(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar Profesor");
		Profesor profesor;
		try {
			profesor = controlador.buscar(Consola.leerProfesorFicticio());
			String mensaje = (profesor != null) ? profesor.toString() : "No existe dicho profesor.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		try {
			controlador.borrar(Consola.leerProfesorFicticio());
			System.out.println("Profesor borrado satisfactoriamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarProfesores() {
		Consola.mostrarCabecera("Listado de Profesores");
		Profesor[] profesores = controlador.getProfesores();
		if (profesores[0] != null) {
			for (Profesor profesor : profesores) {
				if (profesor != null)
					System.out.println(profesor);
			}
		} else {
			System.out.println("No hay profesores que mostrar.");
		}
	}

	public void insertarTutoria() {
		Consola.mostrarCabecera("Insertar tutoría");
		try {
			controlador.insertar(Consola.leerTutoria());
			System.out.println("Tutoría insertada correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarTutoria() {
		Consola.mostrarCabecera("Buscar tutoría");
		Tutoria tutoria;
		try {
			tutoria = controlador.buscar(Consola.leerTutoria());
			String mensaje = (tutoria != null) ? tutoria.toString() : "No existe dicha tutoría.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarTutoria() {
		Consola.mostrarCabecera("Borrar tutoría");
		try {
			controlador.borrar(Consola.leerTutoria());
			System.out.println("Tutoría borrada satisfactoriamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarTutorias() {
		Consola.mostrarCabecera("Listado de tutorías");
		Tutoria[] tutorias = controlador.getTutorias();
		if (tutorias[0] != null) {
			for (Tutoria tutoria : tutorias) {
				if (tutoria != null)
					System.out.println(tutoria);
			}
		} else {
			System.out.println("No hay tutorías que mostrar.");
		}
	}

	public void listarTutoriasProfesor() {
		Consola.mostrarCabecera("Listado de tutorías por profesor");
		Tutoria[] tutorias = controlador.getTutorias(Consola.leerProfesorFicticio());
		if (tutorias[0] != null) {
			for (Tutoria tutoria : tutorias) {
				if (tutoria != null)
					System.out.println(tutoria);
			}
		} else {
			System.out.println("No hay tutorías para ese profesor que mostrar.");
		}
	}

	public void insertarSesion() {
		Consola.mostrarCabecera("Insertar Sesión");
		try {
			Sesion sesion = Consola.leerSesion();
			controlador.insertar(sesion);
			System.out.println("Sesión insertada correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarSesion() {
		Consola.mostrarCabecera("Buscar Sesión");
		Sesion sesion;
		try {
			sesion = controlador.buscar(Consola.leerSesionFicticia());
			String mensaje = (sesion != null) ? sesion.toString() : "No existe dicha sesión.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarSesion() {
		Consola.mostrarCabecera("Borrar sesión");
		try {
			controlador.borrar(Consola.leerSesionFicticia());
			System.out.println("Sesión borrada satisfactoriamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarSesiones() {
		Consola.mostrarCabecera("Listado de sesiones");
		Sesion[] sesiones = controlador.getSesiones();
		if (sesiones[0] != null) {
			for (Sesion sesion : sesiones) {
				if (sesion != null)
					System.out.println(sesion);
			}
		} else {
			System.out.println("No hay sesiones que mostrar.");
		}
	}

	public void listarSesionesTutoria() {
		Consola.mostrarCabecera("Listado de Sesiones por Tutoria");
		Sesion[] sesiones = controlador.getSesiones(Consola.leerTutoria());
		if (sesiones[0] != null) {
			for (Sesion sesion : sesiones) {
				if (sesion != null)
					System.out.println(sesion);
			}
		} else {
			System.out.println("No hay sesiones, para dicha tutoría, que mostrar.");
		}
	}

	public void insertarCita() {
		Consola.mostrarCabecera("Insertar cita");
		try {
			controlador.insertar(Consola.leerCita());
			System.out.println("Cita insertada correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarCita() {
		Consola.mostrarCabecera("Buscar cita");
		Cita cita;
		try {
			cita = controlador.buscar(Consola.leerCita());
			String mensaje = (cita != null) ? cita.toString() : "No existe esa cita.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarCita() {
		Consola.mostrarCabecera("Borrar cita");
		try {
			controlador.borrar(Consola.leerCita());
			System.out.println("Cita borrada satisfactoriamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarCitas() {
		Consola.mostrarCabecera("Listado de citas");
		Cita[] citas = controlador.getCitas();
		if (citas[0] != null) {
			for (Cita cita : citas) {
				if (cita != null)
					System.out.println(cita);
			}
		} else {
			System.out.println("No hay citas que mostrar.");
		}
	}

	public void listarCitasSesion() {
		Consola.mostrarCabecera("Listado de citas por sesión");
		Cita[] citas = controlador.getCitas(Consola.leerSesionFicticia());
		if (citas[0] != null) {
			for (Cita cita : citas) {
				if (cita != null)
					System.out.println(cita);
			}
		} else {
			System.out.println("No hay citas, para dicha sesión, que mostrar.");
		}
	}

	public void listarCitasAlumno() {
		Consola.mostrarCabecera("Listado de citas por alumno");
		Cita[] citas = controlador.getCitas(Consola.leerAlumnoFicticio());
		if (citas[0] != null) {
			for (Cita cita : citas) {
				if (cita != null)
					System.out.println(cita);
			}
		} else {
			System.out.println("No hay citas, para dicho alumno, que mostrar.");
		}
	}

}
