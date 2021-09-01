package logica;

import dominio.Cliente;

public class ListaClientes {
	private int max;
	private int cantidad;
	private Cliente [] lista;
	
	public ListaClientes(int max) {
		this.max = max;
		cantidad = 0;
		lista = new Cliente[max];
	}
	
	public boolean ingresarCliente(Cliente x) {
		if (cantidad < max) {
			lista[cantidad] = x;
			cantidad++;
			return true;
		}
		else {
			return false;
		}
	}
	
	public Cliente getCliente(String rut) {
		 for (int i = 0; i < cantidad;i++) {
			 if(lista[i].getRut().equals(rut)) {
				 return lista[i];
			 }
		 }
		 return null;
	}
	
	public Cliente getClienteI(int index) {
		return lista[index];
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
