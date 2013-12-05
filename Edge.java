public class Edge{
	
	private String lowestTitle, edgeName;

	//Edge Constructor
	public Edge(String concatNode, String connection){
		this.lowestTitle = connection;
		this.edgeName = concatNode;
	}

	//Replaces title if lexicographically smaller.
	public void addTitle(String title){
		if ((this.lowestTitle).compareTo(title) > 0){
			lowestTitle = title;
		}
		else
			return;
		}

	public String edgeName(){
		return lowestTitle;
	}
}