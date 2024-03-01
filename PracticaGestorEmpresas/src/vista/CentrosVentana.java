package vista;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import modales.InsertarCentro;
import modales.ModificacionCentro;
import modales.MostrarTutores;
import modelo.Consultas;

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

public class CentrosVentana extends JPanel {

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private String consulta = "";

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JButton btnNuevoCentro;
	private JButton btnEditarCentro;
	private JButton btnBorrarCentro;
	private JTable jtResultados;
	private int filaTabla;
	private JTextField textField;
	private CentrosVentana ventanaActual;
	private Consultas c = new Consultas();

	public CentrosVentana(Ventana ventana, boolean esAdmin, int idCentro) {
		ventanaActual = this;
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

		JLabel lblConsultar = new JLabel("Centros");
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
		btnNuevoCentro = new JButton("Nuevo centro");
		btnNuevoCentro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InsertarCentro dialog = new InsertarCentro(ventanaActual);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		// ----------------------------------------------

		btnNuevoCentro.setForeground(new Color(9, 3, 62));
		btnNuevoCentro.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNuevoCentro.setBorder(null);
		btnNuevoCentro.setBackground(new Color(254, 86, 86));
		btnNuevoCentro.setBounds(1134, 258, 130, 37);
		add(btnNuevoCentro);

		// -- EDITAR SOCIO --
		btnEditarCentro = new JButton("Editar centro");
		btnEditarCentro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();
				if (filaTabla != -1) { // Se ha seleccionado una fila
					ModificacionCentro dialog = new ModificacionCentro(ventanaActual, jtResultados.getValueAt(filaTabla, 1).toString(), Integer.parseInt(jtResultados.getValueAt(filaTabla, 0).toString()), jtResultados.getValueAt(filaTabla, 2).toString());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} else {
					// No se ha seleccionado ningún socio por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un centro para editarlo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ---------------------------------------

		btnEditarCentro.setForeground(new Color(9, 3, 62));
		btnEditarCentro.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEditarCentro.setBorder(null);
		btnEditarCentro.setBackground(new Color(254, 86, 86));
		btnEditarCentro.setBounds(1134, 396, 130, 37);
		add(btnEditarCentro);

		// -- BOTÓN ELIMINAR SOCIO --
		btnBorrarCentro = new JButton("Borrar centro");
		btnBorrarCentro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();

				if (filaTabla != -1) { // Se ha seleccionado una fila
					c.borradoLogicoCentro(Integer.parseInt(jtResultados.getValueAt(filaTabla, 0).toString()));
				} else {
					// No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un centro para poder eliminarlo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ----------------------------------------------------

		btnBorrarCentro.setForeground(new Color(9, 3, 62));
		btnBorrarCentro.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBorrarCentro.setBorder(null);
		btnBorrarCentro.setBackground(new Color(254, 86, 86));
		btnBorrarCentro.setBounds(1134, 525, 130, 37);
		add(btnBorrarCentro);

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
		
		JButton btnTutores = new JButton("Ver tutores");
		btnTutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filaTabla = jtResultados.getSelectedRow();

				if (filaTabla != -1) { // Se ha seleccionado una fila
					MostrarTutores dialog = new MostrarTutores(ventanaActual, Integer.parseInt(jtResultados.getValueAt(filaTabla, 0).toString()));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} else {
					// No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un centro para poder mostrar los tutores.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnTutores.setForeground(new Color(9, 3, 62));
		btnTutores.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnTutores.setBorder(null);
		btnTutores.setBackground(new Color(254, 86, 86));
		btnTutores.setBounds(1134, 126, 130, 37);
		add(btnTutores);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		textField.setColumns(10);
		textField.setBounds(834, 129, 227, 20);
		add(textField);
		
		JLabel lblCentros = new JLabel("Buscar por centro");
		lblCentros.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCentros.setBounds(618, 124, 187, 25);
		add(lblCentros);
		
		//poner las columnas necesarias
		modeloTabla.setColumnIdentifiers(new Object[] {"id centro", "Nombre", "Codigo"});
//
		jtResultados.setModel(modeloTabla);
//
		jtResultados.getColumnModel().getColumn(0).setMaxWidth(0);
		jtResultados.getColumnModel().getColumn(0).setMinWidth(0);
		
		jtResultados.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		jtResultados.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

		JTableHeader encabezado = jtResultados.getTableHeader();
		Color rojoClaro = new Color(255, 157, 157);
		Color darkBlue = new Color(9, 3, 62);
		encabezado.setBackground(rojoClaro);
		encabezado.setForeground(darkBlue);
		encabezado.setFont(new Font("Verdana", Font.BOLD, 13));

		jtResultados.getTableHeader().setResizingAllowed(false);
		jtResultados.getTableHeader().setReorderingAllowed(false);
		
		rellenaTabla();

		// -------------------------------------------------------------
	}

	public void rellenaTabla() {
		modeloTabla.setRowCount(0);
		c.rellenarCentros(modeloTabla);
	}
}
