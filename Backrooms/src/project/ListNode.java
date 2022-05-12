package project;

public class ListNode {
	private String vertex;
	private EdgeList edgeList;
	private ListNode next;
	
	public ListNode(String vertex, String edge, int distance) {
		this.vertex = vertex;
		edgeList = new EdgeList();
		edgeList.add(edge, distance);
		next = null;
	}
	
	public ListNode(String vertex) {
		this.vertex = vertex;
		edgeList = new EdgeList();
		next = null;
	}
	
	public String getVertex() {
		return vertex;
	}

	public void setVertex(String vertex) {
		this.vertex = vertex;
	}
	
	public EdgeList getEdgeList() {
		return edgeList;
	}
	
	public void setEdgeList(EdgeList edgeList) {
		this.edgeList = edgeList;
	}
	
	public ListNode getNext() {
		return next;
	}
	
	public void setNext(ListNode next) {
		this.next = next;
	}
}
