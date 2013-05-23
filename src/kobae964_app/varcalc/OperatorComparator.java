package kobae964_app.varcalc;

import java.util.HashSet;
import java.util.Set;



public class OperatorComparator
{
	private static class Pair
	{
		String fst,snd;
		Pair(String fst,String snd)
		{
			this.fst=fst;
			this.snd=snd;
		}
		boolean equals(Pair p)
		{
			return this==p||(this.fst.equals(p.fst)&&this.snd.equals(p.snd));
		}
		@Override
		public boolean equals(Object o)
		{
			return o instanceof Pair&&equals((Pair)o);
		}
		@Override
		public
		int hashCode()
		{
			return fst.hashCode()^snd.hashCode();
		}
	}
	Set<Pair> lt=new HashSet<Pair>();
	Set<Pair> gt=new HashSet<Pair>();
	public void setLt(String op1,String op2)
	{
		lt.add(new Pair(op1,op2));
	}
	public void setGt(String op1,String op2)
	{
		gt.add(new Pair(op1,op2));
	}
	public boolean lt(String op1,String op2)
	{
		return lt.contains(new Pair(op1,op2));
	}
	public boolean gt(String op1,String op2)
	{
		return gt.contains(new Pair(op1,op2));
	}
}