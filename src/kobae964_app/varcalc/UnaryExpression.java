package kobae964_app.varcalc;


public class UnaryExpression extends AbstractExpression
{
	/*
	 * (+|-)UnaryExpression
	 */
	UnaryOperator op=null;
	UnaryExpression uex=null;
	/*
	 * (Expression)
	 */
	Expression ex=null;
	/*
	 * NumericExpression
	 */
	NumericExpression num;
	/*
	 * VariableExpression
	 */
	VariableExpression vex;
	/*
	 * For OpParser's use only
	 * (biop)
	 */
	BinaryOperator bop=null;
	UnaryExpression term1=null,term2=null;
	public UnaryExpression(UnaryOperator op,UnaryExpression uex)
	{
		this.op=op;
		this.uex=uex;
	}
	public UnaryExpression(Expression ex)
	{
		this.ex=ex;
	}
	public UnaryExpression(NumericExpression nex)
	{
		num=nex;
	}
	public UnaryExpression(VariableExpression vex)
	{
		this.vex=vex;
	}
	/**
	 * This constructor should be called only in OpParser.
	 * example: (+ 2 3)
	 * (* 3 (+ 2 6))
	 * 
	 */
	public UnaryExpression(BinaryOperator op2,
			UnaryExpression unaryExpression,
			UnaryExpression unaryExpression2) {
		bop=op2;
		term1=unaryExpression;
		term2=unaryExpression2;
	}
	public int getValue()
	{
		if(uex!=null)
		{
			if(op.getName().equals("+"))return +uex.getValue();
			if(op.getName().equals("-"))return -uex.getValue();
			if(op.getName().equals("!"))return uex.getValue()==0?1:0;
			if(op.getName().equals("~"))return ~uex.getValue();
			throw new IllegalStateException("Invalid Unary Operator:"+op);
		}
		if(ex!=null)
		{
			return ex.getValue();
		}
		if(num!=null)
		{
			return num.getValue();
		}
		if(vex!=null)
		{
			return vex.getValue();
		}
		return getValueBiop();
	}
	private int getValueBiop()
	{
		String name=bop.getName();
		/*
		 * assignment
		 */
		if(name.equals("="))
		{
			if(term1.vex!=null)
			{
				int val=term2.getValue();
				term1.vex.assign(val);
				return val;
			}
			else
			{
				throw new IllegalStateException("invalid assignment:"+term1+" <== "+term2);
			}
		}
		int val1=term1.getValue();
		int val2=term2.getValue();
		//case(bop.getName())
		if(name.equals("+"))
		{
			return val1+val2;
		}
		if(name.equals("-"))
		{
			return val1-val2;
		}
		if(name.equals("*"))
		{
			return val1*val2;
		}
		if(name.equals("/"))
		{
			return val1/val2;
		}
		if(name.equals("%"))
		{
			return val1%val2;
		}
		if(name.equals("&"))
		{
			return val1&val2;
		}
		if(name.equals("|"))
		{
			return val1|val2;
		}
		if(name.equals("^"))
		{
			return val1^val2;
		}
		/*
		 * Comparison
		 */
		if(name.equals("=="))
		{
			return val1==val2?1:0;
		}
		if(name.equals("!="))
		{
			return val1!=val2?1:0;
		}
		if(name.equals("<"))
		{
			return val1<val2?1:0;
		}
		if(name.equals(">"))
		{
			return val1>val2?1:0;
		}
		if(name.equals("<="))
		{
			return val1<=val2?1:0;
		}
		if(name.equals(">="))
		{
			return val1>=val2?1:0;
		}
		/*
		 * Shortcut Operators 
		 */
		if(name.equals("&&"))
		{
			return val1==0?0:val2;
		}
		if(name.equals("||"))
		{
			return val1!=0?val1:val2;
		}
		throw new IllegalStateException("Illegal Operator: "+bop);
	}
	@Override
	public String toString()
	{
		if(uex!=null)
			return op.getName()+uex.toString();
		if(ex!=null)return ex.toString();
		if(num!=null)
			return num.toString();
		if(vex!=null)
			return vex.toString();
		return "("+bop.getName()+" "+term1+" "+term2+")";
	}
}