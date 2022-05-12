package project;

public class EdgeList {
	private EdgeListNode head;
	private EdgeListNode tail;
	int n;
	
	public EdgeList() {
		head = null;
		tail = null;
		n = 0;
	}
	
	public EdgeListNode getHead() {
		return head;
	}

	public void setHead(EdgeListNode head) {
		this.head = head;
	}

	public EdgeListNode getTail() {
		return tail;
	}

	public void setTail(EdgeListNode tail) {
		this.tail = tail;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	public void add(String edge, int distance) {
		if(head == null) {
			head = new EdgeListNode(edge, distance);
			tail = head;
		} else {
			tail.setNext(new EdgeListNode(edge, distance));
			tail = tail.getNext();
		}
		n++;
	}
	
	public void printList(String vertex) {
		if(n==0) {
			System.out.println("Vertex: " + vertex + " | Edges: None");
			return;
		}
		
		EdgeListNode node = head;
		for(int i = 0; i < n; i++) {
			System.out.println("Vertex: " + vertex + " | " + node.toString());
			node = node.getNext();
		}
	}
}
