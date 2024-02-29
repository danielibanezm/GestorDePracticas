package modales;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.MaskFormatter;

import modelo.Consultas;
import vista.PracticasVentana;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InsertarPractica extends JDialog {
	DefaultTableModel modeloTablaEmpresa = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	DefaultTableModel modeloTablaAlumno = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private final JPanel contentPanel = new JPanel();
	Consultas c = new Consultas();
	private JTable tablaEmpresas;
	private JTable tableAlumnos;
	private JTextField textFieldEmpresas;
	private JTextField textFieldAlumnos;
	private InsertarPractica dialogActual;
	private int idAlumno = -1;
	private int idEmpresa = -1;
	private Date fechaInicio;
	private Date fechaFinal;
	private MaskFormatter fecha;
	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * Create the dialog.
	 */
	public InsertarPractica(PracticasVentana ventana, int idCentro) {
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
		
		try {
			fecha = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		JFormattedTextField formattedTextFieldFechaInicial = new JFormattedTextField(fecha);
		formattedTextFieldFechaInicial.setBounds(478, 251, 122, 20);
		contentPanel.add(formattedTextFieldFechaInicial);
		
		JFormattedTextField formattedTextFieldFechaFinal = new JFormattedTextField(fecha);
		formattedTextFieldFechaFinal.setBounds(478, 392, 122, 20);
		contentPanel.add(formattedTextFieldFechaFinal);
		
		System.out.println(c.cogeUltimoIdAnexo());
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setForeground(new Color(0, 0, 0));
		btnContinuar.setBackground(new Color(254, 86, 86));
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (idEmpresa != -1 && idAlumno != -1 && formattedTextFieldFechaFinal.getText().charAt(0) != ' ' && formattedTextFieldFechaInicial.getText().charAt(0) != ' ') {
					try {
						fechaFinal = (Date) formatoFecha.parse(formattedTextFieldFechaFinal.getText());
						fechaInicio = (Date) formatoFecha.parse(formattedTextFieldFechaInicial.getText());
					} catch (ParseException e1) {
						// TODO Bloque catch generado automáticamente
						e1.printStackTrace();
					}
					InsertarAnexos dialog = new InsertarAnexos(ventana, dialogActual);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Los datos son incorrectos o incompletos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnContinuar.setBounds(739, 531, 89, 23);
		contentPanel.add(btnContinuar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(109, 200, 260, 273);
		contentPanel.add(scrollPane);
		
		tablaEmpresas = new JTable();
		tablaEmpresas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idEmpresa = Integer.parseInt(tablaEmpresas.getValueAt(tablaEmpresas.getSelectedRow(), 0).toString());
			}
		});
		scrollPane.setViewportView(tablaEmpresas);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(643, 200, 260, 273);
		contentPanel.add(scrollPane_1);
		
		tableAlumnos = new JTable();
		tableAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idAlumno = Integer.parseInt(tableAlumnos.getValueAt(tableAlumnos.getSelectedRow(), 0).toString());
			}
		});
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
		
		
		modeloTablaAlumno.setColumnIdentifiers(new Object[] {"id alumno", "Nombre"});
//
		tableAlumnos.setModel(modeloTablaAlumno);
//
		tableAlumnos.getColumnModel().getColumn(0).setMaxWidth(0);
		tableAlumnos.getColumnModel().getColumn(0).setMinWidth(0);
		
		tableAlumnos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		tableAlumnos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		
		JTableHeader encabezadoAlumno = tableAlumnos.getTableHeader();
		Color rojoClaro = new Color(255, 157, 157);
		Color darkBlue = new Color(9, 3, 62);
		encabezadoAlumno.setBackground(rojoClaro);
		encabezadoAlumno.setForeground(darkBlue);
		encabezadoAlumno.setFont(new Font("Verdana", Font.BOLD, 13));
		
		modeloTablaEmpresa.setColumnIdentifiers(new Object[] {"id empresa", "Nombre"});
//
		tablaEmpresas.setModel(modeloTablaEmpresa);
//
		tablaEmpresas.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaEmpresas.getColumnModel().getColumn(0).setMinWidth(0);
		
		tablaEmpresas.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		tablaEmpresas.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		
		JTableHeader encabezadoEmpresa = tablaEmpresas.getTableHeader();
		encabezadoEmpresa.setBackground(rojoClaro);
		encabezadoEmpresa.setForeground(darkBlue);
		encabezadoEmpresa.setFont(new Font("Verdana", Font.BOLD, 13));
		rellenaTablaAlumno(idCentro);
		rellenaTablaEmpresa(idCentro);
	}
	public void rellenaTablaAlumno(int idCentro) {
		modeloTablaAlumno.setRowCount(0);
		c.rellenarNombreAlumnos(modeloTablaAlumno, idCentro);
	}
	public void rellenaTablaEmpresa(int idCentro) {
		modeloTablaEmpresa.setRowCount(0);
		c.rellenarNombreEmpresas(modeloTablaEmpresa, idCentro);
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
}
