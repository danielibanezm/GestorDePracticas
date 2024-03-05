package modales;

import java.awt.Color;
import java.awt.Font;
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
import modelo.Empresa;

public class EditarEmpresa extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtCIF;
    private JTextField txtDuenno;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtTutor;
    private JTextField txtEmail;
    private JTextField txtDireccion;
    private JComboBox<String> comboBox;
    private JTextField txtContacto;

    public EditarEmpresa(Empresa empresa, DefaultTableModel modeloTabla, int filaTabla, boolean esAdmin) {
        setResizable(false);
        setModal(true);
        setTitle("Editar Empresa");
        setBounds(100, 100, 1011, 636);
        getContentPane().setLayout(null);
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBounds(0, 0, 997, 596);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);

        JLabel lblInsertarEmpresa = new JLabel("Editar Empresa");
        lblInsertarEmpresa.setForeground(new Color(9, 3, 62));
        lblInsertarEmpresa.setFont(new Font("Dialog", Font.PLAIN, 45));
        lblInsertarEmpresa.setBounds(250, 10, 359, 143);
        contentPanel.add(lblInsertarEmpresa);

        JLabel lblCIF = new JLabel("CIF:");
        lblCIF.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblCIF.setBounds(66, 169, 120, 19);
        contentPanel.add(lblCIF);

        txtCIF = new JTextField();
        txtCIF.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtCIF.setColumns(10);
        txtCIF.setBounds(244, 168, 212, 20);
        contentPanel.add(txtCIF);

        JLabel lblDuenno = new JLabel("Dueño:");
        lblDuenno.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblDuenno.setBounds(66, 211, 134, 19);
        contentPanel.add(lblDuenno);

        txtDuenno = new JTextField();
        txtDuenno.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtDuenno.setColumns(10);
        txtDuenno.setBounds(244, 210, 212, 20);
        contentPanel.add(txtDuenno);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblNombre.setBounds(66, 252, 73, 19);
        contentPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtNombre.setColumns(10);
        txtNombre.setBounds(244, 251, 212, 20);
        contentPanel.add(txtNombre);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblTelefono.setBounds(66, 297, 134, 19);
        contentPanel.add(lblTelefono);

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

        txtDireccion = new JTextField();
        txtDireccion.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtDireccion.setColumns(10);
        txtDireccion.setBounds(680, 168, 212, 20);
        contentPanel.add(txtDireccion);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblEmail.setBounds(561, 211, 73, 19);
        contentPanel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtEmail.setColumns(10);
        txtEmail.setBounds(680, 210, 212, 20);
        contentPanel.add(txtEmail);

        JLabel lblTutor = new JLabel("Tutor:");
        lblTutor.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblTutor.setBounds(561, 252, 50, 19);
        contentPanel.add(lblTutor);

        txtTutor = new JTextField();
        txtTutor.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtTutor.setColumns(10);
        txtTutor.setBounds(680, 251, 212, 20);
        contentPanel.add(txtTutor);
        
        txtContacto = new JTextField();
        txtContacto.setFont(new Font("Verdana", Font.PLAIN, 12));
        txtContacto.setColumns(10);
        txtContacto.setBounds(680, 298, 212, 20);
        contentPanel.add(txtContacto);

        JLabel lblContacto = new JLabel("Contacto:");
        lblContacto.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblContacto.setBounds(561, 297, 73, 19);
        contentPanel.add(lblContacto);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"PRACTICAS", "BECAS", "TRABAJADORES"}));
        comboBox.setBounds(387, 380, 99, 20);
        contentPanel.add(comboBox);
        
        
     // -- RELLENAMOS LOS TEXTFIELD --
		if (empresa != null) {
			txtCIF.setText(empresa.getCIF());
			txtDuenno.setText(empresa.getDuenno());
			txtNombre.setText(empresa.getNombre_empresa());
			txtTelefono.setText(empresa.getTelefono_empresa());
			txtEmail.setText(empresa.getEmail_empresa());
			txtDireccion.setText(empresa.getDireccion_empresa());
			txtTutor.setText(empresa.getTutor_empresa());
			txtContacto.setText(empresa.getContacto_empresa());
	        comboBox.setSelectedItem(empresa.getSolicita());

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
        btnAceptar.setBounds(735, 512, 89, 23);
        contentPanel.add(btnAceptar);


        btnAceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Empresa nuevaEmpresa = rellenaObjeto();
                editar(modeloTabla, nuevaEmpresa, filaTabla);
                dispose();
            }
        });
        
        JLabel lblSolicita = new JLabel("Solicita:");
        lblSolicita.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblSolicita.setBounds(320, 380, 57, 19);
        contentPanel.add(lblSolicita);
        
    }
    

    public Empresa rellenaObjeto() {
        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setCIF(txtCIF.getText());
        nuevaEmpresa.setDuenno(txtDuenno.getText());
        nuevaEmpresa.setNombre_empresa(txtNombre.getText());
        nuevaEmpresa.setTelefono_empresa(txtTelefono.getText());
        nuevaEmpresa.setDireccion_empresa(txtDireccion.getText());
        nuevaEmpresa.setEmail_empresa(txtEmail.getText());
        nuevaEmpresa.setTutor_empresa(txtTutor.getText());
        nuevaEmpresa.setContacto_empresa(txtContacto.getText());
        nuevaEmpresa.setSolicita(comboBox.getSelectedItem().toString());
        return nuevaEmpresa;
    }

    public void editar(DefaultTableModel modeloTabla, Empresa nuevaEmpresa, int filaTabla) {
        // Actualiza el modelo de la tabla
        modeloTabla.setValueAt(nuevaEmpresa.getCIF(), filaTabla, 0);
        modeloTabla.setValueAt(nuevaEmpresa.getDuenno(), filaTabla, 1);
        modeloTabla.setValueAt(nuevaEmpresa.getNombre_empresa(), filaTabla, 2);
        modeloTabla.setValueAt(nuevaEmpresa.getTelefono_empresa(), filaTabla, 3);
        modeloTabla.setValueAt(nuevaEmpresa.getDireccion_empresa(), filaTabla, 4);
        modeloTabla.setValueAt(nuevaEmpresa.getEmail_empresa(), filaTabla, 5);
        modeloTabla.setValueAt(nuevaEmpresa.getTutor_empresa(), filaTabla, 6);
        modeloTabla.setValueAt(nuevaEmpresa.getContacto_empresa(), filaTabla, 7);
        modeloTabla.setValueAt(nuevaEmpresa.getSolicita(), filaTabla, 8);

        
        // Actualiza la base de datos
        Consultas consultas = new Consultas();
        String idEmpresa = consultas.obtenerIdEmpresa(nuevaEmpresa);
        if (idEmpresa != null) { // Asegura que se haya obtenido un ID válido
            consultas.actualizaEmpresa(nuevaEmpresa, idEmpresa);
        } else {
            // Manejar el caso en el que no se puede obtener un ID válido
            System.err.println("No se pudo obtener un ID válido para la empresa.");
        }
    }

}
