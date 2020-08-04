package app;

import db.DB;
import model.dao.DAOFactory;
import model.dao.VendedorDAO;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		DB.conectar();
		
		VendedorDAO vendedorDao = DAOFactory.criarVendedorDAO();
		
		Vendedor vend = vendedorDao.buscarPorId(3);
		
		System.out.println(vend);

	}

}
