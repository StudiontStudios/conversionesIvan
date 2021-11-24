package binToXML;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class BinToXML {

    //Esta clase lee los datos de un fichero binario y los escribe en un XML
    public static void main(String[] args) {
        Departamento d = null;
        File fichero = new File("fichero.dat");
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        ArrayList lista = new ArrayList();

        try {
            fin = new FileInputStream(fichero);
            ois = new ObjectInputStream(fin);
            d = new Departamento();

            while (true) {
                d = (Departamento) ois.readObject();
                lista.add(d);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        } catch (EOFException ex) {
            construirXML(lista); //va al metodo que hace el XML
        } catch (IOException ex) {
            Logger.getLogger(BinToXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Final del archivo");
        } finally {
            try {
                fin.close();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(BinToXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void construirXML(ArrayList lista) {
        Departamento d = new Departamento();
        ArrayList listaEmpleados = new ArrayList();

        try {
            //haces el fichero xml
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Departamentos", null);//nodo padre
            document.setXmlVersion("1.0");

            for (int i = 0; i < lista.size(); i++) {
                d = (Departamento) lista.get(i);
                listaEmpleados = d.getEmpleados();

                //se crean solamente los nodos, sin asignar nada
                Element depart = document.createElement("departamento");//se crea el nodo hijo
                Element empdos = document.createElement("empleados");//se crea el nodo hijo que sera de departamento
                Element emp = document.createElement("empleado");

                document.getDocumentElement().appendChild(depart);//defines el hijo en este caso de depart(departamento)
                //se crean los campos de depart
                crearElemento("nombre", d.getNombre(), depart, document);
                crearElemento("loc", d.getLoc(), depart, document);

                //defines quien es hijo de quien
                depart.appendChild(empdos);
                empdos.appendChild(emp);

                Empleado e = new Empleado();
                //e = (Empleado) listaEmpleados.get(i);

                for (int j = 0; j < listaEmpleados.size(); j++) {
                    e = (Empleado) listaEmpleados.get(j);
                    //campos de empleado
                    crearElemento("nombre", e.getNombre(), emp, document);
                    crearElemento("apellido", e.getApellido(), emp, document);
                    crearElemento("edad", e.getEdad() + "", emp, document);

                    emp = document.createElement("empleado"); //nodo empleado
                }
            }
            //crear el documento
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("Departamentos.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(BinToXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(BinToXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void crearElemento(String etiqueta, String valor, Element raiz, Document document) {
        Element elem = document.createElement(etiqueta);
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor		 	
    }
}
