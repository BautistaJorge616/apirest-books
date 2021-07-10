package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class AssertNotEquals {
	
	@Test
	public void miTest() {
		assertNotEquals(1, 2);
	}
}
