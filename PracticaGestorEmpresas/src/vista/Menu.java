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
	public Menu(Ventana ventana, boolean esAdmin, String idBib) {
//		this.ventana = ventana;
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("GestaPracticas");
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setForeground(new Color(233, 1, 1));
		lblTitulo.setFont(new Font("Lato", Font.PLAIN, 55));
		lblTitulo.setBounds(557, 66, 380, 81);
		add(lblTitulo);
		
		JLabel lblMen = new JLabel("Menú");
		lblMen.setVerticalAlignment(SwingConstants.TOP);
		lblMen.setHorizontalAlignment(SwingConstants.LEFT);
		lblMen.setForeground(new Color(9, 3, 62));
		lblMen.setFont(new Font("Lato", Font.PLAIN, 55));
		lblMen.setBounds(672, 111, 239, 81);
		add(lblMen);
		
		JLabel lblIcono = new JLabel("");
		lblIcono.setBounds(534, 44, 181, 135);
		add(lblIcono);
		
		JButton btnEmpresas = new JButton("Empresas");
		
		//-- ACCIÓN BOTÓN LIBROS --
		btnEmpresas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				ventana.nuevoPanel(new Libros_Ventana(ventana, esAdmin, idBib));
			}
		});
		//--------------------------------------------
		
		btnEmpresas.setBorder(null);
		btnEmpresas.setForeground(new Color(255, 255, 255));
		btnEmpresas.setBackground(new Color(233, 1, 1));
		btnEmpresas.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnEmpresas.setBounds(534, 329, 315, 37);
		add(btnEmpresas);
		
		//-- ACCIÓN BOTÓN SOCIOS --
		JButton btnPracticas = new JButton("Prácticas");
		btnPracticas.setForeground(new Color(255, 255, 255));
		btnPracticas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				ventana.nuevoPanel(new Socios_Ventana(ventana, esAdmin, idBib));
			}
		});
		//------------------------------------------------
		
		btnPracticas.setBackground(new Color(233, 1, 1));
		btnPracticas.setBorder(null);
		btnPracticas.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnPracticas.setBounds(534, 245, 315, 37);
		add(btnPracticas);
		
		//-- ACCIÓN BOTÓN PRÉSTAMOS --
		JButton btnAlumnos = new JButton("Alumnos");
		btnAlumnos.setForeground(new Color(255, 255, 255));
		btnAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				ventana.nuevoPanel(new Prestamos_Ventana(ventana, esAdmin, idBib));
			}
		});
		//---------------------------------------------------
		
		btnAlumnos.setBackground(new Color(233, 1, 1));
		btnAlumnos.setBorder(null);
		btnAlumnos.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAlumnos.setToolTipText("");
		btnAlumnos.setBounds(534, 414, 315, 37);
		add(btnAlumnos);
		
		//-- ACCIÓN BOTÓN MULTAS --
		JButton btnConvenios = new JButton("Convenios");
		btnConvenios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				ventana.nuevoPanel(new Recibos_Ventana(ventana, esAdmin, idBib));
			}
		});
		//-------------------------------------------------------
		
		btnConvenios.setForeground(new Color(255, 255, 255));
		btnConvenios.setBackground(new Color(233, 1, 1));
		btnConvenios.setBorder(null);
		btnConvenios.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnConvenios.setToolTipText("");
		btnConvenios.setBounds(534, 498, 315, 37);
		add(btnConvenios);
		
		//-- ACCIÓN BOTÓN usuarios --
		JButton btnBolsaTrabajo = new JButton("Bolsa de trabajo");
		btnBolsaTrabajo.setForeground(new Color(255, 255, 255));
		btnBolsaTrabajo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				ventana.nuevoPanel(new Usuarios_Ventana(ventana, esAdmin, idBib));	
			}
		});
		//-----------------------------------------------------------
		btnBolsaTrabajo.setBackground(new Color(233, 1, 1));
		btnBolsaTrabajo.setBorder(null);
		btnBolsaTrabajo.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnBolsaTrabajo.setToolTipText("");
		btnBolsaTrabajo.setBounds(534, 582, 315, 37);
		add(btnBolsaTrabajo);
		
		JButton btnSalir = new JButton("Cerrar sesión");
		
		//-- ACCIÓN CERRAR SESIÓN --
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Utilizamos la referencia a Ventana para cambiar al panel Menu
//	            ventana.nuevoPanel(new Login(ventana));
			}
		});
		//-------------------------------------------
		
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setToolTipText("");
		btnSalir.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnSalir.setBorder(null);
		btnSalir.setBackground(new Color(255, 128, 128));
		btnSalir.setBounds(625, 836, 111, 37);
		add(btnSalir);
		
		JButton btnAdministracion = new JButton("Administracion");
		btnAdministracion.setToolTipText("");
		btnAdministracion.setForeground(new Color(255, 255, 255));
		btnAdministracion.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnAdministracion.setBorder(null);
		btnAdministracion.setBackground(new Color(233, 1, 1));
		btnAdministracion.setBounds(534, 749, 315, 37);
		add(btnAdministracion);
		
		JButton btnNecesidades = new JButton("Necesidades");
		btnNecesidades.setToolTipText("");
		btnNecesidades.setForeground(Color.WHITE);
		btnNecesidades.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNecesidades.setBorder(null);
		btnNecesidades.setBackground(new Color(233, 1, 1));
		btnNecesidades.setBounds(534, 665, 315, 37);
		add(btnNecesidades);
		
		if(esAdmin == false) {
			btnAdministracion.setVisible(false);
		}else {
			btnAdministracion.setVisible(true);
		}

	}
}
