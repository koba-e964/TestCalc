package kobae964_app.varcalc;

import static org.junit.Assert.*;
import kobae964_app.varcalc.Parser.Expression;
import kobae964_app.varcalc.Parser.OperatedExpression;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testOpexp() {
		Parser parser=new Parser(new Scanner(
				Tokenizer.split("1+2*3+4%5&8")));
		OperatedExpression result=parser.opexp();
		try
		{
			System.out.println(result);
			fail();
		}
		catch(OperatorOrderUndecidableException ex)
		{
			assertEquals("%", ex.op1);
			assertEquals("&", ex.op2);
		}
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
	@Test
	public void testExp1() {
		Parser parser=new Parser(new Scanner(
				Tokenizer.split("21*(15+34)-15%2")));
		Expression result=parser.exp();
		assertEquals(21*(15+34)-15%2,result.getValue());
		System.out.println(result);
	}
	@Test
	public void testExp2() {
		String[] split=Tokenizer.split("21*(-15+34* -34)-15%2");
		for(String s:split){
			System.out.print(s+" ");
		}
		Parser parser=new Parser(new Scanner(split));
		Expression result=parser.exp();
		assertEquals(21*(-15+34* -34)-15%2,result.getValue());
		System.out.println(result);
	}
	
}
