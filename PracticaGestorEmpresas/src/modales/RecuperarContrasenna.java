package modales;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controlador.CompruebaCredenciales;
import modelo.Consultas;
import modelo.Usuario;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecuperarContrasenna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	Consultas c = new Consultas();
	CompruebaCredenciales cc = new CompruebaCredenciales();

	/**
	 * Create the dialog.
	 */
	public RecuperarContrasenna() {
		setModal(true);
		setBounds(100, 100, 821, 509);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Recuperacion de contraseña");
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 45));
		lblTitulo.setForeground(new Color(9, 3, 62));
		lblTitulo.setBounds(110, 11, 602, 143);
		contentPanel.add(lblTitulo);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(222, 183, 313, 20);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblMuestraContra = new JLabel("Contraseña: ");
		lblMuestraContra.setFont(new Font("Lato", Font.PLAIN, 15));
		lblMuestraContra.setBounds(222, 260, 276, 14);
		contentPanel.add(lblMuestraContra);
		
		JLabel lblIntroduceEmail = new JLabel("Introduce un email");
		lblIntroduceEmail.setFont(new Font("Lato", Font.PLAIN, 15));
		lblIntroduceEmail.setBounds(222, 158, 188, 14);
		contentPanel.add(lblIntroduceEmail);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(446, 345, 89, 23);
		contentPanel.add(btnVolver);
		
		JButton btnRecuperar = new JButton("Recuperar");
		btnRecuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = cc.existeUsuario(txtEmail.getText());
				if(usuario.isExiste()) {
					lblMuestraContra.setText("Contraseña: " + c.cogeHistorico(usuario.getIdUsuario()));
					txtEmail.setEditable(false);
				}else {
					System.out.println("El correo introducido no se encuentra en nuestra base de datos");
				}
			}
		});
		btnRecuperar.setBounds(222, 345, 89, 23);
		contentPanel.add(btnRecuperar);
		btnRecuperar.setBackground(new Color(254, 86, 86));
		btnVolver.setBackground(new Color(254, 86, 86));
	}
}
