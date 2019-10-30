import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;

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
	@SuppressWarnings("rawtypes")
	public APIInterface(String className) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		scrollPane.setBounds(576, 13, 14, 288);
		contentPane.add(scrollPane);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(543, 8, 21, 293);
		contentPane.add(scrollBar);
		
		JTextArea txtrSupers = new JTextArea();
		contentPane.add(txtrSupers);
		
		int SupersY = 0;
		try {
			List<String> clasesPadre = (List<String>) new ArrayList<String>();
			Class<?> Supers = Class.forName(className);
			while(Supers != null) {
				clasesPadre.add(Supers.getName());
				Supers = Supers.getSuperclass();
			}
			
			String texto = txtrSupers.getText();
			
			for(int i=0; i<clasesPadre.size();i++) {
				for (int ii=0; ii != i; ii++) {
					texto = texto + "     ";
				}
				SupersY += 16;
				if(i==0) {
					texto = texto + clasesPadre.get(clasesPadre.size()-i-1) + "\n";
				}
				else {
					texto = texto + "∟" + clasesPadre.get(clasesPadre.size()-i-1) + "\n";
				}
			}
			txtrSupers.setText(texto);
			txtrSupers.setBounds(12, 35, 350, SupersY);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		JLabel lblJerarquia = new JLabel("Jerarquia:");
		lblJerarquia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblJerarquia.setBounds(12, 13, 84, 16);
		contentPane.add(lblJerarquia);
		
		JLabel lblInterfaces = new JLabel("Interfaces:");
		lblInterfaces.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInterfaces.setBounds(12, 40 + SupersY, 84, 16);
		contentPane.add(lblInterfaces);
		
		JTextArea txtrinterfaces = new JTextArea();
		contentPane.add(txtrinterfaces);	
		
		int interfacesy = 0;
		int txtrinterfacesy = 40 + SupersY + 25;
		try {
			Class<?> clase = Class.forName(className);
			List<String> interfacesStr = (List<String>) new ArrayList<String>();
			
			while(clase != null) {
				Class[] interfaces = clase.getInterfaces();
				
				for (Class interfaz : interfaces) {
					interfacesStr.add(interfaz.getSimpleName() + " de la clase " + clase.getSimpleName());
				}
				
				clase = clase.getSuperclass();
			}
			
			String texto = txtrinterfaces.getText();
			
			for(int i=0; i<interfacesStr.size();i++) {
				interfacesy += 16;
				texto = texto + interfacesStr.get(i) + "\n";
			}
			txtrinterfaces.setText(texto);
			txtrinterfaces.setBounds(12, txtrinterfacesy, 350, interfacesy);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		JLabel lblAtributos = new JLabel("Atributos:");
		lblAtributos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAtributos.setBounds(12, txtrinterfacesy + interfacesy + 5, 84, 16);
		contentPane.add(lblAtributos);
		
		JTextArea txtrAtributos = new JTextArea();
		contentPane.add(txtrAtributos);
		
		int atributosy = 0;
		int txtratributosy = txtrinterfacesy + interfacesy + 5 + 25;
		try {
			Class<?> clase = Class.forName(className);
			List<String> atributosStr = (List<String>) new ArrayList<String>();
			
			Field[] fields = clase.getDeclaredFields();
			for (Field field : fields) {
				atributosStr.add(Modifier.toString(field.getModifiers()) + " " + field.getType().getSimpleName() + " " + field.getName());
			}
			
			String texto = txtrAtributos.getText();
			
			for(int i=0; i<atributosStr.size();i++) {
				atributosy += 16;
				texto = texto + atributosStr.get(i) + "\n";
			}
			txtrAtributos.setText(texto);
			txtrAtributos.setBounds(12, txtratributosy, 350, atributosy);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		JLabel lblConstructores = new JLabel("Constructores: ");
		lblConstructores.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConstructores.setBounds(12, txtratributosy + atributosy + 5, 131, 16);
		contentPane.add(lblConstructores);
		
		JTextArea txtrconstructores = new JTextArea();
		contentPane.add(txtrconstructores);
		
		int constructoresy = 0;
		int txtrconstructoresy = txtratributosy + atributosy + 5 + 25;
		try {
			Class<?> clase = Class.forName(className);
			List<String> constructoresStr = (List<String>) new ArrayList<String>();
			
			String texto = "( ";
			
			Constructor[] constructores = clase.getConstructors();
			for (Constructor constructor : constructores) {
				Parameter[] parametros = constructor.getParameters();
				for (Parameter parametro : parametros) {
					texto = texto + parametro.getType().getSimpleName() + " ";
				}
				constructoresStr.add(clase.getSimpleName() + texto + ")");
				texto = "( ";
			}
			
			texto = txtrconstructores.getText();
			
			for(int i=0; i<constructoresStr.size();i++) {
				constructoresy += 16;
				texto = texto + constructoresStr.get(i) + "\n";
			}
			txtrconstructores.setText(texto);
			txtrconstructores.setBounds(12, txtrconstructoresy, 350, constructoresy);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		JLabel lblMetodos = new JLabel("Metodos: ");
		lblMetodos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMetodos.setBounds(12, txtrconstructoresy + constructoresy + 5, 131, 16);
		contentPane.add(lblMetodos);
		
		JTextArea txtrmetodos = new JTextArea();
		contentPane.add(txtrmetodos);
		
		int metodosy = 0;
		int txtrmetodosy = txtrconstructoresy + constructoresy + 5 + 25;
		try {
			Class<?> clase = Class.forName(className);
			List<String> metodosStr = (List<String>) new ArrayList<String>();
			
			String texto = "( ";
			
			Method[] metodos = clase.getDeclaredMethods();
			for (Method metodo : metodos) {
				Parameter[] parametros = metodo.getParameters();
				for (Parameter parametro : parametros) {
					texto = texto + parametro.getType().getSimpleName() + " ";
				}
				metodosStr.add(Modifier.toString(metodo.getModifiers()) + " " +metodo.getName() + texto + ")");
				texto = "( ";
			}
			
			texto = txtrmetodos.getText();
			
			for(int i=0; i<metodosStr.size();i++) {
				metodosy += 16;
				texto = texto + metodosStr.get(i) + "\n";
			}
			txtrmetodos.setText(texto);
			txtrmetodos.setBounds(12, txtrmetodosy, 350, metodosy);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
