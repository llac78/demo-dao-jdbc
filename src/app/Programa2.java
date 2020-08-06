package app;

import java.util.List;

import db.DB;
import model.dao.DAOFactory;
import model.dao.DepartamentoDAO;
import model.entities.Departamento;

public class Programa2 {

	public static void main(String[] args) {

		DB.conectar();
		
		DepartamentoDAO departamentoDAO = DAOFactory.criarDepartamentoDAO();
		
		Departamento departamento = new Departamento(null, "Livros");
		
		System.out.println("=== TESTE INSERIR ===");
		departamentoDAO.inserir(departamento);
		System.out.println("Inserido com sucesso!");
		
		System.out.println("=== TESTE Buscar por ID ===");
		departamento = departamentoDAO.buscarPorId(8);
		System.out.println(departamento);
		
		System.out.println("=== TESTE UPDATE ===");
		departamento.setNome("Ferramentas");
		departamentoDAO.update(departamento);
		System.out.println("Atualizado com sucesso!");
		
		System.out.println("=== TESTE Deletar por ID ===");
		departamentoDAO.deletarPorId(7);
		System.out.println("Deletado com sucesso!");
		
		System.out.println("=== TESTE Listar ===");
		List<Departamento> lista = departamentoDAO.listar();
		for (Departamento dep : lista) {
			System.out.println(dep);
		}
	}

}
