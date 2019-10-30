import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class BuscadorClase {
	
	public BuscadorClase() {
	}

	public String buscarMetodos(String className) throws ClassNotFoundException {
		Class<?> clase = Class.forName(className);
		List<String> metodosStr = (List<String>) new ArrayList<String>();
		
		String texto = "(";
		
		Method[] metodos = clase.getDeclaredMethods();
		for (Method metodo : metodos) {
			Parameter[] parametros = metodo.getParameters();
			for (int i=0; i<parametros.length;i++) {
				if(i+1 == parametros.length) {
					texto = texto + parametros[i].getType().getSimpleName();
				}
				else {
					texto = texto + parametros[i].getType().getSimpleName() + ", ";
				}
			}
			metodosStr.add(Modifier.toString(metodo.getModifiers()) + " " + metodo.getReturnType().getSimpleName() + " " + metodo.getName() + texto + ")");
			texto = "(";
		}
		
		texto = "";
		
		for(int i=0; i<metodosStr.size();i++) {
			if(i+1 == metodosStr.size()) {
				texto = texto + metodosStr.get(i);
			}
			else {
				texto = texto + metodosStr.get(i) + "\n";
			}
		}
		return texto;
	}
	
	public String buscarConstructores(String className) throws ClassNotFoundException {
		Class<?> clase = Class.forName(className);
		List<String> constructoresStr = (List<String>) new ArrayList<String>();
		
		String texto = "(";
		
		Constructor[] constructores = clase.getConstructors();
		for (Constructor constructor : constructores) {
			Parameter[] parametros = constructor.getParameters();
			for (int i=0; i<parametros.length;i++) {
				if(i+1 == parametros.length) {
					texto = texto + parametros[i].getType().getSimpleName();
				}
				else {
					texto = texto + parametros[i].getType().getSimpleName() + ", ";
				}
			}
			constructoresStr.add(clase.getSimpleName() + texto + ")");
			texto = "(";
		}
		
		texto = "";
		
		for(int i=0; i<constructoresStr.size();i++) {
			if(i+1 == constructoresStr.size()) {
				texto = texto + constructoresStr.get(i);
			}
			else {
				texto = texto + constructoresStr.get(i) + "\n";
			}
		}
		return texto;
	}
	
	public String buscarAtributos(String className) throws ClassNotFoundException {
		Class<?> clase = Class.forName(className);
		List<String> atributosStr = (List<String>) new ArrayList<String>();
		
		Field[] fields = clase.getDeclaredFields();
		for (Field field : fields) {
			atributosStr.add(Modifier.toString(field.getModifiers()) + " " + field.getType().getSimpleName() + " " + field.getName());
		}
		
		String texto = "";
		
		for(int i=0; i<atributosStr.size();i++) {
			if(i+1 == atributosStr.size()) {
				texto = texto + atributosStr.get(i);
			}
			else {
				texto = texto + atributosStr.get(i) + "\n";
			}
		}
		return texto;
	}
	
	public String buscarInterfaces(String className) throws ClassNotFoundException {
		Class<?> clase = Class.forName(className);
		List<String> interfacesStr = (List<String>) new ArrayList<String>();
		
		while(clase != null) {
			Class[] interfaces = clase.getInterfaces();
			
			for (Class interfaz : interfaces) {
				interfacesStr.add(interfaz.getSimpleName() + " de la clase " + clase.getSimpleName());
			}
			
			clase = clase.getSuperclass();
		}
		
		String texto = "";
		
		for(int i=0; i<interfacesStr.size();i++) {
			if(i+1 == interfacesStr.size()) {
				texto = texto + interfacesStr.get(i);
			}
			else {
				texto = texto + interfacesStr.get(i) + "\n";
			}
		}
		return texto;
	}
	
	public String buscarSupers(String className) throws ClassNotFoundException {
		List<String> clasesPadre = (List<String>) new ArrayList<String>();
		Class<?> Supers = Class.forName(className);
		while(Supers != null) {
			clasesPadre.add(Supers.getName());
			Supers = Supers.getSuperclass();
		}
		
		String texto = "";
		
		for(int i=0; i<clasesPadre.size();i++) {
			for (int ii=0; ii != i; ii++) {
				texto = texto + "     ";
			}
			if(i==0) {
				texto = texto + clasesPadre.get(clasesPadre.size()-i-1) + "\n";
			}
			else {
				if(i+1 == clasesPadre.size()) {
					texto = texto + "∟" + clasesPadre.get(clasesPadre.size()-i-1);
				}
				else {
					texto = texto + "∟" + clasesPadre.get(clasesPadre.size()-i-1) + "\n";
				}
			}
		}
		return texto;
	}
}
