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

import com.company.books.backend.model.Libro;
import com.company.books.backend.model.dao.ILibroDao;
import com.company.books.backend.response.LibroResponseRest;

@Service
public class LibroServiceImpl implements ILibroService{
	
	private static final Logger log = LoggerFactory.getLogger(LibroServiceImpl.class);
	
	@Autowired
	private ILibroDao libroDao;

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<LibroResponseRest> obtenerLibros() {
		log.info("Inicio del método obtenerLibros()");		
		LibroResponseRest response = new LibroResponseRest();
		
		try {
			
			List<Libro> libros = (List<Libro>) libroDao.findAll();
			response.getLibroResponse().setLibros(libros);
			response.setMetadate("Respuesta ok", "00", "Respuesta exitosa");
			
		}catch(Exception e) {
			response.setMetadate("Respuesta NO ok", "-1", "Error al consultar libros");
			log.error("Error al consultar categorias: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<LibroResponseRest> buscarPorId(Long id) {
		
		log.info("Inicio del método buscarPorId()");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();
		
		try {
			Optional<Libro> libro = libroDao.findById(id);
			
			if(libro.isPresent()) {
				list.add(libro.get());
				response.getLibroResponse().setLibros(list);
			}
			else {
				log.error("Libro no encontrado");
				response.setMetadate("Respuesta no OK", "-1", "Libro no encontrado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND); 
			}
					
		}catch(Exception e) {
			log.error("Libro no encontrado");
			response.setMetadate("Respuesta no OK", "-1", "Libro no encontrado");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 	
		}
		response.setMetadate("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> crear(Libro libro) {
		
		log.info("Inicio del método crear()");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();
		
		try {
			
			Libro newLibro = libroDao.save(libro);
			
			if(newLibro != null) {
				list.add(newLibro);
				response.getLibroResponse().setLibros(list);
			}else {
				log.error("Error al guardar el libro");
				response.setMetadate("Respuesta no OK", "-1", "Error al guardar el libro");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST); 
				
			}
			
		}catch(Exception e) {
			log.error("Error al guardar el libro");
			response.setMetadate("Respuesta no OK", "-1", "Error al guardar el libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.setMetadate("Respuesta ok", "00", "Respuesta exitosa");
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> actualizar(Libro libro, Long id) {
		
		log.info("Inicio del método actualizar()");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();
		
		try {
			
			Optional<Libro> libroBuscar = libroDao.findById(id);
			
			if(libroBuscar.isPresent()) {
				libroBuscar.get().setNombre(libro.getNombre());
				libroBuscar.get().setDescripcion(libro.getDescripcion());
				libroBuscar.get().setCategoria(libro.getCategoria());
				
				Libro libroActualizar = libroDao.save(libroBuscar.get());
				
				if(libroActualizar != null) {
					response.setMetadate("Respuesta ok", "00", "Respuesta exitosa");
					list.add(libroActualizar);
					response.getLibroResponse().setLibros(list);
				}else {
					log.error("Error al actualizar el libro");
					response.setMetadate("Respuesta no OK", "-1", "Error al actualizar el libro");
					return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST); 
					
				}
				
			}else {
				log.error("Error! el id no existe");
				response.setMetadate("Respuesta no OK", "-1", "Error al buscar el libro");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND); 
			}
			
		}catch(Exception e) {
			log.error("Error al actualizar " , e.getMessage());
			e.getStackTrace();
			response.setMetadate("Respuesta no OK", "-1", "Error al actualizar el libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> eliminar(Long id) {
		log.info("Inicio de método eliminar");
		LibroResponseRest response = new LibroResponseRest();
		
		try {
			libroDao.deleteById(id);
			response.setMetadate("Respuesta ok", "00", "Libro eliminado correctamente");
		}catch(Exception e) {
			log.error("Error al elimnar " , e.getMessage());
			e.getStackTrace();
			response.setMetadate("Respuesta no OK", "-1", "Error al eliminar el libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

}




















