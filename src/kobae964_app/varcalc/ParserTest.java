package kobae964_app.varcalc;

import static org.junit.Assert.*;
import kobae964_app.varcalc.Parser.OperatedExpression;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testOpexp() {
		Parser parser=new Parser(new Scanner(
				Tokenizer.split("1+2*3+4%5&8")));
		OperatedExpression result=parser.opexp();
		System.out.println(result);
	}
	@Test
	public void testOpexp2() {
		Parser parser=new Parser(new Scanner(
				Tokenizer.split("1+2*3+4%5&8")));
		OperatedExpression result=parser.opexp();
		try
		{
			System.out.println(result.getValue());
			fail();
		}
		catch(OperatorOrderUndecidableException ex)
		{
			assertEquals("%",ex.op1);
			assertEquals("&", ex.op2);
		}
	}
	@Test
	public void testOpexp3() {
		Parser parser=new Parser(new Scanner(
				Tokenizer.split("21*15+34-15%2")));
		OperatedExpression result=parser.opexp();
		assertEquals(21*15+34-15%2,result.getValue());
		System.out.println(OpParser.parse(result));
		
	}
	
}
