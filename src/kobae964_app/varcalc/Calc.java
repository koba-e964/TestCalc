package kobae964_app.varcalc;

import kobae964_app.varcalc.Parser.Expression;



public final class Calc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String exp="2*(3+4)*1-4";
		String[] split=Tokenizer.split(exp);
		Parser parser=new Parser(new Scanner(split));
		Expression uex=parser.exp();
		System.out.println(uex.getValue());
	}

}
