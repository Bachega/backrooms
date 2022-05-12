package project;

public class Path {
	private ListNode vertex;
	private int cost;
	private ListNode predecessor;
	
	public Path() {
		
	}
	
	public ListNode getVertex() {
		return vertex;
	}
	
	public void setVertex(ListNode vertex) {
		this.vertex = vertex;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public ListNode getPredecessor() {
		return predecessor;
	}
	
	public void setPredecessor(ListNode predecessor) {
		this.predecessor = predecessor;
	}
}
