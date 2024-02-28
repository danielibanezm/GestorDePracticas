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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;

import javax.swing.JFormattedTextField;

public class InsertarPractica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Consultas c = new Consultas();
	private JTable tablaEmpresas;
	private JTable tableAlumnos;
	private JTextField textFieldEmpresas;
	private JTextField textFieldAlumnos;
	private InsertarPractica dialogActual;
	private int idAlumno;
	private int idEmpresa;
	private Date fechaInicio;
	private Date fechaFinal;
	/**
	 * Create the dialog.
	 */
	public InsertarPractica(PracticasVentana ventana) {
		setModal(true);
		setBounds(100, 100, 1015, 632);
		dialogActual = this;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Insertar practica");
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 45));
		lblTitulo.setForeground(new Color(9, 3, 62));
		lblTitulo.setBounds(349, 11, 359, 143);
		contentPanel.add(lblTitulo);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setBackground(new Color(255, 157, 157));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(211, 531, 89, 23);
		contentPanel.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Continuar");
		btnAceptar.setForeground(new Color(0, 0, 0));
		btnAceptar.setBackground(new Color(254, 86, 86));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarAnexos dialog = new InsertarAnexos(ventana, dialogActual);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				setVisible(false);
			}
		});
		btnAceptar.setBounds(739, 531, 89, 23);
		contentPanel.add(btnAceptar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(109, 200, 260, 273);
		contentPanel.add(scrollPane);
		
		tablaEmpresas = new JTable();
		scrollPane.setViewportView(tablaEmpresas);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(643, 200, 260, 273);
		contentPanel.add(scrollPane_1);
		
		tableAlumnos = new JTable();
		scrollPane_1.setViewportView(tableAlumnos);
		
		JLabel lblEmpresas = new JLabel("Empresas");
		lblEmpresas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmpresas.setBounds(108, 166, 78, 23);
		contentPanel.add(lblEmpresas);
		
		textFieldEmpresas = new JTextField();
		textFieldEmpresas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		textFieldEmpresas.setBounds(196, 169, 173, 20);
		contentPanel.add(textFieldEmpresas);
		textFieldEmpresas.setColumns(10);
		
		textFieldAlumnos = new JTextField();
		textFieldAlumnos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		textFieldAlumnos.setColumns(10);
		textFieldAlumnos.setBounds(731, 168, 173, 20);
		contentPanel.add(textFieldAlumnos);
		
		JLabel lblAlumnos = new JLabel("Alumnos");
		lblAlumnos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlumnos.setBounds(643, 165, 78, 23);
		contentPanel.add(lblAlumnos);
		
		JLabel lblFechaInicio = new JLabel("Fecha inicio");
		lblFechaInicio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFechaInicio.setBounds(379, 248, 89, 23);
		contentPanel.add(lblFechaInicio);
		
		JLabel lblFechafin = new JLabel("Fecha fin");
		lblFechafin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFechafin.setBounds(379, 389, 78, 23);
		contentPanel.add(lblFechafin);
		
		JFormattedTextField formattedTextFieldFechaInicial = new JFormattedTextField();
		formattedTextFieldFechaInicial.setBounds(478, 251, 122, 20);
		contentPanel.add(formattedTextFieldFechaInicial);
		
		JFormattedTextField formattedTextFieldFechaFinal = new JFormattedTextField();
		formattedTextFieldFechaFinal.setBounds(478, 392, 122, 20);
		contentPanel.add(formattedTextFieldFechaFinal);
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	private void rellenarComboCentros(JComboBox comboCentro) {
		ArrayList<String> arrlCentros = c.cogeNombreCentros();
		for (String centro : arrlCentros) {
			comboCentro.addItem(centro);
		}
	}
}
