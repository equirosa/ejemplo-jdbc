import java.time.LocalDate;

public class Mascota {
	private String nombre, raza;
	private LocalDate fechaNac;
	private Persona duenno;
	private int id;
	
	public Mascota(String nombre, String raza, LocalDate fechaNac, Persona duenno) {
		this.nombre = nombre;
		this.raza = raza;
		this.fechaNac = fechaNac;
		this.duenno = duenno;
	}
	
	public Mascota() {
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getRaza() {
		return raza;
	}
	
	public void setRaza(String raza) {
		this.raza = raza;
	}
	
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	
	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	public Persona getDuenno() {
		return duenno;
	}
	
	public void setDuenno(Persona duenno) {
		this.duenno = duenno;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Mascota mascota = (Mascota) o;
		
		return id == mascota.id;
		
	}
	
	@Override
	public String toString() {
		return "Mascota{" +
				"nombre='" + nombre + '\'' +
				", raza='" + raza + '\'' +
				", fechaNac=" + fechaNac +
				", duenno=" + duenno +
				", id=" + id +
				'}';
	}
}
