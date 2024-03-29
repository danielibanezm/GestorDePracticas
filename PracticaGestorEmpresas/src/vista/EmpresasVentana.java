package vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import modales.EditarEmpresa;
import modales.EditarNecesidad;
import modales.InsertarConvenio;
import modales.InsertarEmpresa;
import modales.InsertarNecesidad;
import modales.VerNecesidad;
import modelo.Consultas;
import modelo.Empresa;
import modelo.Necesidad;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmpresasVentana extends JPanel {

	DefaultTableModel modeloTabla = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private Consultas c = new Consultas();
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JButton btnNuevaEmpresa;
	private JButton btnEditarEmpresa;
	private JButton btnBorrarEmpresa;
	private JTable jtResultados;
	private Empresa empresa = new Empresa();
	private Desktop abreFichero = Desktop.getDesktop();

	private int filaTabla;

	private Consultas bd = new Consultas();
	private EditarEmpresa editarEmpresa;
	private JButton btnInsertarConvenio;

	protected File convenio;

	public EmpresasVentana(Ventana ventana, boolean esAdmin, int idCentro) {
		InsertarEmpresa insertarEmpresa = new InsertarEmpresa(idCentro, modeloTabla, ventana, esAdmin);

		setLayout(null);
		setBackground(Color.WHITE);

		JButton btnMen = new JButton("MENÚ");
		btnMen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new Menu(ventana, esAdmin, idCentro));
			}
		});
		btnMen.setForeground(Color.BLACK);
		btnMen.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnMen.setBackground(new Color(254, 86, 86));
		btnMen.setBounds(32, 24, 79, 37);
		add(btnMen);

		JLabel lblConsultar = new JLabel("Empresas");
		lblConsultar.setVerticalAlignment(SwingConstants.TOP);
		lblConsultar.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsultar.setForeground(new Color(0, 0, 0));
		lblConsultar.setFont(new Font("Lato", Font.PLAIN, 55));
		lblConsultar.setBounds(414, 24, 273, 81);
		add(lblConsultar);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(32, 160, 1029, 463);
		add(scrollPane);

		btnNuevaEmpresa = new JButton("Nueva empresa");
		btnNuevaEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (insertarEmpresa != null) {
					insertarEmpresa.setVisible(true);
				}
			}
		});
		btnNuevaEmpresa.setForeground(new Color(9, 3, 62));
		btnNuevaEmpresa.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNuevaEmpresa.setBorder(null);
		btnNuevaEmpresa.setBackground(new Color(254, 86, 86));
		btnNuevaEmpresa.setBounds(1134, 258, 130, 37);
		add(btnNuevaEmpresa);

		btnEditarEmpresa = new JButton("Editar empresa");
		btnEditarEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filaTabla = jtResultados.getSelectedRow();
				if (filaTabla != -1) {

//<<<<<<< Updated upstream
                	String cif = (String) modeloTabla.getValueAt(filaTabla, 0);
                    String duenno = (String) modeloTabla.getValueAt(filaTabla, 1);
                    String nombre = (String) modeloTabla.getValueAt(filaTabla, 2);
                    String telefono = (String) modeloTabla.getValueAt(filaTabla, 3);
                    String email = (String) modeloTabla.getValueAt(filaTabla, 4);
                    String direccion = (String) modeloTabla.getValueAt(filaTabla, 5);
                    String tutor = (String) modeloTabla.getValueAt(filaTabla, 6);
                    String contacto = (String) modeloTabla.getValueAt(filaTabla, 7);
                	String solicita = (String) modeloTabla.getValueAt(filaTabla, 8);

                    
                    Empresa empresaSeleccionada = new Empresa();
                    empresaSeleccionada.setCIF(cif);
                    empresaSeleccionada.setDuenno(duenno);
                    empresaSeleccionada.setNombre_empresa(nombre);
                    empresaSeleccionada.setTelefono_empresa(telefono);
                    empresaSeleccionada.setEmail_empresa(email);
                    empresaSeleccionada.setDireccion_empresa(direccion);
                    empresaSeleccionada.setTutor_empresa(tutor);
                    empresaSeleccionada.setContacto_empresa(contacto);
                    empresaSeleccionada.setSolicita(solicita);
                    
                    editarEmpresa = new EditarEmpresa(empresaSeleccionada, modeloTabla, filaTabla, esAdmin);
                    editarEmpresa.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una empresa para editar", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
//=======
//					String cif = (String) modeloTabla.getValueAt(filaTabla, 0);
//					String duenno = (String) modeloTabla.getValueAt(filaTabla, 1);
//					String nombre = (String) modeloTabla.getValueAt(filaTabla, 2);
//					String telefono = (String) modeloTabla.getValueAt(filaTabla, 3);
//					String email = (String) modeloTabla.getValueAt(filaTabla, 4);
//					String direccion = (String) modeloTabla.getValueAt(filaTabla, 5);
//					String tutor = (String) modeloTabla.getValueAt(filaTabla, 6);
//					String contacto = (String) modeloTabla.getValueAt(filaTabla, 7);
//>>>>>>> Stashed changes
//
//					Empresa empresaSeleccionada = new Empresa();
//					empresaSeleccionada.setCIF(cif);
//					empresaSeleccionada.setDuenno(duenno);
//					empresaSeleccionada.setNombre_empresa(nombre);
//					empresaSeleccionada.setTelefono_empresa(telefono);
//					empresaSeleccionada.setEmail_empresa(email);
//					empresaSeleccionada.setDireccion_empresa(direccion);
//					empresaSeleccionada.setTutor_empresa(tutor);
//					empresaSeleccionada.setContacto_empresa(contacto);
//
//					editarEmpresa = new EditarEmpresa(empresaSeleccionada, modeloTabla, filaTabla, esAdmin);
//					editarEmpresa.setVisible(true);
//				} else {
//					JOptionPane.showMessageDialog(null, "Seleccione una empresa para editar", "Error",
//							JOptionPane.ERROR_MESSAGE);
//				}
//			}
//		});
//
		btnEditarEmpresa.setForeground(new Color(9, 3, 62));
		btnEditarEmpresa.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEditarEmpresa.setBorder(null);
		btnEditarEmpresa.setBackground(new Color(254, 86, 86));
		btnEditarEmpresa.setBounds(1134, 396, 130, 37);
		add(btnEditarEmpresa);

		btnBorrarEmpresa = new JButton("Borrar empresa");
		btnBorrarEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = jtResultados.getSelectedRow();
				if (filaSeleccionada != -1) {
					String cif = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
					String duenno = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
					String nombre_empresa = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
					String telefono_empresa = (String) modeloTabla.getValueAt(filaSeleccionada, 3);
					String email = (String) modeloTabla.getValueAt(filaSeleccionada, 4);
					String direccion = (String) modeloTabla.getValueAt(filaSeleccionada, 5);
					String tutor = (String) modeloTabla.getValueAt(filaSeleccionada, 6);
					String contacto = (String) modeloTabla.getValueAt(filaSeleccionada, 7);
					String solicita = (String) modeloTabla.getValueAt(filaSeleccionada, 8);
					Empresa empresa = new Empresa(cif, duenno, nombre_empresa, telefono_empresa, direccion, email,
							tutor, contacto, solicita, false);
					String idEmpresa = bd.obtenerIdEmpresa(empresa);

					if (idEmpresa != null) {
						bd.borrarEmpresaLogico(idEmpresa);
						modeloTabla.removeRow(filaSeleccionada);
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo obtener el ID de la empresa", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una empresa para borrar", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnBorrarEmpresa.setForeground(new Color(9, 3, 62));
		btnBorrarEmpresa.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBorrarEmpresa.setBorder(null);
		btnBorrarEmpresa.setBackground(new Color(254, 86, 86));
		btnBorrarEmpresa.setBounds(1134, 525, 130, 37);
		add(btnBorrarEmpresa);

		jtResultados = new JTable();
		jtResultados.setForeground(new Color(36, 54, 69));
		jtResultados.setFont(new Font("Verdana", Font.PLAIN, 13));
		jtResultados.setBackground(new Color(255, 255, 255));
		jtResultados.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jtResultados.getTableHeader().setReorderingAllowed(false);
		jtResultados.setRowHeight(30);
		scrollPane.setViewportView(jtResultados);

		modeloTabla.setColumnIdentifiers(new Object[] { "CIF", "Dueño", "Nombre", "Teléfono", "Email", "Dirección",
				"Tutor", "Contacto", "Solicita" });

		jtResultados.setModel(modeloTabla);

		JButton btnVerConvenio = new JButton("Ver convenio");
		btnVerConvenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = jtResultados.getSelectedRow();
				if (filaSeleccionada != -1) {
					Empresa empresa = new Empresa();
					empresa.setCIF((String) modeloTabla.getValueAt(filaSeleccionada, 0));
					empresa.setDuenno((String) modeloTabla.getValueAt(filaSeleccionada, 1));
					empresa.setEmail_empresa((String) modeloTabla.getValueAt(filaSeleccionada, 4));
					int idConvenio = c.cogeIdConvenioPorIdEmpresa(Integer.parseInt(c.obtenerIdEmpresa(empresa)));
					if(idConvenio != -1) {
						InputStream is = c.obtenConvenio(idConvenio);
						convenio = c.leeFichero("anexo8.pdf", is);
						try {
							abreFichero.open(convenio);
						} catch (IOException e1) {
							// TODO Bloque catch generado automáticamente
							e1.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null, "La empresa seleccionada no tiene un convenio.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una empresa para ver el convenio.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVerConvenio.setForeground(new Color(9, 3, 62));
		btnVerConvenio.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnVerConvenio.setBorder(null);
		btnVerConvenio.setBackground(new Color(254, 86, 86));
		btnVerConvenio.setBounds(1134, 129, 130, 37);
		add(btnVerConvenio);

		btnInsertarConvenio = new JButton("Insertar convenio");
		btnInsertarConvenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = jtResultados.getSelectedRow();
				if (filaSeleccionada != -1) {
					Empresa empresa = new Empresa();
					empresa.setCIF((String) modeloTabla.getValueAt(filaSeleccionada, 0));
					empresa.setDuenno((String) modeloTabla.getValueAt(filaSeleccionada, 1));
					empresa.setEmail_empresa((String) modeloTabla.getValueAt(filaSeleccionada, 4));
					InsertarConvenio dialog = new InsertarConvenio(idCentro,
							Integer.parseInt(c.obtenerIdEmpresa(empresa)));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una empresa para insertar el convenio", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnInsertarConvenio.setForeground(new Color(9, 3, 62));
		btnInsertarConvenio.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnInsertarConvenio.setBorder(null);
		btnInsertarConvenio.setBackground(new Color(254, 86, 86));
		btnInsertarConvenio.setBounds(1134, 640, 130, 37);
		add(btnInsertarConvenio);
		
		JButton btnNuevaNecesidad = new JButton("Nueva necesidad");
		btnNuevaNecesidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = jtResultados.getSelectedRow();
				if (filaSeleccionada != -1) {
					Empresa empresa = new Empresa();
					empresa.setCIF((String) modeloTabla.getValueAt(filaSeleccionada, 0));
					empresa.setDuenno((String) modeloTabla.getValueAt(filaSeleccionada, 1));
					empresa.setEmail_empresa((String) modeloTabla.getValueAt(filaSeleccionada, 4));
					if(c.obtenerNecesidad(c.obtenerIdEmpresa(empresa)) == null) {
						
						InsertarNecesidad dialog = new InsertarNecesidad(Integer.parseInt(c.obtenerIdEmpresa(empresa)), esAdmin);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Esta empresa ya tiene una necesidad asociada.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una empresa para insertar la necesidad", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNuevaNecesidad.setForeground(new Color(9, 3, 62));
		btnNuevaNecesidad.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNuevaNecesidad.setBorder(null);
		btnNuevaNecesidad.setBackground(new Color(254, 86, 86));
		btnNuevaNecesidad.setBounds(166, 648, 130, 37);
		add(btnNuevaNecesidad);
		
		JButton btnModificarNecesidad = new JButton("Modificar necesidad");
		btnModificarNecesidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int filaSeleccionada = jtResultados.getSelectedRow();
				if (filaSeleccionada != -1) {
					Empresa empresa = new Empresa();
					empresa.setCIF((String) modeloTabla.getValueAt(filaSeleccionada, 0));
					empresa.setDuenno((String) modeloTabla.getValueAt(filaSeleccionada, 1));
					empresa.setEmail_empresa((String) modeloTabla.getValueAt(filaSeleccionada, 4));
					Necesidad necesidad = c.obtenerNecesidad(c.obtenerIdEmpresa(empresa));
					if(necesidad != null) {
						EditarNecesidad dialog = new EditarNecesidad(c.obtenerNecesidad(c.obtenerIdEmpresa(empresa)), esAdmin);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Esta empresa no tiene una necesidad asociada.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una empresa para insertar la necesidad", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModificarNecesidad.setForeground(new Color(9, 3, 62));
		btnModificarNecesidad.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnModificarNecesidad.setBorder(null);
		btnModificarNecesidad.setBackground(new Color(254, 86, 86));
		btnModificarNecesidad.setBounds(397, 648, 130, 37);
		add(btnModificarNecesidad);
		
		JButton btnBorrarNecesidad = new JButton("Borrar necesidad");
		btnBorrarNecesidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = jtResultados.getSelectedRow();
				if (filaSeleccionada != -1) {
					Empresa empresa = new Empresa();
					empresa.setCIF((String) modeloTabla.getValueAt(filaSeleccionada, 0));
					empresa.setDuenno((String) modeloTabla.getValueAt(filaSeleccionada, 1));
					empresa.setEmail_empresa((String) modeloTabla.getValueAt(filaSeleccionada, 4));
					Necesidad necesidad = c.obtenerNecesidad(c.obtenerIdEmpresa(empresa));
					if(necesidad != null) {
						c.borradoLogicoNecesidad(necesidad);
					}else {
						JOptionPane.showMessageDialog(null, "Esta empresa no tiene una necesidad asociada.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una empresa para insertar la necesidad", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnBorrarNecesidad.setForeground(new Color(9, 3, 62));
		btnBorrarNecesidad.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBorrarNecesidad.setBorder(null);
		btnBorrarNecesidad.setBackground(new Color(254, 86, 86));
		btnBorrarNecesidad.setBounds(828, 648, 130, 37);
		add(btnBorrarNecesidad);
		
		JButton btnVerNecesidad = new JButton("Ver necesidad");
		btnVerNecesidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = jtResultados.getSelectedRow();
				if (filaSeleccionada != -1) {
					Empresa empresa = new Empresa();
					empresa.setCIF((String) modeloTabla.getValueAt(filaSeleccionada, 0));
					empresa.setDuenno((String) modeloTabla.getValueAt(filaSeleccionada, 1));
					empresa.setEmail_empresa((String) modeloTabla.getValueAt(filaSeleccionada, 4));
					if(c.obtenerNecesidad(c.obtenerIdEmpresa(empresa)) != null) {
						
						VerNecesidad dialog = new VerNecesidad(empresa, c.obtenerNecesidad(c.obtenerIdEmpresa(empresa)), esAdmin);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Esta empresa no tiene ninguna necesidad asociada.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una empresa para insertar la necesidad", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVerNecesidad.setForeground(new Color(9, 3, 62));
		btnVerNecesidad.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnVerNecesidad.setBorder(null);
		btnVerNecesidad.setBackground(new Color(254, 86, 86));
		btnVerNecesidad.setBounds(616, 648, 130, 37);
		add(btnVerNecesidad);

		jtResultados.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(2).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(3).setPreferredWidth(120);
		jtResultados.getColumnModel().getColumn(4).setPreferredWidth(80);
		jtResultados.getColumnModel().getColumn(5).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(6).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(7).setPreferredWidth(100);
		jtResultados.getColumnModel().getColumn(8).setPreferredWidth(100);

		JTableHeader encabezado = jtResultados.getTableHeader();
		Color rojoClaro = new Color(255, 157, 157);
		Color darkBlue = new Color(9, 3, 62);
		encabezado.setBackground(rojoClaro);
		encabezado.setForeground(darkBlue);
		encabezado.setFont(new Font("Verdana", Font.BOLD, 13));

		jtResultados.getTableHeader().setResizingAllowed(false);
		jtResultados.getTableHeader().setReorderingAllowed(false);

		rellenaTabla(idCentro);
	}

	public void rellenaTabla(int idCentro) {
		modeloTabla.setRowCount(0);

		ArrayList<Empresa> empresas = bd.cogeEmpresas();

		for (Empresa empresa : empresas) {
			if (!empresa.isEliminado()) { // Solo añade empresas que no estén marcadas como eliminadas
				modeloTabla.addRow(new Object[] { empresa.getCIF(), empresa.getDuenno(), empresa.getNombre_empresa(),
						empresa.getTelefono_empresa(), empresa.getEmail_empresa(), empresa.getDireccion_empresa(),
						empresa.getTutor_empresa(), empresa.getContacto_empresa(), empresa.getSolicita() });
			}
		}

		modeloTabla.fireTableDataChanged();
	}
}
