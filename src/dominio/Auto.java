package dominio;

public class Auto extends Vehiculo {
	
	private int rendimientoMotor;
	private static int costoVariable = 77;
	private static int revisionTecnica = 7950;
	
	public Auto(String modelo, String placa, int precio, int year, String tipoVehiculo, int rendimientoMotor) {
		super(modelo, placa, precio, year, tipoVehiculo);
		this.rendimientoMotor = rendimientoMotor;
	}

	public int getRendimientoMotor() {
		return rendimientoMotor;
	}

	public void setRendimientoMotor(int rendimientoMotor) {
		this.rendimientoMotor = rendimientoMotor;
	}

	public static int getCostoVariable() {
		return Auto.costoVariable;
	}

	public static void setCostoVariable(int costoVariable) {
		Auto.costoVariable = costoVariable;
	}

	public static int getRevisionTecnica() {
		return Auto.revisionTecnica;
	}

	public static void setRevisionTecnica(int revisionTecnica) {
		Auto.revisionTecnica = revisionTecnica;
	}

	@Override
	public int calcularPrecioVenta() {
		int PV = (this.getPrecio())/((Auto.getCostoVariable() + this.getRendimientoMotor())/100);
		return PV;
	}
	
	@Override
	public int calcularPrecioCompra() {
		int PC = ((this.getPrecio())/(Auto.getCostoVariable()/100)) + Auto.getRevisionTecnica();
		return PC;
	}
	
	@Override
	public String toString() {	
		String text = "";
		text += "Placa: "+this.getPlaca()+", Tipo: "+this.getTipoVehiculo()+", Precio: "+this.getPrecio()+", Year: "+this.getYear()+", Rendimiento del motor: "+this.getRendimientoMotor()+"\n";
		return text;
	}

	

}
