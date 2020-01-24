package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public class Sesiones {

	private Sesion[] coleccionSesiones;
	private int capacidad;
	private int tamano;

	public Sesiones(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		tamano = 0;
		this.capacidad = capacidad;
		coleccionSesiones = new Sesion[capacidad];
	}

	public Sesion[] get() {
		return copiaProfundaSesiones();
	}

	private Sesion[] copiaProfundaSesiones() {
		Sesion[] copiaSesion = new Sesion[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaSesion[i] = new Sesion(coleccionSesiones[i]);
		}
		return copiaSesion;
	}

	public Sesion[] get(Tutoria tutoria) {
		if (tutoria == null) {
			throw new IllegalArgumentException("ERROR: tutoria nula.");
		}
		int j = 0;
		Sesion[] sesionesTutoria = new Sesion[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionSesiones[i].getTutoria().equals(tutoria)) {
				sesionesTutoria[j++] = new Sesion(coleccionSesiones[i]);
			}
		}
		return sesionesTutoria;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	public void insertar(Sesion sesion) throws OperationNotSupportedException {
		int indice = buscarIndice(sesion);

		if (sesion == null) {
			throw new NullPointerException("ERROR: No se puede insertar una sesión nula.");
		}

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más sesiones.");
		}

		if (tamanoSuperado(indice)) {
			coleccionSesiones[indice] = new Sesion(sesion);
			tamano += 1;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una sesión con esa fecha.");

		}
	}

	private int buscarIndice(Sesion sesion) {
		boolean esEncontrado = false;
		int indice = 0;

		while (!tamanoSuperado(indice) && !esEncontrado) {
			if (coleccionSesiones[indice].equals(sesion)) {
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

	public Sesion buscar(Sesion sesion) {
		int indice = buscarIndice(sesion);
		if (sesion == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una sesión nula.");
		}
		if (!tamanoSuperado(indice)) {
			return new Sesion(coleccionSesiones[indice]);
		} else {
			return null;
		}

	}

	public void borrar(Sesion sesion) throws OperationNotSupportedException {
		int indice = buscarIndice(sesion);
		if (sesion == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una sesión nula.");
		}
		if (buscar(sesion) != null) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna sesión con esa fecha.");
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < coleccionSesiones.length - 1; i++) {
			if (tamano > 0) {
				coleccionSesiones[i] = coleccionSesiones[i + 1];
			} else {
				coleccionSesiones[i] = null;
			}
		}
	}

}
