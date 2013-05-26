package kobae964_app.varcalc;

import static kobae964_app.varcalc.TokenType.*;



public class Parser {
	Scanner scan;
	public Parser(Scanner scan)
	{
		this.scan=scan;
	}
	public NumericExpression numeric()
	{
		return new NumericExpression(scan.next());
	}
	public VariableExpression variable()
	{
		return new VariableExpression(scan.next());
	}
	public UnaryOperator unop()
	{
		return new UnaryOperator(scan.next());
	}
	/**
	 * UnaryExpression = ("+"|"-") UnaryExpression | "(" Expression ")" | NumericExpression
	 *
	 */
	public UnaryExpression unary()
	{
		Token next=scan.peek();
		switch(next.getType())
		{
		case OPERATOR:
		{
			UnaryOperator unop=unop();
			return new UnaryExpression(unop,unary());
		}
		case LEFT_PAREN:
		{
			next=scan.next();
			UnaryExpression una=new UnaryExpression(exp());
			if(scan.next().getType()!=RIGHT_PAREN)
				throw new IllegalArgumentException();
			return una;
		}
		case NUMERIC:
			return new UnaryExpression(numeric());
		case VARIABLE:
			return new UnaryExpression(variable());
		default:
			throw new IllegalStateException("unary() couldn't find an unary expression"+scan);
		}
		//constructor UnaryExpression(BinaryOperator,UnaryExpression,UnaryExpression) is not dealt with here.
	}
	public BinaryOperator biop()
	{
		return new BinaryOperator(scan.next());
	}
	/**
	 * OperatedExpression ::= UnaryExpression (BinaryOperator UnaryExpression)*
	 *
	 */
	public OperatedExpression opexp()
	{
		UnaryExpression first=unary();
		if(scan.hasNext()&& scan.peek().getType()==OPERATOR)
		{
			BinaryOperator op=biop();
			OperatedExpression rest=opexp();
			return new OperatedExpression(first, op, rest);
		}
		return new OperatedExpression(first);
	}
	/**
	 * Expression ::= OperatedExpression
	 */
	public Expression exp()
	{
		return new Expression(opexp());
	}
}
