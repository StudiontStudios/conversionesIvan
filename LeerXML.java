/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerXML;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;/*
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;*/
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Ventilador Gaming
 */
public class LeerXML {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("Departamentos.xml"));
            document.getDocumentElement().normalize();
            
            /* CODIGO PARA MOSTRAR LOS EMPLEADOS POR CONSOLA
            //System.out.println(document.getDocumentElement().getNodeName());
            NodeList empleados = document.getElementsByTagName("empleado");

            System.out.println("Numero de empleados: " + empleados.getLength());

            
            for (int i = 0; i < empleados.getLength(); i++) {
                Node emple = empleados.item(i);
                if (emple.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) emple;
                    System.out.println("----------------------\nDatos empleado: \n"
                            + "nombre: "+elemento.getElementsByTagName("nombre").item(0).getTextContent()
                            +"\napellido: "+elemento.getElementsByTagName("apellido").item(0).getTextContent()
                            +"\nedad: "+elemento.getElementsByTagName("edad").item(0).getTextContent());
                }
            }
            */
            
            //INICIALIZACION DE OBJETOS DE HIBERNATE
            /*
            Configuration cfg = new Configuration().configure();
            SessionFactory sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
            Session session = sessionFactory.openSession();
            */
            NodeList departamentos = document.getElementsByTagName("departamento");
            
            for(int i = 0;i<departamentos.getLength();i++){
                Node departamento = departamentos.item(i);
                if(departamento.getNodeType() == Node.ELEMENT_NODE){
                    Element elemento = (Element) departamento;
                    
                    System.out.println("nombre depart: "+elemento.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("loc depart: "+elemento.getElementsByTagName("loc").item(0).getTextContent());
                }
            }
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(LeerXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(LeerXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeerXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
