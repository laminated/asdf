import java.util.ArrayList;

/*Node class that represent nodes or vertices on a graph,
 holds references to other nodes and it's own name
*/
public class Node{

//name of node
protected String name;
private ArrayList<String> references;

	/*Creates a node*/	
	public Node(String nodeName){
		this.name = nodeName;
		this.references = new ArrayList<String>();
	}

	/*getter method for name
	@modifies chances name of node*/
	public String getName(){
		return this.name;
	}

	/*Adds reference to node, checks for existence of node, if !exist, then add reference.
	@param nodeName -> name of node to be added
	@modifies changes list of nodes in ArrayList<String> references*/
	public void addRef(Node nodeName){
		if ((this.refExists(nodeName.name))==false){
			references.add(nodeName.name);		
		}
		else{
				return;
			}
	}
	/*Checks for existence of reference within node
	@param takes name of node as parameter
	@returns returns boolean true if exists, else returns false*/
	public boolean refExists(String nodeName){
		return references.contains(nodeName);
		}

	/*@Returns ArrayList containing all references to other nodes.*/
	public ArrayList<String> getReferences(){
		return references;
	}

	public int numRefs(){
		int temp=0;
		for (String s : references){
			temp++;
		}
		return temp;
	}

}	