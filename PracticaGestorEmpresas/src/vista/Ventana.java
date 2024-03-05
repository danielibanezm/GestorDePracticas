package vista;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Rectangle;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public void ventana(Ventana frame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Ventana() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource("/imagenes/logo2.jpeg")));
		setBounds(100, 100, 1300, 760);
		setTitle("Gestor de practicas");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(true);
		
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		Ventana ventana = this;
        Login loginPanel = new Login(this);
        nuevoPanel(loginPanel);
        //------------------------------------------------
        
        //---------	ACCIÓN CERRAR EL JFRAME	----
        addWindowListener(new WindowAdapter() {
  			@Override
  			public void windowClosing(WindowEvent e) {
  				cerrarJFrame();
  			}
  		});
        //---------------------------------------------------
	}
	
	public void nuevoPanel(JPanel panelActual) {
		contentPane.removeAll();
		contentPane.add(panelActual);
		contentPane.repaint();
		contentPane.revalidate();
	}
	
	public void cerrarJFrame() {
		String botones[] = {"Cerrar", "Cancelar"};
		String titulo = "Aviso";
		int elegido = JOptionPane.showOptionDialog(this, "¿Desea cerrar la aplicación?", titulo, 0, 0, null, botones, this);
		
		if(elegido == JOptionPane.YES_OPTION) {
			System.exit(0);
		}else if(elegido == JOptionPane.NO_OPTION) {
			
		}
	}

}
