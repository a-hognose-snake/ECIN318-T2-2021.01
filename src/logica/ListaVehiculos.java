package logica;

import dominio.Vehiculo;

public class ListaVehiculos {
	
	private int max;
	private int cantidad;
	private Vehiculo [] lista;
	
	public ListaVehiculos(int max) {
		this.max = max;
		lista = new Vehiculo[max];
	}
	
	public boolean ingresarVehiculo(Vehiculo x) {
		if (cantidad < max) {
			lista[cantidad] = x;
			cantidad++;
			return true;
		}
		else {
			return false;
		}
	}
	
	public Vehiculo getVehiculo(String placa) {
		 for (int i = 0; i < cantidad;i++) {
			 if(lista[i].getPlaca().equals(placa)) {
				 return lista[i];
			 }
		 }
		 return null;
	}
	
	public Vehiculo getVehiculoI(int index) {
		return lista[index];
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	@Override
	public String toString() {
		String text = "";
		for (int i = 0; i < cantidad; i++){
			Vehiculo x = lista[i];
			text += x.toString()+"\n";	
		}
	return text;
	}
	
	public boolean eliminarVehiculo(String placa) {
		int i = 0;
		while (i < cantidad && !placa.equals(lista[i].getPlaca())) {
			i++;
		}
		if (i == cantidad) {
			return false;
		}
		else {
			///corrimiento
			for (int j = 0; j < cantidad - 1; j++) {
				lista[j] = lista[j+1];	
			}
			cantidad --;
			return true;
		}
	}

}
