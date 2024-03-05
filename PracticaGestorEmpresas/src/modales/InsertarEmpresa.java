package modales;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Consultas;
import modelo.Empresa;
import vista.Ventana;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import java.awt.TextArea;
import java.awt.Scrollbar;
import java.awt.ScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class InsertarEmpresa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCIF;
	private JTextField txtDuenno;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtTutor;
	private JTextField txtEmail;
	private JTextField txtDireccion;
	private TextArea txtAreaSolicita;
	private Empresa nuevaEmpresa = new Empresa();
	private Consultas bd = new Consultas();
	private JTextField txtContacto;
	private JComboBox comboBox;

	public InsertarEmpresa(int idCentro, DefaultTableModel modeloTabla, Ventana ventana, boolean esAdmin) {
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 1006, 624);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBounds(0, 0, 989, 577);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblCIF = new JLabel("CIF:");
		lblCIF.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCIF.setBounds(66, 169, 120, 19);
		contentPanel.add(lblCIF);
		
		JLabel lblDuenno = new JLabel("Dueño:");
		lblDuenno.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDuenno.setBounds(66, 211, 134, 19);
		contentPanel.add(lblDuenno);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNombre.setBounds(66, 252, 73, 19);
		contentPanel.add(lblNombre);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblTelefono.setBounds(66, 297, 134, 19);
		contentPanel.add(lblTelefono);
		
		txtCIF = new JTextField();
		txtCIF.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtCIF.setColumns(10);
		txtCIF.setBounds(244, 168, 212, 20);
		contentPanel.add(txtCIF);
		
		txtDuenno = new JTextField();
		txtDuenno.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDuenno.setColumns(10);
		txtDuenno.setBounds(244, 210, 212, 20);
		contentPanel.add(txtDuenno);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(244, 251, 212, 20);
		contentPanel.add(txtNombre);
		
		txtTelefono = new JTextField();
		txtTelefono.setToolTipText("aaa-mm-dd");
		txtTelefono.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(244, 296, 212, 20);
		contentPanel.add(txtTelefono);
		
		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDireccion.setBounds(561, 169, 73, 19);
		contentPanel.add(lblDireccion);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEmail.setBounds(561, 211, 73, 19);
		contentPanel.add(lblEmail);
		
		JLabel lblTutor = new JLabel("Tutor:");
		lblTutor.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblTutor.setBounds(561, 252, 50, 19);
		contentPanel.add(lblTutor);
		
		txtTutor = new JTextField();
		txtTutor.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtTutor.setColumns(10);
		txtTutor.setBounds(680, 251, 212, 20);
		contentPanel.add(txtTutor);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(680, 210, 212, 20);
		contentPanel.add(txtEmail);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(680, 168, 212, 20);
		contentPanel.add(txtDireccion);
		
		JLabel lblContacto = new JLabel("Contacto:");
		lblContacto.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblContacto.setBounds(561, 297, 73, 19);
		contentPanel.add(lblContacto);
		
		txtContacto = new JTextField();
		txtContacto.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtContacto.setColumns(10);
		txtContacto.setBounds(680, 296, 212, 20);
		contentPanel.add(txtContacto);
		
		JLabel lblSolicita = new JLabel("Solicita:");
		lblSolicita.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblSolicita.setBounds(322, 383, 57, 19);
		contentPanel.add(lblSolicita);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBackground(new Color(255, 157, 157));
		btnCancelar.setBounds(207, 512, 89, 23);
		contentPanel.add(btnCancelar);
		
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		JButton btnAceptar = new JButton("Continuar");
		btnAceptar.setForeground(Color.BLACK);
		btnAceptar.setBackground(new Color(254, 86, 86));
		btnAceptar.setBounds(735, 512, 89, 23);
		contentPanel.add(btnAceptar);
		
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (camposLlenos()) {
					
					nuevaEmpresa = rellenaObjeto();
					insertar(nuevaEmpresa);
					dispose();
					actualizarse(nuevaEmpresa, modeloTabla);
					
				}
			}
					
		});	
		
		JLabel lblInsertarEmpresa = new JLabel("Insertar empresa");
		lblInsertarEmpresa.setForeground(new Color(9, 3, 62));
		lblInsertarEmpresa.setFont(new Font("Dialog", Font.PLAIN, 45));
		lblInsertarEmpresa.setBounds(322, 10, 359, 143);
		contentPanel.add(lblInsertarEmpresa);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"PRACTICAS", "BECAS", "TRABAJADORES"}));
		comboBox.setBounds(389, 382, 110, 22);
		contentPanel.add(comboBox);
	}
	
	public boolean camposLlenos() {
		return !(txtCIF.getText().isEmpty() || txtDuenno.getText().isEmpty() || txtNombre.getText().isEmpty()
				|| txtDireccion.getText().isEmpty() || txtTutor.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| txtTelefono.getText().isEmpty());
	}
	
	public Empresa rellenaObjeto() {
		Empresa empresa = new Empresa();

		
		empresa.setCIF(txtCIF.getText());		
		empresa.setDuenno(txtDuenno.getText());
		empresa.setNombre_empresa(txtNombre.getText());
		empresa.setTelefono_empresa(txtTelefono.getText());
		empresa.setDireccion_empresa(txtDireccion.getText());
		empresa.setEmail_empresa(txtEmail.getText());
		empresa.setTutor_empresa(txtTutor.getText());
		empresa.setContacto_empresa(txtContacto.getText());
		empresa.setSolicita(comboBox.getSelectedItem().toString());
		empresa.setEliminado(false);
		
		
		System.out.println(empresa);

		return empresa;
	}
	
	public void insertar(Empresa nuevaEmpresa) {
		int opcion = 0;

		if (opcion == 0) {
			bd.insertaEmpresa(nuevaEmpresa);

		} else {

		}
	}
	
	public void actualizarse(Empresa nuevaEmpresa, DefaultTableModel modeloTabla) {
		Object[] nuevaFila = {nuevaEmpresa.getCIF(), nuevaEmpresa.getDuenno(),
				nuevaEmpresa.getNombre_empresa(), nuevaEmpresa.getTelefono_empresa(), nuevaEmpresa.getEmail_empresa(), nuevaEmpresa.getDireccion_empresa(),
				nuevaEmpresa.getTutor_empresa(), nuevaEmpresa.getContacto_empresa(), nuevaEmpresa.getSolicita()};
		modeloTabla.addRow(nuevaFila);

		modeloTabla.fireTableDataChanged();
	}
	
	
}
