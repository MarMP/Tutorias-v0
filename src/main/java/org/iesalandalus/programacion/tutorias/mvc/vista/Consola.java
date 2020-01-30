package org.iesalandalus.programacion.tutorias.mvc.vista;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private Consola() {
	}

	public static void mostrarMenu() {
		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}

	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		String formatoStr = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(formatoStr, 0).replace("0", "-"));
	}

	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.print("\nElige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}

	public static Alumno leerAlumno() {
		System.out.print("Introduce el nombre del alumno: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduce el correo del alumno: ");
		String correo = Entrada.cadena();
		Alumno alumno = null;
		alumno = new Alumno(nombre, correo);

		return alumno;
	}

	public static Alumno leerAlumnoFicticio() {
		System.out.print("Introduce el correo del alumno: ");
		return Alumno.getAlumnoFicticio(Entrada.cadena());
	}

	public static Profesor leerProfesor() {
		System.out.print("Introduce el nombre del profesor: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduce el DNI del profesor: ");
		String dni = Entrada.cadena();
		System.out.print("Introduce el correo del profesor: ");
		String correo = Entrada.cadena();
		Profesor profesor = null;
		profesor = new Profesor(nombre, dni, correo);
		return profesor;
	}

	public static Profesor leerProfesorFicticio() {
		System.out.print("Introduce el dni del profesor: ");
		return Profesor.getProfesorFicticio(Entrada.cadena());
	}

	public static Tutoria leerTutoria() {
		Profesor profesor = leerProfesorFicticio();
		System.out.print("Introduce el nombre de la tutoria: ");
		String nombreTutoria = Entrada.cadena();
		Tutoria tutoria = null;
		tutoria = new Tutoria(profesor, nombreTutoria);
		return tutoria;
	}

	public static Sesion leerSesion() {
		Tutoria tutoria = leerTutoria();
		LocalDate fecha = null;
		String cadenaFormato = "dd/MM/yyyy";
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(cadenaFormato);

		try {
			System.out.printf("Introduzca fecha para la sesión (%s): " , cadenaFormato);
			fecha = LocalDate.parse(Entrada.cadena(), formatoFecha);
		} catch (DateTimeParseException e) {
			System.out.println("ERROR: El formato de la fecha no es el correcto.");
		}

		LocalTime horaInicio = null;
		LocalTime horaFin = null;
		String cadenaHora = "HH:mm";
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern(cadenaHora);

		try {
			System.out.printf("Introduzca hora inicio sesión (%s): " , cadenaHora);
			horaInicio = LocalTime.parse(Entrada.cadena(), formatoHora);
			System.out.printf("Introduzca hora fin de sesión (%s): " , cadenaHora);
			horaFin = LocalTime.parse(Entrada.cadena(), formatoHora);
		} catch (DateTimeParseException e) {
			System.out.println("ERROR: El formato de la hora no es el correcto.");
		}

		System.out.print("Introduzca los minutos de duración de la sesión: ");
		int minutosDuracion = Entrada.entero();

		Sesion sesion = null;
		sesion = new Sesion(tutoria, fecha, horaInicio, horaFin, minutosDuracion);
		return sesion;
	}

	public static Sesion leerSesionFicticia() {
		LocalDate fecha = null;
		String cadenaFormato = "dd/MM/yyyy";
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(cadenaFormato);

		try {
			System.out.printf("Introduzca fecha para la sesión (%s): " , cadenaFormato);
			fecha = LocalDate.parse(Entrada.cadena(), formatoFecha);
		} catch (DateTimeParseException e) {
			System.out.println("ERROR: El formato de la fecha no es el correcto.");
		}
		return Sesion.getSesionFicticia(leerTutoria(), fecha);
	}
	
	public static Cita leerCita() {
		Cita cita = null;
		LocalTime hora = null;
		String cadenaHora = "HH:mm";
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern(cadenaHora);
		
		try {
			System.out.printf("Introduzca hora de la cita (%s): " , cadenaHora);
			hora = LocalTime.parse(Entrada.cadena(), formatoHora);
		} catch (DateTimeParseException e) {
			System.out.println("ERROR: El formato de la hora no es el correcto.");
		}
		cita = new Cita (leerAlumnoFicticio(), leerSesion(), hora);
		return cita;
	}

}
