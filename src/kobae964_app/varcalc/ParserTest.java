package kobae964_app.varcalc;

import static org.junit.Assert.*;

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
		Parser parser=new Parser(new Scanner(split));
		Expression result=parser.exp();
		assertEquals(21*(-15+34* -34)-15%2,result.getValue());
		System.out.println(result);
	}
	@Test
	public void testExp3() {
		String[] split=Tokenizer.split("3000000*(13*13-11*11)/(13*13+11*11)");
		Parser parser=new Parser(new Scanner(split));
		Expression result=parser.exp();
		System.out.println(result.getValue());
	}
	@Test
	public void testExp4() {
		String[] code={"val=2","val*23"};
		for(String c:code)
		{
			String[] split=Tokenizer.split(c);
			Parser parser=new Parser(new Scanner(split));
			Expression result=parser.exp();
			System.out.println("code=\""+c+"\"");
			System.out.println(result);
			System.out.println(result.getValue());
			//System.out.println(VariableMap.vars);
		}
	}
	
}
