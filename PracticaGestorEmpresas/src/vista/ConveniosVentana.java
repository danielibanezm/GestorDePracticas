package vista;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConveniosVentana extends JPanel {

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private String consulta = "";

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JButton btnMostrarConvenio;
	private JTable jtResultados;
	private int filaTabla;
	private JLabel lblBuscarEmpresa;
	private JTextField textFieldEmpresa;

//	private BaseDeDatos bd = new BaseDeDatos();
//	private Errores err = new Errores();

	public ConveniosVentana(Ventana ventana, boolean esAdmin, int idCentro) {
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		// -- VOLVER AL MENÚ --
		JButton btnMen = new JButton("MENÚ");
		btnMen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new Menu(ventana, esAdmin, idCentro));
			}
		});
		// ------------------------------------------------------------

		btnMen.setForeground(Color.BLACK);
		btnMen.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnMen.setBorder(new LineBorder(new Color(233, 1, 1), 2, true));
		btnMen.setBackground(Color.WHITE);
		btnMen.setBounds(32, 24, 79, 37);
		add(btnMen);

		JLabel lblConsultar = new JLabel("Convenios");
		lblConsultar.setVerticalAlignment(SwingConstants.TOP);
		lblConsultar.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultar.setForeground(new Color(0, 0, 0));
		lblConsultar.setFont(new Font("Lato", Font.PLAIN, 55));
		lblConsultar.setBounds(414, 24, 586, 81);
		add(lblConsultar);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(32, 160, 1029, 463);
		add(scrollPane);

		// -- AÑADIR SOCIO --
		btnMostrarConvenio = new JButton("Mostrar convenio");
		btnMostrarConvenio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		// ----------------------------------------------

		btnMostrarConvenio.setForeground(new Color(9, 3, 62));
		btnMostrarConvenio.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnMostrarConvenio.setBorder(null);
		btnMostrarConvenio.setBackground(new Color(254, 86, 86));
		btnMostrarConvenio.setBounds(1134, 258, 130, 37);
		add(btnMostrarConvenio);

		// -------------------------- JTABLE --------------------------------------

		// -- SELECCIONAR SOCIO DE LA TABLA --
		jtResultados = new JTable();
		jtResultados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {


			}
		});
		// ------------------

		jtResultados.setForeground(new Color(36, 54, 69));
		jtResultados.setFont(new Font("Verdana", Font.PLAIN, 13));
		jtResultados.setBackground(new Color(255, 255, 255));
		jtResultados.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jtResultados.getTableHeader().setReorderingAllowed(false);

		jtResultados.setRowHeight(30);
		scrollPane.setViewportView(jtResultados);
		
		//poner las columnas necesarias
		modeloTabla.setColumnIdentifiers(new Object[] {"Id convenio", "Empresa"});
//
		jtResultados.setModel(modeloTabla);
		
		lblBuscarEmpresa = new JLabel("Buscar por empresa");
		lblBuscarEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBuscarEmpresa.setBounds(152, 125, 195, 24);
		add(lblBuscarEmpresa);
		
		textFieldEmpresa = new JTextField();
		textFieldEmpresa.setBounds(357, 129, 262, 20);
		add(textFieldEmpresa);
		textFieldEmpresa.setColumns(10);
//
//		jtResultados.getColumnModel().getColumn(0).setPreferredWidth(100);
//		jtResultados.getColumnModel().getColumn(1).setPreferredWidth(100);
//		jtResultados.getColumnModel().getColumn(2).setPreferredWidth(100);
//		jtResultados.getColumnModel().getColumn(3).setPreferredWidth(120);
//		jtResultados.getColumnModel().getColumn(4).setPreferredWidth(80);
//		jtResultados.getColumnModel().getColumn(5).setPreferredWidth(100);
//		jtResultados.getColumnModel().getColumn(6).setPreferredWidth(100);
//		jtResultados.getColumnModel().getColumn(7).setPreferredWidth(10);

		JTableHeader encabezado = jtResultados.getTableHeader();
		Color violeta = new Color(230, 217, 240);
		Color darkBlue = new Color(9, 3, 62);
		encabezado.setBackground(violeta);
		encabezado.setForeground(darkBlue);
		encabezado.setFont(new Font("Verdana", Font.BOLD, 13));

		jtResultados.getTableHeader().setResizingAllowed(false);
		jtResultados.getTableHeader().setReorderingAllowed(false);
		
		rellenaTabla(idCentro);

		// -------------------------------------------------------------
	}

	public void rellenaTabla(int idCentro) {
		modeloTabla.setRowCount(0);
	}

	public void eliminar(int filaTabla, String idBib) {
	}
}
