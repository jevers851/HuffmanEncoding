import java.util.ArrayList;
import java.util.List;

//TREE BUILDER ENCODE BUILD
public class Tree 
{
	public final Node root;
	
	private List<List<Integer>> codes;
	
	private void buildList(Node node, List<Integer> b )
	{
		if(node instanceof iNode)
		{
			iNode inode = (iNode)node;
			b.add(0);
			buildList(inode.leftchild, b);
			b.remove(b.size() - 1);
			b.add(1);
			buildList(inode.rightchild, b);
			b.remove(b.size() - 1);
			
		}
		else if(node instanceof Leaf)
		{
			Leaf leaf = (Leaf)node;
			
			codes.set(leaf.sym,  new ArrayList<Integer>(b));
		}
	}
	
	public List<Integer> getCode( int sym)
	{
		return codes.get(sym);
	}
	
	public Tree( Node root, int symLim)
	{
		this.root = root;
		
		codes = new ArrayList<List<Integer>>();
		for(int i =0; i < symLim; i++)
			codes.add(null);
		buildList(root, new ArrayList<Integer>());
	}
	

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		toString("", root, sb);
		return sb.toString();
		
		
	}
	public static void toString(String b, Node node, StringBuilder sb)
	{
			iNode inode = (iNode)node;
			toString(b + "0", inode.leftchild, sb);
			toString(b + "1", inode.rightchild, sb);
	}
}
