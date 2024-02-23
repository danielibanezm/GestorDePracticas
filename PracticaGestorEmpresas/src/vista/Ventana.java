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
//		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource("/img/libro.png")));
		setMinimumSize(new Dimension(1400, 800));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1414, 926);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		/* -- 	PONEMOS EL PANEL LOGIN SIEMPRE POR DEFECTO --
		"this" hace referencia a la instancia de Ventana*/
		Ventana ventana = this;
		Menu menuPanel = new Menu(this, false, "2");
 //       Login loginPanel = new Login(this);
        nuevoPanel(menuPanel);
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
	
	/**
	 * Método que vamos a llamar desde la acción de los JMenu
	 * y mediante el cual vamos a realizar las siguientes acciones:
	 * 1- Eliminamos el contenido del contentPane.
	 * 2- Le añadimos el panel que se nos pasa en ese momento por parámetros.
	 * 3- Método para que el componente se repinte.
	 * 4- Método que obliga a que todos los componentes que hayan sido borrados o modificados aparezcan correctamente.
	 * @param panelActual
	 */
	public void nuevoPanel(JPanel panelActual) {
		contentPane.removeAll();
		contentPane.add(panelActual);
		contentPane.repaint();
		contentPane.revalidate();
	}
	
	/**
	 * Método utilizado para que cuando pulsemos en el botón de la 'x' (cerrar) de la aplicación,
	 * es decir, del JFrame, nos prgunte si realmente queremos hacerlo.
	 */
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
