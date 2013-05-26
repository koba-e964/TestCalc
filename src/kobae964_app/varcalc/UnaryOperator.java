package kobae964_app.varcalc;

import java.util.Arrays;
import java.util.List;

public class UnaryOperator
{
	String op;
	final List<String> ops=Arrays.asList("+","-","!","~");
	public UnaryOperator(Token tok)
	{
		String op=tok.getContent();
		if(!ops.contains(op))
		{
			throw new IllegalArgumentException("Illegal unary operator:"+op);
		}
		this.op=op;
	}
	public String getName()
	{
		return op;
	}
	@Override
	public String toString()
	{
		return "operator"+op+"(int)";
	}
}