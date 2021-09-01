package dominio;

import logica.ListaVehiculos;
public class Cliente {
	
	private String rut;
	private String nombre;
	private String apellido;
	private String clave;
	private int cantidadVehiculos;
	private int saldo;
	private ListaVehiculos vehiculos;
	
	public Cliente(String rut, String nombre, String apellido, String clave, int cantidadVehiculos, int saldo) {
		this.rut = rut;
		this.nombre = nombre;
		this.apellido = apellido;
		this.clave = clave;
		this.cantidadVehiculos = cantidadVehiculos;
		this.saldo = saldo;
		vehiculos = new ListaVehiculos(10);
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getCantidadVehiculos() {
		return cantidadVehiculos;
	}

	public void setCantidadVehiculos(int cantidadVehiculos) {
		this.cantidadVehiculos = cantidadVehiculos;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public ListaVehiculos getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(ListaVehiculos vehiculos) {
		this.vehiculos = vehiculos;
	}
	
}
