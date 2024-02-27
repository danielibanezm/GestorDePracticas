package controlador;


import java.util.ArrayList;

import modelo.Consultas;
import modelo.Usuario;


public class CompruebaCredenciales {
	Consultas c = new Consultas();
	
	public Usuario comprobarCredenciales(String email, String historico) {
		Usuario usuarioEncontrado = new Usuario();
		boolean encontrado = false;
		int idUser = -1;
		int i = 0;
		
		if(email.isBlank() || historico.isBlank()) {
			return usuarioEncontrado;
		}
		ArrayList<Usuario> arrlUsuarios = c.tomaTodosUsuarios();
		while(!encontrado && i < arrlUsuarios.size()) {
			System.out.println(arrlUsuarios.get(i).getEmail() + "  =  " + email);
			if(arrlUsuarios.get(i).getEmail().equals(email)) {
				System.out.println("se encontro");
				encontrado = true;
				idUser = arrlUsuarios.get(i).getIdUsuario();
			}
			i++;
		}
		if(!encontrado) {
			System.out.println("No se ha encontrado");
			return usuarioEncontrado;
		}
		
		String historicoBD = c.cogeHistorico(idUser);
		if(!historico.equals(historicoBD)) {
			System.out.println("La contraseña no coincide");
			return usuarioEncontrado;
		}
		
		usuarioEncontrado = arrlUsuarios.get(i-1);
		usuarioEncontrado.setExiste(true);
		System.out.println(usuarioEncontrado.getEmail() + " " + usuarioEncontrado.isExiste());
		return usuarioEncontrado;
	}

	
	//Para comprobar si existe el email cuando se cambia la contraseña
	public Usuario existeUsuario(String email) {
		boolean encontrado = false;
		Usuario usuarioEncontrado = new Usuario();
		int i = 0;
		if(email.isBlank()) {
			return usuarioEncontrado;
		}
		ArrayList<Usuario> arrlUsuarios = c.tomaTodosUsuarios();
		while(!encontrado && i < arrlUsuarios.size()) {
			if(arrlUsuarios.get(i).getEmail().equals(email) || arrlUsuarios.get(i).getEmail().equals(email)) {
				encontrado = true;
				usuarioEncontrado = arrlUsuarios.get(i);
				usuarioEncontrado.setExiste(true);
			}
			i++;
		}
		return usuarioEncontrado;
	}

}
