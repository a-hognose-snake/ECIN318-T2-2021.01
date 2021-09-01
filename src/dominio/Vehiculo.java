package dominio;

public abstract class Vehiculo {
	
	private String modelo;
	private String placa;
	private int precio;
	private int year;
	private String tipoVehiculo;
	private String propietario;
	
	public Vehiculo(String modelo, String placa, int precio, int year, String tipoVehiculo) {
		this.modelo = modelo;
		this.placa = placa;
		this.precio = precio;
		this.year = year;
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public abstract int calcularPrecioVenta();
	
	public abstract int calcularPrecioCompra();
	
	@Override
	public String toString() {	
		String text = "";
		text += "Placa: "+this.getPlaca()+", Tipo: "+this.getTipoVehiculo()+", Precio: "+this.getPrecio()+", Year: "+this.getYear()+"\n";
		return text;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	
}
