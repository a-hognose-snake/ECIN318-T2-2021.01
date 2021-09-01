package logica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * Space Girls: Taller 02
 * @author a_hognose_snake
 * @author dorime_a1
 */
public class App {

	/**
	 * Executes the program
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		SistemaRueditas system = new SistemaRueditasImpl();
		lectura(system);
		iniciarSesion(system);
	}

	/**
	 * Allows the user to log in and obtain information according to its role (client or admin) or to try again or close the system.
	 * @param system
	 * @throws IOException
	 */
	private static void iniciarSesion(SistemaRueditas system) throws IOException{
		// [1] Cliente, [2] Admin, [3] Volver a intentar, [0] Cerrar sistema
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String rutInput = ingresarRut();
		int tipoLogIn = LogIn(system, rutInput);
		while (tipoLogIn == 3) {
			String rut = ingresarRut();
			tipoLogIn = LogIn(system, rut);	
		}
		if (tipoLogIn != 0) {
			if (tipoLogIn == 1) {
				// Menu Cliente
				desplegarMenuCliente();
				System.out.println("[ ] : ");
				while (true) {
					try {
						int operacion = Integer.parseInt(scan.nextLine());
						
						if (operacion == 1) {
							// [1] Desplegar datos cliente
							System.out.println(system.obtenerVehiculosDeCliente(rutInput));
							throw new NullPointerException("");
						}
						
						if (operacion == 2) {
							// [2] Agregar Saldo
							System.out.println("Saldo a agregar: ");
							int saldo = Integer.parseInt(scan.nextLine());
							System.out.println("Clave: ");
							String claveInput1 = scan.nextLine();
							System.out.println("Clave nuevamente: ");
							String claveInput2 = scan.nextLine();
							if (claveInput1.equals(claveInput2)) {
								system.agregarSaldo(rutInput, saldo, claveInput2);
								System.out.println("Exito.");
								throw new NullPointerException("");
							} 
							else {
								throw new NullPointerException("Las claves no son igual. No se pudo agregar saldo.");
							}
						}
							
						if (operacion == 3) {
							// [3] Cambiar clave
							System.out.println("Clave: ");
							String claveInput1 = scan.nextLine();
							if (system.validarAcceso(rutInput, claveInput1)) {
								System.out.println("Clave nueva: ");
								String claveNueva1 = scan.nextLine();
								System.out.println("Clave nueva nuevamente: ");
								String claveNueva2 = scan.nextLine();
								if (claveNueva1.equals(claveNueva2)) {
									system.cambiarClave(rutInput, claveNueva2);
									System.out.println("Exito.");
									throw new NullPointerException("");
								} 
								else {
									throw new NullPointerException("Las claves no son igual. No se pudo cambiar clave.");
								}
							} 
							else {
								throw new NullPointerException("Clave incorrecta. No se pudo cambiar clave.");
							}
						}
						
						if (operacion == 4) {
							// [4] Vender vehiculo
							System.out.println(system.obtenerPrecioDeVehiculosDeCliente(rutInput));
							System.out.println("Placa: ");
							String placa = scan.nextLine();
							System.out.println(system.obtenerPrecioDeVentaDeVehiculo(placa));
							System.out.println("Vender [S/N]: ");
							String y = scan.nextLine();
							if (y.equals("S")) {
								system.venderVehiculo(rutInput, placa);
								System.out.println("Exito.");
								throw new NullPointerException("");	
							} 
							else {
								throw new NullPointerException("No se realizo la venta del vehiculo.");
							}
							
								
						}
						
						if (operacion == 5) {
							// [4] Comprar vehiculo
							System.out.println(system.obtenerVehiculosDeAutomotora());
							System.out.println("Placa: ");
							String placa = scan.nextLine();
							system.comprarVehiculo(rutInput, placa);
							System.out.println("Exito.");
							throw new NullPointerException("");	
						}
						
						
						if (operacion == 0) {
							// 0] Cerrar Sistema
							reEscribirTxt(system);
							System.out.println("\n************************************************************");
							System.out.println("SISTEMA CERRADO");
							System.out.println("************************************************************");
							System.exit(0);
						} 
						
						else {
							throw new NumberFormatException();
						}
					} 
					
					catch (NumberFormatException exception) {
						System.out.print("Ingrese una opcion valida.\n");
						System.out.println("[ ] : ");
					}
					
					catch (NullPointerException e) {
			            System.out.print(e.getMessage());
			            desplegarMenuCliente();
			            System.out.println("[ ] : ");
			        }
				}
			}
			
			if (tipoLogIn == 2) {
				// [2] ADMIN
				desplegarMenuAdmin();
				System.out.println("[ ] : ");
				while (true) {
					try {
						int operacion = Integer.parseInt(scan.nextLine());
						
						if (operacion == 1) {
							// [1] Info del cliente
							System.out.println("Rut del cliente: ");
							String rut = cambiarFormato(scan.nextLine());
							System.out.println(system.obtenerPrecioUsdDeVehiculosDeCliente(rut));
							throw new NullPointerException("");	
						}
						
						if (operacion == 2) {
							// [2] Posible compra
							System.out.println(system.obtenerPosibleClienteComprador());
							throw new NullPointerException("");
						}
						
						if (operacion == 3) {
							// [3] Info venta/compra USD
							System.out.println(system.obtenerPrecioCompraVentaDeVehiculosDeAutomotora());
							throw new NullPointerException("");
						}
						
						if (operacion == 4) {
							// [4] Futuras ganancias
							System.out.println(system.obtenerFuturasGananciasDeAutomotora());
							throw new NullPointerException("");
						}
						
						if (operacion == 0) {
							// [0] Cerrar Sistema
							reEscribirTxt(system);
							System.out.println("\n************************************************************");
							System.out.println("SISTEMA CERRADO");
							System.out.println("************************************************************");
							System.exit(0);
						} 
						
						else {
							throw new NumberFormatException();
						}
						
					} 
					catch (NumberFormatException exception) {
						System.out.print("Ingrese una opcion valida.\n");
						System.out.print("[ ] : ");
					}
					catch (NullPointerException e) {
						System.out.println(e.getMessage() + "\n");
			            desplegarMenuAdmin();
			            System.out.println("[ ] : ");
				}
			}
		}
	}
	}
				

	/**
	 * Obtains data from the system and updates the .txt files.
	 * @param system
	 * @throws IOException
	 */
	private static void reEscribirTxt(SistemaRueditas system) throws IOException {
		FileWriter file_clientes = new FileWriter("clientes.txt");
        PrintWriter escritura_clientes = new PrintWriter(file_clientes);
        escritura_clientes.print(system.obtenerTxtClientes());
        escritura_clientes.close();
        file_clientes.close();
        
        FileWriter file_vehiculos = new FileWriter("vehiculos.txt");
        PrintWriter escritura_vehiculos = new PrintWriter(file_vehiculos);
        escritura_vehiculos.print(system.obtenerTxtVehiculos());
        escritura_vehiculos.close();
        file_vehiculos.close();
	}

	/**
	 * Displays the admin's menu of options.
	 */
	private static void desplegarMenuAdmin() {
		System.out.println("\n************************************************************");
		System.out.println("MENU ADMIN");
		System.out.println("************************************************************\n");
	    System.out.println("[1] Ver info cliente");
	    System.out.println("[2] Posible compra");
	    System.out.println("[3] Vehiculos a la venta");
	    System.out.println("[4] Futuras ganancias");
	    System.out.println("[0] Cerrar Sistema");
		
	}

	/**
	 * Displays the client's menu of options.
	 */
	private static void desplegarMenuCliente() {
		System.out.println("************************************************************");
		System.out.println("MENU CLIENTE");
		System.out.println("************************************************************\n");
	    System.out.println("[1] Ver datos cliente");
	    System.out.println("[2] Agregar saldo");
	    System.out.println("[3] Cambiar clave");
	    System.out.println("[4] Vender vehiculo");
	    System.out.println("[5] Comprar vehiculo");
	    System.out.println("[0] Cerrar Sistema");
		
	}

	/**
	 * Allows to know what type of user is trying to log in and it validates its access to the system or it lets the system know the next action to take.
	 * @param system
	 * @param rutInput
	 * @return
	 */
	private static int LogIn(SistemaRueditas system, String rutInput) {
		// [1] Cliente, [2] Admin, [3] Volver a intentar, [0] Cerrar sistema
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		boolean accesoInvalido = false;
		int tipoLogIn;
		if (system.validarUsuario(rutInput)) {
			// [1] : Cliente
			System.out.println("CONTRASEÑA: ");
			String contrasena = scan.nextLine();
			tipoLogIn = 1;
			if (system.validarAcceso(rutInput, contrasena)) {
				if (rutInput.equals("ADMIN")) {
					// [2] Admin
					tipoLogIn = 2;
					return tipoLogIn;
				}
				else {
					tipoLogIn = 1;
					return tipoLogIn;
				}
				
			} 
			else {
				accesoInvalido = true;
			}
		}
		
		if (accesoInvalido) {
			System.out.println("[ERROR]: Clave Incorrecta");
		    System.out.println("[3] Volver a intentarlo");
		    System.out.println("[0] Salir del sistema");
		    System.out.println("[ ]:  ");
		    String opcion = scan.nextLine();
		    while (!opcion.equals("3") && !opcion.equals("0")) {
		    	System.out.println("[ERROR]: Opcion incorrecta.");
		    	System.out.println("[3] Volver a intentarlo");
		        System.out.println("[0] Salir del sistema");
		        System.out.println("[ ]:  ");
		        opcion = scan.nextLine();
			}
		    if (opcion.equals("3")) {
		        String rut = ingresarRut();
		        return LogIn(system, rut);
		      } 
		    else {
		        return 0;
		    }	
		}
		else {
			String rutIn = ingresarRut();
			return LogIn(system, rutIn);
		}	
	}

	/**
	 * Asks for id number.
	 * @returns a string with the formated number
	 */
	private static String ingresarRut() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("\n************************************************************");
		System.out.println("INICIAR SESIÓN");
		System.out.println("************************************************************\n");
		System.out.println("RUT: ");
		String rutInput = scan.nextLine();
		rutInput = cambiarFormato(rutInput);
		return rutInput;
	}
	
	/**
	 * Changes the card number input format to a universal format.
	 * @param rutInput
	 * @returns a string with the formated input parameter
	 */
	private static String cambiarFormato(String rutInput) {
		String[] lista = rutInput.split("");
	    String retorno = "";
	    for (int i = 0; i < lista.length; i++) {
	      if (lista[i].equals(".") || lista[i].equals("-")) {
	      } else {
	        if (lista[i].equalsIgnoreCase("k")) {
	          retorno += "k";
	        } else {
	          retorno += lista[i];
	        }
	      }
	    }
	    return retorno;
	}
	
	/**
	 * Reads all files and registers data to the system.
	 * @param system
	 * @throws IOException
	 */
	private static void lectura(SistemaRueditas system) throws IOException {
		File file_clientes = new File("clientes.txt");
		Scanner  scan_clientes = new Scanner(file_clientes);
		System.out.println("************************************************************");
		System.out.println("LECTURA DE ARCHIVOS");
		System.out.println("************************************************************\n");
		while (scan_clientes.hasNextLine()) {
			String banana = scan_clientes.nextLine();
			String [] partes = banana.split(",");
			String rut = cambiarFormato(partes[0]);
			String nombre = partes[1];
			String apellido = partes[2];
			String clave = partes[3];
			int cantidadV = Integer.parseInt(partes[4]);
			int saldo = Integer.parseInt(partes[5]);
			try {
				if (system.ingresarCliente(rut, nombre, apellido, clave, cantidadV, saldo)) {
					System.out.println("Se ingreso correctamente al cliente.");
				} 
				else {
					System.out.println("[ERROR] : No se pudo ingresar el cliente.");
				}
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		scan_clientes.close();
		
		File file_vehiculos = new File("vehiculos.txt");
		Scanner scan_vehiculos = new Scanner(file_vehiculos);
		while (scan_vehiculos.hasNextLine()) {
			String banana = scan_vehiculos.nextLine();
			String [] partes = banana.split(",");
			String x = partes[0];
			String modelo = partes[1];
			String placa = partes[2];
			int precio = Integer.parseInt(partes[3]);
			int year = Integer.parseInt(partes[4]);
			String tipo = partes[5];
			if (x.equalsIgnoreCase("En Venta")) {
				if (tipo.equals("Auto")) {
					int rendimiento = Integer.parseInt(partes[6]);
					try {
						if (system.ingresarAuto(modelo, placa, precio, year, tipo, rendimiento)) {
							System.out.println("Se ingreso correctamente al Auto En Venta.");
						} 
						else {
							System.out.println("[ERROR] : No se pudo ingresar al Auto En Venta.");
						}
					} 
					catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} 
				else {
					try {
						if (system.ingresarMoto(modelo, placa, precio, year, tipo)) {
							System.out.println("Se ingreso correctamente a la Moto En Venta.");
						} 
						else {
							System.out.println("[ERROR] : No se pudo ingresar a la Moto En Venta.");
						}
					} 
					catch (Exception e) {
						System.out.println(e.getMessage());
					}

				}	
			} 
			else {
				String rutPropietario = cambiarFormato(x);
				if (tipo.equals("Auto")) {
					int rendimiento = Integer.parseInt(partes[6]);
					try {
						if (system.ingresarAuto(modelo, placa, precio, year, tipo, rendimiento)) {
							system.asociarPropietarioDeVehiculo(rutPropietario, placa);
							System.out.println("Se ingreso y se asocio correctamente al Auto.");
						} 
						else {
							System.out.println("[ERROR] : No se pudo ingresar y/o asociar el Auto.");
						}
					} 
					catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} 
				else {
					try {
						if (system.ingresarMoto(modelo, placa, precio, year, tipo)) {
							system.asociarPropietarioDeVehiculo(rutPropietario, placa);
							System.out.println("Se ingreso y se asocio correctamente a la Moto.");
						} 
						else {
							System.out.println("[ERROR] : No se pudo ingresar y/o asociar a la Moto.");
						}
					} 
					catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
		scan_clientes.close();
	}

}
