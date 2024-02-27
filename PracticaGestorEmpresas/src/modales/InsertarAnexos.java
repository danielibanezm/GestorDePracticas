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
import vista.PracticasVentana;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class InsertarAnexos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Consultas c = new Consultas();

	/**
	 * Create the dialog.
	 */
	public InsertarAnexos(PracticasVentana ventana, InsertarPractica dialogAterior) {
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
		
		JLabel lblAnexo1 = new JLabel("Anexo 1");
		lblAnexo1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAnexo1.setBounds(91, 183, 85, 25);
		contentPanel.add(lblAnexo1);
		
		JLabel lblAnexo1Seleccionado = new JLabel("");
		lblAnexo1Seleccionado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo1Seleccionado.setBounds(194, 183, 172, 25);
		contentPanel.add(lblAnexo1Seleccionado);
		
		JButton btnSeleccionarAnexo = new JButton("Seleccionar");
		btnSeleccionarAnexo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSeleccionarAnexo.setBounds(466, 308, 89, 23);
		contentPanel.add(btnSeleccionarAnexo);
		
		JLabel lblAnexo2_1 = new JLabel("Anexo 2.1");
		lblAnexo2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAnexo2_1.setBounds(91, 240, 93, 25);
		contentPanel.add(lblAnexo2_1);
		
		JLabel lblAnexo2_1Seleccionado = new JLabel("");
		lblAnexo2_1Seleccionado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo2_1Seleccionado.setBounds(194, 240, 172, 25);
		contentPanel.add(lblAnexo2_1Seleccionado);
		
		JLabel lblAnexo2_2 = new JLabel("Anexo 2.2");
		lblAnexo2_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAnexo2_2.setBounds(91, 303, 93, 25);
		contentPanel.add(lblAnexo2_2);
		
		JLabel lblAnexo2_2Seleccionado = new JLabel("");
		lblAnexo2_2Seleccionado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo2_2Seleccionado.setBounds(194, 303, 172, 25);
		contentPanel.add(lblAnexo2_2Seleccionado);
		
		JLabel lblAnexo3 = new JLabel("Anexo 3");
		lblAnexo3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAnexo3.setBounds(91, 354, 85, 25);
		contentPanel.add(lblAnexo3);
		
		JLabel lblAnexo3Seleccionado = new JLabel("");
		lblAnexo3Seleccionado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo3Seleccionado.setBounds(194, 354, 172, 25);
		contentPanel.add(lblAnexo3Seleccionado);
		
		JLabel lblAnexo8 = new JLabel("Anexo 8");
		lblAnexo8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAnexo8.setBounds(91, 411, 85, 25);
		contentPanel.add(lblAnexo8);
		
		JLabel lblAnexo8Seleccionado = new JLabel("");
		lblAnexo8Seleccionado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo8Seleccionado.setBounds(194, 411, 172, 25);
		contentPanel.add(lblAnexo8Seleccionado);
		
		JLabel lblSubTitulo = new JLabel("Seleccion de anexos");
		lblSubTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubTitulo.setForeground(new Color(0, 0, 0));
		lblSubTitulo.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblSubTitulo.setBounds(194, 80, 359, 92);
		contentPanel.add(lblSubTitulo);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				dialogAterior.setVisible(true);
			}
		});
		btnAtras.setBackground(new Color(255, 255, 255));
		btnAtras.setBounds(99, 515, 89, 23);
		contentPanel.add(btnAtras);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Insertar la practica y los anexos
				dialogAterior.dispose();
				dispose();
			}
		});
		btnFinalizar.setBackground(Color.WHITE);
		btnFinalizar.setBounds(597, 515, 89, 23);
		contentPanel.add(btnFinalizar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ANEXO 1", "ANEXO 2.1", "ANEXO 2.2", "ANEXO 3", "ANEXO 8"}));
		comboBox.setBounds(601, 308, 85, 22);
		contentPanel.add(comboBox);
	}
}
