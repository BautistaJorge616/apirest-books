package com.company.books.backend.ejemplos.junit;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AssertArrayEquals {
	
	@Test
	public void pruebaArregloIguales() {
		String [] arreglo1 = {"a","b"};
		String [] arreglo2 = {"a","b"};
		String [] arreglo3 = {"a","b","c"};
		
		assertArrayEquals(arreglo1, arreglo2);
	}
	
}
