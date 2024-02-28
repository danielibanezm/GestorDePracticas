package modales;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import modelo.Consultas;
import vista.CentrosVentana;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarTutores extends JDialog {
	DefaultTableModel modeloTabla = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	private int filaTabla;
	private final JPanel contentPanel = new JPanel();
	private JTable jtResultados;
	private Consultas c = new Consultas();
	private MostrarTutores ventana;

	/**
	 * Create the dialog.
	 * @param ventanaActual 
	 */
	public MostrarTutores(CentrosVentana ventanaActual, int idCentro) {
		ventana = this;
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 783, 508);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTutores = new JLabel("Tutores");
			lblTutores.setHorizontalAlignment(SwingConstants.CENTER);
			lblTutores.setForeground(new Color(9, 3, 62));
			lblTutores.setFont(new Font("Lato", Font.PLAIN, 45));
			lblTutores.setBounds(81, 11, 351, 143);
			contentPanel.add(lblTutores);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 152, 526, 280);
		contentPanel.add(scrollPane);
		
		jtResultados = new JTable();
		scrollPane.setViewportView(jtResultados);
		
		JButton btnNuevoTutor = new JButton("Nuevo tutor");
		btnNuevoTutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Añadir tutor
				InsertarTutor dialog = new InsertarTutor(ventana);
				dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnNuevoTutor.setForeground(new Color(9, 3, 62));
		btnNuevoTutor.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNuevoTutor.setBorder(null);
		btnNuevoTutor.setBackground(new Color(254, 86, 86));
		btnNuevoTutor.setBounds(596, 149, 130, 37);
		contentPanel.add(btnNuevoTutor);
		
		JButton btnModificarTutor = new JButton("Modificar tutor");
		btnModificarTutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Modificar tutor
				filaTabla = jtResultados.getSelectedRow();

				if (filaTabla != -1) { // Se ha seleccionado una fila
					ModificacionTutor dialog = new ModificacionTutor(ventana, jtResultados.getValueAt(filaTabla, 2).toString(), Integer.parseInt(jtResultados.getValueAt(filaTabla, 1).toString()));
					dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} else {
					// No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un tutor para poder modificarlo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModificarTutor.setForeground(new Color(9, 3, 62));
		btnModificarTutor.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnModificarTutor.setBorder(null);
		btnModificarTutor.setBackground(new Color(254, 86, 86));
		btnModificarTutor.setBounds(596, 270, 130, 37);
		contentPanel.add(btnModificarTutor);
		
		JButton btnEliminarTutor = new JButton("Eliminar tutor");
		btnEliminarTutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eliminar tutor
				filaTabla = jtResultados.getSelectedRow();

				if (filaTabla != -1) { // Se ha seleccionado una fila
					ModificacionTutor dialog = new ModificacionTutor(ventana, jtResultados.getValueAt(filaTabla, 2).toString(), Integer.parseInt(jtResultados.getValueAt(filaTabla, 1).toString()));
					dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} else {
					// No se ha seleccionado ningún libro por lo tanto se muestra un error.
					JOptionPane.showMessageDialog(null, "Seleccione un tutor para poder eliminarlo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminarTutor.setForeground(new Color(9, 3, 62));
		btnEliminarTutor.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEliminarTutor.setBorder(null);
		btnEliminarTutor.setBackground(new Color(254, 86, 86));
		btnEliminarTutor.setBounds(596, 395, 130, 37);
		contentPanel.add(btnEliminarTutor);
		modeloTabla.setColumnIdentifiers(new Object[] {"id tutor", "id centro", "Nombre"});
		
		jtResultados.setModel(modeloTabla);
		
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
		
		rellenaTabla(idCentro);

		// -------------------------------------------------------------
	}

	public void rellenaTabla(int idCentro) {
		modeloTabla.setRowCount(0);
		
	}
}
