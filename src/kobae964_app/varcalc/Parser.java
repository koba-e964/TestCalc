package kobae964_app.varcalc;

import static kobae964_app.varcalc.Scanner.TokenType.*;

import java.util.HashSet;
import java.util.Set;


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
	public static class NumericExpression
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
	/**
	 * UnaryExpression = + UnaryExpression | - UnaryExpression | (Expression) | NumericExpression
	 *
	 */
	public UnaryExpression unary()
	{
		Token next=scan.peek();
		if(next.type==OPERATOR)
		{
			next=scan.next();
			return new UnaryExpression(next,unary());
		}
		if(next.getType()==LEFT_PAREN)
		{
			next=scan.next();
			UnaryExpression una=new UnaryExpression(exp());
			if(scan.next().getType()!=RIGHT_PAREN)
				throw new IllegalArgumentException();
			return una;
		}
		return new UnaryExpression(numeric());
		//constructor UnaryExpression(BinaryOperator,UnaryExpression,UnaryExpression) is not dealt with here.
	}
	public static class UnaryExpression
	{
		NumericExpression num;
		Token op=null;
		UnaryExpression uex=null;
		Expression ex=null;
		/*
		 * For OpParser's use only
		 */
		BinaryOperator bop=null;
		UnaryExpression term1=null,term2=null;
		public UnaryExpression(Token op,UnaryExpression uex)
		{
			if(op.getType()!=OPERATOR)
			{
				throw new IllegalArgumentException();
			}
			this.op=op;
			this.uex=uex;
		}
		public UnaryExpression(NumericExpression nex)
		{
			num=nex;
		}
		public UnaryExpression(Expression ex)
		{
			this.ex=ex;
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
			if(num==null)
			{
				if(ex!=null)
					return ex.getValue();
				return uex.getValue()* (op.toString().equals("-")?-1:1);
			}
			return num.getValue();
		}
		@Override
		public String toString()
		{
			if(num==null)
			{
				if(ex!=null)return ex.toString();
				if(uex!=null)
					return (op.toString().equals("-")?"-":"")+uex.toString();
				return "("+bop+" "+term1+" "+term2+")";
			}
			return num.toString();
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
				ops.add(new String(new char[]{ch}));
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
	public OperatedExpression opexp()
	{
		UnaryExpression first=unary();
		Token peeked=scan.peek();
		if(peeked!=null && peeked.getType()==OPERATOR)
		{
			BinaryOperator op=biop();
			OperatedExpression rest=opexp();
			return new OperatedExpression(first, op, rest);
		}
		return new OperatedExpression(first);
	}
	/**
	 * OperatedExpression ::= UnaryExpression (BinaryOperator UnaryExpression)*
	 *
	 */
	public static class OperatedExpression
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
			return "("+first.toString()+" "+op+" "+rest+")";
		}
	}
	public Expression exp()
	{
		throw new UnsupportedOperationException("exp() Not yet implemented");
	}
	public static class Expression
	{

		public int getValue() {
			// TODO Auto-generated method stub
			return 0;
		}
	}
}
