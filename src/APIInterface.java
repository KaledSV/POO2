import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class APIInterface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					APIInterface frame = new APIInterface();
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
	public APIInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(133, 13, 148, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNombreDeClase = new JLabel("Nombre de Clase:");
		lblNombreDeClase.setBounds(12, 16, 116, 16);
		contentPane.add(lblNombreDeClase);
		
		JLabel lblClasesEncontradas = new JLabel("Clases encontradas:");
		lblClasesEncontradas.setBounds(12, 42, 116, 16);
		contentPane.add(lblClasesEncontradas);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(133, 39, 231, 22);
		contentPane.add(comboBox);
		
		JButton btnGenerarApi = new JButton("Generar API");
		btnGenerarApi.setEnabled(false);
		btnGenerarApi.setBounds(125, 71, 116, 25);
		contentPane.add(btnGenerarApi);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(293, 12, 71, 25);
		contentPane.add(btnBuscar);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnGenerarApi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
