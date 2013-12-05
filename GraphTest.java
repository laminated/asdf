public class GraphTest{
	
	public static void main(String [] args)
		{
			Graph marvelUniverse = new Graph();
			try{
				marvelUniverse.stringToGraph();
			 }catch(Exception e){
			 	e.printStackTrace();
 			}
 			
 			marvelUniverse.createNode("Tony Wong");//node creation works
 			marvelUniverse.createNode("Princess Leia");
 			marvelUniverse.nodeJoin("Tony Wong", "Princess Leia", "Lord of the Rings");
 			marvelUniverse.nodeJoin("Tony Wong", "Darth Vader", "Smelly Pigs from Mars");//node creation works via nodeJoin
 			String[] test = (marvelUniverse.breadthFirstSearch("Darth Vader", "Princess Leia"));
 			for (int i=0;i<test.length;i++){
 				System.out.println(test[i]);
 			}
			System.out.println(marvelUniverse.isAdjacent("Tony Wong","Princess Leia"));
			System.out.println(marvelUniverse.isAdjacent("M'SHULLA","FROST, CARMILLA"));
 			System.out.println(marvelUniverse.isAdjacent("FROST, CARMILLA","24-HOUR MAN/EMMANUEL"));
 			System.out.println(marvelUniverse.isAdjacent("VAPOR","AJAX"));
 			System.out.println(marvelUniverse.isAdjacent("VAPOR","MASTER FOUR")); //should be false
 			System.out.println(marvelUniverse.isAdjacent("WARLORD","MASTER FOUR"));
 			System.out.println(marvelUniverse.isAdjacent("Princess Leia","Tony Wong"));
			System.out.println(marvelUniverse.isAdjacent("Princess Leia","John Snow")); //should print invalid
		}

}