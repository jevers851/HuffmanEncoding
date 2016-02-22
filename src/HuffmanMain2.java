//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.Arrays;

//DECOMPRESS
//ARGS 0 INPUT
//ARGS 1 OUTPUT
//
//public class HuffmanMain2 
//{
//	private static void main(String[] args) throws IOException
//	{
//		File 			inputFile =  new File(args[0]);
//		File 			outputFile = new File(args[1]);
//		BitInputStream  in = 		 new BitInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
//		OutputStream 	out = 		 new BufferedOutputStream(new FileOutputStream(outputFile));
//		
//		try //try for IO
//		{
//			Tree tree = readCode(in);
//			decompress(in, out, tree);
//			
//		}
//		finally
//		{
//			out.close();
//			in.close();
//		}
//		
//	}

//	private static void decompress(BitInputStream in, OutputStream out, Tree tree) throws IOException
//	{
//		iNode thisnode = (iNode)tree.root;
//		while(true)
//		{
//			int temp = in.read();
//			
//			
//			Node nNode;
//			

//					out.write(sym);
//					thisnode = (iNode)tree.root;
//				}
//				else
//				{
//					thisnode = (iNode)nNode;
//				}
//		}
//	}
//}
