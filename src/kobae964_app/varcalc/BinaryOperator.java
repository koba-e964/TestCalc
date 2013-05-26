package kobae964_app.varcalc;

import java.util.HashSet;
import java.util.Set;

public class BinaryOperator
{
	static Set<String> ops;
	private String op;
	static
	{
		ops=new HashSet<String>();
		for(char ch:"+-*/%&|^=<>".toCharArray())
		{
			ops.add(new String(new char[]{ch}).intern());
		}
		ops.add("&&");
		ops.add("||");
		ops.add("==");
		ops.add("!=");
		ops.add("<=");
		ops.add(">=");
	}
	public BinaryOperator(Token op)
	{
		this.op=op.getContent();
		if(!ops.contains(op.getContent()))
		{
			throw new IllegalArgumentException("Illegal operator:"+op);
		}
	}
	public String getName()
	{
		return op;
	}
	@Override
	public String toString()
	{
		return "operator"+op;
	}
}