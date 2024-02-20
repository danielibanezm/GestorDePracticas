package vista;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;


import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JPanel{
	private JLabel lblTitulo;
	private JLabel img;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;
	
	private String usuario = "";
	private String contrasenia = "";
	private JLabel lblerror;
	private boolean correcto;
	private JButton btnAceptar;
	private Ventana ventana;
	private boolean esAdmin = true;
//	private BaseDeDatos bd = new BaseDeDatos();
	private String idBib = "";

	
	//Modificamos el constructor para recibir una referencia de la instancia de "Ventana".
	public Login(Ventana ventana) {
		this.ventana = ventana;
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		btnAceptar = new JButton("Entrar");
		
		img = new JLabel("");
		img.setIcon(new ImageIcon(Login.class.getResource("/img/Bib.png")));
		img.setBounds(493, 80, 171, 177);
		add(img);
		
		lblTitulo = new JLabel("GestaPracticas");
		lblTitulo.setForeground(new Color(233, 1, 1));
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 55));
		lblTitulo.setBounds(669, 162, 239, 81);
		add(lblTitulo);
		
		txtUsuario = new JTextField();
		txtUsuario.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				lblerror.setText("");
			}
		});
		
		txtUsuario.setBorder(new LineBorder(new Color(233, 1, 1), 3));
		txtUsuario.setBounds(522, 331, 342, 37);
		add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasenia = new JPasswordField();
		txtContrasenia.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				lblerror.setText("");
			}
		});
		
		txtContrasenia.setBorder(new LineBorder(new Color(233, 1, 1), 3));
		txtContrasenia.setColumns(10);
		txtContrasenia.setBounds(522, 428, 342, 37);
		add(txtContrasenia);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(new Color(9, 3, 62));
		lblUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblUsuario.setBounds(522, 306, 117, 14);
		add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña:");
		lblContrasenia.setForeground(new Color(9, 3, 62));
		lblContrasenia.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblContrasenia.setBounds(522, 403, 153, 14);
		add(lblContrasenia);
		
		//-- ACCIÓN ACEPTAR --
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				usuario = txtUsuario.getText();
				contrasenia = new String(txtContrasenia.getPassword());
				
//				correcto = bd.compruebaUsuario(usuario, contrasenia);
				if(correcto) {
					
					//Comprobamos si el usuario es admin o no para habilitar el botón de usuarios en el menú.
					if(!(usuario.contains("admin"))){
						esAdmin = false;
					}
					
//					idBib = bd.obtenBiblioteca(usuario);
					//Utilizamos la referencia a Ventana para cambiar al panel Menu
					ventana.nuevoPanel(new Menu(ventana, esAdmin, idBib));
					
				}else {
					lblerror.setText("Usuario o contraseña incorrecto.");
				}
	
			}
		});
		//--------------------------------------------------
		
		btnAceptar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAceptar.setBorder(null);
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setBackground(new Color(233, 1, 1));
		btnAceptar.setBounds(522, 516, 117, 37);
		add(btnAceptar);
		
		lblerror = new JLabel("");
		lblerror.setForeground(new Color(64, 0, 0));
		lblerror.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblerror.setBounds(575, 478, 239, 14);
		add(lblerror);

	}
	
}
