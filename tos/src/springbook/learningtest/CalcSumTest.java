package springbook.learningtest;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalcSumTest {
	
	private Calculator calculator;
	private String path;
	
	@Before
	public void setUp() {
		calculator = new Calculator();
		path = getClass().getResource("numbers.txt").getPath();
	}
	
	@Test
	public void sumOfNumbers() throws IOException {
		assertThat(calculator.calcSum(path), is(50));
	}
	
	@Test
	public void multiplyOfNumbers() throws IOException {
		assertThat(calculator.calcMultiply(path), is(600));
	}
	
	@Test
	public void concatenateOfNumbers() throws IOException {
		assertThat(calculator.concatenate(path), is("2030"));
	}

}
