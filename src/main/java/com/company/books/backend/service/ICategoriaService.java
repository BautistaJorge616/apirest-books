package com.company.books.backend.service;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.response.CategoriaResponseRest;

public interface ICategoriaService {
	
	public ResponseEntity<CategoriaResponseRest> buscarCategorias();
	
	
	
}
