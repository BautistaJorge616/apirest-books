package com.company.books.backend.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.books.backend.model.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	/*
	 * 
	 *   Tinenes que definir el método segun se llamen los campos de la clase
	 */
	
	public Usuario findByNombreUsuario(String nombreUsuario);
	
	//Consultas a clases 
	@Query("select u from Usuario u where u.nombreUsuario=?1")
	public Usuario findByIdNombreUsuarioV2(String usuario);
	
	
}
