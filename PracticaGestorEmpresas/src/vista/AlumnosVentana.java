package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import modales.EditarAlumno;
import modales.InsertarAlumno;
import modelo.Alumno;
import modelo.Consultas;

public class AlumnosVentana extends JPanel {

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private String consulta = "";

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JButton btnNuevaAlumnos;
	private JButton btnEditarAlumnos;
	private JButton btnBorrarAlumnos;
	private EditarAlumno editarAlumno;
	private Consultas bd = new Consultas();

	private JTable jtResultados;
	private int filaTabla;

	
	public AlumnosVentana(Ventana ventana, boolean esAdmin, int idCentro) {
		InsertarAlumno insertarAlumno = new InsertarAlumno(idCentro, modeloTabla, ventana, esAdmin);
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

		JLabel lblConsultar = new JLabel("Alumnos");
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
		btnNuevaAlumnos = new JButton("Nuevo alumno");
		btnNuevaAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (insertarAlumno != null) {
					insertarAlumno.setVisible(true);
				}			
			}
		});
		// ----------------------------------------------

		btnNuevaAlumnos.setForeground(new Color(9, 3, 62));
		btnNuevaAlumnos.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNuevaAlumnos.setBorder(null);
		btnNuevaAlumnos.setBackground(new Color(254, 86, 86));
		btnNuevaAlumnos.setBounds(1134, 258, 130, 37);
		add(btnNuevaAlumnos);

		// -- EDITAR ALUMNO --
		btnEditarAlumnos = new JButton("Editar alumno");
		btnEditarAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();
				if (filaTabla != -1) { // Se ha seleccionado una fila
					
					String alumno = (String) modeloTabla.getValueAt(filaTabla, 0);
	                String centro = (String) modeloTabla.getValueAt(filaTabla, 1);
	                String dni = (String) modeloTabla.getValueAt(filaTabla, 2);
	                String valido = (String) modeloTabla.getValueAt(filaTabla, 3);
	                String ss = (String) modeloTabla.getValueAt(filaTabla, 4);
	                String ciclo = (String) modeloTabla.getValueAt(filaTabla, 5);
	                String anio = (String) modeloTabla.getValueAt(filaTabla, 6);	                
	                
	                Alumno alumnoSeleccionado = new Alumno();
	                alumnoSeleccionado.setAlumno(alumno);
	                
	                //int idC = bd.buscaIDCentro(centro);	                
	                alumnoSeleccionado.setCentro(centro);	                
	                alumnoSeleccionado.setValido(valido);
	                alumnoSeleccionado.setDni(dni);
	                alumnoSeleccionado.setSs(ss);
	                alumnoSeleccionado.setCiclo(ciclo);
	                alumnoSeleccionado.setAnio(anio);
	               
	                
	                editarAlumno = new EditarAlumno(alumnoSeleccionado, modeloTabla, filaTabla, esAdmin);
	                editarAlumno.setVisible(true);
					
				} else {
					// No se ha seleccionado ningún socio por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un alumno para editarlo", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ---------------------------------------

		btnEditarAlumnos.setForeground(new Color(9, 3, 62));
		btnEditarAlumnos.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEditarAlumnos.setBorder(null);
		btnEditarAlumnos.setBackground(new Color(254, 86, 86));
		btnEditarAlumnos.setBounds(1134, 396, 130, 37);
		add(btnEditarAlumnos);

		// -- BOTÓN ELIMINAR ALUMNO --
		btnBorrarAlumnos = new JButton("Borrar alumno");
		btnBorrarAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();
				if (filaTabla != -1) {
					String alumno = (String) modeloTabla.getValueAt(filaTabla, 0);
	                String centro = (String) modeloTabla.getValueAt(filaTabla, 1);
	                String dni = (String) modeloTabla.getValueAt(filaTabla, 2);
	                String valido = (String) modeloTabla.getValueAt(filaTabla, 3);
	                String ss = (String) modeloTabla.getValueAt(filaTabla, 4);
	                String ciclo = (String) modeloTabla.getValueAt(filaTabla, 5);
	                String anio = (String) modeloTabla.getValueAt(filaTabla, 6);
	                
					Alumno alumnoO = new Alumno(alumno, centro, dni, valido, ss, ciclo,
							anio, false);
					int idAlumno = bd.obtenerIdAlumno(alumnoO);

					if (idAlumno != -1) {
						bd.borrarAlumnoLogico(idAlumno);
						modeloTabla.removeRow(filaTabla);
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo obtener el ID del alumno", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}  else {
					// No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un alumno para poder eliminarlo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ----------------------------------------------------

		btnBorrarAlumnos.setForeground(new Color(9, 3, 62));
		btnBorrarAlumnos.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBorrarAlumnos.setBorder(null);
		btnBorrarAlumnos.setBackground(new Color(254, 86, 86));
		btnBorrarAlumnos.setBounds(1134, 525, 130, 37);
		add(btnBorrarAlumnos);

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
		modeloTabla.setColumnIdentifiers(new Object[] {"Alumno", "Centro", "DNI", "Valido", "Seg.Social", "Ciclo", "Año"});

		jtResultados.setModel(modeloTabla);

		jtResultados.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(2).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(3).setPreferredWidth(120);
		jtResultados.getColumnModel().getColumn(4).setPreferredWidth(80);
		jtResultados.getColumnModel().getColumn(5).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(6).setPreferredWidth(100);
		
//		jtResultados.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
//		jtResultados.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
//		jtResultados.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
//		jtResultados.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);

		JTableHeader encabezado = jtResultados.getTableHeader();
		Color rojoClaro = new Color(255, 157, 157);
		Color darkBlue = new Color(9, 3, 62);
		encabezado.setBackground(rojoClaro);
		encabezado.setForeground(darkBlue);
		encabezado.setFont(new Font("Verdana", Font.BOLD, 13));

		jtResultados.getTableHeader().setResizingAllowed(false);
		jtResultados.getTableHeader().setReorderingAllowed(false);
		
		rellenaTabla(idCentro);

		// -------------------------------------------------------------
	}

	public void rellenaTabla(int idCentro) {
		modeloTabla.setRowCount(0);

		ArrayList<Alumno> alumnos = bd.cogeAlumno();

		for (Alumno alumno : alumnos) {
			if (!alumno.isEliminado()) { // Solo añade empresas que no estén marcadas como eliminadas
				modeloTabla.addRow(new Object[] {alumno.getAlumno(), alumno.getCentro(), alumno.getDni(),
						alumno.getValido(), alumno.getSs(), alumno.getCiclo(),
						alumno.getAnio()});
			}
		}

		modeloTabla.fireTableDataChanged();
	}


	
}
