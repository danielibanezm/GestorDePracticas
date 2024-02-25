package modelo;

public class Usuario {
	private int idUsuario;
	private boolean primeraVez;
	private String email;
	private String perfil;
	private int idCentro;
	private boolean existe;
	
	public Usuario() {
		existe = false;
	}
	public Usuario(int idUsuario, boolean primeraVez, String email, String perfil, int idCentro) {
		this.idUsuario = idUsuario;
		this.primeraVez = primeraVez;
		this.email = email;
		this.perfil = perfil;
		this.idCentro = idCentro;
	}
	public boolean isExiste() {
		return existe;
	}
	public void setExiste(boolean existe) {
		this.existe = existe;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public boolean isPrimeraVez() {
		return primeraVez;
	}
	public void setPrimeraVez(boolean primeraVez) {
		this.primeraVez = primeraVez;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public int getIdCentro() {
		return idCentro;
	}
	public void setIdCentro(int idCentro) {
		this.idCentro = idCentro;
	}
	
	
}
