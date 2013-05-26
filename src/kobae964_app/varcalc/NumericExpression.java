package kobae964_app.varcalc;

import static kobae964_app.varcalc.TokenType.NUMERIC;

public class NumericExpression extends AbstractExpression
{
	Token num;
	public NumericExpression(Token tok)
	{
		if(tok.getType()!=NUMERIC)
		{
			throw new IllegalArgumentException();
		}
		num=tok;
	}
	@Override
	public int getValue()
	{
		return Integer.parseInt(num.toString());
	}
	@Override
	public String toString()
	{
		return num.toString();
	}
}