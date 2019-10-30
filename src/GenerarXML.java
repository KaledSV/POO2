import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@SuppressWarnings("rawtypes")
public class GenerarXML {
	
	public void creadorSupers(String className, Document document, Element jerarquia) throws ClassNotFoundException {
		List<String> clasesPadre = (List<String>) new ArrayList<String>();
		Class<?> Supers = Class.forName(className);
		while(Supers != null) {
			clasesPadre.add(Supers.getName());
			Supers = Supers.getSuperclass();
		}
		
		for(int i=0; i<clasesPadre.size(); i++) {
			Element claseSuper = document.createElement("Class" + Integer.toString(i+1));
			claseSuper.appendChild(document.createTextNode(clasesPadre.get(clasesPadre.size()-i-1)));
	        jerarquia.appendChild(claseSuper);
		}
	}
	
	public void crearInterfaces(String className, Document document, Element EInterfaz) throws ClassNotFoundException {
		Class<?> clase = Class.forName(className);
		List<String> interfacesStr = (List<String>) new ArrayList<String>();
		List<String> padresInterfaz = (List<String>) new ArrayList<String>();
		
		while(clase != null) {
			Class[] interfaces = clase.getInterfaces();
			for (Class interfaz : interfaces) {
				interfacesStr.add(interfaz.getSimpleName());
				padresInterfaz.add(clase.getSimpleName());
			}
			
			clase = clase.getSuperclass();
		}
		
		for(int i=0; i<interfacesStr.size(); i++) {
			Element interfaz = document.createElement(interfacesStr.get(i));
			
			Attr implementado = document.createAttribute("implementado");
			implementado.setValue(padresInterfaz.get(i));
			interfaz.setAttributeNode(implementado);
			
			EInterfaz.appendChild(interfaz);
		}
	}
	
	public void crearAtributos(String className, Document document, Element atributos) throws ClassNotFoundException {
		Class<?> clase = Class.forName(className);
		List<String> atributosStr = (List<String>) new ArrayList<String>();
		List<String> modificadorsStr = (List<String>) new ArrayList<String>();
		List<String> tipoStr = (List<String>) new ArrayList<String>();
		
		Field[] fields = clase.getDeclaredFields();
		for (Field field : fields) {
			modificadorsStr.add(Modifier.toString(field.getModifiers()));
			tipoStr.add(field.getType().getSimpleName());
			atributosStr.add(field.getName());
		}
		
		for(int i=0; i<atributosStr.size(); i++) {
			Element atributo = document.createElement(atributosStr.get(i));
			
			Element modificadores = document.createElement("modifiers");
			modificadores.appendChild(document.createTextNode(modificadorsStr.get(i)));
			atributo.appendChild(modificadores);
			
			Element type = document.createElement("type");
			type.appendChild(document.createTextNode(tipoStr.get(i)));
			atributo.appendChild(type);
			
			atributos.appendChild(atributo);
		}
	}
	
	public void crearConstructores(String className, Document document, Element EConstructores) throws ClassNotFoundException {
		Class<?> clase = Class.forName(className);
		List<String> constructoresStr = (List<String>) new ArrayList<String>();
		List<List<String>> parametrosStr = (List<List<String>>) new ArrayList<List<String>>();
		
		Constructor[] constructores = clase.getConstructors();
		
		for (int i=0; i<constructores.length;i++) {
			constructoresStr.add(clase.getSimpleName());
			
			List<String> nuevosParametros= (List<String>) new ArrayList<String>();
			parametrosStr.add(nuevosParametros);
			
			Parameter[] parametros = constructores[i].getParameters();
			
			for (int ii=0; ii<parametros.length; ii++) {
				parametrosStr.get(i).add(parametros[ii].getType().getSimpleName());
			}
		}
		
		for(int i=0; i<constructoresStr.size();i++) {
			Element constructor = document.createElement(constructoresStr.get(i));
			
			Element parametros = document.createElement("parameters");
			for (int ii=0; ii<parametrosStr.get(i).size(); ii++) {
				parametros.appendChild(document.createTextNode(parametrosStr.get(i).get(ii)));
			}
			constructor.appendChild(parametros);
			
			EConstructores.appendChild(constructor);
		}
	}
	
	public void crearMetodos(String className, Document document, Element EMetodos) throws ClassNotFoundException {
		Class<?> clase = Class.forName(className);
		List<String> metodosStr = (List<String>) new ArrayList<String>();
		List<String> modificadorsStr = (List<String>) new ArrayList<String>();
		List<String> tipoStr = (List<String>) new ArrayList<String>();
		List<List<String>> parametrosStr = (List<List<String>>) new ArrayList<List<String>>();
		
		Method[] metodos = clase.getDeclaredMethods();
		for (int i=0; i<metodos.length;i++) {
			metodosStr.add(metodos[i].getName());
			modificadorsStr.add(Modifier.toString(metodos[i].getModifiers()));
			tipoStr.add(metodos[i].getReturnType().getSimpleName());
			
			List<String> nuevosParametros= (List<String>) new ArrayList<String>();
			parametrosStr.add(nuevosParametros);
			
			Parameter[] parametros = metodos[i].getParameters();
			for (int ii=0; ii<parametros.length; ii++) {
				parametrosStr.get(i).add(parametros[ii].getType().getSimpleName());
			}
		}
		
		for(int i=0; i<metodosStr.size();i++) {
			Element metodo = document.createElement(metodosStr.get(i));
			
			Element modificadores = document.createElement("modifiers");
			modificadores.appendChild(document.createTextNode(modificadorsStr.get(i)));
			metodo.appendChild(modificadores);
			
			Element type = document.createElement("type");
			type.appendChild(document.createTextNode(tipoStr.get(i)));
			metodo.appendChild(type);
			
			Element parametros = document.createElement("parameters");
			for (int ii=0; ii<parametrosStr.get(i).size(); ii++) {
				parametros.appendChild(document.createTextNode(parametrosStr.get(i).get(ii)));
			}
			metodo.appendChild(parametros);
			
			EMetodos.appendChild(metodo);
		}
	}
	
	public GenerarXML(String className) {
		String xmlFilePath = "E:\\eclipses\\Proyectos\\POO2\\"+ className +".xml";
		try {
			 
	        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

	        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

	        Document document = documentBuilder.newDocument();

	       
	        Element clase = document.createElement(className);
	        document.appendChild(clase);

	        //Jerarquia de la clase
	        Element jerarquia = document.createElement("Jerarquia");
	        Element interfaces = document.createElement("Interfaces");
	        Element atributos = document.createElement("Atributos");
	        Element constructores = document.createElement("Constructores");
	        Element metodos = document.createElement("Metodos");
	        
	        try {
	        	creadorSupers(className, document, jerarquia);
	        	crearInterfaces(className, document, interfaces);
	        	crearAtributos(className, document, atributos);
	        	crearConstructores(className, document, constructores);
	        	crearMetodos(className, document, metodos);
	        } catch (ClassNotFoundException e) {
	        	e.printStackTrace();
			}
	        
	        clase.appendChild(jerarquia);
	        clase.appendChild(interfaces);
	        clase.appendChild(atributos);
	        clase.appendChild(constructores);
	        clase.appendChild(metodos);
	        
	        //ayuda
	        Attr attr = document.createAttribute("id");
	        attr.setValue("10");
	        jerarquia.setAttributeNode(attr);

	        // create the xml file
	        //transform the DOM Object to an XML File
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource domSource = new DOMSource(document);
	        StreamResult streamResult = new StreamResult(new File(xmlFilePath));

	        // If you use
	        // StreamResult result = new StreamResult(System.out);
	        // the output will be pushed to the standard output ...
	        // You can use that for debugging 

	        transformer.transform(domSource, streamResult);

	        System.out.println("Done creating XML File");

	    } catch (ParserConfigurationException pce) {
	        pce.printStackTrace();
	    } catch (TransformerException tfe) {
	        tfe.printStackTrace();
	    }
	}
}
