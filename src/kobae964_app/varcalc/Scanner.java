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
	@Override
	public String toString()
	{
		StringBuilder sb=new StringBuilder("Scanner(pos="+pos+", toks:\n read:\n");
		for(int i=0;i<pos;i++)
		{
			sb.append(token[i]+" ");
		}
		sb.append("\nremaining:\n");
		for(int i=pos;i<token.length;i++)
		{
			sb.append(token[i]+" ");
		}
		return sb.toString();
	}
}
