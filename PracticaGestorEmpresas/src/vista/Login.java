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
import javax.swing.JDialog;
import javax.swing.border.LineBorder;

import controlador.CompruebaCredenciales;
import modales.CambioContrasenna;
import modales.RecuperarContrasenna;
import modelo.Usuario;

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

public class Login extends JPanel {
	private JLabel lblTitulo;
	private JTextField txtUsuario;
	private JLabel img;
	private JPasswordField txtContrasenia;

	private String email = "";
	private String contrasenia = "";
	private JLabel lblerror;
	private boolean correcto;
	private JButton btnAceptar;
	private Ventana ventana;
	private boolean esAdmin = true;
	private CompruebaCredenciales cc = new CompruebaCredenciales();
	private Usuario usuario;

	// Modificamos el constructor para recibir una referencia de la instancia de
	// "Ventana".
	/**
	 * @wbp.parser.constructor
	 */
	public Login(Ventana ventana) {
		this.ventana = ventana;

		setBackground(new Color(255, 255, 255));
		setLayout(null);
		btnAceptar = new JButton("Entrar");

		img = new JLabel("");	
		img.setIcon(new ImageIcon(Login.class.getResource("/imagenes/logoColegio2.png")));
		img.setBounds(330, 69, 171, 177);
		add(img);


		lblTitulo = new JLabel("GestaPracticas");
		lblTitulo.setForeground(new Color(233, 1, 1));
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 55));
		lblTitulo.setBounds(511, 134, 446, 81);
		add(lblTitulo);

		txtUsuario = new JTextField();
		txtUsuario.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				lblerror.setText("");
			}
		});

		txtUsuario.setBorder(new LineBorder(new Color(233, 1, 1), 3));
		txtUsuario.setBounds(455, 296, 342, 37);
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
		txtContrasenia.setBounds(455, 393, 342, 37);
		add(txtContrasenia);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(new Color(9, 3, 62));
		lblUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblUsuario.setBounds(455, 271, 117, 14);
		add(lblUsuario);

		JLabel lblContrasenia = new JLabel("Contraseña:");
		lblContrasenia.setForeground(new Color(9, 3, 62));
		lblContrasenia.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblContrasenia.setBounds(455, 368, 153, 14);
		add(lblContrasenia);

		// -- ACCIÓN ACEPTAR --
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				email = txtUsuario.getText();
				contrasenia = new String(txtContrasenia.getPassword());
				usuario = cc.comprobarCredenciales(txtUsuario.getText(), new String(txtContrasenia.getPassword()));

				correcto = usuario.isExiste();
				if (correcto) {
					if (usuario.isPrimeraVez()) {
						CambioContrasenna dialog = new CambioContrasenna(usuario);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} else {
						if (usuario.getPerfil().equals("ADMINISTRADOR")) {
							esAdmin = true;
						} else {
							esAdmin = false;
						}
						ventana.nuevoPanel(new Menu(ventana, esAdmin, usuario.getIdCentro()));
					}

				} else {
					lblerror.setText("Usuario o contraseña incorrecto.");
				}

			}
		});
		// --------------------------------------------------

		btnAceptar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAceptar.setBorder(null);
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setBackground(new Color(233, 1, 1));
		btnAceptar.setBounds(556, 507, 117, 37);
		add(btnAceptar);

		lblerror = new JLabel("");
		lblerror.setForeground(new Color(64, 0, 0));
		lblerror.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblerror.setBounds(508, 443, 239, 14);
		add(lblerror);

		JLabel lblOlvido = new JLabel("He olvidado mi contraseña");
		lblOlvido.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblOlvido.setBounds(405, 460, 186, 14);
		lblOlvido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RecuperarContrasenna dialog = new RecuperarContrasenna();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblOlvido.setForeground(Color.CYAN);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblOlvido.setForeground(Color.BLUE);
			}
		});
		lblOlvido.setForeground(Color.BLUE);
		add(lblOlvido);

		JLabel lblCambiarMiContrasea = new JLabel("Cambiar mi contraseña");
		lblCambiarMiContrasea.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCambiarMiContrasea.setBounds(694, 460, 159, 14);
		lblCambiarMiContrasea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CambioContrasenna dialog = new CambioContrasenna(usuario);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblCambiarMiContrasea.setForeground(Color.CYAN);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblCambiarMiContrasea.setForeground(Color.BLUE);
			}
		});
		lblCambiarMiContrasea.setForeground(Color.BLUE);
		add(lblCambiarMiContrasea);
	}
}
