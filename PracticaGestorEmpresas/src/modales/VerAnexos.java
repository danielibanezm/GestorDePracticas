package modales;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Consultas;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VerAnexos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Consultas c = new Consultas();
	private File anexo2_1 = null;
	private File anexo2_2 = null;
	private File anexo3 = null;
	private File anexo8 = null;
	private Desktop abreFichero = Desktop.getDesktop();
	
	public VerAnexos(int idPractica) {
		setBounds(100, 100, 765, 454);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnVer2_1 = new JButton("Ver");
			btnVer2_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					anexo2_1 = c.leeFichero("anexo2_1.pdf", c.obtenAnexo2_1(idPractica));
					try {
						abreFichero.open(anexo2_1);
					} catch (IOException e1) {
						// TODO Bloque catch generado automáticamente
						e1.printStackTrace();
					}
				}
			});
			btnVer2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnVer2_1.setBounds(69, 252, 97, 38);
			btnVer2_1.setForeground(Color.BLACK);
			btnVer2_1.setBackground(new Color(255, 157, 157));
			contentPanel.add(btnVer2_1);
		}
		
		JLabel lblAnexo2_1 = new JLabel("Anexo 2.1");
		lblAnexo2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnexo2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo2_1.setBounds(69, 219, 97, 28);
		contentPanel.add(lblAnexo2_1);
		
		JButton btnVer2_2 = new JButton("Ver");
		btnVer2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anexo2_2 = c.leeFichero("anexo2_2.pdf", c.obtenAnexo2_2(idPractica));
				try {
					abreFichero.open(anexo2_2);
				} catch (IOException e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
				}
			}
		});
		btnVer2_2.setForeground(Color.BLACK);
		btnVer2_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVer2_2.setBackground(new Color(255, 157, 157));
		btnVer2_2.setBounds(198, 252, 97, 38);
		contentPanel.add(btnVer2_2);
		
		JLabel lblAnexo2_2 = new JLabel("Anexo 2.2");
		lblAnexo2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnexo2_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo2_2.setBounds(198, 219, 97, 28);
		contentPanel.add(lblAnexo2_2);
		
		JButton btnVer3 = new JButton("Ver");
		btnVer3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anexo3 = c.leeFichero("anexo3.pdf", c.obtenAnexo3(idPractica));
				try {
					abreFichero.open(anexo3);
				} catch (IOException e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
				}
			}
		});
		btnVer3.setForeground(Color.BLACK);
		btnVer3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVer3.setBackground(new Color(255, 157, 157));
		btnVer3.setBounds(332, 252, 97, 38);
		contentPanel.add(btnVer3);
		
		JLabel lblAnexo3 = new JLabel("Anexo 3");
		lblAnexo3.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnexo3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo3.setBounds(332, 219, 97, 28);
		contentPanel.add(lblAnexo3);
		
		JButton btnVer8 = new JButton("Ver");
		btnVer8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anexo8 = c.leeFichero("anexo8.pdf", c.obtenAnexo8(idPractica));
				try {
					abreFichero.open(anexo8);
				} catch (IOException e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
				}
			}
		});
		btnVer8.setForeground(Color.BLACK);
		btnVer8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVer8.setBackground(new Color(255, 157, 157));
		btnVer8.setBounds(463, 252, 97, 38);
		contentPanel.add(btnVer8);
		
		JLabel lblAnexo8 = new JLabel("Anexo 8");
		lblAnexo8.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnexo8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo8.setBounds(463, 219, 97, 28);
		contentPanel.add(lblAnexo8);
		
		JButton btnVerSemanales = new JButton("Ver");
		btnVerSemanales.setForeground(Color.BLACK);
		btnVerSemanales.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerSemanales.setBackground(new Color(255, 157, 157));
		btnVerSemanales.setBounds(587, 252, 97, 38);
		contentPanel.add(btnVerSemanales);
		
		JLabel lblAnexo4 = new JLabel("Anexo 4");
		lblAnexo4.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnexo4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnexo4.setBounds(587, 219, 97, 28);
		contentPanel.add(lblAnexo4);
		
		JLabel lblVerAnexos = new JLabel("Ver anexos\r\n");
		lblVerAnexos.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerAnexos.setForeground(new Color(9, 3, 62));
		lblVerAnexos.setFont(new Font("Lato", Font.PLAIN, 45));
		lblVerAnexos.setBounds(198, 11, 359, 92);
		contentPanel.add(lblVerAnexos);
	}
}
