package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Consultas {
	private java.util.Date hoy = java.sql.Date.from(Instant.now());
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
				JOptionPane.showMessageDialog(null, "Contraseña modificada correcatamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al modificar la contraseña", "Info",
						JOptionPane.ERROR_MESSAGE);
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
				System.out.println("Guay");
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
		int idUsuario = -1;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			int valor = statement
					.executeUpdate("INSERT INTO usuarios (id_centro, email, primer, perfil, eliminado) VALUES ("
							+ idCentro + ", '" + email + "', 1, '" + perfil + "', 0)");
			if (valor == 1) {
				JOptionPane.showMessageDialog(null, "Usuario insertado correcatamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				if ((idUsuario = cogeUltimoIdUsuario()) != -1) {
					statement.executeUpdate("INSERT INTO otros (id_usuario, material, eliminado) VALUES (" + idUsuario
							+ ", '12345', 0);");
				} else {
					System.out.println("no se puede seleccionar el ultimo id");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error al insertar usuario", "Info", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "Usuario borrado correcatamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al borrar usuario", "Info", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "Usuario actualizado correcatamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al actualizar usuario", "Info", JOptionPane.ERROR_MESSAGE);
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
	
	public int cogeIdConvenioPorIdEmpresa(int idEmpresa) {
		int id = -1;
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT id_convenio FROM convenio WHERE eliminado != 1 AND id_empresa = " + idEmpresa + ";");

			if (rs.next()) {
				id = rs.getInt("id_convenio");
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
			String consulta = "SELECT practica.id_practica, practica.id_anexo, empresa.nombre_empresa, alumno.nombre, practica.inicio, practica.final FROM practica, alumno, empresa WHERE practica.id_alumno = alumno.id_alumno AND practica.id_empresa = empresa.id_empresa AND practica.eliminado != 1;";
			System.out.println(consulta);
			ResultSet rs = statement.executeQuery(consulta);

			while (rs.next()) {

				tabla.addRow(new Object[] { rs.getInt("practica.id_practica"),
						rs.getInt("practica.id_anexo"),
						rs.getString("alumno.nombre"), 
						rs.getString("empresa.nombre_empresa"), 
						rs.getDate("inicio"),
						rs.getDate("final") });

			}
		} catch (SQLException e) {
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
					JOptionPane.showMessageDialog(null, "Anexo insertado correcatamente", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error al insertar anexos", "Info", JOptionPane.ERROR_MESSAGE);
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

	public int cogeUltimoIdUsuario() {
		Connection conexion = null;
		Statement statement = null;
		int idUsuario = -1;
		String consulta = "SELECT MAX(id_usuario) FROM usuarios";
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			ResultSet rs = statement.executeQuery(consulta);
			if (rs.next()) {
				idUsuario = rs.getInt(1);
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
		return idUsuario;
	}

	public void insertarPractica(int idAnexo, int idAlumno, int idEmpresa, Date fechaInicio, Date fechaFinal) {
		Connection conexion = null;
		Statement statement = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			String insert = "INSERT INTO practica (id_anexo, id_alumno, id_empresa, inicio, final, eliminado) VALUES ("
					+ idAnexo + ", " + idAlumno + ", " + idEmpresa + ", " + fechaInicio + ", " + fechaFinal + ", 0)";
			System.out.println(insert);
			// int valor = statement.executeUpdate(insert);
			PreparedStatement ps = conexion.prepareStatement(
					"INSERT INTO practica (id_anexo, id_alumno, id_empresa, inicio, final, eliminado) VALUES (?, ?, ?, ?, ?, ?)");
			ps.setInt(1, idAnexo);
			ps.setInt(2, idAlumno);
			ps.setInt(3, idEmpresa);
			ps.setDate(4, fechaInicio);
			ps.setDate(5, fechaFinal);
			ps.setInt(6, 0);
			int valor = ps.executeUpdate();
			if (valor == 1) {
				JOptionPane.showMessageDialog(null, "Practica insertada correcatamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al insertar practica", "Info", JOptionPane.ERROR_MESSAGE);
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
	
	public void rellenarNombreEmpresasPorNombre(DefaultTableModel modeloTablaEmpresa, int idCentro, String nombre) {
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(
					"select id_empresa, nombre_empresa from empresa WHERE eliminado != 1 AND nombre_empresa LIKE '%"+ nombre + "%' AND id_empresa = "
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

	public void rellenarNombreAlumnosPorNombre(DefaultTableModel modeloTablaAlumno, int idCentro, String nombre) {
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(
					"select id_alumno, nombre from alumno WHERE eliminado != 1 AND id_centro = " + idCentro + " AND valido != 1 AND nombre LIKE '%"+ nombre +"%';");

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
	public void rellenarNombreAlumnos(DefaultTableModel modeloTablaAlumno, int idCentro) {
		Connection conexion = null;
		Statement statement = null;
		
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(
					"select id_alumno, nombre from alumno WHERE eliminado != 1 AND id_centro = " + idCentro + " AND valido != 1;");
			
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

	public ArrayList<Empresa> cogeEmpresas() {
	    ArrayList<Empresa> arrlEmpresas = new ArrayList<>();
	    Connection conexion = null;
	    Statement consultita = null;
	    ResultSet registro = null;

	    try {
	        conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
	        consultita = conexion.createStatement();

	        registro = consultita.executeQuery("SELECT CIF, dueño_empresa, nombre_empresa, telefono_empresa, email_empresa, direccion_empresa, tutor_empresa, correo_contacto, solicita FROM empresa WHERE eliminado != 1");

	        if (!registro.next()) {
	            String titulo = "Error";
	            JOptionPane.showMessageDialog(null, "La base de datos está vacía.", titulo, JOptionPane.ERROR_MESSAGE);
	        } else {
	            do {
	                Empresa nuevaEmpresa = new Empresa();
	                nuevaEmpresa.setCIF(registro.getString("CIF"));
	                nuevaEmpresa.setDuenno(registro.getString("dueño_empresa"));
	                nuevaEmpresa.setNombre_empresa(registro.getString("nombre_empresa"));
	                nuevaEmpresa.setTelefono_empresa(registro.getString("telefono_empresa"));
	                nuevaEmpresa.setDireccion_empresa(registro.getString("direccion_empresa"));
	                nuevaEmpresa.setEmail_empresa(registro.getString("email_empresa"));
	                nuevaEmpresa.setTutor_empresa(registro.getString("tutor_empresa"));
	                nuevaEmpresa.setContacto_empresa(registro.getString("correo_contacto"));
	                nuevaEmpresa.setSolicita(registro.getString("solicita"));

	                arrlEmpresas.add(nuevaEmpresa);
	            } while (registro.next());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return arrlEmpresas;
	}


	public void insertaEmpresa(Empresa empresa) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			consulta = conexion.createStatement();			
			
			consulta.executeUpdate(
					"INSERT INTO EMPRESA (CIF, dueño_empresa, nombre_empresa, telefono_empresa, email_empresa, direccion_empresa, tutor_empresa, correo_contacto, solicita, eliminado) "
							+ "VALUES ('" + empresa.getCIF() + "', '" + empresa.getDuenno() + "', '"
							+ empresa.getNombre_empresa() + "', '" + empresa.getTelefono_empresa() + "', '"
							+ empresa.getEmail_empresa() + "', '" + empresa.getDireccion_empresa() + "', '"
							+ empresa.getTutor_empresa() + "', '" + empresa.getContacto_empresa() + "', '"
							+ empresa.getSolicita() + "', 0)");

			// err.confirmarInsert();

		} catch (SQLException e) {
			e.printStackTrace();
			// err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

	}
	
	
	public void actualizaEmpresa(Empresa empresa, String idEmpresa) {
		
		System.out.println("EL id de empresa es: " + idEmpresa);
		
	    Connection conexion = null;
	    PreparedStatement statement = null;
	    try {
	        conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);

	        String query = "UPDATE empresa SET CIF=?, dueño_empresa=?, nombre_empresa=?, telefono_empresa=?, "
	                     + "email_empresa=?, direccion_empresa=?, tutor_empresa=?, correo_contacto=?, solicita=? "
	                     + "WHERE id_empresa=?";
	        
	        statement = conexion.prepareStatement(query);
	        statement.setString(1, empresa.getCIF());
	        statement.setString(2, empresa.getDuenno());
	        statement.setString(3, empresa.getNombre_empresa());
	        statement.setString(4, empresa.getTelefono_empresa());
	        statement.setString(5, empresa.getEmail_empresa());
	        statement.setString(6, empresa.getDireccion_empresa());
	        statement.setString(7, empresa.getTutor_empresa());
	        statement.setString(8, empresa.getContacto_empresa());
	        statement.setString(9, empresa.getSolicita());
	        statement.setString(10, idEmpresa);
	        
	        int valor = statement.executeUpdate();

	        if (valor == 1) {
	            JOptionPane.showMessageDialog(null, "Empresa modificada correctamente", "Info",
	                    JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(null, "Error al modificar empresa", "Info", JOptionPane.ERROR_MESSAGE);
	        }
	        
	        conexion.close();

	    } catch (SQLException e) {
	        e.printStackTrace();	    
	    }
	}
	
	public String obtenerIdEmpresa(Empresa empresa) {
	    String id = null; // Initialize id to null
	    Connection conexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);

	        String query = "SELECT id_empresa FROM empresa WHERE CIF = ? AND dueño_empresa = ? AND email_empresa = ? AND eliminado = 0";
	        statement = conexion.prepareStatement(query);
	        statement.setString(1, empresa.getCIF());
	        statement.setString(2, empresa.getDuenno());
	        statement.setString(3, empresa.getEmail_empresa());

	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            id = resultSet.getString("id_empresa");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return id;
	}


	public void borrarEmpresaLogico(String idEmpresa) {
	    Connection conexion = null;
	    PreparedStatement statement = null;
	    try {
	        conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);

	        String query = "UPDATE empresa SET eliminado = 1 WHERE id_empresa = ?";
	        
	        statement = conexion.prepareStatement(query);
	        statement.setString(1, idEmpresa);
	        
	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected == 1) {
	            JOptionPane.showMessageDialog(null, "Empresa borrada correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(null, "Error al borrar empresa", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


	public void insertarSocio(Empresa empresa) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			consulta = conexion.createStatement();

			consulta.executeUpdate(
					"INSERT INTO EMPRESA (CIF, dueño_empresa, nombre_empresa, telefono_empresa, email_empresa, direccion_empresa, tutor_empresa, correo_contacto, solicita, eliminado) "
							+ "VALUES ('" + empresa.getCIF() + "', '" + empresa.getDuenno() + "', '"
							+ empresa.getNombre_empresa() + "', '" + empresa.getTelefono_empresa() + "', '"
							+ empresa.getEmail_empresa() + "', '" + empresa.getDireccion_empresa() + "', '"
							+ empresa.getTutor_empresa() + "', '" + empresa.getContacto_empresa() + "', '"
							+ empresa.getSolicita() + "', 0)");

			// err.confirmarInsert();

		} catch (SQLException e) {
			e.printStackTrace();
			// err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

	}

	public void insertarTutor(String nombre, int idCentro) {
		Connection conexion = null;
		Statement statement = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			int valor = statement.executeUpdate(
					"INSERT INTO tutor (id_centro, nombre, eliminado) VALUES (" + idCentro + ", '" + nombre + "', 0)");
			if (valor == 1) {
				JOptionPane.showMessageDialog(null, "Tutor insertado correcatamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al insertar tutor", "Info", JOptionPane.ERROR_MESSAGE);
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

	public void actualizaTutor(String nombre, int idCentro) {
		Connection conexion = null;
		Statement statement = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			int valor = statement
					.executeUpdate("UPDATE tutor SET nombre = '" + nombre + "' WHERE id_centro = " + idCentro + ";");
			if (valor == 1) {
				JOptionPane.showMessageDialog(null, "Tutor modificado correcatamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al modificar tutor", "Info", JOptionPane.ERROR_MESSAGE);
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

	public void borradoLogicoTutores(int idTutor) {
		Connection conexion = null;
		Statement statement = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			int valor = statement.executeUpdate("update tutor set eliminado = 1 where id_tutor = " + idTutor);
			if (valor == 1) {
				JOptionPane.showMessageDialog(null, "Tutor borrado correcatamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al borrar tutor", "Info", JOptionPane.ERROR_MESSAGE);
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

	public void borradoLogicoCentro(int idCentro) {
		Connection conexion = null;
		Statement statement = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			int valor = statement.executeUpdate("update centro set eliminado = 1 where id_centro = " + idCentro);
			if (valor == 1) {
				JOptionPane.showMessageDialog(null, "Centro borrado correcatamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al borrar centro", "Info", JOptionPane.ERROR_MESSAGE);
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

	public void actualizarCentro(int idCentro, String codigo, String nombre) {
		Connection conexion = null;
		Statement statement = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			int valor = statement.executeUpdate("update centro set codigo = '" + codigo + "', nombre ='" + nombre
					+ "' where id_centro = " + idCentro);
			if (valor == 1) {
				JOptionPane.showMessageDialog(null, "Centro actualizado correcatamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al actuaizar centro", "Info", JOptionPane.ERROR_MESSAGE);
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

	public void insertarCentro(String codigo, String nombre) {
		Connection conexion = null;
		Statement statement = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			int valor = statement.executeUpdate(
					"INSERT INTO centro (nombre, codigo, eliminado) VALUES ('" + nombre + "', " + codigo + ", 0)");
			if (valor == 1) {
				JOptionPane.showMessageDialog(null, "Tutor insertado correcatamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al insertar tutor", "Info", JOptionPane.ERROR_MESSAGE);
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

	public InputStream obtenAnexo2_1(int idPractica) {
		Connection conexion = null;
		Statement statement = null;
		InputStream anexoStream = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(
					"select anexo_2_1 from anexos WHERE id_anexo = (SELECT id_anexo FROM practica WHERE id_practica = "
							+ idPractica + ");");

			if (rs.next()) {
				anexoStream = rs.getBinaryStream("anexo_2_1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
				anexoStream.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return anexoStream;
	}

	public InputStream obtenAnexo2_2(int idPractica) {
		Connection conexion = null;
		Statement statement = null;
		InputStream anexoStream = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(
					"select anexo_2_2 from anexos WHERE id_anexo = (SELECT id_anexo FROM practica WHERE id_practica = "
							+ idPractica + ");");

			if (rs.next()) {
				anexoStream = rs.getBinaryStream("anexo_2_2");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
				anexoStream.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return anexoStream;
	}

	public InputStream obtenAnexo3(int idPractica) {
		Connection conexion = null;
		Statement statement = null;
		InputStream anexoStream = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(
					"select anexo_3 from anexos WHERE id_anexo = (SELECT id_anexo FROM practica WHERE id_practica = "
							+ idPractica + ");");

			if (rs.next()) {
				anexoStream = rs.getBinaryStream("anexo_3");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
				anexoStream.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return anexoStream;
	}

	public InputStream obtenAnexo8(int idPractica) {
		Connection conexion = null;
		Statement statement = null;
		InputStream anexoStream = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(
					"select anexo_8 from anexos WHERE id_anexo = (SELECT id_anexo FROM practica WHERE id_practica = "
							+ idPractica + ");");

			if (rs.next()) {
				anexoStream = rs.getBinaryStream("anexo_8");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
				anexoStream.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return anexoStream;
	}

	public File leeFichero(String nombrenexo, InputStream anexoStream) {
		File anexo = new File(nombrenexo);
		OutputStream os = null;
		try {
			os = new FileOutputStream(anexo);
			byte[] buf = new byte[1024];
			int len;
			while ((len = anexoStream.read(buf)) > 0) {
				os.write(buf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				anexoStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return anexo;
	}

	public void insertaAnexoSemana(int idPractica, File anexoSemanal) {
		Connection conexion = null;
		Statement statement = null;
		FileInputStream anexoSemanalStream = null;
		int idAnexo = -1, idAnexoSemanal = -1;
		String consultaMaxSemanales = "SELECT MAX(id_anexo_semanal) FROM anexos_semanales";
		String consultaIdAnexo = "SELECT id_anexo FROM practica WHERE id_practica = " + idPractica + ";";
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			anexoSemanalStream = new FileInputStream(anexoSemanal);
			PreparedStatement preparedStatement = conexion
					.prepareStatement("INSERT INTO anexos_semanales (seguiminento, eliminado) VALUES(?, ?)");
			preparedStatement.setBinaryStream(1, anexoSemanalStream);
			preparedStatement.setInt(2, 0);
			int valor = preparedStatement.executeUpdate();
			if (valor == 1) {
				
				ResultSet rs = statement.executeQuery(consultaIdAnexo);
				if (rs.next()) {
					idAnexo = rs.getInt("id_anexo");
				}
				rs = statement.executeQuery(consultaMaxSemanales);
				if (rs.next()) {
					idAnexoSemanal = rs.getInt(1);
				}
				preparedStatement = conexion.prepareStatement("INSERT INTO intermedia_anexos VALUES (?, ?, ?)");
				preparedStatement.setInt(1, idAnexoSemanal);
				preparedStatement.setInt(2, idAnexo);
				preparedStatement.setInt(3, 0);
				
				valor = preparedStatement.executeUpdate();
				if(valor == 1) {
					JOptionPane.showMessageDialog(null, "Anexo insertado correcatamente", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Error al insertar anexos", "Info", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error al insertar anexos", "Info", JOptionPane.ERROR_MESSAGE);
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
	}

	public int cogeCantidadSemanal(int idPratica) {
		Connection conexion = null;
		Statement statement = null;
		int semana = -1;
		String consulta = "SELECT MAX(id_anexo_semanal) FROM anexos_semanales WHERE id_anexo_semanal = "
				+ "(SELECT id_anexo_semanal FROM intermedia_anexos WHERE id_anexo = "
				+ "(SELECT id_anexo FROM practica WHERE id_practica = " + idPratica + "));";
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			ResultSet rs = statement.executeQuery(consulta);
			if (rs.next()) {
				semana = rs.getInt(1);
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
		return semana + 1;
	}
	
	public void borradoLogicoPractica(int idPratica) {
		Connection conexion = null;
		Statement statement = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			int valor = statement.executeUpdate("update practica set eliminado = 1 where id_practica = " + idPratica);
			if (valor == 1) {
				JOptionPane.showMessageDialog(null, "Practica borrado correcatamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al borrar la practica", "Info", JOptionPane.ERROR_MESSAGE);
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
	
	public ArrayList<String> cogeNombreEmpresas() {
		ArrayList<String> arrlEmpresas = new ArrayList<>();
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery("SELECT nombre_empresa FROM empresa WHERE eliminado != 1;");

			while (rs.next()) {
				arrlEmpresas.add(rs.getString("nombre_empresa"));
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
		return arrlEmpresas;
	}
	
	
	public void rellenarPracticasActualesPorNombre(DefaultTableModel tabla, int idCentro, String nombreAlumno) {
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
//			String consulta = "SELECT practica.id_practica, practica.id_anexo, empresa.nombre_empresa, alumno.nombre, practica.inicio, practica.final from practica, alumno, empresa"
//					+ "WHERE practica.eliminado != 1 AND alumno.id_centro = "
//					+ idCentro + " AND alumno.nombre LIKE '%" + nombreAlumno +"%';";
			String consulta = "SELECT practica.id_practica, practica.id_anexo, empresa.nombre_empresa, alumno.nombre, practica.inicio, practica.final FROM practica, alumno, empresa WHERE practica.id_alumno = alumno.id_alumno AND practica.id_empresa = empresa.id_empresa AND practica.eliminado != 1 AND "
					+ "alumno.nombre LIKE '%" + nombreAlumno +"%';";
			System.out.println(consulta);
			ResultSet rs = statement.executeQuery(consulta);

			while (rs.next()) {

				tabla.addRow(new Object[] { rs.getInt("practica.id_practica"),
						rs.getInt("practica.id_anexo"),
						rs.getString("alumno.nombre"), 
						rs.getString("empresa.nombre_empresa"), 
						rs.getDate("inicio"),
						rs.getDate("final") });

			}
		} catch (SQLException e) {
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
	
	public void rellenarPracticasActualesPorEmpresa(DefaultTableModel tabla, int idCentro, String nombreEmpresa) {
		Connection conexion = null;
		Statement statement = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
//			String consulta = "SELECT practica.id_practica, practica.id_anexo, empresa.nombre_empresa, alumno.nombre, practica.inicio, practica.final from practica, alumno, empresa"
//					+ "WHERE practica.eliminado != 1 AND alumno.id_centro = "
//					+ idCentro + " AND alumno.nombre LIKE '%" + nombreAlumno +"%';";
			String consulta = "SELECT practica.id_practica, practica.id_anexo, empresa.nombre_empresa, alumno.nombre, practica.inicio, practica.final FROM practica, alumno, empresa WHERE practica.id_alumno = alumno.id_alumno AND practica.id_empresa = empresa.id_empresa AND practica.eliminado != 1 AND "
					+ "empresa.nombre_empresa = '" + nombreEmpresa +"';";
			System.out.println(consulta);
			ResultSet rs = statement.executeQuery(consulta);

			while (rs.next()) {

				tabla.addRow(new Object[] { rs.getInt("practica.id_practica"),
						rs.getInt("practica.id_anexo"),
						rs.getString("alumno.nombre"), 
						rs.getString("empresa.nombre_empresa"), 
						rs.getDate("inicio"),
						rs.getDate("final") });

			}
		} catch (SQLException e) {
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

	public void insertaConvenio(int idCentro, int idEmpresa, File convenio) {
		Connection conexion = null;
		Statement statement = null;
		FileInputStream convenioStream = null;
		Random random = new Random();
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();

			convenioStream = new FileInputStream(convenio);
			PreparedStatement preparedStatement = conexion.prepareStatement(
					"INSERT INTO convenio (id_empresa, id_centro, anexo_1, numero_serie, eliminado) VALUES(?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, idEmpresa);
			preparedStatement.setInt(2, idCentro);
			preparedStatement.setBinaryStream(3, convenioStream);
			preparedStatement.setInt(4, random.nextInt(1000000, 9999999));
			preparedStatement.setInt(5, 0);
			int valor = preparedStatement.executeUpdate();
			if (valor == 1) {
				JOptionPane.showMessageDialog(null, "Convenio insertado", "Info", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al insertar el convenio", "Info", JOptionPane.ERROR_MESSAGE);
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
	}
	public InputStream obtenConvenio(int idConvenio) {
		Connection conexion = null;
		Statement statement = null;
		InputStream anexoStream = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(
					"select anexo_1 from convenio WHERE id_convenio = "	+ idConvenio + ";");

			if (rs.next()) {
				anexoStream = rs.getBinaryStream("anexo_1");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
				anexoStream.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return anexoStream;
	}
	
	
	public String buscaCentro(int idCentro) {
		Connection conexion = null;
		Statement statement = null;
		String centro = "";
		
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(
					"select nombre from centro WHERE id_centro = "	+ idCentro + ";");

			if (rs.next()) {
				centro = rs.getString("nombre");
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
		
		return centro;
		
	}
	
	
	public int buscaIDCentro(String centro) {
		Connection conexion = null;
		Statement statement = null;
		int id = -1;
		
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(
					"select id_centro from centro WHERE nombre = '"	+ centro + "'");

			if (rs.next()) {
				id = rs.getInt("id_centro");
				System.out.println(id);
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
	
	public int obtenerIdAlumno(Alumno alumno) {
	    int id = -1; // Initialize id to null
	    Connection conexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);

	        
	        System.out.println("SELECT id_alumno FROM alumno WHERE nombre =" +  alumno.getAlumno() + " AND dni =" + alumno.getDni() + " AND ss = " + alumno.getSs() + "AND eliminado = 0");
	        
	        String query = "SELECT id_alumno FROM alumno WHERE nombre = ? AND dni = ? AND ss = ? AND eliminado = 0";
	        statement = conexion.prepareStatement(query);
	        statement.setString(1, alumno.getAlumno());
	        statement.setString(2, alumno.getDni());
	        statement.setString(3, alumno.getSs());

	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            id = resultSet.getInt("id_alumno");
	        }
	        

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return id;
	}
	
	public ArrayList<Alumno> cogeAlumno() {
	    ArrayList<Alumno> arrlAlumno = new ArrayList<>();
	    Connection conexion = null;
	    Statement consultita = null;
	    ResultSet registro = null;

	    try {
	        conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
	        consultita = conexion.createStatement();

	        registro = consultita.executeQuery("SELECT id_centro, nombre, dni, valido, ss, ciclo, año FROM alumno WHERE eliminado != 1");

	        if (!registro.next()) {
	            String titulo = "Error";
	            JOptionPane.showMessageDialog(null, "La base de datos está vacía.", titulo, JOptionPane.ERROR_MESSAGE);
	        } else {
	            do {
	                Alumno nuevoAlumno= new Alumno();
	                
	                String centro = buscaCentro(registro.getInt("id_centro"));
	                
	                nuevoAlumno.setCentro(centro);
	                nuevoAlumno.setAlumno(registro.getString("nombre"));
	                nuevoAlumno.setDni(registro.getString("dni"));
	                
	                if (registro.getBoolean("valido") == true) {
	                	nuevoAlumno.setValido("Si");
	                }else {
	                	nuevoAlumno.setValido("No");
	                }
	                
	                nuevoAlumno.setSs(registro.getString("ss"));
	                nuevoAlumno.setCiclo(registro.getString("ciclo"));
	                nuevoAlumno.setAnio(registro.getString("año"));

	                arrlAlumno.add(nuevoAlumno);
	            } while (registro.next());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return arrlAlumno;
	}


	
	public void insertaAlumno(Alumno alumno) {
		Connection conexion = null;
		Statement consulta = null;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
			consulta = conexion.createStatement();			
			
			int idCentro = buscaIDCentro(alumno.getCentro());
			
			System.out.println("INSERT INTO alumno (id_centro, nombre, dni, valido, ss, ciclo, año, eliminado) "
					+ "VALUES (" + idCentro + ", '" + alumno.getAlumno() + "', '"
					+ alumno.getDni() + "', '" + alumno.getValido() + "', '"
					+ alumno.getSs() + "', '" + alumno.getCiclo() + "', '"
					+ alumno.getAnio() + "', 0)");
			
			consulta.executeUpdate(
					"INSERT INTO alumno (id_centro, nombre, dni, valido, ss, ciclo, año, eliminado) "
							+ "VALUES (" + idCentro + ", '" + alumno.getAlumno() + "', '"
							+ alumno.getDni() + "', '" + alumno.getValido() + "', '"
							+ alumno.getSs() + "', '" + alumno.getCiclo() + "', '"
							+ alumno.getAnio() + "', 0)");

			
			
			// err.confirmarInsert();

		} catch (SQLException e) {
			e.printStackTrace();
			// err.baseDatosNoConexion();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
			} catch (NullPointerException e) {
			}
		}

	}
	
	
	public void borrarAlumnoLogico(int idAlumno) {
	    Connection conexion = null;
	    PreparedStatement statement = null;
	    try {
	        conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);

	        String query = "UPDATE alumno SET eliminado = 1 WHERE id_alumno = ?";
	        
	        statement = conexion.prepareStatement(query);
	        statement.setInt(1, idAlumno);
	        
	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected == 1) {
	            JOptionPane.showMessageDialog(null, "Alumno borrado correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(null, "Error al borrar alumno", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
public void actualizaAlumno(Alumno alumno, int idAlumno) {
		
		System.out.println("EL id del alumno es: " + idAlumno);
		
	    Connection conexion = null;
	    PreparedStatement statement = null;
	    try {
	        conexion = DriverManager.getConnection(baseDeDatos, user, contrasenna);
	        
	        int id = buscaIDCentro(alumno.getCentro());

	        
	        System.out.println("UPDATE alumno SET id_centro=" + id + ", nombre=" + alumno.getAlumno() +" dni= " + alumno.getDni() + " valido=" + alumno.getValido()
	                     + "ss= " + alumno.getSs() +" ciclo=" + alumno.getCiclo() + "año=" +  alumno.getAnio()
	                     + "WHERE id_alumno=" + idAlumno + ")");

	        String query = "UPDATE alumno SET id_centro=?, nombre=?, dni=?, valido=?, "
	                     + "ss=?, ciclo=?, año=? "
	                     + "WHERE id_alumno=?";
	        
	        statement = conexion.prepareStatement(query);
	        
	        
	        statement.setInt(1, id);
	        statement.setString(2, alumno.getAlumno());
	        statement.setString(3, alumno.getDni());
	        
	        if (alumno.getValido().equalsIgnoreCase("si")){
	        	statement.setInt(4, 1);
	        }else {
	        	statement.setInt(4, 0);

	        }
	        
	        statement.setString(5, alumno.getSs());
	        statement.setString(6, alumno.getCiclo());
	        statement.setString(7, alumno.getAnio());
	        statement.setInt(8, idAlumno);
	        
	        int valor = statement.executeUpdate();

	        if (valor == 1) {
	            JOptionPane.showMessageDialog(null, "Empresa modificada correctamente", "Info",
	                    JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(null, "Error al modificar empresa", "Info", JOptionPane.ERROR_MESSAGE);
	        }
	        
	        conexion.close();

	    } catch (SQLException e) {
	        e.printStackTrace();	    
	    }
	}
	
	
}
