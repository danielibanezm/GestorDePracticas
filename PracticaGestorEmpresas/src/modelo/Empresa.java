package modelo;

public class Empresa {
	
	private int id_empresa;
	private String CIF;
	private String duenno;
	private String nombre_empresa;
	private String telefono_empresa;
	private String direccion_empresa;
	private String email_empresa;
	private String tutor_empresa;
	private String contacto_empresa;
	private String solicita;
	private boolean eliminado;
	
	public Empresa(int id_empresa, String CIF, String duenno, String nombre_empresa, String telefono_empresa,
			String direccion_empresa, String email_empresa, String tutor_empresa, String contacto_empresa,
			String solicita, boolean eliminado) {
		super();
		this.id_empresa = id_empresa;
		this.CIF = CIF;
		this.duenno = duenno;
		this.nombre_empresa = nombre_empresa;
		this.telefono_empresa = telefono_empresa;
		this.direccion_empresa = direccion_empresa;
		this.email_empresa = email_empresa;
		this.tutor_empresa = tutor_empresa;
		this.contacto_empresa = contacto_empresa;
		this.solicita = solicita;
		this.eliminado = eliminado;
	}
	
	public Empresa() {
		id_empresa = 0;
		CIF = "";
		duenno = "";
		nombre_empresa = "";
		telefono_empresa = "";
		direccion_empresa = "";
		email_empresa = "";
		tutor_empresa = "";
		contacto_empresa = "";
		solicita = "";
		eliminado = false;	
	}

	public int getIdEmpresa() {
		return id_empresa;
	}

	public void setIdEmpresa(int idUsuario) {
		this.id_empresa = idUsuario;
	}

	public String getCIF() {
		return CIF;
	}

	public void setCIF(String CIF) {
		CIF = CIF;
	}

	public String getDuenno() {
		return duenno;
	}

	public void setDuenno(String duenno) {
		this.duenno = duenno;
	}

	public String getNombre_empresa() {
		return nombre_empresa;
	}

	public void setNombre_empresa(String nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}

	public String getTelefono_empresa() {
		return telefono_empresa;
	}

	public void setTelefono_empresa(String telefono_empresa) {
		this.telefono_empresa = telefono_empresa;
	}

	public String getDireccion_empresa() {
		return direccion_empresa;
	}

	public void setDireccion_empresa(String direccion_empresa) {
		this.direccion_empresa = direccion_empresa;
	}

	public String getEmail_empresa() {
		return email_empresa;
	}

	public void setEmail_empresa(String email_empresa) {
		this.email_empresa = email_empresa;
	}

	public String getTutor_empresa() {
		return tutor_empresa;
	}

	public void setTutor_empresa(String tutor_empresa) {
		this.tutor_empresa = tutor_empresa;
	}

	public String getContacto_empresa() {
		return contacto_empresa;
	}

	public void setContacto_empresa(String contacto_empresa) {
		this.contacto_empresa = contacto_empresa;
	}

	public String getSolicita() {
		return solicita;
	}

	public void setSolicita(String solicita) {
		this.solicita = solicita;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	
	
	
}
