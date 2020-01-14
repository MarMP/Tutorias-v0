package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profesor {
	private static final String ER_NOMBRE = "(?=.*\\s.+)(?![a-zA-Zñáéíóúü]\\s)(?!.*\\s[a-zA-Zñáéíóúü]\\s)(?!.*\\s[a-zA-Zñáéíóúü]$).[a-zA-Zñáéíóúü\\s]+";
	private static final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";
	private String nombre;
	private String dni;
	private String correo;

	public Profesor (String nombre, String dni, String correo) {
		setNombre(nombre);
		setDni(dni);
		setCorreo(correo);
	}
	
	public Profesor (Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No es posible copiar un profesor nulo.");
		}
		setNombre(profesor.nombre);
		setDni(profesor.dni);
		setCorreo(profesor.correo);
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
		cadenaMinus = cadenaMinus.toLowerCase(); // convierto la cadena a minúscula
		// System.out.println(cadenaMinus);
		cadenaMinus = cadenaMinus.trim(); // quito espacios en blanco iniciales y finales
		cadenaMinus = cadenaMinus.replaceAll(" +", " "); // quita espacios de mas
		char[] cadCaracter = cadenaMinus.toCharArray(); // convierte la cadena en un array de caracteres
		cadCaracter[0] = Character.toUpperCase(cadCaracter[0]); // primera letra mayúscula

		// Recorre el array de char
		for (int i = 0; i < cadenaMinus.length() - 1; i++)
			// Si encuentra un espacio o punto, suma 1 a la posicion de "i" que encuentre y
			// convierte en mayus la primera letra
			if (cadCaracter[i] == ' ' || cadCaracter[i] == '.') {
				cadCaracter[i + 1] = Character.toUpperCase(cadCaracter[i + 1]);
			}
		String nombreFormateado = new String(cadCaracter);

		return nombreFormateado;
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		if (dni.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		if (!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		if (comprobarLetraDni(dni) == false) {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}
		this.dni = dni;
	}
	
	private static boolean comprobarLetraDni(String dni) {

		char[] letra = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };
		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile(ER_DNI);
		matcher = pattern.matcher(dni);
		boolean letraValida = false;
		int posicionLetra;

		while (matcher.find()) {
			String numeroDni = matcher.group(1); // grupo 1 de la expresion
			posicionLetra = Integer.parseInt(numeroDni) % 23; // calcula el resto que sera la letra
			String letraDni = matcher.group(2);
			//System.out.println("estoy aqui");
			char caracter = letraDni.charAt(0);
			if (caracter == letra[posicionLetra]) {
				letraValida = true;
			} else {
				letraValida = false;
			}
		}

		return letraValida;
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
	
	public static Profesor getProfesorFicticio(String dni) {
		Profesor profesor = new Profesor("José Ramón", dni,"joseramon.jimenez@iesalandalus.org");
		return profesor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Profesor)) {
			return false;
		}
		Profesor other = (Profesor) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return "nombre=" + nombre +  " (" + getIniciales() + ")" + ", DNI=" + dni + ", correo=" + correo;
	}

	private String getIniciales() {
		String[] nombres = nombre.split(" "); // Genera array de String Maria del Mar --> ["María" "del" "Mar"]
		String iniciales = "";
		for (String nombre : nombres) {
			if (!nombre.equals("")) {
				iniciales = iniciales + nombre.charAt(0); // MdM
			}
		}
		return iniciales.toUpperCase();
	}

}
