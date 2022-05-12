package project;

import java.util.Scanner;

public class App {
	private int op;
	private Scanner sc;
	private GameMap gameMap;
	private Pathfinder pathFinder;
	private ListNode start;
	private ListNode goal;
	
	public App() {
		op = -1;
		sc = new Scanner(System.in);
		gameMap = new GameMap();
		pathFinder = new Pathfinder();
		
		Menu();

		sc.close();
	}
	
	private void loadMap() {
		System.out.print("Specify filepath: ");
		String filepath = sc.nextLine();
		gameMap.load(filepath);
	}
	
	private void loadStartGoal() {
		System.out.println();
		gameMap.getVertexList().selectMenu();
		System.out.print("\nSelect start/goal (example: room1 room2): ");
		String[] input = sc.nextLine().split("\\s+");
		start = gameMap.getVertexList().find(input[0]);
		goal = gameMap.getVertexList().find(input[1]);
	}
	
	private void runUcs() {
		loadStartGoal();
		System.out.println("\nSTART: " + start.getVertex() + " | GOAL: " + goal.getVertex() + "\n");
		pathFinder.uniformCostSearch(gameMap.getVertexList(), start, goal);
		System.out.println(pathFinder.getUcsRoomsVisited()+"\n");
		System.out.println(pathFinder.getUcsPath());
	}
	
	private void runDfs() {
		loadStartGoal();
		System.out.println("\nSTART: " + start.getVertex() + " | GOAL: " + goal.getVertex() + "\n");
		pathFinder.depthFirstSearch(gameMap.getVertexList(), start, goal);
		System.out.println(pathFinder.getDfsRoomsVisited()+"\n");
		System.out.println(pathFinder.getDfsPath());
	}
	
	private void runBfs() {
		loadStartGoal();
		System.out.println("\nSTART: " + start.getVertex() + " | GOAL: " + goal.getVertex() + "\n");
		pathFinder.breadthFirstSearch(gameMap.getVertexList(), start, goal);
		System.out.println(pathFinder.getBfsRoomsVisited()+"\n");
		System.out.println(pathFinder.getBfsPath());
	}
	
	private void mostDistanceEffective() {
		loadStartGoal();
		System.out.println("\nSTART: " + start.getVertex() + " | GOAL: " + goal.getVertex() + "\n");
		
		pathFinder.uniformCostSearch(gameMap.getVertexList(), start, goal);
		
		pathFinder.depthFirstSearch(gameMap.getVertexList(), start, goal);
		
		pathFinder.breadthFirstSearch(gameMap.getVertexList(), start, goal);
		
		int ucsPathCost = pathFinder.getUcsPathCost();
		int dfsPathCost = pathFinder.getDfsPathCost();
		int bfsPathCost = pathFinder.getBfsPathCost();
		
		String res="";
		String paths="";
		String rep="";
		
		switch (pathFinder.returnMostCostEffective()) {
		case 0:
			res+="Uniform Cost Search had the least costful path\n";
			paths+="UCS: "+pathFinder.getUcsPath();
			rep+="UNIFORM COST SEARCH\n"+pathFinder.getUcsRoomsVisited();
			if(ucsPathCost == dfsPathCost) {
				res+="The path from Depth-First Search has the same distance\n";
				paths+="DFS: "+pathFinder.getDfsPath();
				rep+="\nDEPTH-FIRST SEARCH\n"+pathFinder.getDfsRoomsVisited();
			}
			if(ucsPathCost == bfsPathCost) {
				res+="The path from Breadth-First Search has the same distance\n";
				paths+="BFS: "+pathFinder.getBfsPath()+"\n";
				rep+="\nBREADTH-FIRST SEARCH\n"+pathFinder.getBfsRoomsVisited();
			}
			System.out.print(rep + "\n" + res + "\n" + paths);
			break;
		case 1:
			res+="Depth-First Search had the least costful path\n";
			paths+="DFS: "+pathFinder.getDfsPath();
			rep+="DEPTH-FIRST SEARCH\n"+pathFinder.getDfsRoomsVisited();
			if(dfsPathCost == ucsPathCost) {
				res+="The path from Uniform Cost Search has the same distance\n";
				paths+="UCS: "+pathFinder.getUcsPath();
				rep+="\nUNIFORM COST SEARCH\n"+pathFinder.getUcsRoomsVisited();
			}
			if(dfsPathCost == bfsPathCost) {
				res+="The path from Breadth-First Search has the same distance\n";
				paths+="BFS: "+pathFinder.getBfsPath()+"\n";
				rep+="\nBREADTH-FIRST SEARCH\n"+pathFinder.getBfsRoomsVisited();
			}
			System.out.println(rep + "\n" + res + "\n" + paths);
			break;
		case 2:
			res+="Breadth-First Search had the least costful path\n";
			paths+="BFS: "+pathFinder.getBfsPath();
			rep+="BREADTH-FIRST SEARCH\n"+pathFinder.getBfsRoomsVisited();
			if(bfsPathCost == ucsPathCost) {
				res+="The path from Uniform Cost Search has the same distance\n";
				paths+="UCS: "+pathFinder.getUcsPath();
				rep+="\nUNIFORM COST SEARCH\n"+pathFinder.getUcsRoomsVisited();
			}
			if(bfsPathCost == dfsPathCost) {
				res+="The path from Depth-First Search has the same distance\n";
				paths+="DFS: "+pathFinder.getDfsPath()+"\n";
				rep+="\nDEPTH-FIRST SEARCH\n"+pathFinder.getDfsRoomsVisited();
			}
			System.out.println(rep + "\n" + res + "\n" + paths);
			break;
		default:
			System.out.println("Something went wrong");
			break;
		}
	}
	
	private void mostRoomsEffective() {
		loadStartGoal();
		System.out.println("\nSTART: " + start.getVertex() + " | GOAL: " + goal.getVertex() + "\n");
		
		pathFinder.uniformCostSearch(gameMap.getVertexList(), start, goal);
		
		pathFinder.depthFirstSearch(gameMap.getVertexList(), start, goal);
		
		pathFinder.breadthFirstSearch(gameMap.getVertexList(), start, goal);
		
		int ucsNumberOfRooms = pathFinder.getUcsNumberOfRooms();
		int dfsNumberOfRooms = pathFinder.getDfsNumberOfRooms();
		int bfsNumberOfRooms = pathFinder.getBfsNumberOfRooms();
		
		String res="";
		String paths="";
		String rep="";
		
		switch (pathFinder.returnMostRoomsEffective()) {
		case 0:
			res+="Uniform Cost Search visited the fewest number of rooms\n";
			paths+="UCS: "+pathFinder.getUcsPath();
			rep+="UNIFORM COST SEARCH\n"+pathFinder.getUcsRoomsVisited();
			if(ucsNumberOfRooms == dfsNumberOfRooms) {
				res+="Depth-First Search also visited the fewest number of rooms\n";
				paths+="DFS: "+pathFinder.getDfsPath();
				rep+="\nDEPTH-FIRST SEARCH\n"+pathFinder.getDfsRoomsVisited();
			}
			if(ucsNumberOfRooms == bfsNumberOfRooms) {
				res+="Breadth-First Search also visited the fewest number of rooms\n";
				paths+="BFS: "+pathFinder.getBfsPath()+"\n";
				rep+="\nBREADTH-FIRST SEARCH\n"+pathFinder.getBfsRoomsVisited();
			}
			System.out.print(rep + "\n" + res + "\n" + paths);
			break;
		case 1:
			res+="Depth-First Search visited the fewest number of rooms\n";
			paths+="DFS: "+pathFinder.getDfsPath();
			rep+="DEPTH-FIRST SEARCH\n"+pathFinder.getDfsRoomsVisited();
			if(dfsNumberOfRooms == ucsNumberOfRooms) {
				res+="Uniform Cost Search also visited the fewest number of rooms\n";
				paths+="UCS: "+pathFinder.getUcsPath();
				rep+="\nUNIFORM COST SEARCH\n"+pathFinder.getUcsRoomsVisited();
			}
			if(dfsNumberOfRooms == bfsNumberOfRooms) {
				res+="Breadth-First Search also visited the fewest number of rooms\n";
				paths+="BFS: "+pathFinder.getBfsPath()+"\n";
				rep+="\nBREADTH-FIRST SEARCH\n"+pathFinder.getBfsRoomsVisited();
			}
			System.out.println(rep + "\n" + res + "\n" + paths);
			break;
		case 2:
			res+="Breadth-First Search visited the fewest number of rooms\n";
			paths+="BFS: "+pathFinder.getBfsPath();
			rep+="BREADTH-FIRST SEARCH\n"+pathFinder.getBfsRoomsVisited();
			if(bfsNumberOfRooms == ucsNumberOfRooms) {
				res+="Uniform Cost Search also visited the fewest number of rooms\n";
				paths+="UCS: "+pathFinder.getUcsPath();
				rep+="\nUNIFORM COST SEARCH\n"+pathFinder.getUcsRoomsVisited();
			}
			if(bfsNumberOfRooms == dfsNumberOfRooms) {
				res+="Depth-First Search also visited the fewest number of rooms\n";
				paths+="DFS: "+pathFinder.getDfsPath()+"\n";
				rep+="\nDEPTH-FIRST SEARCH\n"+pathFinder.getDfsRoomsVisited();
			}
			System.out.println(rep + "\n" + res + "\n" + paths);
			break;
		default:
			System.out.println("Something went wrong");
			break;
		}
	}
	
	private void leastDistanceEffective() {
		loadStartGoal();
		System.out.println("\nSTART: " + start.getVertex() + " | GOAL: " + goal.getVertex() + "\n");
		pathFinder.uniformCostSearch(gameMap.getVertexList(), start, goal);
		pathFinder.depthFirstSearch(gameMap.getVertexList(), start, goal);
		pathFinder.breadthFirstSearch(gameMap.getVertexList(), start, goal);
		
		int ucsPathCost = pathFinder.getUcsPathCost();
		
		int dfsPathCost = pathFinder.getDfsPathCost();
		
		int bfsPathCost = pathFinder.getBfsPathCost();
		
		String res="";
		String paths="";
		String rep="";
		
		switch (pathFinder.returnLeastCostEffective()) {
		case 0:
			res+="Uniform Cost Search had the most costful path\n";
			paths+="UCS: "+pathFinder.getUcsPath();
			rep+="UNIFORM COST SEARCH\n"+pathFinder.getUcsRoomsVisited();
			if(ucsPathCost == dfsPathCost) {
				res+="The path from Depth-First Search has the same distance\n";
				paths+="DFS: "+pathFinder.getDfsPath();
				rep+="\nDEPTH-FIRST SEARCH\n"+pathFinder.getDfsRoomsVisited();
			}
			if(ucsPathCost == bfsPathCost) {
				res+="The path from Breadth-First Search has the same distance\n";
				paths+="BFS: "+pathFinder.getBfsPath()+"\n";
				rep+="\nBREADTH-FIRST SEARCH\n"+pathFinder.getBfsRoomsVisited();
			}
			System.out.print(rep + "\n" + res + "\n" + paths);
			break;
		case 1:
			res+="Depth-First Search had the most costful path\n";
			paths+="DFS: "+pathFinder.getDfsPath();
			rep+="DEPTH-FIRST SEARCH\n"+pathFinder.getDfsRoomsVisited();
			if(dfsPathCost == ucsPathCost) {
				res+="The path from Uniform Cost Search has the same distance\n";
				paths+="DFS: "+pathFinder.getUcsPath();
				rep+="\nUNIFORM COST SEARCH\n"+pathFinder.getUcsRoomsVisited();
			}
			if(dfsPathCost == bfsPathCost) {
				res+="The path from Breadth-First Search has the same distance\n";
				paths+="BFS: "+pathFinder.getBfsPath()+"\n";
				rep+="\nBREADTH-FIRST SEARCH\n"+pathFinder.getBfsRoomsVisited();
			}
			System.out.println(rep + "\n" + res + "\n" + paths);
			break;
		case 2:
			res+="Breadth-First Search had the most costful path\n";
			paths+="BFS: "+pathFinder.getBfsPath();
			rep+="BREADTH-FIRST SEARCH\n"+pathFinder.getBfsRoomsVisited();
			if(bfsPathCost == ucsPathCost) {
				res+="The path from Uniform Cost Search has the same distance\n";
				paths+="UCS: "+pathFinder.getUcsPath();
				rep+="\nUNIFORM COST SEARCH\n"+pathFinder.getUcsRoomsVisited();
			}
			if(bfsPathCost == dfsPathCost) {
				res+="The path from Depth-First Search has the same distance\n";
				paths+="DFS: "+pathFinder.getDfsPath()+"\n";
				rep+="\nDEPTH-FIRST SEARCH\n"+pathFinder.getDfsRoomsVisited();
			}
			System.out.println(rep + "\n" + res + "\n" + paths);
			break;
		default:
			System.out.println("Something went wrong");
			break;
		}
	}
	
	private void leastRoomsEffective() {
		loadStartGoal();
		System.out.println("\nSTART: " + start.getVertex() + " | GOAL: " + goal.getVertex() + "\n");
		
		pathFinder.uniformCostSearch(gameMap.getVertexList(), start, goal);
		
		pathFinder.depthFirstSearch(gameMap.getVertexList(), start, goal);
		
		pathFinder.breadthFirstSearch(gameMap.getVertexList(), start, goal);
		
		int ucsNumberOfRooms = pathFinder.getUcsNumberOfRooms();
		int dfsNumberOfRooms = pathFinder.getDfsNumberOfRooms();
		int bfsNumberOfRooms = pathFinder.getBfsNumberOfRooms();
		
		String res="";
		String paths="";
		String rep="";
		
		switch (pathFinder.returnLeastRoomsEffective()) {
		case 0:
			res+="Uniform Cost Search visited the most rooms\n";
			paths+="UCS: "+pathFinder.getUcsPath();
			rep+="UNIFORM COST SEARCH\n"+pathFinder.getUcsRoomsVisited();
			if(ucsNumberOfRooms == dfsNumberOfRooms) {
				res+="Depth-First Search had the same result\n";
				paths+="DFS: "+pathFinder.getDfsPath();
				rep+="\nDEPTH-FIRST SEARCH\n"+pathFinder.getDfsRoomsVisited();
			}
			if(ucsNumberOfRooms == bfsNumberOfRooms) {
				res+="Breadth-First Search had the same result\n";
				paths+="BFS: "+pathFinder.getBfsPath()+"\n";
				rep+="\nBREADTH-FIRST SEARCH\n"+pathFinder.getBfsRoomsVisited();
			}
			System.out.print(rep + "\n" + res + "\n" + paths);
			break;
		case 1:
			res+="Depth-First Search visited the most rooms\n";
			paths+="DFS: "+pathFinder.getDfsPath();
			rep+="DEPTH-FIRST SEARCH\n"+pathFinder.getDfsRoomsVisited();
			if(dfsNumberOfRooms == ucsNumberOfRooms) {
				res+="Uniform Cost Search had the same result\n";
				paths+="UCS: "+pathFinder.getUcsPath();
				rep+="\nUNIFORM COST SEARCH\n"+pathFinder.getUcsRoomsVisited();
			}
			if(dfsNumberOfRooms == bfsNumberOfRooms) {
				res+="Breadth-First Search had the same result\n";
				paths+="BFS: "+pathFinder.getBfsPath()+"\n";
				rep+="\nBREADTH-FIRST SEARCH\n"+pathFinder.getBfsRoomsVisited();
			}
			System.out.println(rep + "\n" + res + "\n" + paths);
			break;
		case 2:
			res+="Breadth-First Search visited the most rooms\n";
			paths+="BFS: "+pathFinder.getBfsPath();
			rep+="BREADTH-FIRST SEARCH\n"+pathFinder.getBfsRoomsVisited();
			if(bfsNumberOfRooms == ucsNumberOfRooms) {
				res+="Uniform Cost Search had the same result\n";
				paths+="UCS: "+pathFinder.getUcsPath();
				rep+="\nUNIFORM COST SEARCH\n"+pathFinder.getUcsRoomsVisited();
			}
			if(bfsNumberOfRooms == dfsNumberOfRooms) {
				res+="Depth-First Search had the same result\n";
				paths+="DFS: "+pathFinder.getDfsPath()+"\n";
				rep+="\nDEPTH-FIRST SEARCH\n"+pathFinder.getDfsRoomsVisited();
			}
			System.out.println(rep + "\n" + res + "\n" + paths);
			break;
		default:
			System.out.println("Something went wrong");
			break;
		}
	}
	
	private void runAllThree() {
		loadStartGoal();
		System.out.println("\nSTART: " + start.getVertex() + " | GOAL: " + goal.getVertex() + "\n");
		
		pathFinder.uniformCostSearch(gameMap.getVertexList(), start, goal);
		
		pathFinder.depthFirstSearch(gameMap.getVertexList(), start, goal);
		
		pathFinder.breadthFirstSearch(gameMap.getVertexList(), start, goal);
		
		System.out.println("UNIFORM COST SEARCH\n"+pathFinder.getUcsRoomsVisited());
		System.out.println("DEPTH-FIRST SEARCH\n"+pathFinder.getDfsRoomsVisited());
		System.out.println("BREADTH-FIRST SEARCH\n"+pathFinder.getBfsRoomsVisited());
		System.out.println("\nUCS: "+pathFinder.getUcsPath()+"\nDFS: "+pathFinder.getDfsPath()+"\nBFS: "+pathFinder.getBfsPath());
	}
 	
	private void Menu() {
		while(op != 0 && op != 1) {
			System.out.println("0- Quit\n1- Load game map");
			try {
				op = Integer.parseInt(sc.nextLine());
				
				if(op == 1) {
					System.out.print("\nSpecify filepath: ");
					String filepath = sc.nextLine();
					gameMap.load(filepath);
				} else if(op == 0) {
					return;
				} else {
					System.out.println("\nInvalid option\n");
					op = -1;
				}
			} catch(Exception e) {
				System.out.println("\nInvalid option\n");
				op = -1;
			}
		}
		
		op = -1;		
		while(op != 0) {
			System.out.println("\n1- Load new game map\n2- Run Uniform Cost Search\n3- Run Depth-First Search\n"
					+ "4- Run Breadth-First Search\n5- Run Most Cost Effective\n6- Run Least Cost Effective\n"
							+ "7- Run Fewest Rooms Visited\n8- Run Most Rooms Visited\n9- Run all three\n0- Quit");
			try {
				op = Integer.parseInt(sc.nextLine());
				
				switch(op) {
				case 0:
					return;
				case 1:
					loadMap();
					break;
				case 2:
					runUcs();
					break;
				case 3:
					runDfs();
					break;
				case 4:
					runBfs();
					break;
				case 5:
					mostDistanceEffective();
					break;
				case 6:
					leastDistanceEffective();
					break;
				case 7:
					mostRoomsEffective();
					break;
				case 8:
					leastRoomsEffective();
					break;
				case 9:
					runAllThree();
					break;
				default:
					System.out.println("\nInvalid option");
					op = -1;
				}
			} catch(Exception e) {
				System.out.println("\nInvalid option\n");
				op = -1;
			}
		}
	}
	
	public static void main(String args[]) {
		new App();
	}
}
