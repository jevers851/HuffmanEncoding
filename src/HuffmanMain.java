import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//COMPRESSSION
//ARGS0 IS INPUT
//ARGS1 IS OUTPUT


//MAIN TODO: MAKE COMMAND MENU

//NEED ERROR CHK;
//CANONICAL CODE IMP

public class HuffmanMain
{
	public static int _menu = 0;

	public static void main(String[] args) throws IOException
	{
		System.out.println("*************	HUFFMAN ENCODING PROGRAM	***********");
		System.out.println("***********************************************************");
		
		System.out.println("1. Compression");
		System.out.println("1. Decompression");
		System.out.println("3. Help");
		System.out.println("4. Exit");
		
		Scanner uInput = new Scanner(System.in);
		
		_menu = uInput.nextInt();
		
		
		
		if (_menu == 1)
		{
			
		
			File 			inputFile 	= new File(args[0]);
			FreqTable 		freqs 		= buildFrequencyTable(inputFile);
			Tree 			code 		= freqs.buildTree();
			huffCanCode 	canCode 	= new huffCanCode(code, 257);
			code 						= canCode.toTree();
			File 			outputFile 	= new File(args[1]);
			InputStream		in 			= new BufferedInputStream(new FileInputStream(inputFile));
			BitOutputStream out 		= new BitOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
			writeCode(out,canCode);
			compress(in, out, code);
			
			out.close();
			in.close();
		}
		else if(_menu == 2)
		{
			File 			inputFile  = new File(args[0]);
			File 			outputFile = new File(args[1]);
			BitInputStream  in 	       = new BitInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
			OutputStream 	out 	   = new BufferedOutputStream(new FileOutputStream(outputFile));
			
			try //try for IO
			{
				Tree tree = readCode(in);
				decompress(in, out, tree);
				
			}
			finally
			{
				out.close();
				in.close();
			}
		}
		else if (_menu == 3)
		{
			System.out.println("This program requires you "
					+ "to: Start the application while inserting "
					+ "your arguements for file names in format"
					+ " <input> <output> ");
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}  
			
		}
	}
	
	public static FreqTable buildFrequencyTable(File file) throws IOException
	{
			InputStream input = new BufferedInputStream(new FileInputStream(file));
			int[] freqs = new int[257];
			freqs[256]++;
			try //TRY FOR IO
			{
		while(true)
			{
				int temp = input.read();
				if (temp == -1)
					break;
				freqs[temp]++;
			}
			} 
		finally
			{
				input.close();
			}
		return new FreqTable(freqs);
	}
	public static void writeCode(BitOutputStream out, huffCanCode canCode) throws IOException
	{
		for (int i = 0; i < canCode.getsymLim(); i++)
		{
			int val = canCode.getlengths(i);
			if(val == -1)
				val = 0;
			for(int k = 7; k >= 0; k--)
				out.write((val >>> k) & 1);
		}
	}

	private static void writeSym(int sym, BitOutputStream out, Tree tree) throws IOException
		{
			List<Integer> bits = tree.getCode(sym);
			for (int b: bits)
				out.write(b);
		}
	private static Tree readCode(BitInputStream in) throws IOException
		{
		
		int[] lengths = new int[257];
		Arrays.fill(lengths, -1);
		
		for (int i = 0; i < lengths.length; i++)
		{
			int val = 0;
			for (int k = 0; k < 8; k++)
			{
				int temp = in.read();
				val = val << 1 | temp;
			}
			lengths[i] = val;
		}
		//huffCanCode code = new huffCanCode(lengths);
		//
		return new huffCanCode(lengths).toTree();
		
		}
	//************************COMPRESSION*********************************************************
	//************************COMPRESSION*********************************************************
	//************************COMPRESSION*********************************************************
	
	private static void compress(InputStream in, BitOutputStream out, Tree tree) throws IOException
		{
			while(true)
			{
				int temp = in.read();
				if (temp == -1)
					break;
				writeSym(temp, out, tree);
			}
			writeSym(256, out, tree);
		}
	//************************DECOMPRESSION*********************************************************
	//************************DECOMPRESSION*********************************************************
	//************************DECOMPRESSION*********************************************************
	
	private static void decompress(BitInputStream in, OutputStream out, Tree tree) throws IOException
		{
			iNode thisnode = (iNode)tree.root;
			while(true)
			{
				int temp = in.read();
				
				
				Node nNode;
				
				if (temp == 0)
					nNode = thisnode.leftchild;
				else
					nNode = thisnode.rightchild;
				if (nNode instanceof Leaf)
					{
						int sym = ((Leaf)nNode).sym;
						if(sym == 256)
							break;
						
						out.write(sym);
						thisnode = (iNode)tree.root;
					}
					else
					{
						thisnode = (iNode)nNode;
					}
			}
		}
}
