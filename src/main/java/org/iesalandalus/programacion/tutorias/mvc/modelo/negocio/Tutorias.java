package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public class Tutorias {
	
	private Tutoria[] coleccionTutorias;
	private int capacidad;
	private int tamano;
	
	public Tutorias(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		tamano = 0;
		this.capacidad = capacidad;
		coleccionTutorias = new Tutoria[capacidad];

	}

	public Tutoria[] get() {
		return copiaProfundaTutorias();
	}
	public Tutoria[] get(Profesor profesor) {
		if (profesor == null) {
			throw new IllegalArgumentException("ERROR: profesor nulo.");
		}
		int j = 0;
		Tutoria[] tutoriasProfesor = new Tutoria [capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionTutorias[i].getProfesor().equals(profesor)) {
				tutoriasProfesor[j++] = new Tutoria (coleccionTutorias[i]);
			}
		}
		return tutoriasProfesor;
	} 

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	private Tutoria[] copiaProfundaTutorias() {
		Tutoria[] copiaTutorias = new Tutoria[capacidad];
		for (int i = 0;!tamanoSuperado(i); i++) {
			copiaTutorias[i] = new Tutoria(coleccionTutorias[i]);
		}
		return copiaTutorias;

	}

	public void insertar(Tutoria tutoria) throws OperationNotSupportedException {
		int indice = buscarIndice(tutoria);

		if (tutoria == null) {
			throw new NullPointerException("ERROR: No se puede insertar una tutoría nula.");
		}

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más tutorías.");
		}

		if (tamanoSuperado(indice)) {
			coleccionTutorias[indice] = new Tutoria(tutoria);
			tamano += 1;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una tutoría con ese identificador.");

		}
	}

	private int buscarIndice(Tutoria tutoria) {
		boolean esEncontrado = false;
		int indice = 0;

		while (!tamanoSuperado(indice) && !esEncontrado) {
			if (coleccionTutorias[indice].equals(tutoria)) {
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

	public Tutoria buscar(Tutoria tutoria) {
		int indice = buscarIndice(tutoria);
		if (tutoria == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una tutoría nula.");
		}
		if (!tamanoSuperado(indice)) {
			return new Tutoria(coleccionTutorias[indice]);
		} else {
			return null;
		}

	}

	public void borrar(Tutoria tutoria) throws OperationNotSupportedException {
		int indice = buscarIndice(tutoria);
		if (tutoria == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una tutoría nula.");
		}
		if (buscar(tutoria) != null) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna tutoría con ese identificador.");
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < coleccionTutorias.length - 1; i++) {
			if (tamano > 0) {
				coleccionTutorias[i] = coleccionTutorias[i + 1];
			} else {
				coleccionTutorias[i] = null;
			}
		}
	}

}
