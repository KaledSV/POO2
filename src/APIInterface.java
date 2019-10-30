import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JScrollPane;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class APIInterface extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					APIInterface frame = new APIInterface("java.lang.Integer");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public APIInterface(String className) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setPreferredSize(new Dimension(620, 460));
		setContentPane(contentPane);
		
		// buscador sirve para obtener la informacion de cada categoria
		BuscadorClase buscador = new BuscadorClase();
		
		// se definen todas las textAreas y labels de cada categoria a mostrar
		JLabel lblSupers = new JLabel("Jerarquia:");
		lblSupers.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSupers.setBounds(29, 38, 84, 16);
		contentPane.add(lblSupers);
		
		JTextArea txtrSupers = new JTextArea();
		JScrollPane supersScroll = new JScrollPane(txtrSupers, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		supersScroll.setBounds(135, 12, 350, 75);
		contentPane.add(supersScroll);
		
		JLabel lblInterfaces = new JLabel("Interfaces:");
		lblInterfaces.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInterfaces.setBounds(29, 128, 84, 16);
		contentPane.add(lblInterfaces);
		
		JTextArea txtrinterfaces = new JTextArea();
		JScrollPane interfacesScroll = new JScrollPane(txtrinterfaces, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		interfacesScroll.setBounds(135, 102, 350, 75);
		contentPane.add(interfacesScroll);
		
		JLabel lblAtributos = new JLabel("Atributos:");
		lblAtributos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAtributos.setBounds(39, 218, 84, 16);
		contentPane.add(lblAtributos);
		
		JTextArea txtrAtributos = new JTextArea();
		JScrollPane atributosScroll = new JScrollPane(txtrAtributos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		atributosScroll.setBounds(135, 192, 350, 75);
		contentPane.add(atributosScroll);
		
		JLabel lblConstructores = new JLabel("Constructores: ");
		lblConstructores.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConstructores.setBounds(12, 308, 131, 16);
		contentPane.add(lblConstructores);
		
		JTextArea txtrconstructores = new JTextArea();
		JScrollPane constructoresScroll = new JScrollPane(txtrconstructores, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		constructoresScroll.setBounds(135, 282, 350, 75);
		contentPane.add(constructoresScroll);
		
		JLabel lblMetodos = new JLabel("Metodos: ");
		lblMetodos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMetodos.setBounds(46, 397, 77, 16);
		contentPane.add(lblMetodos);
		
		JTextArea txtrmetodos = new JTextArea();
		JScrollPane metodosScroll = new JScrollPane(txtrmetodos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		metodosScroll.setBounds(135, 372, 350, 75);
		contentPane.add(metodosScroll);
		
		// se define el boton para generar un XML y terminar el programa
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				GenerarXML generar = new GenerarXML(className);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		btnGenerar.setBounds(184, 460, 97, 25);
		contentPane.add(btnGenerar);
		
		// se coloca informacion de cada categoria en su respectiva textArea
		try {
			txtrSupers.setText(buscador.buscarSupers(className));
			txtrinterfaces.setText(buscador.buscarInterfaces(className));
			txtrAtributos.setText(buscador.buscarAtributos(className));
			txtrconstructores.setText(buscador.buscarConstructores(className));
			txtrmetodos.setText(buscador.buscarMetodos(className));
			btnGenerar.setEnabled(true);
		} 
		// si hubo un error se deshabilita el boton para generar el XML
		catch (ClassNotFoundException e) {
			btnGenerar.setEnabled(false);
		}
	}
}
