package kobae964_app.varcalc;

public class Expression extends AbstractExpression
{
	OperatedExpression oex;
	public Expression(OperatedExpression oex)
	{
		this.oex=oex;
	}
	@Override
	public int getValue() {
		return oex.getValue();
	}
	@Override
	public String toString()
	{
		return oex.toString();
	}
}