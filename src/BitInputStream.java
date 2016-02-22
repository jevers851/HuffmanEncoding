import java.io.IOException;
import java.io.InputStream;


public class BitInputStream
{
	private InputStream input;
	private int 		nextBits;
	private int 		bits;
	private boolean 	isEndOfStream;
	
	public BitInputStream(InputStream in)
	{

		input 		  = in;
		bits 		  = 0;
		isEndOfStream = false;	
	}
	
	public int read() throws IOException
	{
		
		//TODO: NEED ERR CHK;
		//Can be broken by larger than 8-bit symbols
		if (isEndOfStream)
			return -1;
		if(bits == 0)
		{
			nextBits = input.read();
			if(nextBits == -1)
			{
				isEndOfStream = true;
				return -1;
			}
			bits = 8;
		}
		bits--;
		return (nextBits >>> bits) & 1;
	}
	public void close() throws IOException 
	{
		
		input.close();
		
	}
}
