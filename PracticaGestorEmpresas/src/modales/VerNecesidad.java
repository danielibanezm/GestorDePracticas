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
import javax.swing.border.EmptyBorder;

import modelo.Consultas;
import modelo.Empresa;
import modelo.Necesidad;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class VerNecesidad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Consultas c = new Consultas();

	public VerNecesidad(Empresa empresa, Necesidad necesidad, boolean esAdmin) {
		setResizable(false);
		setModal(true);
		setTitle("Editar Alumno");
		setBounds(100, 100, 722, 416);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBounds(0, 0, 985, 455);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblInsertarAlumno = new JLabel("Ver necesidad");
		lblInsertarAlumno.setForeground(new Color(9, 3, 62));
		lblInsertarAlumno.setFont(new Font("Dialog", Font.PLAIN, 45));
		lblInsertarAlumno.setBounds(192, 11, 359, 143);
		contentPanel.add(lblInsertarAlumno);

		JLabel lblConvocatoria = new JLabel("Convocatoria: ");
		lblConvocatoria.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblConvocatoria.setBounds(131, 169, 228, 19);
		contentPanel.add(lblConvocatoria);

		JLabel lblCantidadDam = new JLabel("Cantidad DAM: ");
		lblCantidadDam.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCantidadDam.setBounds(131, 211, 168, 19);
		contentPanel.add(lblCantidadDam);

		JLabel lblCantidadDaw = new JLabel("Cantidad DAW: ");
		lblCantidadDaw.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCantidadDaw.setBounds(131, 257, 134, 19);
		contentPanel.add(lblCantidadDaw);

		JLabel lblCantidadAsir = new JLabel("Cantidad ASIR: ");
		lblCantidadAsir.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCantidadAsir.setBounds(391, 169, 143, 19);
		contentPanel.add(lblCantidadAsir);

		JLabel lblCantidadMarketing = new JLabel("Cantidad marketing: ");
		lblCantidadMarketing.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCantidadMarketing.setBounds(391, 211, 143, 19);
		contentPanel.add(lblCantidadMarketing);

		JLabel lblCantidadFinanzas = new JLabel("Cantidad finanazas: ");
		lblCantidadFinanzas.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblCantidadFinanzas.setBounds(391, 257, 143, 19);
		contentPanel.add(lblCantidadFinanzas);

		JLabel lblEmpresa = new JLabel("Empresa: ");
		lblEmpresa.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblEmpresa.setBounds(192, 116, 304, 19);
		contentPanel.add(lblEmpresa);

		lblEmpresa.setText(lblEmpresa.getText() + c.cogeNombreEmpresaUnico(c.obtenerIdEmpresa(empresa)));

		lblCantidadAsir.setText(lblCantidadAsir.getText() + necesidad.getCantidadAsir());
		lblCantidadDam.setText(lblCantidadDam.getText() + necesidad.getCantidadDam());
		lblCantidadDaw.setText(lblCantidadDaw.getText() + necesidad.getCantidadDaw());
		lblCantidadFinanzas.setText(lblCantidadFinanzas.getText() + necesidad.getCantidadFinazas());
		lblCantidadMarketing.setText(lblCantidadMarketing.getText() + necesidad.getCantidadFinazas());

		lblConvocatoria.setText(lblConvocatoria.getText() + necesidad.getConvocatoria());

	}
}
