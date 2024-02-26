package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import modelo.Consultas;
import modelo.Usuario;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CambioContrasenna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNuevaContra;
	private JTextField txtRepiteContra;
	Consultas c = new Consultas();

	/**
	 * Create the dialog.
	 */
	public CambioContrasenna(Usuario usuario) {
		setBounds(100, 100, 821, 509);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Cambio de contraseña");
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 45));
		lblTitulo.setForeground(new Color(9, 3, 62));
		lblTitulo.setBounds(170, 11, 469, 143);
		contentPanel.add(lblTitulo);
		
		txtNuevaContra = new JPasswordField();
		txtNuevaContra.setBounds(222, 183, 313, 20);
		contentPanel.add(txtNuevaContra);
		txtNuevaContra.setColumns(10);
		
		txtRepiteContra = new JPasswordField();
		txtRepiteContra.setColumns(10);
		txtRepiteContra.setBounds(222, 308, 313, 20);
		contentPanel.add(txtRepiteContra);
		
		JLabel lblRepiteContra = new JLabel("Repite la contraseña");
		lblRepiteContra.setFont(new Font("Lato", Font.PLAIN, 15));
		lblRepiteContra.setBounds(222, 283, 188, 14);
		contentPanel.add(lblRepiteContra);
		
		JLabel lblNuevaContra = new JLabel("Nueva contraseña");
		lblNuevaContra.setFont(new Font("Lato", Font.PLAIN, 15));
		lblNuevaContra.setBounds(222, 158, 188, 14);
		contentPanel.add(lblNuevaContra);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String repiteContra = new String(txtRepiteContra.getText());
				String nuevaContra = new String(txtNuevaContra.getText());
				if(nuevaContra.equals(repiteContra)) {
					c.actualizarHistorico(repiteContra, usuario.getIdUsuario());
					c.actualizarPrimeraVez(usuario.getIdUsuario());
					usuario.setPrimeraVez(false);
					dispose();
				}else {
					System.out.println("Las contraseñas no coinciden");
				}
			}
		});
		btnAceptar.setBounds(222, 384, 89, 23);
		contentPanel.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(446, 384, 89, 23);
		contentPanel.add(btnCancelar);
	}
}
