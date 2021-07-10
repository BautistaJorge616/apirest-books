package com.company.books.backend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.service.ICategoriaService;

public class CategoriaRestControllerTest {
	
	@InjectMocks
	CategoriaRestController categoriaRestControllerTest;
	
	@Mock
	private ICategoriaService service;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	
	}
	
	@Test
	public void crearTest() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Categoria categoria = new Categoria(Long.valueOf(1), "Nombre Cat", "Desc Cat");
		
		when(service.crear(any(Categoria.class))).thenReturn(new ResponseEntity<CategoriaResponseRest>(HttpStatus.OK));
		
		ResponseEntity<CategoriaResponseRest> respuesta = categoriaRestControllerTest.crear(categoria);
		
		assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);
		
	}
	
	
}





















