package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cita {

	private Alumno alumno;
	private Sesion sesion;
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	private LocalTime hora;

	public Cita(Alumno alumno, Sesion sesion, LocalTime hora) {
		setAlumno(alumno);
		setSesion(sesion);
		setHora(hora);
	}

	public Cita(Cita cita) {
		if (cita == null) {
			throw new NullPointerException("ERROR: No es posible copiar una cita nula.");
		}
		setAlumno(cita.getAlumno());
		setSesion(cita.getSesion());
		setHora(cita.getHora());
	}

	public Alumno getAlumno() {
		return new Alumno(alumno);
	}

	private void setAlumno(Alumno alumno) {
		if (alumno == null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		this.alumno = new Alumno(alumno);
	}

	public Sesion getSesion() {
		return new Sesion(sesion);
	}

	private void setSesion(Sesion sesion) {
		if (sesion == null) {
			throw new NullPointerException("ERROR: La sesión no puede ser nula.");
		}
		this.sesion = new Sesion(sesion);
	}

	public LocalTime getHora() {
		return hora;
	}

	private void setHora(LocalTime hora) {
		if (hora == null) {
			throw new NullPointerException("ERROR: La hora no puede ser nula.");
		}
		if (hora.isBefore(sesion.getHoraInicio())
				|| hora.isAfter(sesion.getHoraFin().minusMinutes(sesion.getMinutosDuracion()))) {
			throw new IllegalArgumentException(
					"ERROR: La hora debe estar comprendida entre la hora de inicio y fin de la sesión.");
		}
		if (((hora.toSecondOfDay() - sesion.getHoraInicio().toSecondOfDay()) / 60) % sesion.getMinutosDuracion() != 0) {
			throw new IllegalArgumentException(
					"ERROR: La hora debe comenzar en un múltiplo de los minutos de duración.");
		}
		this.hora = hora;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alumno, hora, sesion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Cita)) {
			return false;
		}
		Cita other = (Cita) obj;
		return Objects.equals(alumno, other.alumno) && Objects.equals(hora, other.hora)
				&& Objects.equals(sesion, other.sesion);
	}

	@Override
	public String toString() {
		return String.format("alumno=%s, sesion=%s, hora=%s" , alumno, sesion, hora.format(FORMATO_HORA));
	}

}
