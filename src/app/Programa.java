package app;

import java.util.Date;

import db.DB;
import model.dao.DAOFactory;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		DB.conectar();
		
		VendedorDAO vendedorDao = DAOFactory.criarVendedorDAO();
		
		
		Departamento dep = new Departamento(1, "Computers");
		
//		Vendedor vend = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.0, dep);
//		vendedorDao.inserir(vend);
		
//		System.out.println(vend);
		
		Vendedor delete = vendedorDao.buscarPorId(9);
		vendedorDao.deletarPorId(delete.getId());
		System.out.println("Deletado");
		
		

	}

}
