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
import vista.AdministracionVentana;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class InsertarUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Consultas c = new Consultas();
	private JTextField textFieldEmail;

	/**
	 * Create the dialog.
	 */
	public InsertarUsuario(AdministracionVentana ventana) {
		setBounds(100, 100, 649, 403);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Insertar usuario");
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 45));
		lblTitulo.setForeground(new Color(9, 3, 62));
		lblTitulo.setBounds(136, 11, 359, 143);
		contentPanel.add(lblTitulo);
		
		JLabel lblRepiteContra = new JLabel("Perfil de usuario");
		lblRepiteContra.setFont(new Font("Lato", Font.PLAIN, 15));
		lblRepiteContra.setBounds(154, 224, 188, 14);
		contentPanel.add(lblRepiteContra);
		
		JLabel lblNuevoEmail = new JLabel("Email");
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
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(154, 307, 89, 23);
		contentPanel.add(btnCancelar);
		
		JComboBox comboBoxCentros = new JComboBox();
		comboBoxCentros.setBounds(352, 183, 153, 22);
		contentPanel.add(comboBoxCentros);
		
		rellenarComboCentros(comboBoxCentros);
		
		JLabel lblCentro = new JLabel("Centro");
		lblCentro.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblCentro.setBounds(352, 158, 188, 14);
		contentPanel.add(lblCentro);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = new String(textFieldEmail.getText());
				if(!email.isBlank()) {
					c.insertarUsuario(email, comboBox.getSelectedItem().toString(), c.cogeIdCentro(comboBoxCentros.getSelectedItem().toString()));
					ventana.rellenaTabla(c.cogeIdCentro(comboBoxCentros.getSelectedItem().toString()));
					dispose();
				}else {
					System.out.println("El email no puede estar vacio");
				}
			}
		});
		btnAceptar.setBounds(374, 307, 89, 23);
		contentPanel.add(btnAceptar);
	}
	private void rellenarComboCentros(JComboBox comboCentro) {
		ArrayList<String> arrlCentros = c.cogeNombreCentros();
		for (String centro : arrlCentros) {
			comboCentro.addItem(centro);
		}
	}
}
