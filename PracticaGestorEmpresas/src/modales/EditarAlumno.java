package modales;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Alumno;
import modelo.Consultas;

public class EditarAlumno extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtAlumno;
	private JTextField txtDNI;
	private JTextField txtss;
	private JTextField txtciclo;
	private JTextField txtanio;
    private JComboBox<String> comboBox;
    private JComboBox<String> comboBox2;

    public EditarAlumno(Alumno alumno, DefaultTableModel modeloTabla, int filaTabla, boolean esAdmin) {
        setResizable(false);
        setModal(true);
        setTitle("Editar Alumno");
        setBounds(100, 100, 1011, 636);
        getContentPane().setLayout(null);
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBounds(0, 0, 997, 596);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);

        JLabel lblInsertarAlumno = new JLabel("Editar Alumno");
        lblInsertarAlumno.setForeground(new Color(9, 3, 62));
        lblInsertarAlumno.setFont(new Font("Dialog", Font.PLAIN, 45));
        lblInsertarAlumno.setBounds(250, 10, 359, 143);
        contentPanel.add(lblInsertarAlumno);

        JLabel lblAlumno = new JLabel("Alumno:");
        lblAlumno.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblAlumno.setBounds(66, 169, 120, 19);
        contentPanel.add(lblAlumno);

        txtAlumno = new JTextField();
        txtAlumno.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtAlumno.setColumns(10);
        txtAlumno.setBounds(244, 168, 212, 20);
        contentPanel.add(txtAlumno);

        JLabel lblCentro = new JLabel("Centro:");
        lblCentro.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblCentro.setBounds(405, 424, 54, 19);
        contentPanel.add(lblCentro);

        JLabel lblDNI= new JLabel("DNI:");
        lblDNI.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblDNI.setBounds(66, 211, 73, 19);
        contentPanel.add(lblDNI);

        txtDNI = new JTextField();
        txtDNI.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtDNI.setColumns(10);
        txtDNI.setBounds(244, 210, 212, 20);
        contentPanel.add(txtDNI);

        JLabel lblSS= new JLabel("Seguridad Social:");
        lblSS.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblSS.setBounds(66, 257, 134, 19);
        contentPanel.add(lblSS);

        txtss = new JTextField();
        txtss.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtss.setColumns(10);
        txtss.setBounds(244, 256, 212, 20);
        contentPanel.add(txtss);

        JLabel lblCiclo = new JLabel("Ciclo:");
        lblCiclo.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblCiclo.setBounds(608, 169, 73, 19);
        contentPanel.add(lblCiclo);

        txtciclo = new JTextField();
        txtciclo.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtciclo.setColumns(10);
        txtciclo.setBounds(680, 168, 212, 20);
        contentPanel.add(txtciclo);
        
        JLabel lblAnio = new JLabel("Año:");
		lblAnio.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblAnio.setBounds(608, 211, 73, 19);
		contentPanel.add(lblAnio);
        

		txtanio = new JTextField();
		txtanio.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtanio.setColumns(10);
		txtanio.setBounds(680, 210, 212, 20);
		contentPanel.add(txtanio);
		
		
		JLabel lblValido = new JLabel("Valido:");
		lblValido.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblValido.setBounds(405, 362, 54, 19);
		contentPanel.add(lblValido);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"VALIDO", "NO VALIDO"}));
        comboBox.setBounds(510, 362, 99, 20);
        contentPanel.add(comboBox);
        
        comboBox2 = new JComboBox<>();
        comboBox2.setBounds(510, 424, 99, 19);
        contentPanel.add(comboBox2);
        llenarCentros();
        
        
     // -- RELLENAMOS LOS TEXTFIELD --
		if (alumno != null) {
			txtAlumno.setText(alumno.getAlumno());
			
			txtDNI.setText(alumno.getDni());
			txtss.setText(alumno.getSs());
			txtciclo.setText(alumno.getCiclo());
			txtanio.setText(alumno.getAnio());
			comboBox.setSelectedItem(alumno.getValido());

		}
        
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
        btnAceptar.setBounds(735, 512, 99, 23);
        contentPanel.add(btnAceptar);
        
        btnAceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Alumno nuevoAlumno = rellenaObjeto();
                editar(modeloTabla, nuevoAlumno, filaTabla, alumno);
                dispose();
            }
        });
        
    }
    
    private void llenarCentros() {
		Consultas consultas = new Consultas();
        // Limpia el JComboBox
		comboBox2.removeAllItems();

        // Llena el JComboBox usando la instancia de Consultas
        ArrayList<String> nombresCentros = consultas.obtenerNombresCentros();
        for (String nombreCentro : nombresCentros) {
        	comboBox2.addItem(nombreCentro);
        }
    }
    

    public Alumno rellenaObjeto() {
        Alumno nuevoAlumno = new Alumno();
        nuevoAlumno.setAlumno(txtAlumno.getText());
       // nuevoAlumno.setCentro(txtCentro.getText());
        nuevoAlumno.setDni(txtDNI.getText());
        nuevoAlumno.setSs(txtss.getText());
        nuevoAlumno.setCiclo(txtciclo.getText());
        nuevoAlumno.setAnio(txtanio.getText());
        nuevoAlumno.setValido(comboBox.getSelectedItem().toString());
        return nuevoAlumno;
    }

    public void editar(DefaultTableModel modeloTabla, Alumno nuevoAlumno, int filaTabla, Alumno alumno) {
        // Actualiza el modelo de la tabla
        modeloTabla.setValueAt(nuevoAlumno.getAlumno(), filaTabla, 0);
        modeloTabla.setValueAt(nuevoAlumno.getCentro(), filaTabla, 1);
        modeloTabla.setValueAt(nuevoAlumno.getDni(), filaTabla, 2);
        modeloTabla.setValueAt(nuevoAlumno.getValido(), filaTabla, 3);
        modeloTabla.setValueAt(nuevoAlumno.getSs(), filaTabla, 4);
        modeloTabla.setValueAt(nuevoAlumno.getCiclo(), filaTabla, 5);
        modeloTabla.setValueAt(nuevoAlumno.getAnio(), filaTabla, 6);
        
        // Actualiza la base de datos
        Consultas consultas = new Consultas();
        int idAlumno= consultas.obtenerIdAlumno(alumno);
        if (idAlumno != -1) { // Asegura que se haya obtenido un ID válido
            consultas.actualizaAlumno(nuevoAlumno, idAlumno);
        } else {
            // Manejar el caso en el que no se puede obtener un ID válido
            System.err.println("No se pudo obtener un ID válido para el alumno.");
        }
    }
}

