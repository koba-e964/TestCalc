package kobae964_app.varcalc;

import kobae964_app.varcalc.Parser.BinaryOperator;

public class OperatorOrderUndecidableException extends RuntimeException
{
	public OperatorOrderUndecidableException(String s)
	{
		super(s);
	}
	public OperatorOrderUndecidableException(BinaryOperator op1,BinaryOperator op2)
	{
		super("undecidable order: "+op1+" <=> "+op2);
	}
}