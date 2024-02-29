package modales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Consultas;
import modelo.Empresa;
import java.awt.TextArea;

public class EditarEmpresa extends JDialog{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private Consultas bd = new Consultas();
	//private Errores err = new Errores();
	//private ComprobarCampos comprobar = new ComprobarCampos();
	private JCheckBox chckListaNegra;
	private EditarEmpresa dialogActual;
	private JTextField txtCIF;
	private JTextField txtDuenno;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtTutor;
	private JTextField txtEmail;
	private JTextField txtDireccion;
	private JTextField txtContacto;
    private TextArea txtAreaSolicita;


	public EditarEmpresa(Empresa empresa, DefaultTableModel modeloTabla, int filaTabla, int idCentro) {
		setBounds(100, 100, 1015, 632);
		dialogActual = this;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEditarEmpresa = new JLabel("Editar empresa");
		lblEditarEmpresa.setForeground(new Color(9, 3, 62));
		lblEditarEmpresa.setFont(new Font("Dialog", Font.PLAIN, 45));
		lblEditarEmpresa.setBounds(353, 10, 359, 143);
		contentPanel.add(lblEditarEmpresa);
		
		JLabel lblCIF = new JLabel("CIF:");
		lblCIF.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCIF.setBounds(92, 160, 120, 19);
		contentPanel.add(lblCIF);
		
		JLabel lblDuenno = new JLabel("Dueño:");
		lblDuenno.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDuenno.setBounds(92, 202, 134, 19);
		contentPanel.add(lblDuenno);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNombre.setBounds(92, 243, 73, 19);
		contentPanel.add(lblNombre);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblTelefono.setBounds(92, 288, 134, 19);
		contentPanel.add(lblTelefono);
		
		txtCIF = new JTextField();
		txtCIF.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtCIF.setColumns(10);
		txtCIF.setBounds(270, 159, 212, 20);
		contentPanel.add(txtCIF);
		
		txtDuenno = new JTextField();
		txtDuenno.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDuenno.setColumns(10);
		txtDuenno.setBounds(270, 201, 212, 20);
		contentPanel.add(txtDuenno);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(270, 242, 212, 20);
		contentPanel.add(txtNombre);
		
		txtTelefono = new JTextField();
		txtTelefono.setToolTipText("aaa-mm-dd");
		txtTelefono.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(270, 287, 212, 20);
		contentPanel.add(txtTelefono);
		
		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDireccion.setBounds(587, 160, 73, 19);
		contentPanel.add(lblDireccion);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEmail.setBounds(587, 202, 73, 19);
		contentPanel.add(lblEmail);
		
		JLabel lblTutor = new JLabel("Tutor:");
		lblTutor.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblTutor.setBounds(587, 243, 50, 19);
		contentPanel.add(lblTutor);
		
		txtTutor = new JTextField();
		txtTutor.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtTutor.setColumns(10);
		txtTutor.setBounds(706, 242, 212, 20);
		contentPanel.add(txtTutor);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(706, 201, 212, 20);
		contentPanel.add(txtEmail);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(706, 159, 212, 20);
		contentPanel.add(txtDireccion);
		
		JLabel lblContacto = new JLabel("Contacto:");
		lblContacto.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblContacto.setBounds(587, 288, 73, 19);
		contentPanel.add(lblContacto);
		
		txtContacto = new JTextField();
		txtContacto.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtContacto.setColumns(10);
		txtContacto.setBounds(706, 287, 212, 20);
		contentPanel.add(txtContacto);
		
		JLabel lblSolicita = new JLabel("Solicita:");
		lblSolicita.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblSolicita.setBounds(348, 374, 57, 19);
		contentPanel.add(lblSolicita);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBackground(new Color(255, 157, 157));
		btnCancelar.setBounds(233, 503, 89, 23);
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
		btnAceptar.setBounds(761, 503, 89, 23);
		contentPanel.add(btnAceptar);
		
		TextArea txtAreaSolicita = new TextArea();
		txtAreaSolicita.setBounds(411, 347, 219, 150);
		contentPanel.add(txtAreaSolicita);

		

		// -- RELLENAMOS LOS TEXTFIELD --
		if (empresa != null) {
			if (empresa.isEliminado() == false) {
				txtCIF.setText(empresa.getCIF());
				txtDuenno.setText(empresa.getDuenno());
				txtNombre.setText(empresa.getNombre_empresa());
				txtTelefono.setText(empresa.getTelefono_empresa());
				txtDireccion.setText(empresa.getDireccion_empresa());
				txtEmail.setText(empresa.getEmail_empresa());
				txtContacto.setText(empresa.getContacto_empresa());
				txtAreaSolicita.setText(empresa.getSolicita());
			}
			
		}
	}

	public Empresa rellenaObjeto() {
		Empresa empresa = new Empresa();

		empresa.setCIF(txtCIF.getText());
		empresa.setDuenno(txtNombre.getText());
		empresa.setNombre_empresa(txtNombre.getText());
		empresa.setTelefono_empresa(txtTelefono.getText());
		empresa.setDireccion_empresa(txtDireccion.getText());
		empresa.setEmail_empresa(txtEmail.getText());
		empresa.setTutor_empresa(txtTutor.getText());
		empresa.setContacto_empresa(txtContacto.getText());
		empresa.setSolicita(txtAreaSolicita.getText());
		

		return empresa;
	}
	
//	public boolean camposLlenos() {
//		return !(txtCif.getText().isEmpty() || txtDuenno.getText().isEmpty() || txtNombreEmpresa.getText().isEmpty()
//				|| txtTelefonoEmpresa.getText().isEmpty() || txtDireccionEmpresa.getText().isEmpty() || txtEmailEmpresa.getText().isEmpty()
//				|| txtContactoEmpresa.getText().isEmpty());
//	}

}
