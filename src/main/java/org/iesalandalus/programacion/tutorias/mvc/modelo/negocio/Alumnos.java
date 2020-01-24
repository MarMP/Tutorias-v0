package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;

public class Alumnos {

	private Alumno[] coleccionAlumnos;
	private int capacidad;
	private int tamano;

	public Alumnos(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		tamano = 0;
		this.capacidad = capacidad;
		coleccionAlumnos = new Alumno[capacidad];

	}

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public Alumno[] get() {
		return copiaProfundaAlumno();
	}

	private Alumno[] copiaProfundaAlumno() {
		Alumno[] copiaAlumnos = new Alumno[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaAlumnos[i] = new Alumno(coleccionAlumnos[i]);
		}
		return copiaAlumnos;
	}

	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		int indice = buscarIndice(alumno);

		if (alumno == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
		}

		if (tamanoSuperado(indice)) {
			coleccionAlumnos[indice] = new Alumno(alumno);
			tamano ++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese expediente.");

		}
	}

	private int buscarIndice(Alumno alumno) {
		boolean esEncontrado = false;
		int indice = 0;

		while (!tamanoSuperado(indice) && !esEncontrado) {
			if (coleccionAlumnos[indice].equals(alumno)) {
				esEncontrado = true; // es un alumno igual a otro
			} else {
				indice++;
			}

		}
		return indice;
	}

	private boolean tamanoSuperado(int indice) {
		return indice >= tamano;
	}

	private boolean capacidadSuperada(int indice) {
		return indice >= capacidad;
	}

	public Alumno buscar(Alumno alumno) {
		int indice = buscarIndice(alumno);
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");
		}
		if (!tamanoSuperado(indice)) {
			return new Alumno(coleccionAlumnos[indice]);
		} else {
			return null;
		}

	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		int indice = buscarIndice(alumno);
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
		}
		if (buscar(alumno) != null) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese expediente.");
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < coleccionAlumnos.length - 1; i++) {
			if (tamano > 0) {
				coleccionAlumnos[i] = coleccionAlumnos[i + 1];
			} else {
				coleccionAlumnos[i] = null;
			}
		}
	}

}
