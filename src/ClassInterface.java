import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ClassInterface extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassInterface frame = new ClassInterface();
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
	public ClassInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// se define el textFiel, labels y botones de la interfaz
		JTextField textField = new JTextField();
		textField.setBounds(133, 13, 148, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNombreDeClase = new JLabel("Nombre de Clase:");
		lblNombreDeClase.setBounds(12, 16, 116, 16);
		contentPane.add(lblNombreDeClase);
		
		JLabel lblClasesEncontradas = new JLabel("Clases encontradas:");
		lblClasesEncontradas.setBounds(12, 42, 116, 16);
		contentPane.add(lblClasesEncontradas);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(133, 39, 249, 22);
		contentPane.add(comboBox);
		
		JButton btnGenerarApi = new JButton("Generar API");
		btnGenerarApi.setEnabled(false);
		btnGenerarApi.setBounds(133, 73, 116, 25);
		contentPane.add(btnGenerarApi);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(284, 12, 98, 25);
		contentPane.add(btnBuscar);
		
		// se define la funcion del boton buscar
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// se busca en todos los paquetes cualquier clase que concuerde con el nombre ingresado
				final Package[] packages = Package.getPackages();
			    final String className = textField.getText();
			    int validar = 0;
			    comboBox.removeAllItems();
			    
			    for (final Package p : packages) {
			        final String pack = p.getName();
			        final String quiza = pack + "." + className;
			        try {
			            Class.forName(quiza);
			            comboBox.addItem(quiza);
			            validar++;
			        } catch (final ClassNotFoundException e) {
			            continue;
			        }
			    }
			    if (validar != 0) {
			    	btnGenerarApi.setEnabled(true);
			    }
			    else {
			    	comboBox.addItem("No se encontro ninguna clase");
			    	btnGenerarApi.setEnabled(false);
			    }
			}
		});
		
		// boton para mostar la informacion de la clase seleccionado en la comboBox
		btnGenerarApi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false); 
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				String classname = (String) comboBox.getSelectedItem();
				APIInterface api = new APIInterface(classname);
				api.setVisible(true);
			}
		});
	}

}
