package modales;

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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ModificacionUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Consultas c = new Consultas();
	private JTextField textFieldEmail;

	/**
	 * Create the dialog.
	 */
	public ModificacionUsuario(int idUsuario) {
		setBounds(100, 100, 649, 403);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Modificar usuario");
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 45));
		lblTitulo.setForeground(new Color(9, 3, 62));
		lblTitulo.setBounds(136, 11, 359, 143);
		contentPanel.add(lblTitulo);
		
		JLabel lblRepiteContra = new JLabel("Nuevo perfil de usuario");
		lblRepiteContra.setFont(new Font("Lato", Font.PLAIN, 15));
		lblRepiteContra.setBounds(154, 224, 188, 14);
		contentPanel.add(lblRepiteContra);
		
		JLabel lblNuevoEmail = new JLabel("Nuevo email");
		lblNuevoEmail.setFont(new Font("Lato", Font.PLAIN, 15));
		lblNuevoEmail.setBounds(154, 158, 188, 14);
		contentPanel.add(lblNuevoEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(154, 183, 153, 20);
		contentPanel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ADMINISTRADOR", "EMPLEADO"}));
		comboBox.setBounds(154, 249, 153, 22);
		contentPanel.add(comboBox);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nuevoEmail = new String(textFieldEmail.getText());
				if(!nuevoEmail.isBlank()) {
					c.actualizarUsuarios(nuevoEmail, comboBox.getSelectedItem().toString(), idUsuario);
					dispose();
				}else {
					System.out.println("El email no puede estar vacio");
				}
			}
		});
		btnAceptar.setBounds(378, 182, 89, 23);
		contentPanel.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(378, 249, 89, 23);
		contentPanel.add(btnCancelar);

	}
}
