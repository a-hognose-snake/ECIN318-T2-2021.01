package logica;

public interface SistemaRueditas {
	
	/**
	 * Registers a client to the system.
	 * @param rut
	 * @param nombre
	 * @param apellido
	 * @param clave
	 * @param cantidadVehiculos
	 * @param saldo
	 * @return a boolean (true if the registration is a success, false if it isn't)
	 */
	public boolean ingresarCliente(String rut, String nombre, String apellido, String clave, int cantidadVehiculos, int saldo);
	
	/**
	 * Registers a car to the system.
	 * @param modelo
	 * @param placa
	 * @param precio
	 * @param year
	 * @param tipoVehiculo
	 * @param rendimiento
	 * @return a boolean (true if the registration is a success, false if it isn't)
	 */
	public boolean ingresarAuto(String modelo, String placa, int precio, int year, String tipoVehiculo, int rendimiento);
	
	/**
	 * Registers a motorcycle to the system.
	 * @param modelo
	 * @param placa
	 * @param precio
	 * @param year
	 * @param tipoVehiculo
	 * @return a boolean (true if the registration is a success, false if it isn't)
	 */
	public boolean ingresarMoto(String modelo, String placa, int precio, int year, String tipoVehiculo);
	
	/**
	 * Connects a clients id to a car or motorcycle and add it to the client list of posessions.
	 * @param rut
	 * @param placa
	 */
	public void asociarPropietarioDeVehiculo(String rut, String placa);
	
	/**
	 * Checks if the rut input belongs to a client in the system.
	 * @param userId
	 * @return a boolean (true if it belongs, false if it doesn't)
	 */
	public boolean validarUsuario(String userId); 
	
	/**
	 * Checks if the input password of the client designated by userId is the same as the one in the system.
	 * @param userId
	 * @param clave
	 * @return a boolean (true if it belongs, false if it doesn't)
	 */
	public boolean validarAcceso(String userId, String clave); 
	
	/**
	 * Obtains all the vehicles that the client owns.
	 * @param rut
	 * @return
	 */
	public String obtenerVehiculosDeCliente(String rut);
	
	/**
	 * Adds money to the clients account.
	 * @param rut
	 * @param dinero
	 * @param clave
	 */
	public void agregarSaldo(String rut, int dinero, String clave);
	
	/**
	 * Allows client to change his/her password.
	 * @param rut
	 * @param nuevaClave
	 */
	public void cambiarClave(String rut, String nuevaClave);
	
	/**
	 * Obtains the prices of the clients's vehicles.
	 * @param rut
	 * @return
	 */
	public String obtenerPrecioDeVehiculosDeCliente(String rut);
	
	/**
	 * Obstains selling price for a specific vehicle.
	 * @param placa
	 * @return
	 */
	public String obtenerPrecioDeVentaDeVehiculo(String placa);
	
	/**
	 * Allows client to sell his/her vehicle.
	 * @param rut
	 * @param placa
	 */
	public void venderVehiculo(String rut, String placa);
	
	/**
	 * Obstains are the vehicles that are on sale by the car dealership.
	 * @return a string with the info.
	 */
	public String obtenerVehiculosDeAutomotora();
	
	/**
	 * Allows client to buy a vehicle.
	 * @param rut
	 * @param placa
	 */
	public void comprarVehiculo(String rut, String placa);
	
	/**
	 * Obtains the prices of all the vehicle a clients owns in USD.
	 * @param rut
	 * @return
	 */
	public String obtenerPrecioUsdDeVehiculosDeCliente(String rut);
	
	/**
	 * Obstains the client with most cars.
	 * @return info about the client with the most cars.
	 */
	public String obtenerPosibleClienteComprador();
	
	/**
	 * Obstains selling and buying price of cars at the dealership.
	 * @return a String
	 */
	public String obtenerPrecioCompraVentaDeVehiculosDeAutomotora();
	
	/**
	 * Obstains the full monetary gain of selling all the cars at the dealership.
	 * @return a string with the information.
	 */
	public String obtenerFuturasGananciasDeAutomotora();
	
	/**
	 * Obtains all the data of the clients in the system.
	 * @return a string with that data.
	 */
	public String obtenerTxtClientes();
	
	/**
	 * Obtains all the data of the vehicles/cars in the system.
	 * @return a string with the data.
	 */
	public String obtenerTxtVehiculos();
	
	

}
