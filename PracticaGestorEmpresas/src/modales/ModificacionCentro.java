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
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ModificacionCentro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Consultas c = new Consultas();
	private JTextField textFieldNombre;
	private JTextField textFieldCodigo;

	/**
	 * Create the dialog.
	 */
	public ModificacionCentro(CentrosVentana ventana, String nombre, int idCentro, String codigo) {
		setModal(true);
		setBounds(100, 100, 649, 403);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Modificar centro");
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 45));
		lblTitulo.setForeground(new Color(9, 3, 62));
		lblTitulo.setBounds(136, 11, 359, 143);
		contentPanel.add(lblTitulo);
		
		JLabel lblNuevoNombre = new JLabel("Nuevo nombre");
		lblNuevoNombre.setFont(new Font("Lato", Font.PLAIN, 15));
		lblNuevoNombre.setBounds(117, 158, 188, 14);
		contentPanel.add(lblNuevoNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(117, 183, 209, 20);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(255, 157, 157));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldCodigo.getText().length() < 9 && textFieldNombre.getText().length() < 20) {
					c.actualizarCentro(idCentro, textFieldCodigo.getText(), textFieldNombre.getText());
					ventana.rellenaTabla();
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Longitud de los campos demasiado larga");
				}
			}
		});
		btnAceptar.setBounds(378, 182, 89, 23);
		contentPanel.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 157, 157));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(378, 289, 89, 23);
		contentPanel.add(btnCancelar);
		
		JLabel lblAntiguoNombre = new JLabel("Antiguo nombre " + nombre);
		lblAntiguoNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblAntiguoNombre.setBounds(117, 125, 188, 20);
		contentPanel.add(lblAntiguoNombre);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setColumns(10);
		textFieldCodigo.setBounds(117, 290, 209, 20);
		contentPanel.add(textFieldCodigo);
		
		JLabel lblNuevoCodigo = new JLabel("Nuevo código");
		lblNuevoCodigo.setFont(new Font("Lato", Font.PLAIN, 15));
		lblNuevoCodigo.setBounds(117, 265, 188, 14);
		contentPanel.add(lblNuevoCodigo);
		
		JLabel lblAntiguoCodigo = new JLabel("Antiguo código " + codigo);
		lblAntiguoCodigo.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblAntiguoCodigo.setBounds(117, 232, 188, 22);
		contentPanel.add(lblAntiguoCodigo);

	}
}
