package kobae964_app.varcalc;


public class OperatorOrderUndecidableException extends RuntimeException
{
	private static final long serialVersionUID = 8738643976975207212L;
	String op1,op2;
	public OperatorOrderUndecidableException(BinaryOperator op1,BinaryOperator op2)
	{
		super("undecidable order: "+op1+" <=> "+op2);
		this.op1=op1.getName();
		this.op2=op2.getName();
	}
}