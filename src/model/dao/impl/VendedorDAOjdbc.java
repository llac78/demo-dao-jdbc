package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DBException;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDAOjdbc implements VendedorDAO {

	private Connection conn;
	
	public VendedorDAOjdbc(Connection conn) {
		this.conn = conn; 
	}
	
	@Override
	public void inserir(Vendedor vendedor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vendedor vendedor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarPorId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor buscarPorId(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Departamento dep = instanciarDepartamento(rs);
				Vendedor vend = instanciarVendedor(rs, dep);
				
				return vend;
			}
			
			return null;
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.fecharStatement(st);
			DB.fecharResultSet(rs);
		}
		
	}

	private Vendedor instanciarVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor vend = new Vendedor();
		vend.setId(rs.getInt("Id"));
		vend.setNome(rs.getString("Name"));
		vend.setEmail(rs.getString("Email"));
		vend.setDataNascimento(rs.getDate("BirthDate"));
		vend.setSalario(rs.getDouble("BaseSalary"));
		vend.setDepartamento(dep);
		
		return vend;
	}

	private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		
		return dep;
	}

	@Override
	public List<Vendedor> listar() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while(rs.next()) {
				Departamento depart = map.get(rs.getInt("DepartmentId"));
				
				if(depart == null) {
					depart = instanciarDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), depart);
				}
				
				Vendedor vend = instanciarVendedor(rs, depart);
				lista.add(vend);
				
				return lista;
			}
			
			return null;
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.fecharStatement(st);
			DB.fecharResultSet(rs);
		}
	}

	@Override
	public List<Vendedor> listarPorDepartamento(Departamento departamento) {

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? ORDER BY Name");
			
			st.setInt(1, departamento.getId());
			rs = st.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while(rs.next()) {
				Departamento depart = map.get(rs.getInt("DepartmentId"));
				
				if(depart == null) {
					depart = instanciarDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), depart);
				}
				
				Vendedor vend = instanciarVendedor(rs, depart);
				lista.add(vend);
				
				return lista;
			}
			
			return null;
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.fecharStatement(st);
			DB.fecharResultSet(rs);
		}
		
	}

}
