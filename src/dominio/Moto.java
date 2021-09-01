package dominio;

public class Moto extends Vehiculo {
	
	private static int costoVariable = 81;
	private static int revisionTecnica = 8250;
	
	public Moto(String modelo, String placa, int precio, int year, String tipoVehiculo) {
		super(modelo, placa, precio, year, tipoVehiculo);
	}

	public static int getCostoVariable() {
		return Moto.costoVariable;
	}

	public static void setCostoVariable(int costoVariable) {
		Moto.costoVariable = costoVariable;
	}

	public static int getRevisionTecnica() {
		return Moto.revisionTecnica;
	}

	public static void setRevisionTecnica(int revisionTecnica) {
		Moto.revisionTecnica = revisionTecnica;
	}

	@Override
	public int calcularPrecioVenta() {
		int PV = (int) ((this.getPrecio()*0.87) - revisionTecnica);
		return PV;
	}

	@Override
	public int calcularPrecioCompra() {
		int PC = (int) ((this.getPrecio())/(float)( Moto.getCostoVariable()/100));
		return PC;
	}
	
	@Override
	public String toString() {	
		String text = "";
		text += "Placa: "+this.getPlaca()+", Tipo: "+this.getTipoVehiculo()+", Precio: "+this.getPrecio()+", Year: "+this.getYear()+"\n";
		return text;
	}

}
