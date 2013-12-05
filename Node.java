import java.util.ArrayList;


public class Node{

//name of node
protected String name;
private ArrayList<String> references;
//private HashMap<Node,String> neighbour;
	
	public Node(String nodeName){
		this.name = nodeName;
		this.references = new ArrayList<String>();
	}

	public String getName(){
		return this.name;
	}

	public void addRef(Node nodeName){
		if ((this.refExists(nodeName.name))==false){
			references.add(nodeName.name);
			//Adds to HashMap<String, ArrayList> within Edge object connections twice, once for each node.
			//connections.connect(nodeName.getName(),this.getName());
		}
		else{
			//System.out.println(references.get(0));
			return;
			//connections.connect(nodeName.getName(),this.getName());
		}
	}

	public boolean refExists(String nodeName){
		return references.contains(nodeName);
		}

	public String printRefs(){
		for (String x : references){
			System.out.println(x);
		}
		return "";
	}



}	