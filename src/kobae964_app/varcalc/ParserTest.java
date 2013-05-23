package kobae964_app.varcalc;

import static org.junit.Assert.*;
import kobae964_app.varcalc.Parser.OperatedExpression;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testOpexp() {
		Parser parser=new Parser(new Scanner(
				new String[]{"1","+","2","*","3","%","5"}));
		OperatedExpression result=parser.opexp();
		System.out.println(result);
	}

}
