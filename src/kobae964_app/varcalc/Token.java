package kobae964_app.varcalc;

import static kobae964_app.calc.Scanner.TokenType.*;

import java.util.Arrays;
import java.util.List;

import kobae964_app.calc.Scanner.TokenType;

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
		}
		if(isOperator(str))
		{
			type=OPERATOR;
		}
		if(isMOperator(str))
		{
			type=OPERATOR_M;
		}
		if(str.equals("("))
		{
			type=LEFT_PAREN;
		}
		if(str.equals(")"))
		{
			type=RIGHT_PAREN;
		}
		
	}
	public TokenType getType()
	{
		return type;
	}
	public String toString()
	{
		return str;
	}
	public static boolean isNumeric(String str)
	{
		if(str.length()==0)return false;
		char ch=str.charAt(0);
		return ch>='0' && ch<='9';
	}
	private static List<String> operators=Arrays.asList(new String[]{"+","-"});
	private static List<String> operators_m=Arrays.asList(new String[]{"*","/"});
	public static boolean isOperator(String str)
	{
		return operators.contains(str);
	}
	public static boolean isMOperator(String str)
	{
		return operators_m.contains(str);
	}
}