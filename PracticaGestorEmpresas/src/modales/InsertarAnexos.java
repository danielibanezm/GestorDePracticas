package modales;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import modelo.Consultas;
import modelo.Usuario;
import vista.AdministracionVentana;
import vista.PracticasVentana;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class InsertarAnexos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Consultas c = new Consultas();
	private JComboBox comboBox;
	private File anexo2_1 = null;
	private File anexo2_2 = null;
	private File anexo3 = null;
	private File anexo8 = null;

	/**
	 * Create the dialog.
	 */
	public InsertarAnexos(PracticasVentana ventana, InsertarPractica dialogAterior) {
		setModal(true);
		setBounds(100, 100, 791, 603);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblTitulo = new JLabel("Insertar practica");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 45));
		lblTitulo.setForeground(new Color(9, 3, 62));
		lblTitulo.setBounds(194, -2, 359, 92);
		contentPanel.add(lblTitulo);
		
		JLabel lblAnexo2_1 = new JLabel("Anexo 2.1");
		lblAnexo2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAnexo2_1.setBounds(99, 207, 93, 25);
		contentPanel.add(lblAnexo2_1);

		JLabel lblAnexo2_1Seleccionado = new JLabel("");
		lblAnexo2_1Seleccionado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo2_1Seleccionado.setBounds(202, 207, 172, 25);
		contentPanel.add(lblAnexo2_1Seleccionado);

		JLabel lblAnexo2_2 = new JLabel("Anexo 2.2");
		lblAnexo2_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAnexo2_2.setBounds(99, 270, 93, 25);
		contentPanel.add(lblAnexo2_2);

		JLabel lblAnexo2_2Seleccionado = new JLabel("");
		lblAnexo2_2Seleccionado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo2_2Seleccionado.setBounds(202, 270, 172, 25);
		contentPanel.add(lblAnexo2_2Seleccionado);

		JLabel lblAnexo3 = new JLabel("Anexo 3");
		lblAnexo3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAnexo3.setBounds(99, 321, 85, 25);
		contentPanel.add(lblAnexo3);

		JLabel lblAnexo3Seleccionado = new JLabel("");
		lblAnexo3Seleccionado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo3Seleccionado.setBounds(202, 321, 172, 25);
		contentPanel.add(lblAnexo3Seleccionado);

		JLabel lblAnexo8 = new JLabel("Anexo 8");
		lblAnexo8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAnexo8.setBounds(99, 378, 85, 25);
		contentPanel.add(lblAnexo8);

		JLabel lblAnexo8Seleccionado = new JLabel("");
		lblAnexo8Seleccionado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo8Seleccionado.setBounds(202, 378, 172, 25);
		contentPanel.add(lblAnexo8Seleccionado);

		JButton btnSeleccionarAnexo = new JButton("Seleccionar");
		btnSeleccionarAnexo.setForeground(new Color(0, 0, 0));
		btnSeleccionarAnexo.setBackground(new Color(255, 157, 157));
		btnSeleccionarAnexo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int valor = chooser.showOpenDialog(null);
				if (valor == JFileChooser.APPROVE_OPTION) {
					
					switch (comboBox.getSelectedItem().toString()) {
					case "ANEXO 2.1":
						anexo2_1 = chooser.getSelectedFile();
						lblAnexo2_1Seleccionado.setText(anexo2_1.getName());
						break;
					case "ANEXO 2.2":
						anexo2_2 = chooser.getSelectedFile();
						lblAnexo2_2Seleccionado.setText(anexo2_2.getName());
						break;
					case "ANEXO 3":
						anexo3 = chooser.getSelectedFile();
						lblAnexo3Seleccionado.setText(anexo3.getName());
						break;
					case "ANEXO 8":
						anexo8 = chooser.getSelectedFile();
						lblAnexo8Seleccionado.setText(anexo8.getName());
						break;

					default:
						break;
					}
				}
			}
		});
		btnSeleccionarAnexo.setBounds(466, 308, 89, 23);
		contentPanel.add(btnSeleccionarAnexo);

		JLabel lblSubTitulo = new JLabel("Seleccion de anexos");
		lblSubTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubTitulo.setForeground(new Color(0, 0, 0));
		lblSubTitulo.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblSubTitulo.setBounds(194, 80, 359, 92);
		contentPanel.add(lblSubTitulo);

		JButton btnAtras = new JButton("Atras");
		btnAtras.setForeground(new Color(0, 0, 0));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				dialogAterior.setVisible(true);
			}
		});
		btnAtras.setBackground(new Color(255, 157, 157));
		btnAtras.setBounds(99, 515, 89, 23);
		contentPanel.add(btnAtras);

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setForeground(new Color(0, 0, 0));
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Insertar la practica y los anexos
				int idAnexo = c.insertarAnexos(anexo2_1, anexo2_2, anexo3, anexo8);
				if (idAnexo != -1) {
					c.insertarPractica(idAnexo, dialogAterior.getIdAlumno(), dialogAterior.getIdEmpresa(), dialogAterior.getFechaInicio(), dialogAterior.getFechaFinal());
					dialogAterior.dispose();
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Error al insertar los anexos", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnFinalizar.setBackground(new Color(254, 86, 86));
		btnFinalizar.setBounds(597, 515, 89, 23);
		contentPanel.add(btnFinalizar);

		comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setBackground(new Color(255, 157, 157));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "ANEXO 2.1", "ANEXO 2.2", "ANEXO 3", "ANEXO 8" }));
		comboBox.setBounds(601, 308, 85, 22);
		contentPanel.add(comboBox);
	}
}
