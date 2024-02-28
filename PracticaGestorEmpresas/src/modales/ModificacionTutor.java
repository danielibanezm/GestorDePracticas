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
import vista.CentrosVentana;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class ModificacionTutor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Consultas c = new Consultas();
	private JTextField textFieldNombre;

	/**
	 * Create the dialog.
	 */
	public ModificacionTutor(MostrarTutores ventana, String nombre, int idCentro) {
		setModal(true);
		setBounds(100, 100, 649, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Modificar tutor");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 45));
		lblTitulo.setForeground(new Color(9, 3, 62));
		lblTitulo.setBounds(73, 11, 472, 143);
		contentPanel.add(lblTitulo);
		
		JLabel lblNuevoNombre = new JLabel("Nuevo nombre");
		lblNuevoNombre.setFont(new Font("Lato", Font.PLAIN, 15));
		lblNuevoNombre.setBounds(73, 158, 188, 14);
		contentPanel.add(lblNuevoNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(73, 183, 209, 20);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(255, 157, 157));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//actualizar tutor
				
			}
		});
		btnAceptar.setBounds(334, 182, 89, 23);
		contentPanel.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 157, 157));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(456, 182, 89, 23);
		contentPanel.add(btnCancelar);
		
		JLabel lblAntiguoNombre = new JLabel("Antiguo nombre <dynamic>");
		lblAntiguoNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblAntiguoNombre.setBounds(73, 125, 188, 29);
		contentPanel.add(lblAntiguoNombre);

	}
}
