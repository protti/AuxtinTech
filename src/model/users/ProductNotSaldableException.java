package model.users;

public class ProductNotSaldableException extends Exception {
	public ProductNotSaldableException(String admin, String product)
	{
		super("Il prodotto " + product + " non è saldabile, contatta il proprietario " + admin);
	}
	
	public ProductNotSaldableException()
	{
		super("L'acquisto non è saldabile, prego controlla l'importo sulla tua carta");
	}
}
