package kobae964_app.varcalc;

import static kobae964_app.varcalc.TokenType.VARIABLE;

public class VariableExpression extends AbstractExpression
{
	String name;
	public VariableExpression(Token tok)
	{
		if(tok.getType()!=VARIABLE)
		{
			throw new IllegalArgumentException("Invalid variable:"+tok.getContent());
		}
		name=tok.getContent();
	}
	@Override
	public int getValue()
	{
		return VariableMap.get(name);
	}
	public String getName()
	{
		return name;
	}
	public void assign(int value)
	{
		VariableMap.put(name, value);
	}
	@Override
	public String toString()
	{
		return "var:"+name;
	}
}