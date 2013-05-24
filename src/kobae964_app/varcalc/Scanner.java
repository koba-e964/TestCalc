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
	public Token next()throws NoSuchElementException
	{
		if(pos<token.length)
			return token[pos++];
		throw new NoSuchElementException("No tokens remaining");
	}
	public Token peek()throws NoSuchElementException
	{
		if(pos<token.length)
			return token[pos];
		throw new NoSuchElementException("No tokens remaining");
	}
	public boolean hasNext()
	{
		return pos<token.length;
	}
	public static enum TokenType
	{
		NUMERIC,
		OPERATOR,
		LEFT_PAREN,
		RIGHT_PAREN,
	}
}
