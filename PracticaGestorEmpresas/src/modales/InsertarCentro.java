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
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class InsertarCentro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Consultas c = new Consultas();
	private JTextField textFieldNombre;
	private JTextField textFieldCodigo;

	/**
	 * Create the dialog.
	 */
	public InsertarCentro(CentrosVentana ventana) {
		setModal(true);
		setBounds(100, 100, 649, 403);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Insertar centro");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 45));
		lblTitulo.setForeground(new Color(9, 3, 62));
		lblTitulo.setBounds(154, 11, 351, 143);
		contentPanel.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Lato", Font.PLAIN, 15));
		lblNombre.setBounds(154, 152, 188, 20);
		contentPanel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(154, 183, 153, 20);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 157, 157));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(154, 307, 89, 23);
		contentPanel.add(btnCancelar);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblCodigo.setBounds(352, 152, 188, 20);
		contentPanel.add(lblCodigo);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setColumns(10);
		textFieldCodigo.setBounds(352, 183, 153, 20);
		contentPanel.add(textFieldCodigo);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(255, 157, 157));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldCodigo.getText().length() < 9 && textFieldNombre.getText().length() < 20) {
					c.insertarCentro(textFieldCodigo.getText(), textFieldNombre.getText());
					ventana.rellenaTabla();
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Longitud de los campos demasiado larga");
				}
			}
		});
		btnAceptar.setBounds(416, 307, 89, 23);
		contentPanel.add(btnAceptar);
		
	}
}
