package project;

public class EdgeListNode {
	private String edge;
	private int distance;
	private EdgeListNode next;
	
	public EdgeListNode(String edge, int distance) {
		this.edge = edge;
		this.distance = distance;
		next = null;
	}
	
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public EdgeListNode getNext() {
		return next;
	}

	public void setNext(EdgeListNode next) {
		this.next = next;
	}

	public String getEdge() {
		return edge;
	}
	
	public void setEdge(String edge) {
		this.edge = edge;
	}
	
	public String toString() {
		return "Edge: " + edge + " | Distance: " + distance;
	}
	
}
