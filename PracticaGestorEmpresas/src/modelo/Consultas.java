package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Blob;

public class Consultas {
	private String baseDeDatos = "jdbc:mysql://localhost/gestor_practicas";
	private String user = "root";
	private String contrasenna = "";
	
	public ArrayList<Usuario> tomaTodosUsuarios() {
		Usuario aux;
		ArrayList<Usuario> arrlUsuarios = new ArrayList<>();
		Connection conexion = null;
		Statement statement = null;
		
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user , contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery("select distinct * from Usuarios order by email");
			
			while(rs.next()) {
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
		}finally {
			try {
				conexion.close();
			}catch (NullPointerException e) {
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
			conexion = DriverManager.getConnection(baseDeDatos, user , contrasenna);
			statement = conexion.createStatement();
			
			ResultSet rs = statement.executeQuery("select distinct material from Otros where id_usuario = " + idUser);
			if(rs.next()) {
				historico = rs.getString("material");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			}catch (NullPointerException e) {
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
		if(nuevoHistorico.isBlank()) {
			System.out.println("No se pueden cosas vacias");
			return;
		}
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user , contrasenna);
			statement = conexion.createStatement();
			
			int valor = statement.executeUpdate("update otros set material = '" + nuevoHistorico + "' where id_usuario = " + idUsuario);
			if(valor == 1) {
				System.out.println("Todo correcto master");
			}else{
				System.out.println("Uy Uy Uy :(");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			}catch (NullPointerException e) {
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
			conexion = DriverManager.getConnection(baseDeDatos, user , contrasenna);
			statement = conexion.createStatement();
			
			int valor = statement.executeUpdate("update usuarios set primer = 0 where id_usuario = " + idUsuario);
			if(valor == 1) {
				System.out.println("Todo correcto master");
			}else{
				System.out.println("Uy Uy Uy :(");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			}catch (NullPointerException e) {
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
			conexion = DriverManager.getConnection(baseDeDatos, user , contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery("select distinct * from Usuarios WHERE eliminado != 1 AND id_centro = " + idCentro);
			
			while(rs.next()) {
				tabla.addRow(new Object[] {
						rs.getInt("id_usuario"),
						rs.getInt("id_centro"),
						rs.getString("email"),
						rs.getString("perfil")
				});
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			}catch (NullPointerException e) {
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
			conexion = DriverManager.getConnection(baseDeDatos, user , contrasenna);
			statement = conexion.createStatement();
			
			int valor = statement.executeUpdate("INSERT INTO usuarios (id_centro, email, primer, perfil, eliminado) VALUES ("
					+ idCentro +", '"
					+ email + "', 1, '"
					+ perfil + "', 0)");
			if(valor == 1) {
				System.out.println("Todo correcto master");
			}else{
				System.out.println("Uy Uy Uy :(");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			}catch (NullPointerException e) {
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
			conexion = DriverManager.getConnection(baseDeDatos, user , contrasenna);
			statement = conexion.createStatement();
			
			int valor = statement.executeUpdate("update usuarios set eliminado = 1 where id_usuario = " + idUsuario);
			if(valor == 1) {
				System.out.println("Todo correcto master");
			}else{
				System.out.println("Uy Uy Uy :(");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			}catch (NullPointerException e) {
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
			conexion = DriverManager.getConnection(baseDeDatos, user , contrasenna);
			statement = conexion.createStatement();
			String update = "UPDATE usuarios SET email = '" + nuevoEmail + "', perfil = '" + nuevoPerfil + "' WHERE id_usuario = " + idUsuario + ";";
			int valor = statement.executeUpdate(update);
			if(valor == 1) {
				System.out.println("Todo correcto master");
			}else{
				System.out.println("Uy Uy Uy :(");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			}catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<String> cogeNombreCentros(){
		ArrayList<String> arrlCentros = new ArrayList<>();
		Connection conexion = null;
		Statement statement = null;
		
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user , contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery("SELECT nombre FROM centro WHERE eliminado != 1;");
			
			while(rs.next()) {
				arrlCentros.add(rs.getString("nombre"));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			}catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrlCentros;
	}
	
	public int cogeIdCentro(String nombre){
		int id = -1;
		Connection conexion = null;
		Statement statement = null;
		
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user , contrasenna);
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery("SELECT id_centro FROM centro WHERE eliminado != 1 AND nombre = '" + nombre + "';");
			
			if (rs.next()) {
				id = rs.getInt("id_centro");
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			}catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	
	public void rellenarPracticas(DefaultTableModel tabla, int idCentro) {
		Connection conexion = null;
		Statement statement = null;
		
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user , contrasenna);
			statement = conexion.createStatement();
			String consulta = "SELECT practica.id_practica, practica.id_anexo, empresa.nombre_empresa, alumno.nombre from practica, alumno, empresa WHERE practica.eliminado != 1 AND alumno.id_centro = "+ idCentro + ";";
			System.out.println(consulta);
			ResultSet rs = statement.executeQuery(consulta);
			
			while(rs.next()) {
				tabla.addRow(new Object[] {
						rs.getInt("practica.id_practica"),
						rs.getInt("practica.id_anexo"),
						rs.getString("alumno.nombre"),
						rs.getString("empresa.nombre_empresa")
				});
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conexion.close();
			}catch (NullPointerException e) {
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
			anexo2_1Stream = new FileInputStream(anexo2_1);
			anexo2_2Stream = new FileInputStream(anexo2_2);
			anexo3Stream = new FileInputStream(anexo3);
			anexo8Stream = new FileInputStream(anexo8);
			conexion = DriverManager.getConnection(baseDeDatos, user , contrasenna);
			statement = conexion.createStatement();
			
			int valor = statement.executeUpdate("INSERT INTO anexo (anexo_8, anexo_2_1, anexo_2_2, anexo_3, eliminado) VALUES ("
					+ anexo8Stream +", "
					+ anexo2_1Stream + ", "
					+ anexo2_2Stream + ", "
					+ anexo3Stream + ", 0)");
			if(valor == 1) {
				System.out.println("Todo correcto master");
				ResultSet rs = statement.executeQuery(consulta);
				if (rs.next()) {
					idAnexo = rs.getInt("id_anexo");
				}
			}else{
				System.out.println("Uy Uy Uy :(");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				anexo2_1Stream.close();
				anexo2_2Stream.close();
				anexo3Stream.close();
				anexo8Stream.close();
				conexion.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public void insertarPractica(int idAnexo, int idAlumno, int idEmpresa, Date fechaInicio, Date fechaFinal) {
		Connection conexion = null;
		Statement statement = null;
		try {
			conexion = DriverManager.getConnection(baseDeDatos, user , contrasenna);
			statement = conexion.createStatement();
			
			int valor = statement.executeUpdate("INSERT INTO practica (id_anexo, id_alumno, id_empresa, inicio, final, eliminado) VALUES ("
					+ idAnexo +", "
					+ idAlumno + ", "
					+ idEmpresa + ", "
					+ fechaInicio + ", "
					+ fechaFinal + ", 0)");
			if(valor == 1) {
				System.out.println("Todo correcto master");
			}else{
				System.out.println("Uy Uy Uy :(");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
