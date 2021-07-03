package com.company.books.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.response.CategoriaResponseRest;

@Service
public class CategoriaServiceImpl implements ICategoriaService {
	
	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
		log.info("Inicio del método buscarCategorias()");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		try {
			List<Categoria> categorias = (List<Categoria>) categoriaDao.findAll();
			
			response.getCategoriaResponse().setCategorias(categorias);
			response.setMetadate("Respuesta ok", "00", "Respuesta exitosa");
		}
		catch(Exception e) {
			response.setMetadate("Respuesta NO ok", "-1", "Error al consultar categorias");
			log.error("Error al consultar categorias: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id){
		log.info("Se inicio el metodo buscarPorId");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		List<Categoria> list = new ArrayList<>();
		
		try {
			
			Optional<Categoria> categoria = categoriaDao.findById(id);
			
			if(categoria.isPresent()) {
				list.add(categoria.get());
				response.getCategoriaResponse().setCategorias(list);
			}
			else {
				log.error("Categoria no encontrada");
				response.setMetadate("Respuesta no OK", "-1", "Categoria no encontrada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND); 
			}
			
		}
		catch(Exception e){
			log.error("Categoria no encontrada");
			response.setMetadate("Respuesta no OK", "-1", "Categoria no encontrada");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadate("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {
		log.info("Se inicio el metodo crear");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		List<Categoria> list = new ArrayList<>();
		
		try {
			
			Categoria newCategoria = categoriaDao.save(categoria);
			
			if(newCategoria != null) {
				list.add(newCategoria);
				response.getCategoriaResponse().setCategorias(list);
			}else {
				log.error("Error al grabar la categoria");
				response.setMetadate("Respuesta no OK", "-1", "Error al grabar la categoria");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			log.error("Error al grabar la categoria");
			response.setMetadate("Respuesta no OK", "-1", "Error al grabar la categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadate("Respuesta ok", "00", "Categoria guardada exitosamente");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {
		log.info("Iniciando método actualizar");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		List<Categoria> list=new ArrayList<>();
		
		try {
			
			Optional<Categoria> categoriaBuscar = categoriaDao .findById(id);
			
			if(categoriaBuscar.isPresent()) {
				categoriaBuscar.get().setNombre(categoria.getNombre());
				categoriaBuscar.get().setDescripcion(categoria.getDescripcion());
				
				Categoria categoriaActualizar = categoriaDao.save(categoriaBuscar.get());
				
				if(categoriaActualizar != null) {
					response.setMetadate("Respuesta ok", "00", "Categoria actualizada correctamente");
					list.add(categoriaActualizar);
					response.getCategoriaResponse().setCategorias(list);
				}
				else {
					log.error("Error al actualizar");
					response.setMetadate("Respuesta no OK", "-1", "Error al actualizar");
					return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			}else {
				log.error("No existe una categoría con este id");
				response.setMetadate("Respuesta no OK", "-1", "Error al buscar la categoría");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			log.error("Error al actualizar", e.getMessage());
			e.getStackTrace();
			response.setMetadate("Respuesta no OK", "-1", "Error al actualizar");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> eliminar(Long id) {
		
		log.info("Iniciando método eliminar");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		try {
			categoriaDao.deleteById(id);
			response.setMetadate("Respuesta ok", "00", "Categoria eliminada correctamente");
			
		}catch(Exception e) {
			log.error("Error al eliminar", e.getMessage());
			e.getStackTrace();
			response.setMetadate("Respuesta no OK", "-1", "Error al eliminar");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
