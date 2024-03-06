package modales;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Consultas;
import modelo.Alumno;
import vista.Ventana;

public class InsertarAlumno extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtAlumno;
	private JTextField txtCentro;
	private JTextField txtDNI;
	private JTextField txtss;
	private JTextField txtciclo;
	private JTextField txtanio;
	private Alumno nuevoAlumno = new Alumno();
	private Consultas bd = new Consultas();
	private JComboBox comboBox;

	public InsertarAlumno(int idCentro, DefaultTableModel modeloTabla, Ventana ventana, boolean esAdmin) {
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
		
		JLabel lblAlumno = new JLabel("Alumno:");
		lblAlumno.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblAlumno.setBounds(66, 169, 120, 19);
		contentPanel.add(lblAlumno);
		
		JLabel lblCentro = new JLabel("Centro:");
		lblCentro.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCentro.setBounds(66, 211, 134, 19);
		contentPanel.add(lblCentro);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblDNI.setBounds(66, 252, 73, 19);
		contentPanel.add(lblDNI);
		
		JLabel lblSS = new JLabel("Seguridad Social:");
		lblSS.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblSS.setBounds(542, 169, 120, 19);
		contentPanel.add(lblSS);
		
		JLabel lblCiclo = new JLabel("Ciclo:");
		lblCiclo.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCiclo.setBounds(608, 252, 73, 19);
		contentPanel.add(lblCiclo);
		
		JLabel lblAnio = new JLabel("Año:");
		lblAnio.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblAnio.setBounds(608, 211, 73, 19);
		contentPanel.add(lblAnio);
		
		
		JLabel lblValido = new JLabel("Valido:");
		lblValido.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblValido.setBounds(383, 350, 54, 19);
		contentPanel.add(lblValido);
		
		txtAlumno = new JTextField();
		txtAlumno.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtAlumno.setColumns(10);
		txtAlumno.setBounds(244, 168, 212, 20);
		contentPanel.add(txtAlumno);
		
		txtCentro = new JTextField();
		txtCentro.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtCentro.setColumns(10);
		txtCentro.setBounds(244, 210, 212, 20);
		contentPanel.add(txtCentro);
		
		txtDNI = new JTextField();
		txtDNI.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDNI.setColumns(10);
		txtDNI.setBounds(244, 251, 212, 20);
		contentPanel.add(txtDNI);
		
		txtss = new JTextField();
		txtss.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtss.setColumns(10);
		txtss.setBounds(680, 168, 212, 20);
		contentPanel.add(txtss);
		
		txtciclo = new JTextField();
		txtciclo.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtciclo.setColumns(10);
		txtciclo.setBounds(680, 251, 212, 20);
		contentPanel.add(txtciclo);
		
		txtanio = new JTextField();
		txtanio.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtanio.setColumns(10);
		txtanio.setBounds(680, 210, 212, 20);
		contentPanel.add(txtanio);
		
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
		btnAceptar.setBounds(735, 512, 103, 23);
		contentPanel.add(btnAceptar);
		
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (camposLlenos()) {
					
					nuevoAlumno = rellenaObjeto();
					insertar(nuevoAlumno);
					dispose();
					actualizarse(nuevoAlumno, modeloTabla);
					
				}
			}
					
		});	
		
		JLabel lblInsertarEmpresa = new JLabel("Insertar alumno");
		lblInsertarEmpresa.setForeground(new Color(9, 3, 62));
		lblInsertarEmpresa.setFont(new Font("Dialog", Font.PLAIN, 45));
		lblInsertarEmpresa.setBounds(322, 10, 359, 143);
		contentPanel.add(lblInsertarEmpresa);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"VALIDO", "NO VALIDO"}));
		comboBox.setBounds(459, 349, 110, 22);
		contentPanel.add(comboBox);
	}
	
	public boolean camposLlenos() {
		return !(txtAlumno.getText().isEmpty() || txtCentro.getText().isEmpty() || txtDNI.getText().isEmpty()
				|| txtss.getText().isEmpty() || txtciclo.getText().isEmpty() || txtanio.getText().isEmpty());
	}
	
	public Alumno rellenaObjeto() {
		Alumno alumno = new Alumno();

		
		alumno.setAlumno(txtAlumno.getText());		
		alumno.setCentro(txtCentro.getText());
		alumno.setDni(txtDNI.getText());
		alumno.setSs(txtss.getText());
		alumno.setCiclo(txtciclo.getText());
		alumno.setAnio(txtanio.getText());
		alumno.setValido(comboBox.getSelectedItem().toString());
		alumno.setEliminado(false);
		
		
		System.out.println(alumno);

		return alumno;
	}
	
	public void insertar(Alumno nuevoAlumno) {
		int opcion = 0;

		if (opcion == 0) {
			bd.insertaAlumno(nuevoAlumno);

		} else {

		}
	}
	
	public void actualizarse(Alumno nuevoAlumno, DefaultTableModel modeloTabla) {
		Object[] nuevaFila = {nuevoAlumno.getAlumno(), nuevoAlumno.getCentro(),
				nuevoAlumno.getDni(), nuevoAlumno.getValido(), nuevoAlumno.getSs(), nuevoAlumno.getCiclo(), nuevoAlumno.getAnio()};
		modeloTabla.addRow(nuevaFila);

		modeloTabla.fireTableDataChanged();
	}
	
	
}

