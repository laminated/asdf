import java.util.HashMap;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;


public class Graph
	{
	
	private HashMap<String, Node> nodeList;
	private HashMap<String, Edge> edgeList;
	private HashMap<String,ArrayList> titleList;
	

	public Graph(){
		this.nodeList = new HashMap<String, Node>();
		this.edgeList = new HashMap<String, Edge>();
		this.titleList = new HashMap<String, ArrayList>();
		}

	public String[] breadthFirstSearch(String firstNode, String secondNode){
        String path = "";
        Queue<String> queue = new LinkedBlockingQueue<String>();
        queue.add(firstNode);

        while (!queue.isEmpty()){
            String currentNodeStringPath = queue.remove();
            Node currentNode = nodeList.get(getCurrentNodeName(currentNodeStringPath));

            for(String neighbour : currentNode.getReferences()){
                if(neighbour.equals(secondNode)){
                    path = neighbour + ";" + secondNode;
                    break;
                }else{
                    queue.add(currentNodeStringPath + neighbour);
                }
            }
        }

        String[] characters = path.split(";");
        return characters;
 	}

 	public String getCurrentNodeName(String nodeName){
        int k = nodeName.lastIndexOf(';');
        if(k<0){
            return nodeName;
        }else{
            return nodeName.substring(k+1);
        }
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
		System.out.println(nodeList.get(firstNode).getName());
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

/*	public ArrayList charsToList(String someArr[], int index){
		ArrayList<String> sameBookChars = new ArrayList<String>();
		for (index;someArr[index+1]!=someArr[index+3];index=index+2){
				sameBookChars.add(splitString[index]);
			}
		return sameBookChars;
	}*/

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
			//ArrayList<String> sameBook = new ArrayList<String>();

			String currentBook = "";
			//String[] currentBookCharacters;
			ArrayList<String> currentBookCharacters = new ArrayList<String>();

			for(int i=1;    i<splitString.length;    i += 2){
				if( !splitString[i].equals(currentBook)){
					String[] charsInBook = (String[])currentBookCharacters.toArray();
					for(int j=0;j<charsInBook.length; j++){
						for(int k=j+1;  k<charsInBook.length;k++){
                               
								nodeJoin(charsInBook[j],charsInBook[k],currentBook);
                               // add edge between charsInBooks[j]
                               // and charsInBook[k]
						}
					}
					currentBookCharacters.clear();
					currentBook = splitString[i];
				}else{
					currentBookCharacters.add(splitString[i-1]);
				} 

			}








/*			for (int i=0;i<splitString.length;i=i+2){
				createNode(splitString[i]);
			}
*/
/*			nodeJoin(splitString[0],splitString[2],splitString[1]);
			nodeJoin(splitString[0],splitString[4],splitString[1]);
			nodeJoin(splitString[0],splitString[6],splitString[1]);
			nodeJoin(splitString[0],splitString[8],splitString[1]);
			nodeJoin(splitString[0],splitString[10],splitString[1])
			nodeJoin(splitString[2],splitString[4],splitString[1])
			nodeJoin(splitString[2],splitString[6],splitString[1])
			nodeJoin(splitString[2],splitString[8],splitString[1])
			nodeJoin(splitString[2],splitString[10],splitString[1])
			nodeJoin(splitString[8],splitString[10],splitString[1])
			nodeJoin(splitString[12],splitString[14],splitString[2])
			nodeJoin(splitString[14],splitString[16],splitString[2])
			;*/
			

/*			for (int i=1;i<splitString.length-1;i=i+2){
				if (!(splitString[i].equals(splitString[i+2]))){
					sameBook.add(splitString[i]);
				}
			}
			int q=0;
			for (int s=0;s<sameBook.size();sameBook){

				titleList.put(sameBook,charsToList(splitString[],q))
			}

			int w=0;
			int z=0;
			int iter=2;
			while ((z<sameBook.size()-1) ||

			while( w< (splitString.length-1)){
				while ((sameBook.get(z)).equals(splitString[iter-1])){
				
					nodeJoin(splitString[w],splitString[iter],sameBook.get(z));
					iter=iter+2;
				}
				w=w+2;
				iter=w+2;

				z++;
				
			}*/



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