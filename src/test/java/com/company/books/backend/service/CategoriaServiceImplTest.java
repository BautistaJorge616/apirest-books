package com.company.books.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.response.CategoriaResponseRest;

public class CategoriaServiceImplTest {
	
	//Declaramos una variable de la clase a probar
	//Se inyectan todos los mock a la clase que se va a probar
	@InjectMocks
	CategoriaServiceImpl service;
	
	@Mock
	ICategoriaDao categoriaDao;
	
	List<Categoria> lista = new ArrayList<>();
	

	
	//Cuando se prueba un método se usa el mismo nombre del método a probar mas el sufijo test
	
	@Test
	public void buscarCategoriasTest() {
		
		when(categoriaDao.findAll()).thenReturn(lista);
		
		ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();
		
		assertEquals(4, response.getBody().getCategoriaResponse().getCategorias().size());
		
		//Que se llame una sola vez
		verify(categoriaDao, times(1)).findAll();
		
	}
	
	public void cargarCategorias() {
		
		Categoria categoria1 = new Categoria(Long.valueOf(1), "Acción","Todo loco");
		Categoria categoria2 = new Categoria(Long.valueOf(2), "Deportes","El bichon");
		Categoria categoria3 = new Categoria(Long.valueOf(3), "Salud","Doctora Juguetes");
		Categoria categoria4 = new Categoria(Long.valueOf(4), "Computación","Unos y Ceros");
		
		lista.add(categoria1);
		lista.add(categoria2);
		lista.add(categoria3);
		lista.add(categoria4);
	}
}

















