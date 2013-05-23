package kobae964_app.varcalc;

import java.util.ArrayDeque;
import java.util.Deque;

import kobae964_app.varcalc.Parser.OperatedExpression;
import kobae964_app.varcalc.Parser.UnaryExpression;

public class OpParser {
	public static UnaryExpression parse(OperatedExpression oex)
	{
		OperatedExpression current=oex;
		OperatorComparator oc=new OperatorComparator();
		OpStack stack=new OpStack(oc);
		stack.pushUnary(oex.first);
		while(true)
		{
			if(current.op==null)
			{
				// end of oex. stop.
				break;
			}
			assert current.rest!=null;
			stack.pushUnary(current.rest.first);
			stack.pushOperator(current.op);
			current=current.rest;
		}
		return stack.reduce();
	}
}
