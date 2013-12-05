import java.util.HashMap;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Graph
	{
	
	private HashMap<String, Node> nodeList;
	private HashMap<String, Edge> edgeList;
	

	public Graph(){
		this.nodeList = new HashMap<String, Node>();
		this.edgeList = new HashMap<String, Edge>();
		}

	public void breadthFirstSearch(String firstNode, String secondNode){

	}

	public void allNodes(){
		for (String entry : nodeList.keySet()) {
			System.out.println(entry);
			//System.out.println((nodeList.get(entry)).printRefs());
		}
	}

	public void createNode(String nodeName){
		if (this.nodeExists(nodeName)==false){
			nodeList.put(nodeName, new Node(nodeName));
			}
		return;
	}

	public void nodeJoin(String firstNode, String secondNode, String connection){
		String temp = firstNode + "^" + secondNode;
		createNode(firstNode);
		createNode(secondNode);
		(nodeList.get(firstNode)).addRef(nodeList.get(secondNode));
		(nodeList.get(secondNode)).addRef(nodeList.get(firstNode));
		if (secondNode.compareTo(firstNode) > 0)
			temp = secondNode + "^" + firstNode;
		if (edgeList.get(temp) == null)
			edgeList.put(temp, new Edge(temp, connection));
		else
			(edgeList.get(temp)).addTitle(connection);
	}
		
	public boolean isAdjacent(String firstNode, String secondNode) throws NullPointerException{
		if (nodeList.get(secondNode) == null || nodeList.get(firstNode) == null)
		{
			System.out.println("Invalid Nodes");
			return false;
		}
		if ((nodeList.get(firstNode)).refExists((nodeList.get(secondNode)).getName())){
			return true;
		}
		else
			return false;
	}	

	public boolean nodeExists(String nodeName){
		if(nodeList.get(nodeName)!=null){
		 	return true;
		}
		return false;
	}

	public void stringToGraph() throws IOException{
		try {
			File file = new File("labeled_edges.tsv");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			fileReader.close();
			String temporary = (stringBuffer.toString());

			String[] splitString;
			int x = 0;
			StringTokenizer st = new StringTokenizer(temporary, "\t\"");
			splitString = new String[st.countTokens()];
			while (st.hasMoreTokens()==true){
				splitString[x]=st.nextToken();
				x++;
			}

			//Node Creation
			ArrayList<String> sameBook = new ArrayList<String>();

			for (int i=0;i<splitString.length;i=i+2){
				createNode(splitString[i]);
			}

/*			nodeJoin(splitString[0],splitString[2],splitString[1]);
			nodeJoin(splitString[0],splitString[4],splitString[1]);
			nodeJoin(splitString[0],splitString[6],splitString[1]);
			nodeJoin(splitString[0],splitString[8],splitString[1]);
			nodeJoin(splitString[0],splitString[10],splitString[1]);*/
			

			for (int i=1;i<splitString.length-1;i=i+2){
				if (!(splitString[i].equals(splitString[i+2]))){
					sameBook.add(splitString[i]);
					
				}
			}

			int w=0;
			int z=0;
			while ((z<sameBook.size()-500) || w< (splitString.length-500)){
				for (int i=2;i<splitString.length-500;i=i+2){
					nodeJoin(splitString[w],splitString[i],sameBook.get(z));
				}
				z++;
				w++;
			}



/*			
			System.out.println(sameBook.size());
			int z = 0;
			int q = 0;
			while (z < sameBook.size()){
				if (sameBook.get(z).equals(splitString[q+1])){
					nodeJoin(splitString[z],splitString[q+2],sameBook.get(z));
					q=q+2;
				}
				if (q >splitString.length-1){
					break;
				}
				else{
					z++;	
				}
			}*/

/*			for (int i=1;i<splitString.length+1;i=i+2){
				String tempString = splitString[i];
				if (splitString[i].equals(splitString[i+2])){
					sameBook.add(splitString[i-1]);
				}
				else{
					int q,z,y=0;
					while (q<y){
						for (y=z;y<sameBook.size();y++){
							nodeJoin(sameBook.get(x), sameBook.get(y), tempString);
						}
						q++;
						z++;
					}
				}
			}*/
/*			for (int i=0;i<splitString.length;i=i+2){
				createNode(splitString[i]);
				//System.out.println("Successfully created");
				if(splitString[i+1].equals(splitString[i+3])){
					int y=i;
					//while first book = second book join nodes then increment to next book
					while (splitString[i+1].equals(splitString[y+1])){
						nodeJoin(splitString[i], splitString[y],splitString[y-1]);
						System.out.println("successfully joined");
						y=y+2;

					}
				}
			}*/


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}