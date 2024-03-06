package modales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Consultas;

public class InsertarCV extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Consultas c = new Consultas();

	/**
	 * Create the dialog.
	 */
	private File cv;
	private int i;
	public InsertarCV(int idCentro, int idAlumno) {
		setModal(true);
		setBounds(100, 100, 791, 603);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Insertar CV");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 45));
		lblTitulo.setForeground(new Color(9, 3, 62));
		lblTitulo.setBounds(194, -2, 359, 92);
		contentPanel.add(lblTitulo);

		JLabel lblCV = new JLabel("Currículum Vitae");
		lblCV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCV.setBounds(141, 313, 343, 25);
		contentPanel.add(lblCV);
		
	
		i = 1;
		JButton btnSeleccionarAnexo = new JButton("Seleccionar");
		btnSeleccionarAnexo.setForeground(new Color(0, 0, 0));
		btnSeleccionarAnexo.setBackground(new Color(255, 157, 157));
		btnSeleccionarAnexo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int valor = chooser.showOpenDialog(null);
				if (valor == JFileChooser.APPROVE_OPTION) {
						i++;
						cv = chooser.getSelectedFile();
						lblCV.setText("CV " + i + " " + cv.getName());
				}
			}
		});
		btnSeleccionarAnexo.setBounds(597, 313, 107, 23);
		contentPanel.add(btnSeleccionarAnexo);
		
		JLabel lblAnexo3Seleccionado = new JLabel("");
		lblAnexo3Seleccionado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo3Seleccionado.setBounds(244, 313, 240, 25);
		contentPanel.add(lblAnexo3Seleccionado);
		
		JLabel lblSubTitulo = new JLabel("Introduce el fichero del CV");
		lblSubTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubTitulo.setForeground(new Color(0, 0, 0));
		lblSubTitulo.setFont(new Font("Dialog", Font.PLAIN, 25));
		lblSubTitulo.setBounds(167, 74, 414, 92);
		contentPanel.add(lblSubTitulo);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setForeground(new Color(0, 0, 0));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAtras.setBackground(new Color(255, 157, 157));
		btnAtras.setBounds(99, 515, 89, 23);
		contentPanel.add(btnAtras);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setForeground(new Color(0, 0, 0));
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.insertaCV(idCentro, idAlumno, cv);
				dispose();
			}
		});
		btnFinalizar.setBackground(new Color(254, 86, 86));
		btnFinalizar.setBounds(597, 515, 89, 23);
		contentPanel.add(btnFinalizar);
	}
}
