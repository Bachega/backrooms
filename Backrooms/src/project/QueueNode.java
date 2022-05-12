package project;

public class QueueNode {
	private ListNode vertex;
	private QueueNode next;
	
	public QueueNode(ListNode vertex) {
		this.vertex = vertex;
		next = null;
	}

	public ListNode getVertex() {
		return vertex;
	}

	public void setVertex(ListNode vertex) {
		this.vertex = vertex;
	}

	public QueueNode getNext() {
		return next;
	}

	public void setNext(QueueNode next) {
		this.next = next;
	}
}
