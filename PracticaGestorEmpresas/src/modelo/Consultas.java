package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		}
	}
}
