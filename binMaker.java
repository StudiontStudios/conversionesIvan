package binToXML;

import com.github.javafaker.Faker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class binMaker{
    //este programa hace un archivo binario con un departamento y que
    //contiene uno o varios empleados, tanto los datos de los empleados
    //y el departamento como el numero de empleados por departamento
    //serán aleatorios.
    public static void main(String[] args) {
        File fichero = new File("fichero.dat");
        Faker f = new Faker();
        FileOutputStream fout = null;
        FileInputStream fin = null;
        Departamento d;
        Empleado e;
        ArrayList lista = new ArrayList();
        ObjectOutputStream os = null;
        
        try {
            fout = new FileOutputStream(fichero);
            fin = new FileInputStream(fichero);  
            os = new ObjectOutputStream(new FileOutputStream(fichero));
            
            for(int i=0;i<5;i++){
                d = new Departamento();
                d.setLoc(f.country().capital());
                d.setNombre(f.pokemon().name());
                for(int j=0;j<f.number().numberBetween(1, 5);j++){
                    e = new Empleado();
                    e.setNombre(f.name().firstName());
                    e.setApellido(f.name().lastName());
                    e.setEdad(f.number().numberBetween(18, 65));
                    lista.add(e);
                }
                d.setEmpleados(lista);
                os.writeObject(d);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(binMaker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(binMaker.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                fout.close();
                fin.close();
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(binMaker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
