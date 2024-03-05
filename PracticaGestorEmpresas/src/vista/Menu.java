package vista;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JPanel {
	private Ventana ventana;

	/**
	 * Create the panel.
	 */
	public Menu(Ventana ventana, boolean esAdmin, int idCentro) {
		this.ventana = ventana;
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("GestaPracticas");
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setForeground(new Color(233, 1, 1));
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 55));
		lblTitulo.setBounds(413, 53, 380, 81);
		add(lblTitulo);
		
		JLabel lblMen = new JLabel("Menú");
		lblMen.setVerticalAlignment(SwingConstants.TOP);
		lblMen.setHorizontalAlignment(SwingConstants.LEFT);
		lblMen.setForeground(new Color(9, 3, 62));
		lblMen.setFont(new Font("Lato", Font.PLAIN, 55));
		lblMen.setBounds(533, 131, 239, 81);
		add(lblMen);
		
		JLabel lblIcono = new JLabel("");
		lblIcono.setBounds(465, 42, 181, 135);
		add(lblIcono);
		
		JButton btnEmpresas = new JButton("Empresas");
		
		//-- ACCIÓN BOTÓN EMPRESAS --
		btnEmpresas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new EmpresasVentana(ventana, esAdmin, idCentro));				
			}
		});
		//--------------------------------------------
		
		btnEmpresas.setBorder(null);
		btnEmpresas.setForeground(new Color(255, 255, 255));
		btnEmpresas.setBackground(new Color(233, 1, 1));
		btnEmpresas.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEmpresas.setBounds(529, 244, 181, 37);
		add(btnEmpresas);
		
		//-- ACCIÓN BOTÓN PRACTICAS --
		JButton btnPracticas = new JButton("Prácticas");
		btnPracticas.setForeground(new Color(255, 255, 255));
		btnPracticas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				ventana.nuevoPanel(new PracticasVentana(ventana, esAdmin, idCentro));				
			}
		});
		//------------------------------------------------
		
		btnPracticas.setBackground(new Color(233, 1, 1));
		btnPracticas.setBorder(null);
		btnPracticas.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnPracticas.setBounds(217, 244, 181, 37);
		add(btnPracticas);
		
		//-- ACCIÓN BOTÓN PRÉSTAMOS --
		JButton btnAlumnos = new JButton("Alumnos");
		btnAlumnos.setForeground(new Color(255, 255, 255));
		btnAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new AlumnosVentana(ventana, esAdmin, idCentro));
			}
		});
		//---------------------------------------------------
		
		btnAlumnos.setBackground(new Color(233, 1, 1));
		btnAlumnos.setBorder(null);
		btnAlumnos.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAlumnos.setBounds(217, 424, 181, 37);
		add(btnAlumnos);
		
		//-- ACCIÓN BOTÓN CONVENIOS --
		JButton btnConvenios = new JButton("Convenios");
		btnConvenios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new ConveniosVentana(ventana, esAdmin, idCentro));
			}
		});
		//-------------------------------------------------------
		
		btnConvenios.setForeground(new Color(255, 255, 255));
		btnConvenios.setBackground(new Color(233, 1, 1));
		btnConvenios.setBorder(null);
		btnConvenios.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnConvenios.setBounds(864, 424, 181, 37);
		add(btnConvenios);
		
		//-- ACCIÓN BOTÓN BOLSA DE TRABAJO --
		JButton btnBolsaTrabajo = new JButton("Bolsa de trabajo");
		btnBolsaTrabajo.setForeground(new Color(255, 255, 255));
		btnBolsaTrabajo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new BolsasVentana(ventana, esAdmin, idCentro));	
			}
		});
		//-----------------------------------------------------------
		btnBolsaTrabajo.setBackground(new Color(233, 1, 1));
		btnBolsaTrabajo.setBorder(null);
		btnBolsaTrabajo.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBolsaTrabajo.setBounds(864, 244, 181, 37);
		add(btnBolsaTrabajo);
		
		JButton btnSalir = new JButton("Cerrar sesión");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		//-- ACCIÓN CERRAR SESIÓN --
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Utilizamos la referencia a Ventana para cambiar al panel Menu
	            ventana.nuevoPanel(new Login(ventana));
			}
		});
		//-------------------------------------------
		
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnSalir.setBorder(null);
		btnSalir.setBackground(new Color(255, 128, 128));
		btnSalir.setBounds(558, 645, 111, 37);
		add(btnSalir);
		
		
		//-- ACCIÓN BOTÓN ADMINISTRACION --
		
		JButton btnAdministracion = new JButton("Administracion");
		btnAdministracion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new AdministracionVentana(ventana, esAdmin, idCentro));				
			}
		});
		btnAdministracion.setForeground(new Color(255, 255, 255));
		btnAdministracion.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAdministracion.setBorder(null);
		btnAdministracion.setBackground(new Color(233, 1, 1));
		btnAdministracion.setBounds(529, 570, 181, 37);
		add(btnAdministracion);
				
		//-- ACCIÓN BOTÓN NECESIDADES --
		
		JButton btnCentros = new JButton("Centros");
		btnCentros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				ventana.nuevoPanel(new CentrosVentana(ventana, esAdmin, idCentro));	
			}
		});
		btnCentros.setForeground(Color.WHITE);
		btnCentros.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnCentros.setBorder(null);
		btnCentros.setBackground(new Color(233, 1, 1));
		btnCentros.setBounds(529, 424, 181, 37);
		add(btnCentros);
		
		if(esAdmin == false) {
			btnAdministracion.setVisible(false);
		}else {
			btnAdministracion.setVisible(true);
		}

	}
}
