import java.util.HashMap;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/*Graph class that has the ability to creates nodes and connect them via Edges.*/
public class Graph
	{
	
	private HashMap<String, Node> nodeList;
	private HashMap<String, Edge> edgeList;
	
	/*Graph Constructor*/
	public Graph(){
		this.nodeList = new HashMap<String, Node>();
		this.edgeList = new HashMap<String, Edge>();
		}

	public ArrayList<String> bfs(String rootNode, String endNode){
			ArrayList<String> path = bfsNode(rootNode, endNode);
			ArrayList<String> finalPath= new ArrayList<String>();
			for(int i=0;i<path.size();i++){
				finalPath.add(path.get(i));
				String temp = stringCon(path.get(i),path.get(i+1));
				//System.out.println(temp);
				finalPath.add((edgeList.get(temp)).edgeName());
			}
			return finalPath;
	}

	public String stringCon(String firstNode, String secondNode){
		String temp = firstNode + "_" + secondNode;
		if (secondNode.compareTo(firstNode) > 0)
			temp = secondNode + "_" + firstNode;
		return temp;
	}

	public ArrayList<String> bfsNode(String rootNode, String endNode){
		// BFS uses Queue data structure
		ArrayList<String> previousPath= new ArrayList<String>();
		ArrayList<String> updatedPath= new ArrayList<String>();
		HashMap<String, ArrayList<String>> path = new HashMap<String, ArrayList<String>>();
		Queue queue = new LinkedList();
		queue.add(exposeNode(rootNode));
		previousPath.add(rootNode);
		path.put(rootNode,updatedPath);
		exposeNode(rootNode).visited = true;
		while(!queue.isEmpty()) {
			Node node = (Node)queue.remove();
			for( String child : (node.getReferences()) ) {
				if (exposeNode(child).visited)
					continue;
				exposeNode(child).visited=true;
				queue.add(exposeNode(child));
				updatedPath = (ArrayList<String>)previousPath.clone();
				updatedPath.add(child);
				path.put(child,updatedPath);
				if ((child).equals(endNode)){
					return updatedPath;
				}
			}
		}
		// Clear visited property of nodes
		clearNodes();
		return null;
		}



	public void clearNodes(){
		for (String entry : nodeList.keySet()) {
			exposeNode(entry).visited=false;
		}
	}

	/*BFS implementation
	@params node1, node2, edge that connects them
	@modifies the nodes and info stored in them, queue within BFS
	@returns array of strings holding shortest path*/
	/*public String[] breadthFirstSearch(String firstNode, String secondNode){
        String path = "";
        Queue<String> queue = new LinkedBlockingQueue<String>();
        queue.add(firstNode);

        while (!queue.isEmpty()){
            String currentNodeStringPath = queue.remove();
            Node currentNode = nodeList.get(getCurrentNodeName(currentNodeStringPath));


            for(String neighbour : currentNode.getReferences()){

                if(neighbour.equals(secondNode)){
                    path = currentNodeStringPath + "_" + secondNode;
                    break;

                // this leads to a cycle - in which we should stop looking for this path
                }else if(currentNodeStringPath.contains(neighbour)){}
                else{
                    String newPath = currentNodeStringPath + "_" + neighbour;
                    if(!queue.contains(newPath)){
                        queue.add(newPath);
                    }
                }
            }
        }

        if(path.isEmpty()){
            return null;
        }else{
            String[] characters = path.split("_");
            ArrayList<String> fullPath = new ArrayList<String>();

            for(int i=0;i<characters.length-1;i++){
                Edge e = edgeList.get(characters[i] + "_" + characters[i+1]);
                // small stupid way to get around the ordering of the name
                if(e == null){
                    e = edgeList.get(characters[i+1] + "_" + characters[i]);
                }

                fullPath.add(characters[i]);
                fullPath.add(e.edgeName());
            }
            fullPath.add(characters[characters.length-1]);
            return fullPath.toArray(new String[fullPath.size()]);
        }
 	}

 	//helper method for BFS
 	public String getCurrentNodeName(String nodeName){
        int k = nodeName.lastIndexOf('_');
        if(k<0){
            return nodeName;
        }else{
            return nodeName.substring(k+1);
        }
    }*/

    public ArrayList mostReferences(){
    	int temp=0;
    	int currentMax=0;
    	ArrayList<String> maxRefs = new ArrayList<String>();
    	for (String entry : nodeList.keySet()) {
    		Node tempNode = nodeList.get(entry);
    		temp = tempNode.numRefs();
    		if (temp > currentMax){
    			currentMax = temp;
    			maxRefs.clear();
    			maxRefs.add((nodeList.get(entry)).getName());
    		}
    		else if (temp == currentMax){
    			maxRefs.add((nodeList.get(entry)).getName());
    		}
    	}
    	return maxRefs;
    } 

    public Node exposeNode(String nodeName){
    	return nodeList.get(nodeName);
    }

    //Method used for test, prints all nodes.
	public void allNodes(){
		for (String entry : nodeList.keySet()) {
			System.out.println(entry);
		}
	}

	//Method used for test, prints all edges.
	public void allEdges(){
		for (String entry : edgeList.keySet()) {
			System.out.println(entry);
		}
	}


	/*Creates nodes
	@params takes nodeName and creates node and stores in HashMap
	@modifies hashMap*/
	public void createNode(String nodeName){
		if (this.nodeExists(nodeName)==false){
			nodeList.put(nodeName, new Node(nodeName));
			}
		return;
	}

	/*Joins two nodes using edge specified in parameter
	@params node1, node2, edge
	@modifies Graph object*/
	public void nodeJoin(String firstNode, String secondNode, String connection){
		String temp = firstNode + "_" + secondNode;
		createNode(firstNode);
		createNode(secondNode);
		(nodeList.get(firstNode)).addRef(nodeList.get(secondNode));
		(nodeList.get(secondNode)).addRef(nodeList.get(firstNode));
		if (secondNode.compareTo(firstNode) > 0)
			temp = secondNode + "_" + firstNode;
		if (edgeList.get(temp) == null)
			edgeList.put(temp, new Edge(temp, connection));
		else
			(edgeList.get(temp)).addTitle(connection);
	}
	
/*	@param Takes two nodes, if they are neighbours returns True
	@returns true or false depending on adjacency*/	
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

	/*Checks for hashMap for existence of value associated with nodename Key
	@returns True if exists, false else*/
	public boolean nodeExists(String nodeName){
		if(nodeList.get(nodeName)!=null){
		 	return true;
		}
		return false;
	}

	/*@param takes file, parses, tokenizes and converts to Graph
	@modifies Graph object
	@throws IOException*/
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

			//Converts array of Strings into Graph
			String currentBook = "";
			ArrayList<String> currentBookCharacters = new ArrayList<String>();

			for(int i=1;    i<splitString.length;    i += 2){
				if( !splitString[i].equals(currentBook)){
                    String[] charsInBook = new String[currentBookCharacters.size()];
					charsInBook = currentBookCharacters.toArray(charsInBook);
					for(int j=0;j<charsInBook.length; j++){
						for(int k=j+1;  k<charsInBook.length;k++){ 
								nodeJoin(charsInBook[j],charsInBook[k],currentBook);       
						}
					}
					currentBookCharacters.clear();
                    currentBookCharacters.add(splitString[i-1]);
					currentBook = splitString[i];
				}else{
					currentBookCharacters.add(splitString[i-1]);
				} 

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}