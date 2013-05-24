package kobae964_app.varcalc;

import java.util.NoSuchElementException;

public class Scanner {
	public Scanner(String[] split)
	{
		token=new Token[split.length];
		for(int i=0;i<split.length;i++)
		{
			token[i]=new Token(split[i]);
		}
	}
	Token[] token;
	int pos=0;
	public Token next()
	{
		if(pos<token.length)
			return token[pos++];
		throw new NoSuchElementException("No tokens remaining");
	}
	public Token peek()
	{
		if(pos<token.length)
			return token[pos];
		return null;
	}
	public static enum TokenType
	{
		NUMERIC,
		OPERATOR,
		LEFT_PAREN,
		RIGHT_PAREN,
	}
}
