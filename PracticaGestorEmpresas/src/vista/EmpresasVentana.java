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

public class EmpresasVentana extends JPanel {

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private String consulta = "";

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JButton btnNuevaEmpresa;
	private JButton btnEditarEmpresa;
	private JButton btnBorrarEmpresa;
	private JTable jtResultados;
	private int filaTabla;

//	private BaseDeDatos bd = new BaseDeDatos();
//	private Errores err = new Errores();

	public EmpresasVentana(Ventana ventana, boolean esAdmin, String idBib) {
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		// -- VOLVER AL MENÚ --
		JButton btnMen = new JButton("MENÚ");
		btnMen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new Menu(ventana, esAdmin, idBib));
			}
		});
		// ------------------------------------------------------------

		btnMen.setForeground(Color.BLACK);
		btnMen.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnMen.setBorder(new LineBorder(new Color(233, 1, 1), 2, true));
		btnMen.setBackground(Color.WHITE);
		btnMen.setBounds(32, 24, 79, 37);
		add(btnMen);

		JLabel lblConsultar = new JLabel("Empresas");
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
		btnNuevaEmpresa = new JButton("Nueva empresa");
		btnNuevaEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		// ----------------------------------------------

		btnNuevaEmpresa.setForeground(new Color(9, 3, 62));
		btnNuevaEmpresa.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNuevaEmpresa.setBorder(null);
		btnNuevaEmpresa.setBackground(new Color(254, 86, 86));
		btnNuevaEmpresa.setBounds(1134, 258, 130, 37);
		add(btnNuevaEmpresa);

		// -- EDITAR SOCIO --
		btnEditarEmpresa = new JButton("Editar empresa");
		btnEditarEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();
				if (filaTabla != -1) { // Se ha seleccionado una fila

				} else {
					// No se ha seleccionado ningún socio por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un socio para editarlo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ---------------------------------------

		btnEditarEmpresa.setForeground(new Color(9, 3, 62));
		btnEditarEmpresa.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEditarEmpresa.setBorder(null);
		btnEditarEmpresa.setBackground(new Color(254, 86, 86));
		btnEditarEmpresa.setBounds(1134, 396, 130, 37);
		add(btnEditarEmpresa);

		// -- BOTÓN ELIMINAR SOCIO --
		btnBorrarEmpresa = new JButton("Borrar empresa");
		btnBorrarEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();

				if (filaTabla != -1) { // Se ha seleccionado una fila
					eliminar(filaTabla, idBib);

				} else {
					// No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un socio para poder eliminarlo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ----------------------------------------------------

		btnBorrarEmpresa.setForeground(new Color(9, 3, 62));
		btnBorrarEmpresa.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBorrarEmpresa.setBorder(null);
		btnBorrarEmpresa.setBackground(new Color(254, 86, 86));
		btnBorrarEmpresa.setBounds(1134, 525, 130, 37);
		add(btnBorrarEmpresa);

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
//		modeloTabla.setColumnIdentifiers(new Object[] { });
//
//		jtResultados.setModel(modeloTabla);
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
		
		rellenaTabla(idBib);

		// -------------------------------------------------------------
	}

	public void rellenaTabla(String idBib) {
		modeloTabla.setRowCount(0);
	}

	public void eliminar(int filaTabla, String idBib) {
	}

	
}
