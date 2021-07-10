package com.company.books.backend.ejemplos.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	
	Calculadora calculadora;
	
	@BeforeAll
	public static void antes() {
		System.out.println("@BeforeAll -> Se ejecuta antes de todo una sola vez");
	}
	
	@AfterAll
	public static void despues() {
		System.out.println("@AfterAll -> Se ejecuta después de todo una sola vez");
	}
	
	@BeforeEach
	public  void antesCada() {
		System.out.println("@BeforeEach ->  Se ejecuta antes de cada test");
		calculadora = new Calculadora();
	}
	
	@AfterEach
	public  void despuesCada() {
		System.out.println("@AfterEach -> Se ejecuta después de cada test");
	}
	
	@Test
	@DisplayName("Descripción de ayuda para saber que hace el método")
	//@Disabled("Esta prueba no se va a ejecutar") //Deshabilita la prueba
	public void calculadoraAssertEqualsTest() {
		System.out.println("Ejecutando método: calculadoraAssertEqualsTest() ");
		
		assertEquals(10, calculadora.sumar(5, 5));
		assertEquals(10, calculadora.restar(15, 5));
		assertEquals(10, calculadora.multiplicar(2, 5));
		assertEquals(10, calculadora.dividir(100, 10));
		
	}
	
	@Test
	public void calculadoraTrueFalse() {
		System.out.println("Ejecutando método: calculadoraTrueFalse() ");
		
		assertTrue(calculadora.sumar(2, 2) == 4);
		assertFalse(calculadora.restar(15, 5) == 8);
	}

}
