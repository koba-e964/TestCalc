package kobae964_app.varcalc;

import static org.junit.Assert.*;


import kobae964_app.varcalc.Parser.BinaryOperator;
import kobae964_app.varcalc.Parser.NumericExpression;
import kobae964_app.varcalc.Parser.OperatedExpression;
import kobae964_app.varcalc.Parser.UnaryExpression;

import org.junit.Test;

public class OpParserTest {

	@Test
	public void testParse() {
		OperatedExpression oex=new OperatedExpression(
				new UnaryExpression(new NumericExpression(new Token("12"))),
				new BinaryOperator(new Token("+")),
				new OperatedExpression(new UnaryExpression(new NumericExpression(new Token("34"))))
				);
		UnaryExpression result=OpParser.parse(oex);
		System.out.println(result);
	}
	@Test
	public void testParse2() {
		int val=2;
		Scanner scanner=new Scanner(Tokenizer.split("1+4/3*"+val));
		OperatedExpression oex=new Parser(scanner).opexp();
		UnaryExpression result=OpParser.parse(oex);
		System.out.println(result);
	}
	@Test
	public void testParse3() {
		Scanner scanner=new Scanner(Tokenizer.split("3&32768==0"));
		OperatedExpression oex=new Parser(scanner).opexp();
		try
		{
			UnaryExpression result=OpParser.parse(oex);
			fail(result.toString());
		}catch(OperatorOrderUndecidableException ex)
		{
			assertEquals("&", ex.op1);
			assertEquals("==", ex.op2);
			return;
		}
	}
	@Test
	public void testParse4() {
		Scanner scanner=new Scanner(Tokenizer.split("2<3<4"));
		OperatedExpression oex=new Parser(scanner).opexp();
		System.out.println(oex);
		try
		{
			UnaryExpression result=OpParser.parse(oex);
			fail(result.toString());
		}catch(OperatorOrderUndecidableException ex)
		{
			assertEquals("<", ex.op1);
			assertEquals("<", ex.op2);
			return;
		}
	}

}
