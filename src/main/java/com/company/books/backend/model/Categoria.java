package com.company.books.backend.model;

import java.io.Serializable;

import javax.persistence.*;

//Clase que va a persistir
@Entity

//Nombre para la tabla que se va a crear
@Table(name="categorias")

public class Categoria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2164553723990982332L;
	
	@Id
	//Generar el id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String descripcion;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

}
