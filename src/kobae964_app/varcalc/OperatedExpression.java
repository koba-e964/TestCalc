package kobae964_app.varcalc;

public class OperatedExpression extends AbstractExpression
{
	UnaryExpression first;
	BinaryOperator op;
	OperatedExpression rest;
	public OperatedExpression(UnaryExpression first,BinaryOperator op,OperatedExpression rest)
	{
		this.first=first;
		this.op=op;
		this.rest=rest;
	}
	public OperatedExpression(UnaryExpression cont)
	{
		this.first=cont;
		op=null;
		rest=null;
	}
	public boolean isTerm()
	{
		return op==null;
	}
	@Override
	public String toString()
	{
		return OpParser.parse(this).toString();
	}
	public int getValue()
	{
		return OpParser.parse(this).getValue();
	}
}