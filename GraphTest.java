public class GraphTest{
	
	public static void main(String [] args)
		{
			Graph marvelUniverse = new Graph();
			try{
				marvelUniverse.stringToGraph();
			 }catch(Exception e){
			 	e.printStackTrace();
 			}
 			
 			marvelUniverse.createNode("Tony Wong");
 			marvelUniverse.createNode("Princess Leia");
 			marvelUniverse.nodeJoin("Tony Wong", "Princess Leia", "Lord of the Rings");
 			//marvelUniverse.allNodes();
			System.out.println(marvelUniverse.isAdjacent("Tony Wong","Princess Leia"));
			System.out.println(marvelUniverse.isAdjacent("M'SHULLA","FROST, CARMILLA"));
 			System.out.println(marvelUniverse.isAdjacent("FROST, CARMILLA","24-HOUR MAN/EMMANUEL"));
 			System.out.println(marvelUniverse.isAdjacent("VAPOR","AJAX"));
 			System.out.println(marvelUniverse.isAdjacent("VAPOR","MASTER FOUR"));
 			System.out.println(marvelUniverse.isAdjacent("WARLORD","MASTER FOUR"));
 			System.out.println(marvelUniverse.isAdjacent("Princess Leia","Tony Wong"));
			System.out.println(marvelUniverse.isAdjacent("Princess Leia","John Snow"));
		}

}