package kobae964_app.varcalc;

import static kobae964_app.varcalc.TokenType.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Parser {
	Scanner scan;
	public Parser(Scanner scan)
	{
		this.scan=scan;
	}
	public static abstract class AbstractExpression
	{
		public abstract int getValue();
	}
	public NumericExpression numeric()
	{
		return new NumericExpression(scan.next());
	}
	public static class NumericExpression extends AbstractExpression
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
	public UnaryOperator unop()
	{
		return new UnaryOperator(scan.next());
	}
	public static class UnaryOperator
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
		default:
			throw new IllegalStateException("unary() couldn't find an unary expression");
		}
		//constructor UnaryExpression(BinaryOperator,UnaryExpression,UnaryExpression) is not dealt with here.
	}
	public static class UnaryExpression extends AbstractExpression
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
			return getValueBiop();
		}
		private int getValueBiop()
		{
			int val1=term1.getValue();
			int val2=term2.getValue();
			//case(bop.getName())
			String name=bop.getName();
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
			return "("+bop.getName()+" "+term1+" "+term2+")";
		}
	}
	public BinaryOperator biop()
	{
		return new BinaryOperator(scan.next());
	}
	public static class BinaryOperator
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
	public static class OperatedExpression extends AbstractExpression
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
	/**
	 * Expression ::= OperatedExpression
	 */
	public Expression exp()
	{
		return new Expression(opexp());
	}
	public static class Expression extends AbstractExpression
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
}
