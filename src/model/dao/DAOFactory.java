package model.dao;

import db.DB;
import model.dao.impl.VendedorDAOjdbc;

public class DAOFactory {

	public static VendedorDAO criarVendedorDAO() {
		return new VendedorDAOjdbc(DB.conectar());
	}
}
