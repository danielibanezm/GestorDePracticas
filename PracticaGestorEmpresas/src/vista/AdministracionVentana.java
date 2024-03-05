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

import modales.CambioContrasenna;
import modales.InsertarUsuario;
import modales.ModificacionUsuario;
import modelo.Consultas;
import modelo.Usuario;

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

public class AdministracionVentana extends JPanel {

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private String consulta = "";

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JButton btnNuevoUsuario;
	private JButton btnEditarUsuario;
	private JButton btnBorrarUsuario;
	private JTable jtResultados;
	private int filaTabla;
	private Consultas c = new Consultas();
	private AdministracionVentana ventanaActual;

//	private BaseDeDatos bd = new BaseDeDatos();
//	private Errores err = new Errores();

	public AdministracionVentana(Ventana ventana, boolean esAdmin, int idCentro) {
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

		JLabel lblConsultar = new JLabel("Administracion");
		lblConsultar.setVerticalAlignment(SwingConstants.TOP);
		lblConsultar.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultar.setForeground(new Color(0, 0, 0));
		lblConsultar.setFont(new Font("Lato", Font.PLAIN, 55));
		lblConsultar.setBounds(414, 24, 377, 81);
		add(lblConsultar);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(32, 160, 1029, 463);
		add(scrollPane);

		// -- AÑADIR SOCIO --
		btnNuevoUsuario = new JButton("Nuevo usuario");
		btnNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarUsuario dialog = new InsertarUsuario(ventanaActual);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		// ----------------------------------------------

		btnNuevoUsuario.setForeground(new Color(9, 3, 62));
		btnNuevoUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNuevoUsuario.setBorder(null);
		btnNuevoUsuario.setBackground(new Color(254, 86, 86));
		btnNuevoUsuario.setBounds(1134, 258, 130, 37);
		add(btnNuevoUsuario);

		// -- EDITAR SOCIO --
		btnEditarUsuario = new JButton("Editar usuario");
		btnEditarUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();
				if (filaTabla != -1) { // Se ha seleccionado una fila
					ModificacionUsuario dialog = new ModificacionUsuario(ventanaActual, 
							Integer.parseInt(jtResultados.getValueAt(filaTabla, 0).toString()), 
							Integer.parseInt(jtResultados.getValueAt(filaTabla, 1).toString()),
							jtResultados.getValueAt(filaTabla, 2).toString()
							);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} else {
					// No se ha seleccionado ningún socio por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un usuario para editarlo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ---------------------------------------

		btnEditarUsuario.setForeground(new Color(9, 3, 62));
		btnEditarUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEditarUsuario.setBorder(null);
		btnEditarUsuario.setBackground(new Color(254, 86, 86));
		btnEditarUsuario.setBounds(1134, 396, 130, 37);
		add(btnEditarUsuario);

		// -- BOTÓN ELIMINAR SOCIO --
		btnBorrarUsuario = new JButton("Borrar usuario");
		btnBorrarUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();

				if (filaTabla != -1) { // Se ha seleccionado una fila
					c.borradoLogicoUsuario(Integer.parseInt(jtResultados.getValueAt(filaTabla, 0).toString()));
					rellenaTabla(idCentro);
				} else {
					// No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un usuario para poder eliminarlo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ----------------------------------------------------

		btnBorrarUsuario.setForeground(new Color(9, 3, 62));
		btnBorrarUsuario.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBorrarUsuario.setBorder(null);
		btnBorrarUsuario.setBackground(new Color(254, 86, 86));
		btnBorrarUsuario.setBounds(1134, 525, 130, 37);
		add(btnBorrarUsuario);

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
		modeloTabla.setColumnIdentifiers(new Object[] {"Id_usuario", "Id_Centro", "Email", "Perfil"});

		jtResultados.setModel(modeloTabla);
//
		jtResultados.getColumnModel().getColumn(0).setMaxWidth(0);
		jtResultados.getColumnModel().getColumn(1).setMaxWidth(0);
		jtResultados.getColumnModel().getColumn(0).setMinWidth(0);
		jtResultados.getColumnModel().getColumn(1).setMinWidth(0);
		
		jtResultados.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		jtResultados.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
		jtResultados.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		jtResultados.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
		
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
		c.rellenarUsuarios(modeloTabla, idCentro);
		
	}

	
}
