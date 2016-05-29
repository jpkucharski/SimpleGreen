import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import ui.View1;

public class Test1 extends View1{
	
		private int x,y;
		View1 widok;
		
		@Before
		public void setUp() {
			x=2;
			y=4;
			widok =new View1();	
			widok.setX(x);
			widok.setY(y);
		}
		
		@Test
		public void test1() {
			
			int result1 =6;
			widok.metoda1();
			System.out.println("parametry z klasyy test: "+x+" "+y+" "+widok.metoda1());
			assertEquals(result1,widok.metoda1() );
		}
		
		
		
		
}