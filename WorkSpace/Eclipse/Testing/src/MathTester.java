import static org.junit.Assert.*;
import org.junit.Test;

public class MathTester {

	@Test //Tells the compilers that this method is meant to test something
    public void addTest() {
		assertEquals(4 + 3, 7); //Is 4+3 equal to 7?
	}
	
	@Test
    public void absTest1() {
		assertEquals(Math.abs(7), 7);
	}
	
	@Test
    public void absTest2() {
		assertEquals(Math.abs(-7), 7);
	}
	
	@Test
    public void divideTest1() {
		int x = 10 / 5;
		assertEquals(x, 2);
	}
	
	@Test(expected = ArithmeticException.class) //This method expects to throw an Arithmetic Exception 
    public void divideTest2() {
		int x = 10 / 0; //This should throw an error
	}
	
	@Test
	public void compareTest1() {
		assertTrue(4 > 1);
	}
	
	@Test
	public void compareTest2() {
		assertFalse(4 < 1);
	}
}