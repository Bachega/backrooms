package project;

public class HeapNode {
	private ListNode vertex;
	private int cost;
	
	public HeapNode(ListNode vertex, int cost) {
		this.vertex = vertex;
		this.cost = cost;
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
	
}
