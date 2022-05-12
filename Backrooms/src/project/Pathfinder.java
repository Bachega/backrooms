package project;

import java.util.HashMap;
import java.util.Map;

public class Pathfinder {
	private Map<String, ListNode> ucsPredecessor;
	private Map<String, Integer> ucsCost;
	private int ucsPathCost;
	private int ucsNumberOfRooms;
	private String ucsRoomsVisited;
	private String ucsPath;
	private Map<String, Integer> dfsColor;
	private Map<String, ListNode> dfsPredecessor;
	private Map<String, Integer> dfsCost;
	private int dfsPathCost;
	private int dfsNumberOfRooms;
	private String dfsRoomsVisited;
	private String dfsPath;
	private Map<String, Integer> bfsColor;
	private Map<String, ListNode> bfsPredecessor;
	private Map<String, Integer> bfsCost;
	private int bfsPathCost;
	private int bfsNumberOfRooms;
	private String bfsRoomsVisited;
	private String bfsPath;
	private Map<String, Boolean> tpVisited;
	private Stack stack;
	private boolean quit;
	private double ucsTime;
	private double dfsTime;
	private double bfsTime;
	private int ucsNodesEnqueued;
	private int bfsNodesEnqueued;

	public void uniformCostSearch(List vertex, ListNode start, ListNode goal) {
		long startTime = System.nanoTime();
		PriorityQueue queue = new PriorityQueue(vertex.getN());
		ucsPredecessor = new HashMap<String, ListNode>();
		ucsCost = new HashMap<String, Integer>();
		ucsPathCost = 0;
		ucsNumberOfRooms = 0;
		ucsRoomsVisited = "";
		ucsPath = "";
		int newCost;
		queue.insert(start, 0);
		ucsPredecessor.put(start.getVertex(), null);
		ucsCost.put(start.getVertex(), 0);
		ucsNodesEnqueued = 1;
			
		ListNode current = null;
		EdgeListNode next = null;
		while(!queue.isEmpty()) {
			current = queue.pop().getVertex();
			
			ucsRoomsVisited += "Visiting room: " + current.getVertex() + "\n";
			ucsNumberOfRooms++;
			
			if(current == goal)
				break;
			
			next = current.getEdgeList().getHead();
			while(next != null) {
				newCost = ucsCost.get(current.getVertex()) + next.getDistance();
				if(!ucsCost.containsKey(next.getEdge()) || newCost < ucsCost.get(next.getEdge())) {
					ucsCost.put(next.getEdge(), newCost);
					queue.insert(vertex.find(next.getEdge()), newCost);
					ucsNodesEnqueued++;
					ucsPredecessor.put(next.getEdge(), vertex.find(current.getVertex()));
					ucsRoomsVisited += "Enqueued room: " + next.getEdge() + " with cost: " + newCost + "\n";
				}
				next = next.getNext();
			}
			ucsRoomsVisited += "\n";
		}
		long stopTime = System.nanoTime();
		
		ucsTime = (double)(stopTime - startTime)/1000000000;
			
		ucsPath += "Path: ";
		
		ucsPathCost = ucsCost.get(goal.getVertex());
		ListNode a = current;
		String info="";
		while(a != start) {
			info += a.getVertex() + " >- ";
			a = ucsPredecessor.get(a.getVertex());
		}
		info += a.getVertex();
		ucsPath += new StringBuilder(info).reverse().toString() + " | Number of rooms visited: " + ucsNumberOfRooms + " | Nodes enqueued: " + ucsNodesEnqueued + " | Total cost: " + ucsPathCost + " | Execution time: "+ ucsTime +" seconds\n";
	}
	
	public void depthFirstSearchVisit(List vertex, ListNode start, ListNode goal) {
		dfsColor.put(start.getVertex(), 0);
		EdgeListNode edges = start.getEdgeList().getHead();
				
		if(start.getVertex().equals(goal.getVertex())) {
			quit = true;
			return;
		}
		
		while(edges!=null && !quit)  {
			if(!dfsColor.containsKey(edges.getEdge())) {
				dfsCost.put(edges.getEdge(), edges.getDistance());
				dfsPredecessor.put(edges.getEdge(), start);	
				dfsRoomsVisited += "Visiting room: " + edges.getEdge() + "\n";
				dfsNumberOfRooms++;
				depthFirstSearchVisit(vertex, vertex.find(edges.getEdge()), goal);
			}
			edges = edges.getNext();
		}
		
		dfsColor.put(start.getVertex(), 1);
	}
	
	public void depthFirstSearch(List vertex, ListNode start, ListNode goal) {
		long startTime = System.nanoTime();
		// Doesn't contain -> White | 0 -> Gray | 1 -> Black
		dfsColor = new HashMap<String, Integer>();
		dfsPredecessor = new HashMap<String, ListNode>();
		dfsCost = new HashMap<String, Integer>();
		dfsPathCost = 0;
		dfsNumberOfRooms = 0;
		dfsRoomsVisited = "";
		dfsPath = "";
		dfsColor.put(start.getVertex(), 0);
		dfsPredecessor.put(start.getVertex(), null);
		dfsCost.put(start.getVertex(), 0);
		quit = false;
		
		dfsRoomsVisited += "Visiting room: " + start.getVertex() + "\n";
		dfsNumberOfRooms++;
		
		depthFirstSearchVisit(vertex, start, goal);
		
		long stopTime = System.nanoTime();
		
		dfsTime = (double)(stopTime - startTime)/1000000000;
		
		dfsPath += "Path: ";
		dfsPathCost = 0;
		ListNode a = goal;
		String info="";
		while(a != start) {
			info += a.getVertex() + " >- ";
			dfsPathCost += dfsCost.get(a.getVertex());
			a = dfsPredecessor.get(a.getVertex());
		}
		info += a.getVertex();
		
		dfsPath += new StringBuilder(info).reverse().toString() + " | Number of rooms visited: " + dfsNumberOfRooms + " | Total cost: " + dfsPathCost + " | Execution time: "+dfsTime+" seconds\n";
	}
	
	public void breadthFirstSearch(List vertex, ListNode start, ListNode goal) {
		long startTime = System.nanoTime();
		Queue queue = new Queue();
		bfsColor = new HashMap<String, Integer>();
		bfsPredecessor = new HashMap<String, ListNode>();
		bfsCost = new HashMap<String, Integer>();
		bfsPathCost = 0;
		bfsNumberOfRooms = 0;
		bfsRoomsVisited = "";
		bfsPath = "";
		queue.insert(start);
		bfsPredecessor.put(start.getVertex(), null);
		bfsColor.put(start.getVertex(), 0);
		bfsCost.put(start.getVertex(), 0);
		bfsNodesEnqueued = 1;
		
		ListNode current = null;
		EdgeListNode next = null;
		while(!queue.isEmpty()) {
			current = queue.pop().getVertex();
			
			bfsRoomsVisited += "Visiting room: " + current.getVertex() + "\n";
			bfsNumberOfRooms++;
			
			if(current == goal)
				break;
			
			next = current.getEdgeList().getHead();
			while(next != null) {
				if(!bfsColor.containsKey(next.getEdge())) {
					bfsColor.put(next.getEdge(), 0);
					bfsCost.put(next.getEdge(), next.getDistance());
					queue.insert(vertex.find(next.getEdge()));
					bfsNodesEnqueued++;
					bfsPredecessor.put(next.getEdge(), vertex.find(current.getVertex()));
					bfsRoomsVisited += "Enqueued room: " + next.getEdge() + " with cost: " + next.getDistance() + "\n";
				}
				next = next.getNext();
			}
			bfsRoomsVisited += "\n";
		}
		long stopTime = System.nanoTime();
		
		bfsTime = (double)(stopTime - startTime)/1000000000;
		
		bfsPath += "Path: ";
		ListNode a = goal;
		String info="";
		while(a != start) {
			info += a.getVertex() + " >- ";
			bfsPathCost += bfsCost.get(a.getVertex());
			a = bfsPredecessor.get(a.getVertex());
		}
		info += a.getVertex();
		bfsPath += new StringBuilder(info).reverse().toString() + " | Number of rooms visited: " + bfsNumberOfRooms + " | Nodes enqueued: " + bfsNodesEnqueued + " | Total cost: " + bfsPathCost + " | Execution time: " + bfsTime + " seconds\n";
	}
	
	public void topologicalSortUtil(List vertex, ListNode node) {
		tpVisited.put(node.getVertex(), true);
		EdgeListNode edges = node.getEdgeList().getHead();
		
		while(edges!=null)  {
			if(!tpVisited.containsKey(edges.getEdge())) {
				System.out.println("Entering: " + edges.getEdge());
				topologicalSortUtil(vertex, vertex.find(edges.getEdge()));
			}
			edges = edges.getNext();
		}
		
		stack.add(node);
	}
	
	public void topologicalSort(List vertex) {
		// Doesn't contain -> White | 0 -> Gray | 1 -> Black
		tpVisited = new HashMap<String, Boolean>();
		stack = new Stack();
		quit = false;
		
		ListNode aux = vertex.getHead();
		for(int i = 0; i < vertex.getN(); i++) {
			if(!tpVisited.containsKey(aux.getVertex())) {
				System.out.println("Entering: " + aux.getVertex());
				topologicalSortUtil(vertex, aux);
			}
			aux = aux.getNext();
		}
	}
	
	public void longestPath(List vertex, ListNode start, ListNode goal) {
		Map<String, Integer> dist = new HashMap<String, Integer>();
		Map<String, ListNode> pred = new HashMap<String, ListNode>();
		
		topologicalSort(vertex);
		
		ListNode node = vertex.getHead();
		for(int i = 0; i < vertex.getN(); i++) {
			dist.put(node.getVertex(), Integer.MIN_VALUE);
			node = node.getNext();
		}
		dist.put(start.getVertex(), 0);
		pred.put(start.getVertex(), null);
		
		while(!stack.isEmpty()) {
			node = stack.pop();
			
			if(dist.get(node.getVertex()) != Integer.MIN_VALUE) {
				EdgeListNode e = node.getEdgeList().getHead();
				for(int i = 0; i < node.getEdgeList().getN(); i++) {
					if(dist.get(e.getEdge()) < dist.get(node.getVertex()) + e.getDistance()) {
						dist.put(e.getEdge(), dist.get(node.getVertex()) + e.getDistance());
						pred.put(e.getEdge(), vertex.find(node.getVertex()));
					}
				}
			}
		}
		
		System.out.println("Longest Path\n");
		String info="";
		ListNode a = goal; 
		while(a != start) {
			System.out.println(a.getVertex());
			info += a.getVertex() + " >- ";
			a = pred.get(a.getVertex());
		}
		info += a.getVertex();
		
		System.out.println(new StringBuilder(info).reverse().toString());
	}
	
	public int getUcsPathCost() {
		return ucsPathCost;
	}
	
	public int getDfsPathCost() {
		return dfsPathCost;
	}
	
	public int getBfsPathCost() {
		return bfsPathCost;
	}
	
	public int getUcsNumberOfRooms() {
		return ucsNumberOfRooms;
	}
	
	public int getDfsNumberOfRooms() {
		return dfsNumberOfRooms;
	}

	public int getBfsNumberOfRooms() {
		return bfsNumberOfRooms;
	}
	
	public String getBfsRoomsVisited() {
		return bfsRoomsVisited;
	}

	public void setBfsRoomsVisited(String bfsRoomsVisited) {
		this.bfsRoomsVisited = bfsRoomsVisited;
	}

	public String getUcsRoomsVisited() {
		return ucsRoomsVisited;
	}

	public String getUcsPath() {
		return ucsPath;
	}

	public String getDfsRoomsVisited() {
		return dfsRoomsVisited;
	}

	public String getDfsPath() {
		return dfsPath;
	}

	public String getBfsPath() {
		return bfsPath;
	}

	public int returnMostCostEffective() {
		if(ucsPathCost <= dfsPathCost && ucsPathCost <= bfsPathCost) return 0;
		else if(dfsPathCost <= ucsPathCost && dfsPathCost <= bfsPathCost) return 1;
		else if(bfsPathCost <= ucsPathCost && bfsPathCost <= dfsPathCost) return 2;
		return 3;
	}
	
	
	public int returnMostRoomsEffective() {
		if(ucsNumberOfRooms <= dfsNumberOfRooms && ucsNumberOfRooms <= bfsNumberOfRooms) return 0;
		else if(dfsNumberOfRooms <= ucsNumberOfRooms && dfsNumberOfRooms <= bfsNumberOfRooms) return 1;
		else if(bfsNumberOfRooms <= ucsNumberOfRooms && bfsNumberOfRooms <= dfsNumberOfRooms) return 2;
		return 3;
	}
	
	public int returnLeastCostEffective() {
		if(ucsPathCost >= dfsPathCost && ucsPathCost >= bfsPathCost) return 0;
		else if(dfsPathCost >= ucsPathCost && dfsPathCost >= bfsPathCost) return 1;
		else if(bfsPathCost >= ucsPathCost && bfsPathCost >= dfsPathCost) return 2;
		return 3;
	}
	
	
	public int returnLeastRoomsEffective() {
		if(ucsNumberOfRooms >= dfsNumberOfRooms && ucsNumberOfRooms >= bfsNumberOfRooms) return 0;
		else if(dfsNumberOfRooms >= ucsNumberOfRooms && dfsNumberOfRooms >= bfsNumberOfRooms) return 1;
		else if(bfsNumberOfRooms >= ucsNumberOfRooms && bfsNumberOfRooms >= dfsNumberOfRooms) return 2;
		return 3;
	}
	
}
