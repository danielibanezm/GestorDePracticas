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
		lblTitulo.setBounds(544, 61, 380, 81);
		add(lblTitulo);
		
		JLabel lblMen = new JLabel("Menú");
		lblMen.setVerticalAlignment(SwingConstants.TOP);
		lblMen.setHorizontalAlignment(SwingConstants.LEFT);
		lblMen.setForeground(new Color(9, 3, 62));
		lblMen.setFont(new Font("Lato", Font.PLAIN, 55));
		lblMen.setBounds(664, 139, 239, 81);
		add(lblMen);
		
		JLabel lblIcono = new JLabel("");
		lblIcono.setBounds(534, 44, 181, 135);
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
		btnEmpresas.setBounds(568, 246, 315, 37);
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
		btnPracticas.setBounds(30, 246, 315, 37);
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
		btnAlumnos.setToolTipText("");
		btnAlumnos.setBounds(30, 476, 315, 37);
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
		btnConvenios.setToolTipText("");
		btnConvenios.setBounds(1105, 476, 315, 37);
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
		btnBolsaTrabajo.setToolTipText("");
		btnBolsaTrabajo.setBounds(1105, 246, 315, 37);
		add(btnBolsaTrabajo);
		
		JButton btnSalir = new JButton("Cerrar sesión");
		
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
		btnSalir.setToolTipText("");
		btnSalir.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnSalir.setBorder(null);
		btnSalir.setBackground(new Color(255, 128, 128));
		btnSalir.setBounds(668, 755, 111, 37);
		add(btnSalir);
		
		
		//-- ACCIÓN BOTÓN ADMINISTRACION --
		
		JButton btnAdministracion = new JButton("Administracion");
		btnAdministracion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.nuevoPanel(new AdministracionVentana(ventana, esAdmin, idCentro));				
			}
		});
		btnAdministracion.setToolTipText("");
		btnAdministracion.setForeground(new Color(255, 255, 255));
		btnAdministracion.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAdministracion.setBorder(null);
		btnAdministracion.setBackground(new Color(233, 1, 1));
		btnAdministracion.setBounds(568, 641, 315, 37);
		add(btnAdministracion);
				
		//-- ACCIÓN BOTÓN NECESIDADES --
		
		JButton btnNecesidades = new JButton("Necesidades");
		btnNecesidades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				ventana.nuevoPanel(new NecesidadesVentana(ventana, esAdmin, idCentro));	
			}
		});
		btnNecesidades.setToolTipText("");
		btnNecesidades.setForeground(Color.WHITE);
		btnNecesidades.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNecesidades.setBorder(null);
		btnNecesidades.setBackground(new Color(233, 1, 1));
		btnNecesidades.setBounds(568, 476, 315, 37);
		add(btnNecesidades);
		
		if(esAdmin == false) {
			btnAdministracion.setVisible(false);
		}else {
			btnAdministracion.setVisible(true);
		}

	}
}
