package vista;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import modales.InsertarAnexosSemanal;
import modales.InsertarPractica;
import modales.VerAnexos;
import modelo.Consultas;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class PracticasVentana extends JPanel {

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private String consulta = "";

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JButton btnNuevaPractica;
	private JButton btnEditarPractica;
	private JButton btnBorrarPractica;
	private JTable jtResultados;
	private int filaTabla;
	private JLabel lblEmpresa;
	private JTextField textFieldAlumnos;
	private Consultas c = new Consultas();
	private PracticasVentana ventanaActual;

	public PracticasVentana(Ventana ventana, boolean esAdmin, int idCentro) {
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
		btnMen.setBackground(new Color(254, 86, 86));
		btnMen.setBounds(32, 24, 79, 37);
		add(btnMen);

		JLabel lblConsultar = new JLabel("Prácticas");
		lblConsultar.setVerticalAlignment(SwingConstants.TOP);
		lblConsultar.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultar.setForeground(new Color(0, 0, 0));
		lblConsultar.setFont(new Font("Lato", Font.PLAIN, 55));
		lblConsultar.setBounds(414, 24, 569, 81);
		add(lblConsultar);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(32, 160, 1029, 463);
		add(scrollPane);

		// -- AÑADIR PRACTICA --
		btnNuevaPractica = new JButton("Nueva práctica");
		btnNuevaPractica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InsertarPractica dialog = new InsertarPractica(ventanaActual, idCentro);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		// ----------------------------------------------

		btnNuevaPractica.setForeground(new Color(9, 3, 62));
		btnNuevaPractica.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNuevaPractica.setBorder(null);
		btnNuevaPractica.setBackground(new Color(254, 86, 86));
		btnNuevaPractica.setBounds(1134, 258, 130, 37);
		add(btnNuevaPractica);

		// -- EDITAR PRACTICA --
		btnEditarPractica = new JButton("Editar práctica");
		btnEditarPractica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();
				if (filaTabla != -1) { // Se ha seleccionado una fila

				} else {
					// No se ha seleccionado ningún socio por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione una practica para editarla", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ---------------------------------------

		btnEditarPractica.setForeground(new Color(9, 3, 62));
		btnEditarPractica.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEditarPractica.setBorder(null);
		btnEditarPractica.setBackground(new Color(254, 86, 86));
		btnEditarPractica.setBounds(1134, 396, 130, 37);
		add(btnEditarPractica);

		JButton btnResetFiltros = new JButton("Borrar filtros");

		// -- BOTÓN ELIMINAR SOCIO --
		btnBorrarPractica = new JButton("Borrar práctica");
		btnBorrarPractica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();

				if (filaTabla != -1) { // Se ha seleccionado una fila
					c.borradoLogicoPractica(Integer.parseInt(jtResultados.getValueAt(filaTabla, 0).toString()));
					rellenaTablaActual(idCentro);
				} else {
					// No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione una practica para poder eliminarla.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ----------------------------------------------------

		btnBorrarPractica.setForeground(new Color(9, 3, 62));
		btnBorrarPractica.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBorrarPractica.setBorder(null);
		btnBorrarPractica.setBackground(new Color(254, 86, 86));
		btnBorrarPractica.setBounds(1134, 525, 130, 37);
		add(btnBorrarPractica);

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

		lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmpresa.setBounds(99, 124, 87, 25);
		add(lblEmpresa);

		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(!comboBox.getSelectedItem().toString().equals(comboBox.getItemAt(0).toString())) {
					rellenaTablaActualPorEmpresa(idCentro, comboBox.getSelectedItem().toString());
					textFieldAlumnos.setText("");
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "---EMPRESAS---" }));
		comboBox.setBounds(196, 129, 130, 22);
		add(comboBox);

		JLabel lblAlumnos = new JLabel("Buscar por alumnos");
		lblAlumnos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAlumnos.setBounds(503, 124, 187, 25);
		add(lblAlumnos);

		textFieldAlumnos = new JTextField();
		textFieldAlumnos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				rellenaTablaActualPorNombre(idCentro, textFieldAlumnos.getText());
				comboBox.setSelectedIndex(0);
			}
		});
		textFieldAlumnos.setBounds(719, 129, 227, 20);
		add(textFieldAlumnos);
		textFieldAlumnos.setColumns(10);

		JButton btnVerAnexos = new JButton("Ver anexos");
		btnVerAnexos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ver anexos
				filaTabla = jtResultados.getSelectedRow();

				if (filaTabla != -1) { // Se ha seleccionado una fila
					VerAnexos dialog = new VerAnexos(
							Integer.parseInt(jtResultados.getValueAt(filaTabla, 0).toString()));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} else {
					// No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione una practica para poder ver los anexos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVerAnexos.setForeground(new Color(9, 3, 62));
		btnVerAnexos.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnVerAnexos.setBorder(null);
		btnVerAnexos.setBackground(new Color(254, 86, 86));
		btnVerAnexos.setBounds(252, 634, 130, 37);
		add(btnVerAnexos);

		JButton btnInsertar = new JButton("Insertar seguimiento semanal");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filaTabla = jtResultados.getSelectedRow();

				if (filaTabla != -1) { // Se ha seleccionado una fila
					InsertarAnexosSemanal dialog = new InsertarAnexosSemanal(ventanaActual,
							Integer.parseInt(jtResultados.getValueAt(filaTabla, 0).toString()));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} else {
					// No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione una practica para poder eliminarla.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnInsertar.setForeground(new Color(9, 3, 62));
		btnInsertar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnInsertar.setBorder(null);
		btnInsertar.setBackground(new Color(254, 86, 86));
		btnInsertar.setBounds(629, 634, 227, 37);
		add(btnInsertar);

		// poner las columnas necesarias
		modeloTabla.setColumnIdentifiers(
				new Object[] { "id practicas", "id anexo", "Alumno", "Empresa", "Inicio", "Final" });

		jtResultados.setModel(modeloTabla);

		btnResetFiltros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rellenaTablaActual(idCentro);
				textFieldAlumnos.setText("");
				comboBox.setSelectedIndex(0);
			}
		});
		btnResetFiltros.setBackground(new Color(254, 86, 86));
		btnResetFiltros.setBounds(1134, 122, 130, 37);
		add(btnResetFiltros);

		jtResultados.getColumnModel().getColumn(0).setMaxWidth(0);
		jtResultados.getColumnModel().getColumn(1).setMaxWidth(0);
		jtResultados.getColumnModel().getColumn(0).setMinWidth(0);
		jtResultados.getColumnModel().getColumn(1).setMinWidth(0);

		jtResultados.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		jtResultados.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
		jtResultados.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		jtResultados.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);

		JTableHeader encabezado = jtResultados.getTableHeader();
		Color rojoClaro = new Color(255, 157, 157);
		Color darkBlue = new Color(9, 3, 62);
		encabezado.setBackground(rojoClaro);
		encabezado.setForeground(darkBlue);
		encabezado.setFont(new Font("Verdana", Font.BOLD, 13));

		jtResultados.getTableHeader().setResizingAllowed(false);
		jtResultados.getTableHeader().setReorderingAllowed(false);

		rellenaTablaActual(idCentro);
		rellenarComboCentros(comboBox);

		// -------------------------------------------------------------
	}

	public void rellenaTablaActual(int idCentro) {
		modeloTabla.setRowCount(0);
		c.rellenarPracticasActuales(modeloTabla, idCentro);
	}

	public void rellenaTablaActualPorNombre(int idCentro, String nombre) {
		modeloTabla.setRowCount(0);
		c.rellenarPracticasActualesPorNombre(modeloTabla, idCentro, nombre);
	}

	public void rellenaTablaActualPorEmpresa(int idCentro, String nombre) {
		modeloTabla.setRowCount(0);
		c.rellenarPracticasActualesPorEmpresa(modeloTabla, idCentro, nombre);
	}

	private void rellenarComboCentros(JComboBox comboCentro) {
		ArrayList<String> arrlEmpresas = c.cogeNombreEmpresas();
		for (String empresa : arrlEmpresas) {
			comboCentro.addItem(empresa);
		}
	}
}
