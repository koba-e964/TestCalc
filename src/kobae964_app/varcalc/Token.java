package kobae964_app.varcalc;

import static kobae964_app.varcalc.TokenType.*;

import java.util.Arrays;
import java.util.List;


public class Token
{
	TokenType type;
	String str;
	public Token(String str)
	{
		this.str=str;
		if(isNumeric(str))
		{
			type=NUMERIC;
			return;
		}
		if(isOperator(str))
		{
			type=OPERATOR;
			return;
		}
		if(isVariable(str))
		{
			type=VARIABLE;
			return;
		}
		if(str.equals("("))
		{
			type=LEFT_PAREN;
			return;
		}
		if(str.equals(")"))
		{
			type=RIGHT_PAREN;
			return;
		}
		throw new IllegalArgumentException("Illegal Token:"+str);
	}
	public TokenType getType()
	{
		return type;
	}
	public String toString()
	{
		return str;
	}
	public String getContent()
	{
		return str;
	}
	public static boolean isNumeric(String str)
	{
		if(str.length()==0)return false;
		char ch=str.charAt(0);
		return ch>='0' && ch<='9';
	}
	public static boolean isVariable(String str)
	{
		char ch=str.charAt(0);
		if(ch>='a')
			ch-='a'-'A';
		return 'A'<=ch && ch<='Z';
	}
	private static List<String> operators=Arrays.asList(new String[]{"+","-","*","/","%","&","|","^","&&","||","=","==","<",">","<=",">=","!="});
	public static boolean isOperator(String str)
	{
		return operators.contains(str);
	}
}