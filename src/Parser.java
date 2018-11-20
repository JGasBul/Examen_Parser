import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {
	private Document dom = null;
	private ArrayList<Accion> accion = null;

	public Parser() {
		// TODO Auto-generated constructor stub
		accion = new ArrayList<Accion>();
	}

	public void parseFicheroXml(String fichero) {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parseamos el XML y obtenemos una representación DOM
			dom = db.parse(fichero);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public void parseDocument() {
		// obtenemos el elemento raíz
		Element docEle = dom.getDocumentElement();

		// obtenemos el nodelist de elementos
		NodeList nl = docEle.getElementsByTagName("accion");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element el = (Element) nl.item(i);
				Accion a = getAccion(el);
				accion.add(a);
			}
		}
	}
	private Accion getAccion(Element AccionEle){
		ArrayList<Op> opArray=new ArrayList<Op>();
		String nombre_banco=AccionEle.getAttribute("nombre");
		NodeList nl = AccionEle.getElementsByTagName("operaciones");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				NodeList nl2 = AccionEle.getElementsByTagName("operacion");
				if (nl2 != null && nl2.getLength() > 0) {
					for (int x = 0; x < nl2.getLength(); x++) {
						Element el = (Element) nl2.item(x);
						String tipo=getTextValue(el,"tipo");
						int cantidad=getIntValue(el,"cantidad");
						Float precio=getFloatValue(el,"precio");
						Op op=new Op(tipo,cantidad,precio);
						opArray.add(op);
					}
				}
			}
		}
		Accion a=new Accion(nombre_banco,opArray);
		return a;

	}
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}		
		return textVal;
	}
	private int getIntValue(Element ele, String tagName) {				
		return Integer.parseInt(getTextValue(ele,tagName));
	}
	private Float getFloatValue(Element ele, String tagName) {				
		return Float.parseFloat((getTextValue(ele,tagName)));
	}
	public void print(){

		Iterator it = accion.iterator();
		while(it.hasNext()) {
			Accion a=(Accion) it.next();
			a.getOp();
		}
	}

}
