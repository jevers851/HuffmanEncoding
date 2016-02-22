import java.io.IOException;
import java.io.OutputStream;


public class BitOutputStream 
{
	private OutputStream output;
	private int 		 thisbyte;
	private int 		 thisnbyte;
	private int 		 otherbyte;
	private int 		 mby;
	public BitOutputStream(OutputStream out)
	{
		output 	  = out;
		thisbyte  = 0;
		thisnbyte = 0;
		
	}
	public void write(int b) throws IOException
	{
			
		thisbyte = thisbyte << 1 | b;
		thisnbyte++;
		
		if (thisnbyte == 8) 
		{
			output.write(thisbyte);
			thisnbyte = 0;
		}
	}
	public void close() throws IOException 
	{
		while(thisnbyte != 0)
			write(0);
		output.close();
		
	}
}
