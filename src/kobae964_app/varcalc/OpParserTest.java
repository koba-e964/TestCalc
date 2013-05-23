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

}
