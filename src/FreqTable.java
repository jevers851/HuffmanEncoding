import java.util.PriorityQueue;
import java.util.Queue;


public class FreqTable 
{
	public int[] freq;
	
	public FreqTable(int[] freqs)
	{
		freq = freqs.clone();
	}
	
	//TODO: ERR CHK
	public Tree buildTree()
	{
		Queue<NodeWithFrequency> pq = new PriorityQueue<NodeWithFrequency>();
		
		for( int i = 0; i < freq.length; i++)
		{
			if(freq[i] > 0)
				pq.add(new NodeWithFrequency(new Leaf(i), i, freq[i]));

		}
		
		for (int i = 0; pq.size() < 2 && i < Math.max(freq.length, 2); i++)
		{
			if (i >= freq.length || freq[i] == 0)
				pq.add(new NodeWithFrequency(new Leaf(i), i, freq[i]));
		}
		while (pq.size() > 1) 
		{
			NodeWithFrequency one = pq.remove();
			NodeWithFrequency two = pq.remove();
			pq.add( new NodeWithFrequency( new iNode(one.node, two.node), Math.min(one.lowestSymbol,  two.lowestSymbol), one.frequency + two.frequency));
			
		}
		return new Tree(pq.remove().node, freq.length);
		
	}
	

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < freq.length; i ++)
		{
			sb.append(String.format("%d\t%d%n", i , freq[i]));
		}
		return sb.toString();
	}
	
	
	public int getSymLim()
	{
		return freq.length;
	}
	
	public int getFreq(int sym)
	{
		return freq[sym];
	}

}
	

