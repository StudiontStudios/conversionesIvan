package binToXML;

import java.io.Serializable;
import java.util.ArrayList;

public class Departamento implements Serializable {

    private String nombre;
    private String loc;
    private ArrayList<Empleado> empleados;

    public Departamento(String nombre, String loc, ArrayList<Empleado> empleados) {
        this.nombre = nombre;
        this.loc = loc;
        this.empleados = empleados;
    }

    public Departamento() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

}
