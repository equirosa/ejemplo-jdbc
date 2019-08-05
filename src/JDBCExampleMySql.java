import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;
import java.util.ArrayList;

public class JDBCExampleMySql {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;

    public static void main (String args[]) throws java.io.IOException {
        try {
            mostrarMenu();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void mostrarMenu() throws IOException{
        int opcion;
        opcion = -1;
        do{
            out.println("1.Listar Personas");
            out.println("2.Registrar Persona");
            out.println("3.Modificar Persona ");
            out.println("4. Eliminar Persona ");
            out.println("0.Salir");
            out.println("Digite la opción que dese");
            opcion = Integer.parseInt(in.readLine());
            procesarOpcion(opcion);
        }while(opcion != 0);
    }
    //Rutina para procesar la opción seleccionada por el usuario
    public static void procesarOpcion(int pOpcion)
            throws java.io.IOException{
        switch(pOpcion){
            case 1:
                listar();
                break;
            case 2:
                registrar();
                break;
            case 3:
                modificar();
                break;
            case 4:
                eliminar();
                break;
            case 0:
                out.println("Adiós");
                break;
            default:
                out.println("Opción inválida");
        }
    }
    public static void listar()
    {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            ArrayList<Persona> lista = new ArrayList<>();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/laboratorio?" +
                    "user=root&password=root");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM persona");
            while(rs.next())
            {
                Persona p = new Persona();
                p.setNombre(rs.getString("nombre"));
                p.setCedula(rs.getString("cedula"));
                p.setApellido(rs.getString("apellido"));
                lista.add(p);
            }

            //se imprime el Array list
            for(Persona p : lista){
                out.println(p.toString());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public  static void registrar()
    {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = null;
            Statement stmt = null;
            String nombre ="Pedro";
            String apelldio = "Perez";
            String cedula = "5";
            conn = DriverManager.getConnection("jdbc:mysql://localhost/laboratorio?" +
                    "user=root&password=root");
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO persona(NOMBRE,APELLIDO,CEDULA) VALUES " +
                    "('"+ nombre+ "','" + apelldio + "','" +
                    cedula + "')");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public  static void modificar()
    {
        try {
            // The newInstance() call is a work around for someClass.forName("com.mysql.jdbc.Driver").newInstance();
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = null;
            Statement stmt = null;
            String nombre ="Ratón";
            String apellido = "Perez";
            String cedula = "4";
            conn = DriverManager.getConnection("jdbc:mysql://localhost/laboratorio?" +
                    "user=root&password=root");

            stmt = conn.createStatement();
            String cmdText = "UPDATE PERSONA SET nombre = '" +
                    nombre + "',apellido = '" + apellido + "'" +
                    " WHERE cedula = '" + cedula + "'";
            stmt.execute(cmdText);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public  static void eliminar()
    {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = null;
            Statement stmt = null;
            String nombre ="Ratón";
            String apellido = "Perez";
            String cedula = "4";
            conn = DriverManager.getConnection("jdbc:mysql://localhost/laboratorio?" +
                    "user=root&password=root");
            stmt = conn.createStatement();
            stmt.execute("DELETE  FROM PERSONA  WHERE cedula = '" + cedula + "'");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

