package project;

public class Queue {
	private int n;
	private QueueNode head;
	private QueueNode tail;
	
	public Queue() {
		n = 0;
		head = null;
		tail = null;
	}
	
	public void insert(ListNode v) {
		QueueNode q = new QueueNode(v);
		if(n == 0) {
			head = q;
			tail = head;
		} else {
			tail.setNext(q);
			tail = tail.getNext();
		}
		n++;
	}
	
	public QueueNode pop() {
		if(n == 0) return null;
		
		QueueNode q = head;
		head = head.getNext();
		
		if(n == 1) tail = null;
		
		n--;
		return q;
	}
	
	public boolean isEmpty() {
		return (n==0);
	}
	
	public void print() {
		QueueNode node = head;
		for(int i = 0; i < n; i++) {
			if(i != (n-1))
				System.out.print(node.getVertex().getVertex() + " -> ");
			else
				System.out.print(node.getVertex().getVertex());
			
			node = node.getNext();
		}
		System.out.println();
	}
}
