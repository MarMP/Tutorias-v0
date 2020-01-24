package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

public class Profesores {
	private Profesor[] coleccionProfesores;
	private int capacidad;
	private int tamano;

	public Profesores(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		tamano = 0;
		this.capacidad = capacidad;
		coleccionProfesores = new Profesor[capacidad];

	}

	public Profesor[] get() {
		return copiaProfundaProfesores();
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	private Profesor[] copiaProfundaProfesores() {
		Profesor[] copiaProfesores = new Profesor[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaProfesores[i] = new Profesor(coleccionProfesores[i]);
		}
		return copiaProfesores;

	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		int indice = buscarIndice(profesor);

		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		}

		if (tamanoSuperado(indice)) {
			coleccionProfesores[indice] = new Profesor(profesor);
			tamano += 1;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese DNI.");

		}
	}

	private int buscarIndice(Profesor profesor) {
		boolean esEncontrado = false;
		int indice = 0;

		while (!tamanoSuperado(indice) && !esEncontrado) {
			if (coleccionProfesores[indice].equals(profesor)) {
				esEncontrado = true;
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

	public Profesor buscar(Profesor profesor) {
		int indice = buscarIndice(profesor);
		if (profesor == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un profesor nulo.");
		}
		if (!tamanoSuperado(indice)) {
			return new Profesor(coleccionProfesores[indice]);
		} else {
			return null;
		}

	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		int indice = buscarIndice(profesor);
		if (profesor == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un profesor nulo.");
		}
		if (buscar(profesor) != null) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese DNI.");
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < coleccionProfesores.length - 1; i++) {
			if (tamano > 0) {
				coleccionProfesores[i] = coleccionProfesores[i + 1];
			} else {
				coleccionProfesores[i] = null;
			}
		}
	}

}
