package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;

public class Citas {

	private Cita[] coleccionCitas;
	private int capacidad;
	private int tamano;

	public Citas(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		tamano = 0;
		this.capacidad = capacidad;
		coleccionCitas = new Cita[capacidad];
	}

	public Cita[] get() {
		return copiaProfundaCitas();
	}

	private Cita[] copiaProfundaCitas() {
		Cita[] copiaCita = new Cita[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaCita[i] = new Cita(coleccionCitas[i]);
		}
		return copiaCita;
	}

	public Cita[] get(Sesion sesion) {
		if (sesion == null) {
			throw new NullPointerException("ERROR: La sesión no puede ser nula.");
		}
		int j = 0;
		Cita[] citasSesion = new Cita[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionCitas[i].getSesion().equals(sesion)) {
				citasSesion[j++] = new Cita(coleccionCitas[i]);
			}
		}
		return citasSesion;
	}

	public Cita[] get(Alumno alumno) {
		if (alumno == null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		int j = 0;
		Cita[] citasAlumno = new Cita[capacidad];
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionCitas[i].getAlumno().equals(alumno)) {
				citasAlumno[j++] = new Cita(coleccionCitas[i]);
			}
		}
		return citasAlumno;
	}

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void insertar(Cita cita) throws OperationNotSupportedException {
		int indice = buscarIndice(cita);

		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más citas.");
		}

		if (tamanoSuperado(indice)) {
			coleccionCitas[indice] = new Cita(cita);
			tamano += 1;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita con esa hora.");

		}
	}

	private int buscarIndice(Cita cita) {
		boolean esEncontrado = false;
		int indice = 0;

		while (!tamanoSuperado(indice) && !esEncontrado) {
			if (coleccionCitas[indice].equals(cita)) {
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

	public Cita buscar(Cita cita) {
		int indice = buscarIndice(cita);
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una cita nula.");
		}
		if (!tamanoSuperado(indice)) {
			return new Cita(coleccionCitas[indice]);
		} else {
			return null;
		}

	}

	public void borrar(Cita cita) throws OperationNotSupportedException {
		int indice = buscarIndice(cita);
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}
		if (buscar(cita) != null) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita con esa hora.");
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < coleccionCitas.length - 1; i++) {
			if (tamano > 0) {
				coleccionCitas[i] = coleccionCitas[i + 1];
			} else {
				coleccionCitas[i] = null;
			}
		}
	}

}
