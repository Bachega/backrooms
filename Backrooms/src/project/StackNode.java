package project;

public class StackNode {
	private ListNode vertex;
	private StackNode next;
	
	public StackNode() {
		vertex = null;
		next = null;
	}

	public ListNode getVertex() {
		return vertex;
	}

	public void setVertex(ListNode vertex) {
		this.vertex = vertex;
	}

	public StackNode getNext() {
		return next;
	}

	public void setNext(StackNode next) {
		this.next = next;
	}
}
