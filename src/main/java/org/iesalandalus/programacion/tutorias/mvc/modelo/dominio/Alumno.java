package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.util.Objects;

public class Alumno {

	private static final String ER_NOMBRE = "^([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\']+[\\s])+([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])+[\\s]?([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])?$";
	private static final String PREFIJO_EXPEDIENTE = "SP_";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";
	private static int ultimoIdentificador = 0;
	private String nombre;
	private String correo;
	private String expediente;

	public Alumno(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
		incrementaUltimoIdentificador();
		setExpediente(); 
	}

	public Alumno(Alumno alumno) {
		if (alumno == null) {
			throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
		}
		setNombre(alumno.getNombre());
		setCorreo(alumno.getCorreo());
		this.expediente = alumno.getExpediente();
	}

	public static Alumno getAlumnoFicticio(String correo) {
		return new Alumno("Mar Membrive", correo);
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		} 
		this.nombre = formateaNombre(nombre);

		if (!this.nombre.matches(ER_NOMBRE)) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}		
	}

	private String formateaNombre(String nombre) {
		String cadenaMinus = nombre;
		cadenaMinus = cadenaMinus.toLowerCase(); 
		cadenaMinus = cadenaMinus.trim(); 
		cadenaMinus = cadenaMinus.replaceAll(" +", " "); 
		char[] cadCaracter = cadenaMinus.toCharArray(); 
		cadCaracter[0] = Character.toUpperCase(cadCaracter[0]); 
		
		for (int i = 0; i < cadenaMinus.length() - 1; i++)
			if (cadCaracter[i] == ' ' || cadCaracter[i] == '.') {
				cadCaracter[i + 1] = Character.toUpperCase(cadCaracter[i + 1]);
			}
		return new String(cadCaracter);
	}

	public String getCorreo() {
		return correo;
	}

	private void setCorreo(String correo) {
		if (correo == null) {
			throw new NullPointerException("ERROR: El correo no puede ser nulo.");
		}
		if (correo.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El formato del correo no es válido.");
		}
		if (!correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("ERROR: El formato del correo no es válido.");
		}
		this.correo = correo;
	}

	public String getExpediente() {
		return expediente;
	}

	private void setExpediente() {
		expediente = PREFIJO_EXPEDIENTE + getIniciales() + "_" + ultimoIdentificador; 
	}

	private static void incrementaUltimoIdentificador() {
		ultimoIdentificador++;
	}

	private String getIniciales() {
		String[] nombres = nombre.split(" "); 
		String iniciales = "";
		for (String nombre : nombres) {
			if (!nombre.equals("")) {
				iniciales = iniciales + nombre.charAt(0); 
			}
		}
		return iniciales.toUpperCase();
	}

	@Override
	public int hashCode() {
		return Objects.hash(correo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Alumno)) {
			return false;
		}
		Alumno other = (Alumno) obj;
		return Objects.equals(correo, other.correo);
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + " (" + getIniciales() + ")," + " correo=" + correo + ", expediente=" + expediente;
	}

}
