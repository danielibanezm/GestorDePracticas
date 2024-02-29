package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Blob;

public class Consultas {
	private Date hoy = Date.from(Instant.now());
	private String baseDeDatos = "jdbc:mysql://localhost/gestor_practicas";
	private String user = "root";
	private String contrasenna = "";

	public ArrayList<Usuario> tomaTodosUsuarios() {
		Usuario aux;
		ArrayList<Usuario> arrlUsuarios = new ArrayList<>();
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery("select distinct * from Usuarios order by email");

			while (rs.next()) {
				aux = new Usuario();
				aux.setIdUsuario(rs.getInt("id_usuario"));
				aux.setIdCentro(rs.getInt("id_centro"));
				aux.setEmail(rs.getString("email"));
				aux.setPerfil(rs.getString("perfil"));
				aux.setPrimeraVez(rs.getBoolean("primer"));

				arrlUsuarios.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrlUsuarios;
	}

	public String cogeHistorico(int idUser) {
		String historico = "";
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			ResultSet rs = statement.executeQuery("select distinct material from Otros where id_usuario = " + idUser);
			if (rs.next()) {
				historico = rs.getString("material");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return historico;
	}

	public void actualizarHistorico(String nuevoHistorico, int idUsuario) {
		Connection conexion = null;
		Statement statement = null;
		if (nuevoHistorico.isBlank()) {
			System.out.println("No se pueden cosas vacias");
			return;
		}
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			int valor = statement.executeUpdate(
					"update otros set material = '" + nuevoHistorico + "' where id_usuario = " + idUsuario);
			if (valor == 1) {
				System.out.println("Todo correcto master");
			} else {
				System.out.println("Uy Uy Uy :(");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void actualizarPrimeraVez(int idUsuario) {
		Connection conexion = null;
		Statement statement = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			int valor = statement.executeUpdate("update usuarios set primer = 0 where id_usuario = " + idUsuario);
			if (valor == 1) {
				System.out.println("Todo correcto master");
			} else {
				System.out.println("Uy Uy Uy :(");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void rellenarUsuarios(DefaultTableModel tabla, int idCentro) {
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement
					.executeQuery("select distinct * from Usuarios WHERE eliminado != 1 AND id_centro = " + idCentro);

			while (rs.next()) {
				tabla.addRow(new Object[] { rs.getInt("id_usuario"), rs.getInt("id_centro"), rs.getString("email"),
						rs.getString("perfil") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertarUsuario(String email, String perfil, int idCentro) {
		Connection conexion = null;
		Statement statement = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			int valor = statement
					.executeUpdate("INSERT INTO usuarios (id_centro, email, primer, perfil, eliminado) VALUES ("
							+ idCentro + ", '" + email + "', 1, '" + perfil + "', 0)");
			if (valor == 1) {
				System.out.println("Todo correcto master");
			} else {
				System.out.println("Uy Uy Uy :(");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void borradoLogicoUsuario(int idUsuario) {
		Connection conexion = null;
		Statement statement = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			int valor = statement.executeUpdate("update usuarios set eliminado = 1 where id_usuario = " + idUsuario);
			if (valor == 1) {
				System.out.println("Todo correcto master");
			} else {
				System.out.println("Uy Uy Uy :(");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void actualizarUsuarios(String nuevoEmail, String nuevoPerfil, int idUsuario) {
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			String update = "UPDATE usuarios SET email = '" + nuevoEmail + "', perfil = '" + nuevoPerfil
					+ "' WHERE id_usuario = " + idUsuario + ";";
			int valor = statement.executeUpdate(update);
			if (valor == 1) {
				System.out.println("Todo correcto master");
			} else {
				System.out.println("Uy Uy Uy :(");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<String> cogeNombreCentros() {
		ArrayList<String> arrlCentros = new ArrayList<>();
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery("SELECT nombre FROM centro WHERE eliminado != 1;");

			while (rs.next()) {
				arrlCentros.add(rs.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrlCentros;
	}

	public int cogeIdCentro(String nombre) {
		int id = -1;
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT id_centro FROM centro WHERE eliminado != 1 AND nombre = '" + nombre + "';");

			if (rs.next()) {
				id = rs.getInt("id_centro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	public void rellenarPracticasActuales(DefaultTableModel tabla, int idCentro) {
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			String consulta = "SELECT practica.id_practica, practica.id_anexo, empresa.nombre_empresa, alumno.nombre, practica.inicio, practica.final from practica, alumno, empresa WHERE practica.eliminado != 1 AND alumno.id_centro = "
					+ idCentro + ";";
			System.out.println(consulta);
			ResultSet rs = statement.executeQuery(consulta);

			while (rs.next()) {
				if(rs.getDate("inicio").after(hoy)) {
					tabla.addRow(new Object[] { rs.getInt("practica.id_practica"), rs.getInt("practica.id_anexo"),
							rs.getString("alumno.nombre"), rs.getString("empresa.nombre_empresa"), rs.getDate("inicio"), rs.getDate("final")});
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void rellenarPracticasTotales(DefaultTableModel tabla, int idCentro) {
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			String consulta = "SELECT practica.id_practica, practica.id_anexo, empresa.nombre_empresa, alumno.nombre, practica.inicio, practica.final from practica, alumno, empresa WHERE practica.eliminado != 1 AND alumno.id_centro = "
					+ idCentro + ";";
			System.out.println(consulta);
			ResultSet rs = statement.executeQuery(consulta);

			while (rs.next()) {
				tabla.addRow(new Object[] { rs.getInt("practica.id_practica"), rs.getInt("practica.id_anexo"),
						rs.getString("alumno.nombre"), rs.getString("empresa.nombre_empresa"), rs.getDate("inicio"), rs.getDate("final")});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public int insertarAnexos(File anexo2_1, File anexo2_2, File anexo3, File anexo8) {
		Connection conexion = null;
		Statement statement = null;
		FileInputStream anexo2_1Stream = null;
		FileInputStream anexo2_2Stream = null;
		FileInputStream anexo3Stream = null;
		FileInputStream anexo8Stream = null;
		int idAnexo = -1;
		String consulta = "SELECT MAX(id_anexo) FROM anexos";
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			anexo2_1Stream = new FileInputStream(anexo2_1);
			anexo2_2Stream = new FileInputStream(anexo2_2);
			anexo3Stream = new FileInputStream(anexo3);
			anexo8Stream = new FileInputStream(anexo8);
			PreparedStatement preparedStatement = conexion.prepareStatement(
					"INSERT INTO anexos (anexo_8, anexo_2_1, anexo_2_2, anexo_3, eliminado) VALUES(?, ?, ?, ?, ?)");
			preparedStatement.setBinaryStream(1, anexo8Stream);
			preparedStatement.setBinaryStream(2, anexo2_1Stream);
			preparedStatement.setBinaryStream(3, anexo2_2Stream);
			preparedStatement.setBinaryStream(4, anexo3Stream);
			preparedStatement.setInt(5, 0);
			int valor = preparedStatement.executeUpdate();
			if (valor == 1) {
				ResultSet rs = statement.executeQuery(consulta);
				if (rs.next()) {
					idAnexo = rs.getInt(1);
					System.out.println("Todo correcto master");
				}
			} else {
				System.out.println("Uy Uy Uy :(");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return idAnexo;
	}

	public int cogeUltimoIdAnexo() {
		Connection conexion = null;
		Statement statement = null;
		int idAnexo = -1;
		String consulta = "SELECT MAX(id_anexo) FROM anexos";
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			ResultSet rs = statement.executeQuery(consulta);
			if (rs.next()) {
				idAnexo = rs.getInt(1);
				System.out.println("Todo correcto master");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return idAnexo;
	}

	public void insertarPractica(int idAnexo, int idAlumno, int idEmpresa, Date fechaInicio, Date fechaFinal) {
		Connection conexion = null;
		Statement statement = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			int valor = statement.executeUpdate(
					"INSERT INTO practica (id_anexo, id_alumno, id_empresa, inicio, final, eliminado) VALUES ("
							+ idAnexo + ", " + idAlumno + ", " + idEmpresa + ", " + fechaInicio + ", " + fechaFinal
							+ ", 0)");
			if (valor == 1) {
				System.out.println("Todo correcto master");
			} else {
				System.out.println("Uy Uy Uy :(");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rellenarCentros(DefaultTableModel tabla) {
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery("select * from centro WHERE eliminado != 1;");

			while (rs.next()) {
				tabla.addRow(new Object[] { rs.getInt("id_centro"), rs.getString("nombre"), rs.getString("codigo"), });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void rellenarTutores(DefaultTableModel tabla, int idCentro) {
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement
					.executeQuery("select * from tutor WHERE eliminado != 1 AND id_centro = " + idCentro + ";");

			while (rs.next()) {
				tabla.addRow(new Object[] { rs.getInt("id_tutor"), rs.getInt("id_centro"), rs.getString("nombre"), });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void rellenarNombreEmpresas(DefaultTableModel modeloTablaEmpresa, int idCentro) {
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(
					"select id_empresa, nombre_empresa from empresa WHERE eliminado != 1 AND id_empresa = "
							+ "(SELECT id_empresa FROM convenio where id_centro = " + idCentro + ");");

			while (rs.next()) {
				modeloTablaEmpresa.addRow(new Object[] { rs.getInt("id_empresa"), rs.getString("nombre_empresa"), });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void rellenarNombreAlumnos(DefaultTableModel modeloTablaAlumno, int idCentro) {
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(
					"select id_alumno, nombre from alumno WHERE eliminado != 1 AND id_centro = " + idCentro + ";");

			while (rs.next()) {
				modeloTablaAlumno.addRow(new Object[] { rs.getInt("id_alumno"), rs.getString("nombre"), });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void cogeConvenio(int idEmpresa) {
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(
					"select anexo_1 from convenio WHERE eliminado != 1 AND id_empresa = " + idEmpresa + ";");

			while (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private byte[] leerBytes(File archivo) {
		int longitudArchivo = (int) archivo.length();
		byte[] bytes = new byte[longitudArchivo];
		int indice = 0;
		int byteLeido = 0;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(archivo);
			bytes = Files.readAllBytes(archivo.toPath());
//			while((byteLeido = is.read(bytes, indice, bytes.length-indice)) != 1) {
//				indice+=byteLeido;
//			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bytes;
	}

	public ArrayList<Empresa> cogeEmpresas() {
		ArrayList<Empresa> arrlEmpresas = new ArrayList<>();

		Connection conexion = null;
		Statement consultita = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user , contrasenna);
			consultita = conexion.createStatement();

			registro = consultita.executeQuery(
					"SELECT * FROM empresaWHERE ORDER BY nombre_socio");

			if (!registro.next()) {
				String titulo = "Error";
				JOptionPane.showMessageDialog(null, "La base de datos está vacía.", titulo, JOptionPane.ERROR_MESSAGE);
			}

			while (registro.next()) {
				Empresa nuevaEmpresa = new Empresa();

				nuevaEmpresa.setIdEmpresa(registro.getInt("id_empresa"));
				nuevaEmpresa.setCIF(registro.getString("CIF"));
				nuevaEmpresa.setDuenno(registro.getString("dueño"));
				nuevaEmpresa.setNombre_empresa(registro.getString("nombre_empresa"));
				nuevaEmpresa.setTelefono_empresa(registro.getString("telefono_empresa"));
				nuevaEmpresa.setDireccion_empresa(registro.getString("direccion_empresa"));
				nuevaEmpresa.setEmail_empresa(registro.getString("email_empresa"));
				nuevaEmpresa.setTutor_empresa(registro.getString("tutor_empresa"));
				nuevaEmpresa.setContacto_empresa(registro.getString("contacto_empresa"));
				nuevaEmpresa.setSolicita(registro.getString("solicita"));
				nuevaEmpresa.setEliminado(registro.getBoolean("eliminado"));


				arrlEmpresas.add(nuevaEmpresa);
			}

		} catch (SQLException e) {
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

		return arrlEmpresas;
	}	
}
