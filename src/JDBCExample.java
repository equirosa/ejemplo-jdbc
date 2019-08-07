/*

NOTA: PARA LOS QUE USAN SQL SERVER ESTO ES LO QUE DEBEN HACER SI VAN A
USAR AUTENTICACIÓN INTEGRADA (WINDOWS AUTHENTICATION)

1. DESCARGAR EL DIRVER Y DESOOMPRIMIRLO. ESTO LES CREARÁ UNA CARPETA Microsoft JDBC Driver 6.0 for SQL Server
2. DEBER IR A LA CARPETA \sqljdbc_6.0\enu\auth\x64, SI USA 64 BITS O X86 EN CASO CONTRARIO.
3. DEBE TOMAR LA DLL sqljdbc_auth.dll Y COPIARLA EN LA CARPETA BIN UBICADA EN EL DIRECTORIO DONDE ESTÁ INTALADO JAVA, POR LO GENERAL
   C:\Program Files\Java\jdk1.8.0_92\bin.
4. LUEGO DEBE CERRAR EL IDE RESPECTIVO Y VOLVERLO A INICIAL.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JDBCExample{
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;

    public static void main(String args[]) throws java.io.IOException {
        try {
            mostrarMenu();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void mostrarMenu() throws IOException {
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
            conn = DriverManager.getConnection("jdbc:mysql://localhost/laboratorio?" +
                    "user=root&password=root");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM persona");
            while(rs.next())
            {
                System.out.println( rs.getString("cedula") + " " +
                        rs.getString("nombre") + " " +
                        rs.getString("apellido"));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public  static void registrar()
    {
    }

    public  static void modificar()
    {
    }

    public  static void eliminar()
    {
    }
}


