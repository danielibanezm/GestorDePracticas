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

import modelo.Alumno;
import modelo.Consultas;
import modelo.Necesidad;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class InsertarNecesidad extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JComboBox<String> comboBox2;
    private JSpinner spinnerAsir;
    private JSpinner spinnerDaw;
    private JSpinner spinnerDam;
    private JSpinner spinnerFinanzas;
    private JSpinner spinnerMarke;
    private Consultas c = new Consultas();

    public InsertarNecesidad(int idEmpresa, boolean esAdmin) {
        setResizable(false);
        setModal(true);
        setTitle("Editar Alumno");
        setBounds(100, 100, 996, 477);
        getContentPane().setLayout(null);
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBounds(0, 0, 985, 455);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);

        JLabel lblInsertarAlumno = new JLabel("Insertar necesidad");
        lblInsertarAlumno.setForeground(new Color(9, 3, 62));
        lblInsertarAlumno.setFont(new Font("Dialog", Font.PLAIN, 45));
        lblInsertarAlumno.setBounds(313, 11, 403, 143);
        contentPanel.add(lblInsertarAlumno);

        JLabel lblConvocatoria = new JLabel("Convocatoria:");
        lblConvocatoria.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblConvocatoria.setBounds(66, 169, 120, 19);
        contentPanel.add(lblConvocatoria);

        JLabel lblCantidadDam= new JLabel("Cantidad DAM:");
        lblCantidadDam.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblCantidadDam.setBounds(66, 211, 168, 19);
        contentPanel.add(lblCantidadDam);

        JLabel lblCantidadDaw= new JLabel("Cantidad DAW:");
        lblCantidadDaw.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblCantidadDaw.setBounds(66, 257, 134, 19);
        contentPanel.add(lblCantidadDaw);

        JLabel lblCantidadAsir = new JLabel("Cantidad ASIR:");
        lblCantidadAsir.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblCantidadAsir.setBounds(527, 169, 143, 19);
        contentPanel.add(lblCantidadAsir);
        
        JLabel lblCantidadMarketing = new JLabel("Cantidad marketing:");
		lblCantidadMarketing.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCantidadMarketing.setBounds(527, 211, 143, 19);
		contentPanel.add(lblCantidadMarketing);
        
        comboBox2 = new JComboBox<>();
        comboBox2.setModel(new DefaultComboBoxModel(new String[] {"ABRIL", "SEPTIEMBRE"}));
        comboBox2.setBounds(244, 164, 212, 19);
        contentPanel.add(comboBox2);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setBackground(new Color(255, 157, 157));
        btnCancelar.setBounds(244, 368, 89, 23);
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
        btnAceptar.setBounds(680, 368, 99, 23);
        contentPanel.add(btnAceptar);
        
        JLabel lblCantidadFinanzas = new JLabel("Cantidad finanazas:");
        lblCantidadFinanzas.setFont(new Font("Verdana", Font.PLAIN, 12));
        lblCantidadFinanzas.setBounds(527, 257, 143, 19);
        contentPanel.add(lblCantidadFinanzas);
        
        spinnerDam = new JSpinner();
        spinnerDam.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spinnerDam.setBounds(244, 211, 212, 20);
        contentPanel.add(spinnerDam);
        
        spinnerDaw = new JSpinner();
        spinnerDaw.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spinnerDaw.setBounds(244, 257, 212, 20);
        contentPanel.add(spinnerDaw);
        
        spinnerAsir = new JSpinner();
        spinnerAsir.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spinnerAsir.setBounds(680, 169, 212, 20);
        contentPanel.add(spinnerAsir);
        
        spinnerMarke = new JSpinner();
        spinnerMarke.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spinnerMarke.setBounds(680, 211, 212, 20);
        contentPanel.add(spinnerMarke);
        
        spinnerFinanzas = new JSpinner();
        spinnerFinanzas.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spinnerFinanzas.setBounds(680, 257, 212, 20);
        contentPanel.add(spinnerFinanzas);
        
       


        btnAceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	c.insertarNecesidad(rellenaObjeto(), idEmpresa);
                dispose();
            }
        });
        
        
        
    }
    

    public Necesidad rellenaObjeto() {
        Necesidad nuevaNecesidad = new Necesidad();
        nuevaNecesidad.setConvocatoria(comboBox2.getSelectedItem().toString());
        nuevaNecesidad.setCantidadDam(Integer.parseInt(spinnerDam.getValue().toString()));
        nuevaNecesidad.setCantidadDaw(Integer.parseInt(spinnerDaw.getValue().toString()));
        nuevaNecesidad.setCantidadAsir(Integer.parseInt(spinnerAsir.getValue().toString()));
        nuevaNecesidad.setCantidadMarketing(Integer.parseInt(spinnerMarke.getValue().toString()));
        nuevaNecesidad.setCantidadFinazas(Integer.parseInt(spinnerFinanzas.getValue().toString()));
        return nuevaNecesidad;
    }
}

