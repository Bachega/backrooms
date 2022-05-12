package project;

public class Stack {
	private int n;
	private StackNode top;
	
	public Stack() { 
		n = 0;
		top = null;
	}
	
	public void add(ListNode node) {
		if(n==0) {
			top = new StackNode();
			top.setVertex(node);
		} else {
			StackNode aux = new StackNode();
			aux.setVertex(node);
			aux.setNext(top);
			top = aux;
		}
		n++;
	}
	
	public ListNode pop() {
		if(n<1) return null;
		StackNode aux = top;
		top = top.getNext();
		aux.setNext(null);
		n--;
		return aux.getVertex();
	}
	
	public boolean isEmpty() {
		return (n == 0);
	}
	
	public void print() {
		StackNode node = top;
		for(int i = 0; i < n; i++) {
			node.getVertex().getEdgeList().printList(node.getVertex().getVertex());;
			node = node.getNext();
		}
	}
}
