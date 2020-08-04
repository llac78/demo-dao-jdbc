package model.dao;

import java.util.List;

import model.entities.Departamento;
import model.entities.Vendedor;

public interface DepartamentoDAO {
	
	void inserir(Departamento departamento);
	void update(Departamento departamento);
	void deletarPorId(Integer id);
	Departamento buscarPorId(Integer id);
	List<Departamento> listar();
 
}
