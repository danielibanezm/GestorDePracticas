package modelo;

public class Alumno {
	
	
	private String alumno;
	private String centro;
	private String dni;
	private String valido;
	private String ss;
	private String ciclo;
	private String anio;
	private boolean eliminado;
	
	public Alumno(String alumno, String centro, String dni, String valido, String ss, String ciclo, String anio, boolean eliminado) {
		super();
		this.alumno = alumno;
		this.centro = centro;
		this.dni = dni;
		this.valido = valido;
		this.ss = ss;
		this.ciclo = ciclo;
		this.anio = anio;
		this.eliminado = eliminado;
	}
	
	public Alumno() {
		super();
		alumno = "";
		centro = "";
		dni = "";
		valido = "";
		ss = "";
		ciclo = "";
		anio = "";
		eliminado = false;
	}

	public String getAlumno() {
		return alumno;
	}

	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getValido() {
		return valido;
	}

	public void setValido(String valido) {
		this.valido = valido;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	@Override
	public String toString() {
		return "Alumno [alumno=" + alumno + ", centro=" + centro + ", dni=" + dni + ", valido=" + valido + ", ss=" + ss
				+ ", ciclo=" + ciclo + ", anio=" + anio + ", eliminado=" + eliminado + "]";
	}

	
	
	

}
