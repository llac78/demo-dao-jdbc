package model.dao;

import model.dao.impl.VendedorDAOjdbc;

public class DAOFactory {

	public static VendedorDAO criarVendedorDAO() {
		return new VendedorDAOjdbc();
	}
}
