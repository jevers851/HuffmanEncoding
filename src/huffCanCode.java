import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//Canonical construction
//Higher values end up with longer strings rather
//Only constraint not known is number of bits for each symbol transmitted


public class huffCanCode
{
	private int[] lengths;
	
	public Tree toTree()
	{
		List<Node> nodes = new ArrayList<Node>();
		for (int i = max(lengths); i >= 1; i--)
		{
			List<Node> nnodes = new ArrayList<Node>();
			
			for(int k = 0; k < lengths.length; k++)
			{
				if(lengths[k] == i)
					nnodes.add(new Leaf(k));
			}
			for(int k = 0; k < nodes.size(); k += 2)
				nnodes.add(new iNode(nodes.get(k), nodes.get(k + 1)));

			nodes = nnodes;
			
			
		}
		return new Tree(new iNode(nodes.get(0), nodes.get(1)), lengths.length);
		
	}
	
	private void build(Node node, int depth)
	{
		if (node instanceof iNode)
		{
			iNode inode = (iNode)node;
			build(inode.leftchild, depth + 1);
			//builds left side of tree
			build(inode.rightchild, depth + 1);
			//builds right side of tree
		}
		else if (node instanceof Leaf)
		{
			lengths[((Leaf)node).sym] = depth; 
		}

	
		
	}
	public int getsymLim()
	{
		return lengths.length;
	}
	public int getlengths(int sym) 
	{
		return lengths[sym];
	}
	

	
	private static int max(int[] array)
	{
		int result = array[0];
		for (int i : array)
			result = Math.max(i, result);
		return result;
	}
	
	public huffCanCode(int[] lengths)
	{
		this.lengths = lengths.clone();
	}
	
	public huffCanCode(Tree tree, int list) 
	{
		lengths = new int[list];
		Arrays.fill(lengths, -1);
		build(tree.root, 0);
		
	}

}
