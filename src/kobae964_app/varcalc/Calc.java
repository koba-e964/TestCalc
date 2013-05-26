package kobae964_app.varcalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import kobae964_app.varcalc.Parser.Expression;



public final class Calc {

	private static boolean verbose;
	/**
	 * usage:
	 * java kobae964_app.varcalc.Calc [options] files
	 * files:code files to read
	 * options are:
	 * -v: verbose
	 * -h: help(--help)
	 * --:end of options
	 * If no file was specified, this program reads from stdin.
	 * @param args :
	 */
	public static void main(String[] args) {
		verbose = false;
		boolean option=true;
		List<String> argList=Arrays.asList(args);
		int handled=0;
		for(String a:argList)
		{
			if(option)
			{
				if(a.equals("=v"))
				{
					verbose=true;
					continue;
				}
				if(a.equals("--help")||a.equals("-h"))
				{
					usage();
					return;
				}
				if(a.equals("--"))
				{
					option=false;
					continue;
				}
			}
			handled++;
			java.util.Scanner scan;
			try {
				scan = new java.util.Scanner(new File(a));
				process(scan,false);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				continue;
			}
		}
		if(handled==0)
		{
			process(new java.util.Scanner(System.in),true);
		}
	}
	public static void process(java.util.Scanner scan,boolean stdin) {
		while(true)
		{
			if(stdin)
			{
				System.out.print(">");
			}
			if(!scan.hasNextLine())
			{
				break;
			}
			String code=scan.nextLine();
			if(stdin&&(code.startsWith("exit")||code.startsWith("quit")))
			{
				return;
			}
			try
			{
				if(!stdin)
				{
					System.out.println("input:"+code);
				}
				String[] split=Tokenizer.split(code);
				Parser parser=new Parser(new Scanner(split));
				Expression uex=parser.exp();
				if(verbose)
				{
					System.out.println("Result of parsing:");
					System.out.println(uex);
				}
				System.out.println("result===>"+uex.getValue());
			}
			catch(RuntimeException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	public static void usage()
	{
		System.out.println("usage:");
		System.out.println("java "+Calc.class.getName()+" [options] files");
		System.out.println("files:code files to read");
		System.out.println("options are:");
		System.out.println("-v: verbose");
		System.out.println("-h: help(--help)");
		System.out.println("--:end of options");
		System.out.println("If no file was specified, this program reads from stdin.");
	}
}
