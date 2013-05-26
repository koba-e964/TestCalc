package kobae964_app.varcalc;


public class OpParser {
	public static UnaryExpression parse(OperatedExpression oex)
	{
		OperatedExpression current=oex;
		OperatorComparator oc=new OperatorComparator();
		for(String op1:new String[]{"*","/","%"})
		{
			for(String op2:new String[]{"+","-"})
			{
				oc.setGt(op1, op2);
				oc.setLt(op2, op1);
			}
		}
		final String[]  additive={"+","-"};
		for(String op1:additive)
		{
			for(String op2:additive)
			{
				oc.setGt(op1, op2);
			}
		}
		final String[] multi={"*","/","%"};
		for(String op1:multi)
		{
			for(String op2:multi)
			{
				oc.setGt(op1, op2);
			}
		}
		for(String op1:new String[]{"+","-","*","/","%"})
		{
			for(String op2:new String[]{"==","!=","<=",">="})
			{
				oc.setGt(op1, op2);
				oc.setLt(op2,op1);
			}
		}
		for(String op:new String[]{"&","|","^"})
		{
			oc.setGt(op, op);
		}
		for(String op:new String[]{"+","-","*","/","%","==","!=","<=",">=","&","|","^"})
		{
			oc.setGt(op, "=");
			oc.setLt("=", op);
		}
		oc.setLt("=", "=");
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
