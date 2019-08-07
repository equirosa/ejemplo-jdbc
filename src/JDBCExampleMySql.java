import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
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
			out.println("5. Registrar Mascota\n" +
					"6. Listar Mascotas");
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
			case 5:
				registrarMascota();
				break;
			case 6:
				listarMascotas();
				break;
			case 0:
				out.println("Adiós");
				break;
			default:
				out.println("Opción inválida");
		}
	}
	
	private static void listarMascotas() {
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			ArrayList<Mascota> lista = new ArrayList<>();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Universidad?" +
					"user=root&password=");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Mascota");
			while(rs.next())
			{
				Mascota p = new Mascota();
				p.setId(rs.getInt("id_mascota"));
				p.setNombre(rs.getString("nombre"));
				p.setRaza(rs.getString("raza"));
				p.setFechaNac(LocalDate.parse(rs.getString("fecha_nac")));
				p.setDuenno(buscarPersona(rs.getString("id_persona")));
				lista.add(p);
			}
			
			//se imprime el Array list
			for(Mascota p : lista){
				out.println(p.toString());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private static Persona buscarPersona(String id_persona) {
		try{
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost/Universidad?" +
				"user=root&password=");
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Persona");
		while(rs.next())
		{
			if(rs.getString("cedula").equals(id_persona)){
				return new Persona(rs.getString("nombre"),rs.getString("apellido"),id_persona);
			}
		}
		
	} catch (Exception ex) {
		System.out.println(ex.getMessage());
	}
		return null;
	}
	
	private static void registrarMascota() throws IOException {
		String nombre,raza,cedula;
		LocalDate fechaNac;
		out.println("introduzca el nombre de la mascota");
		nombre=in.readLine();
		out.println("introduzca su raza");
		raza=in.readLine();
		out.println("introduzca la fecha de nacimiento (YYYY-MM-DD)");
		fechaNac=LocalDate.parse(in.readLine());
		listar();
		out.println("seleccione la cedula de uno de las personas registradas.");
		cedula=in.readLine();
		
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = null;
			Statement stmt = null;
			String formatoFecha = "%Y-%m-%d";
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Universidad?" +
					"user=root&password=");
			stmt = conn.createStatement();
			stmt.execute("INSERT INTO Mascota(nombre,raza,fecha_nac,id_persona) VALUES " +
					"('"+ nombre + "','" + raza + "','" +
					fechaNac +"','" + cedula + "')");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static void listar()
	{
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			ArrayList<Persona> lista = new ArrayList<>();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Universidad?" +
					"user=root&password=");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Persona");
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
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = null;
			Statement stmt = null;
			String[] info = pedirInfoPersona();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Universidad?" +
					"user=root&password=");
			stmt = conn.createStatement();
			stmt.execute("INSERT INTO Persona(NOMBRE,APELLIDO,CEDULA) VALUES " +
					"('"+ info[0] + "','" + info[1] + "','" +
					info[2] + "')");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public  static void modificar()
	{
		try {
			// The newInstance() call is a work around for someClass.forName("com.mysql.jdbc.Driver").newInstance();
			// broken Java implementations
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = null;
			Statement stmt = null;
			listar();
			out.println("introduzca la nueva info de la Persona.");
			String[] info = pedirInfoPersona();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Universidad?" +
					"user=root&password=");
			
			stmt = conn.createStatement();
			String cmdText = "UPDATE Persona SET nombre = '" +
					info[0] + "',apellido = '" + info[1] + "'" +
					" WHERE cedula = '" + info[2] + "'";
			stmt.execute(cmdText);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private static String[] pedirInfoPersona() throws IOException {
		String[] info = new String[3];
		out.println("Introduzca el nombre de la persona.");
		info[0]=in.readLine();
		out.println("Introduzca el apellido de la persona.");
		info[1]=in.readLine();
		out.println("Introduzca la cedula de la persona.");
		info[2]=in.readLine();
		return info;
	}
	
	public  static void eliminar()
	{
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = null;
			Statement stmt = null;
			out.println("introduzca la cedula del usuario que desea eliminar.");
			String cedula = in.readLine();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Universidad?" +
					"user=root&password=");
			stmt = conn.createStatement();
			stmt.execute("DELETE  FROM Persona  WHERE cedula = '" + cedula + "'");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}

