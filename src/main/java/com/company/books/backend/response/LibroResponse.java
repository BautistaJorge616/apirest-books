package com.company.books.backend.response;

import java.util.List;
import com.company.books.backend.model.Libro;

public class LibroResponse {
	
	private List<Libro> libros;

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	
	
}
