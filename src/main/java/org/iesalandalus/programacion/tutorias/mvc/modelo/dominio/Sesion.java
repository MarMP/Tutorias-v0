package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Sesion {
	private static final LocalTime HORA_COMIENZO_CLASES = LocalTime.parse("16:00");
	private static final LocalTime HORA_FIN_CLASES = LocalTime.parse("22:15");
	public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	private LocalDate fecha;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private int minutosDuracion;
	private Tutoria tutoria;

	public Sesion(Tutoria tutoria, LocalDate fecha, LocalTime horaInicio, LocalTime horafin, int minutosDuracion) {
		setTutoria(tutoria);
		setFecha(fecha);
		setHoraInicio(horaInicio);
		setHoraFin(horafin);
		setMinutosDuracion(minutosDuracion);
		comprobarValidezSesion();

	}

	public Sesion(Sesion sesion) {
		if (sesion == null) {
			throw new NullPointerException("ERROR: No es posible copiar una sesión nula.");
		}
		setTutoria(sesion.getTutoria());
		setFecha(sesion.getFecha());
		setHoraInicio(sesion.getHoraInicio());
		setHoraFin(sesion.getHoraFin());
		setMinutosDuracion(sesion.getMinutosDuracion());
	}

	public Tutoria getTutoria() {
		return new Tutoria(tutoria);
	}

	private void setTutoria(Tutoria tutoria) {
		if (tutoria == null) {
			throw new NullPointerException("ERROR: La tutoría no puede ser nula.");
		}
		this.tutoria = new Tutoria(tutoria);
	}

	public LocalDate getFecha() {
		return fecha;
	}

	private void setFecha(LocalDate fecha) {
		if (fecha == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}
		this.fecha = fecha;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	private void setHoraInicio(LocalTime horaInicio) {
		if (horaInicio == null) {
			throw new NullPointerException("ERROR: La hora de inicio no puede ser nula.");
		}
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	private void setHoraFin(LocalTime horaFin) {
		if (horaFin == null) {
			throw new NullPointerException("ERROR: La hora de fin no puede ser nula.");
		}
		this.horaFin = horaFin;
	}

	public int getMinutosDuracion() {
		return minutosDuracion;
	}

	private void setMinutosDuracion(int minutosDuracion) {
		if (minutosDuracion == 0) {
			throw new IllegalArgumentException("ERROR: Los minutos de duración no son válidos.");
		}
		this.minutosDuracion = minutosDuracion;
	}

	private void comprobarValidezSesion() {
		if (horaInicio.isBefore(HORA_COMIENZO_CLASES) || horaInicio.isAfter(HORA_FIN_CLASES)) {
			throw new IllegalArgumentException("ERROR: La hora de inicio no es válida.");
		}
		if (horaInicio.equals(HORA_FIN_CLASES)) {
			throw new IllegalArgumentException("ERROR: La hora de inicio no es válida.");
		}
		if (horaFin.isBefore(HORA_COMIENZO_CLASES) || horaFin.isAfter(HORA_FIN_CLASES)) {
			throw new IllegalArgumentException("ERROR: La hora de fin no es válida.");
		}
		if (horaInicio.equals(horaFin)) {
			throw new IllegalArgumentException("ERROR: Las hora para establecer la sesión no son válidas.");
		}
		if (horaInicio.isAfter(horaFin)) {
			throw new IllegalArgumentException("ERROR: Las hora para establecer la sesión no son válidas.");
		}
		if (fecha.isEqual(LocalDate.now()) || fecha.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: Las sesiones de deben planificar para fechas futuras.");
		}
		long intervalo = Duration.between(horaInicio, horaFin).toMinutes();
		if (!((intervalo % minutosDuracion) == 0)) {
			throw new IllegalArgumentException(
					"ERROR: Los minutos de duración no es divisor de los minutos establecidos para toda la sesión.");
		}

	}

	public static Sesion getSesionFicticia(Tutoria tutoria, LocalDate fecha) {
		return new Sesion(tutoria, fecha, LocalTime.of(16, 0), LocalTime.of(19, 0), 30);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, tutoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Sesion)) {
			return false;
		}
		Sesion other = (Sesion) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(tutoria, other.tutoria);
	}

	@Override
	public String toString() {
		return String.format("tutoria=%s, fecha=%s, horaInicio=%s, horaFin=%s, minutosDuracion=%s", tutoria,
				fecha.format(FORMATO_FECHA), horaInicio.format(FORMATO_HORA), horaFin.format(FORMATO_HORA),
				minutosDuracion);

	}

}
