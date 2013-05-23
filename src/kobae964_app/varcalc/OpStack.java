package kobae964_app.varcalc;

import java.util.ArrayList;
import java.util.List;

import kobae964_app.varcalc.Parser.BinaryOperator;
import kobae964_app.varcalc.Parser.UnaryExpression;

class OpStack
{
	private List<UnaryExpression> us;
	private List<BinaryOperator> os;
	OperatorComparator oc;
	OpStack(OperatorComparator oc)
	{
		us=new ArrayList<Parser.UnaryExpression>();
		os=new ArrayList<Parser.BinaryOperator>();
		this.oc=oc;
	}
	void pushUnary(UnaryExpression uex)
	{
		us.add(uex);
	}
	void pushOperator(BinaryOperator op)
	{
		os.add(op);
		while(true)
		{
			if(os.size()<=1)
			{
				break;
			}
			BinaryOperator pre=os.get(os.size()-2);
			if(oc.lt(pre.getName(), op.getName()))
			{
				break;
			}
			else if(oc.gt(pre.getName(), op.getName()))
			{
				reduce_one(1);
			}
			else
			{
				System.err.println(this);
				throw new OperatorOrderUndecidableException(pre, op);
			}
		}
	}
	UnaryExpression reduce()
	{
		while(os.size()>0)
		{
			reduce_one(0);
		}
		//assert us.size()==1
		if(us.size()!=1)throw new AssertionError("|us|!=1");
		return us.get(0);
	}
	/**
	 * unite two UnaryExpressions and one operator and create a new one({@code UnaryExpression}).
	 * @param diff offset of the operator from the last element (0 for the last)
	 */
	void reduce_one(int diff)
	{
		UnaryExpression bigger=new UnaryExpression(os.get(os.size()-1-diff),
				us.get(us.size()-2-diff),us.get(us.size()-1-diff));
		us.set(us.size()-2-diff, bigger);
		us.remove(us.size()-1-diff);
		os.remove(os.size()-1-diff);
	}
	@Override
	public String toString()
	{
		return "(us: "+us+" os: "+os+")";
	}
	
}