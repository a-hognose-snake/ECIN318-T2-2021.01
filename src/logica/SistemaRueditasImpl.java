package logica;

import dominio.Auto;
import dominio.Cliente;
import dominio.Moto;
import dominio.Vehiculo;

public class SistemaRueditasImpl implements SistemaRueditas {
	
	private ListaClientes clientes;
	private ListaVehiculos vehiculos;
	
	public SistemaRueditasImpl() {
		this.clientes = new ListaClientes(10);
		this.vehiculos = new ListaVehiculos(100);
	}

	@Override
	public boolean ingresarCliente(String rut, String nombre, String apellido, String clave, int cantidadVehiculos, int saldo) {
		Cliente c = new Cliente(rut, nombre, apellido, clave, cantidadVehiculos, saldo);
		clientes.ingresarCliente(c);
		return true;
	}

	@Override
	public boolean ingresarAuto(String modelo, String placa, int precio, int year, String tipoVehiculo, int rendimiento) {
		Vehiculo a = new Auto(modelo, placa, precio, year, tipoVehiculo, rendimiento);
		vehiculos.ingresarVehiculo(a);
		return true;
	}

	@Override
	public boolean ingresarMoto(String modelo, String placa, int precio, int year, String tipoVehiculo) {
		Vehiculo m = new Moto(modelo, placa, precio, year, tipoVehiculo);
		vehiculos.ingresarVehiculo(m);
		return true;
	}

	@Override
	public void asociarPropietarioDeVehiculo(String rut, String placa) {
		Vehiculo v = vehiculos.getVehiculo(placa);
		Cliente propietario = clientes.getCliente(rut);
		if (v!=null && propietario!=null) {
			propietario.getVehiculos().ingresarVehiculo(v);
			v.setPropietario(propietario.getRut());	
		} 
		else {
			throw new NullPointerException("No existe vehiculo y/o propietario. No se pudo asociar");
		}
	}

	@Override
	public boolean validarUsuario(String userId) {
		if (userId.equals("ADMIN")) {
			//usuario valido
			return true;
		} 
		else {
			if (clientes.getCliente(userId) != null) {
				//usuario valido
				return true;
			} 
			else {
				//usuario invalido
				return false;
			}
		}
	}

	@Override
	public boolean validarAcceso(String userId, String clave) {
		if (userId.equals("ADMIN") && clave.equals("ADMIN")) {
			//acceso correcto
			return true;
		} 
		else {
			if (clientes.getCliente(userId) != null && clientes.getCliente(userId).getClave().equals(clave)) {
				//acceso correcto
				return true;
			} 
			else {
				//acceso incorrecto
				return false;
			}
		}
	}

	@Override
	public String obtenerVehiculosDeCliente(String rut) {
		String text = "";
		Cliente c = clientes.getCliente(rut);
		if (c!=null) {
			text += "Datos de "+c.getNombre()+" "+c.getApellido()+" (usuario #"+c.getRut()+"):\n";
			text += "Saldo: "+c.getSaldo()+" CLP \n";
			if (c.getVehiculos().getCantidad() <= 0) {
				text += "No tiene vehiculos.";
			} 
			else {
				text += "Cantidad de vehiculos: "+c.getVehiculos().getCantidad()+"\n";
				text += c.getVehiculos().toString();
				text += "\n";
			}
		}
		else {
			text = "No existe cliente con ese usuario.";
		}
		return text;
	}

	@Override
	public void agregarSaldo(String rut, int dinero, String clave) {
		Cliente c = clientes.getCliente(rut);
		if (c!=null) {
			int saldoAnterior = c.getSaldo();
			int saldoActualizado = saldoAnterior + dinero;
			c.setSaldo(saldoActualizado);
		} 
		else {
			throw new NullPointerException("No existe cliente. No se pudo agregar saldo.");
		}
	}

	@Override
	public void cambiarClave(String rut, String nuevaClave) {
		Cliente c = clientes.getCliente(rut);
		if (c!=null) {
			c.setClave(nuevaClave);
		} 
		else {
			throw new NullPointerException("No existe cliente. No se pudo cambiar la clave.");
		}
	}

	@Override
	public String obtenerPrecioDeVehiculosDeCliente(String rut) {
		String text = "";
		Cliente c = clientes.getCliente(rut);
		if (c != null) {
			text += "Cliente: "+c.getNombre()+" "+c.getApellido()+"\n";
			if (c.getVehiculos().getCantidad() <= 0) {
				text = "El cliente no tiene vehiculos.";
			}
			else {
				text += "Cantidad de vehiculos: "+c.getVehiculos().getCantidad()+"\n";
				text += c.getVehiculos().toString();
				text += "\n";
			}
		} 
		else {
			throw new NullPointerException("No existe cliente.");
		}
		return text;
	}

	@Override
	public String obtenerPrecioDeVentaDeVehiculo(String placa) {
		String text = "";
		Vehiculo v = vehiculos.getVehiculo(placa);
		if (v!=null) {
			text = "El vehiculo "+v.getPlaca()+" de "+clientes.getCliente(v.getPropietario()).getNombre()+" tiene un precio de venta de "+v.calcularPrecioVenta()+" CLP.\n";
		} 
		else {
			text = "No existe ese vehiculo.";
		}
		return text;
	}

	@Override
	public void venderVehiculo(String rut, String placa) {
		Cliente c = clientes.getCliente(rut);
		Vehiculo v = vehiculos.getVehiculo(placa);
		if (c!=null && v!=null) {
			if (v.getTipoVehiculo().equals("Auto")) {
				if (v.getYear() > 2011) {
					int precioDeVenta = v.calcularPrecioVenta();
					int saldoCliente = c.getSaldo();
					int saldoActualizado = saldoCliente + precioDeVenta;
					c.setSaldo(saldoActualizado);
					c.getVehiculos().eliminarVehiculo(placa);
					v.setPropietario(null);
				}
				else {
					throw new NullPointerException("No cumple el estandar de la automotora. No se realizo la compra.");
				}
			} 
			else {
				if (v.getYear() > 2016) {
					int precioDeVenta = v.calcularPrecioVenta();
					int saldoCliente = c.getSaldo();
					int saldoActualizado = saldoCliente + precioDeVenta;
					c.setSaldo(saldoActualizado);
					c.getVehiculos().eliminarVehiculo(placa);
					v.setPropietario(null);
					
				} 
				else {
					throw new NullPointerException("No cumple el estandar de la automotora. No se realizo la compra.");
				}
			}

		} 
		else {
			throw new NullPointerException("No existe vehiculo y/o cliente. No se pudo vender el vehiculo");
		}
	}

	@Override
	public String obtenerVehiculosDeAutomotora() {
		String text = "";
		if (vehiculos.getCantidad() > 0) {
			for (int i = 0; i < vehiculos.getCantidad(); i++) {
				Vehiculo v = vehiculos.getVehiculoI(i);
				if (v.getPropietario() == null) {
					text += v.toString()+"\n";
				}
			}
		}
		else {
			text = "La automotora no tiene vehiculos.";
		}
		return text;
	}

	@Override
	public void comprarVehiculo(String rut, String placa) {
		Cliente c = clientes.getCliente(rut);
		Vehiculo v = vehiculos.getVehiculo(placa);
		if (c!=null && v!=null) {
			int precioDeCompra = v.calcularPrecioCompra();
			int saldoCliente = c.getSaldo();
			System.out.println(precioDeCompra);
			if (saldoCliente < precioDeCompra) {
				throw new NullPointerException("No se pudo comprar el vehiculo, ya que el cliente no tiene suficiente dinero.\n");
			} 
			else {
				int saldoActualizado = saldoCliente - precioDeCompra;
				c.setSaldo(saldoActualizado);
				v.setPropietario(c.getRut());

			}
		}
		else {
			throw new NullPointerException("No existe vehiculo y/o cliente. No se pudo comprar el vehiculo");
		}
	}

	@Override
	public String obtenerPrecioUsdDeVehiculosDeCliente(String rut) {
		String text = "";
		Cliente c = clientes.getCliente(rut);
		if (c!=null) {
			for (int i = 0; i < c.getVehiculos().getCantidad(); i++) {
				Vehiculo v = c.getVehiculos().getVehiculoI(i);
				text += "Modelo: "+v.getModelo()+", Precio: "+v.getPrecio()/730+" USD.\n";
			}
		} 
		else {
			throw new NullPointerException("No existe cliente.");
		}
		return text;
	}

	@Override
	public String obtenerPosibleClienteComprador() {
		String text = "";
		int max = -1;
		int index = 0;
		for (int i = 0; i < clientes.getCantidad(); i++) {
			Cliente c = clientes.getClienteI(i);
			int cant = c.getVehiculos().getCantidad();
			if (cant > max) {
				index = i;
			}
		}
		Cliente CM = clientes.getClienteI(index);
		text = "El cliente con mayor cantidad de vehiculos es "+CM.getNombre()+" "+CM.getApellido()+" ("+CM.getRut()+") , ya que tiene "+CM.getVehiculos().getCantidad()+".\n";
		return text;
		
	}

	@Override
	public String obtenerPrecioCompraVentaDeVehiculosDeAutomotora() {
		String text = "";
		if (vehiculos.getCantidad()>0) {
			for (int i = 0; i < vehiculos.getCantidad(); i++) {
				Vehiculo v = vehiculos.getVehiculoI(i);
				if (v.getPropietario() == null) {
					text += "Modelo: "+v.getModelo()+", Precio de venta: "+v.calcularPrecioVenta()/730+" USD, Precio de compra: "+v.calcularPrecioCompra()/730+" USD.\n";
				}
			}
		} 
		else {
			text = "La automotora no tiene vehiculos.";
		}
		return text;
	}

	@Override
	public String obtenerFuturasGananciasDeAutomotora() {
		String text = "";
		int ganancias = 0;
		for (int i = 0; i < vehiculos.getCantidad(); i++) {
			Vehiculo v = vehiculos.getVehiculoI(i);
			if (v.getPropietario() == null) {
				ganancias += v.calcularPrecioCompra() - v.calcularPrecioVenta();
			}				
		}
		text = "Futuras ganancias: "+ganancias+" CLP\n";
		return text;
	}

	@Override
	public String obtenerTxtClientes() {
		String text = "";
		int cantidad = clientes.getCantidad();
		for (int i = 0; i < cantidad; i++) {
			Cliente c = clientes.getClienteI(i);
			if (i == cantidad - 1) {
				text += c.getRut()+","+c.getNombre()+","+c.getApellido()+","+c.getClave()+","+c.getCantidadVehiculos()+","+c.getSaldo();
			} 
			else {
				text += c.getRut()+","+c.getNombre()+","+c.getApellido()+","+c.getClave()+","+c.getCantidadVehiculos()+","+c.getSaldo()+"\n";
			}
		}
		return text;
	}

	@Override
	public String obtenerTxtVehiculos() {
		String text = "";
		int cantidad = vehiculos.getCantidad();
		for (int i = 0; i < cantidad; i++) {
			Vehiculo v = vehiculos.getVehiculoI(i);
			if (i == cantidad - 1) {
				if (v.getPropietario() == null) {
					if (v instanceof Auto) {
						Auto a = (Auto) v;
						text += "En Venta,"+a.getModelo()+","+a.getPlaca()+","+a.getPrecio()+","+a.getYear()+","+a.getTipoVehiculo()+","+a.getRendimientoMotor();
					} 
					else {
						text += "En Venta,"+v.getModelo()+","+v.getPlaca()+","+v.getPrecio()+","+v.getYear()+","+v.getTipoVehiculo();	
					}
				}
				else {
					if (v instanceof Auto) {
						Auto a = (Auto) v;
						text += a.getPropietario()+","+a.getModelo()+","+a.getPlaca()+","+a.getPrecio()+","+a.getYear()+","+a.getTipoVehiculo()+","+a.getRendimientoMotor();
					} 
					else {
						text += v.getPropietario()+","+v.getModelo()+","+v.getPlaca()+","+v.getPrecio()+","+v.getYear()+","+v.getTipoVehiculo();	
					}
				}
			}
			else {
				if (v.getPropietario() == null) {
					if (v instanceof Auto) {
						Auto a = (Auto) v;
						text += "En Venta,"+a.getModelo()+","+a.getPlaca()+","+a.getPrecio()+","+a.getYear()+","+a.getTipoVehiculo()+","+a.getRendimientoMotor()+"\n";
					} 
					else {
						text += "En Venta,"+v.getModelo()+","+v.getPlaca()+","+v.getPrecio()+","+v.getYear()+","+v.getTipoVehiculo()+"\n";	
					}
				}
				else {
					if (v instanceof Auto) {
						Auto a = (Auto) v;
						text += a.getPropietario()+","+a.getModelo()+","+a.getPlaca()+","+a.getPrecio()+","+a.getYear()+","+a.getTipoVehiculo()+","+a.getRendimientoMotor()+"\n";
					} 
					else {
						text += v.getPropietario()+","+v.getModelo()+","+v.getPlaca()+","+v.getPrecio()+","+v.getYear()+","+v.getTipoVehiculo()+"\n";	
					}
				}

			}
			
		}
		return text;
	}
		
}