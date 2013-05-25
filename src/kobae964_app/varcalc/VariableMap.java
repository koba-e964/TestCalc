package kobae964_app.varcalc;

import java.util.HashMap;

public class VariableMap {
	static HashMap<String, Integer> vars=new HashMap<String, Integer>();
	private VariableMap(){}
	public static void put(String v,int i)
	{
		vars.put(v,i);
	}
	public static int remove(String name)
	{
		if(vars.containsKey(name))
			return vars.remove(name);
		throw new IllegalArgumentException("variable not found:"+name);
	}
	public static int get(String name)
	{
		if(vars.containsKey(name))
			return vars.get(name);
		throw new IllegalArgumentException("variable not found:"+name);
	}
	public static boolean contains(String name)
	{
		return vars.containsKey(name);
	}
}
