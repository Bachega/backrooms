package project;

public class List {
	private ListNode head;
	private ListNode tail;
	int n;
	
	public List() {
		head = null;
		n = 0;
	}

	public ListNode getHead() {
		return head;
	}

	public void setHead(ListNode head) {
		this.head = head;
	}
	
	public ListNode getTail() {
		return tail;
	}

	public void setTail(ListNode tail) {
		this.tail = tail;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	public void add(String vertex, String edge, int distance) {
		if(head == null) {
			head = new ListNode(vertex, edge, distance);
			head.setNext(new ListNode(edge));
			tail = head.getNext();
			n+=2;
		} else {
			ListNode node = head;
			for(int i = 0; i < n; i++) {
				if(node.getVertex().equals(vertex)) {
					node.getEdgeList().add(edge, distance);	
					
					if(this.find(edge) == null) {
						tail.setNext(new ListNode(edge));
						tail = tail.getNext();
						n++;
					}
					
					return;
				}
				node = node.getNext();
			}			
			tail.setNext(new ListNode(vertex, edge, distance));
			tail = tail.getNext();
			
			if(this.find(edge) == null) {
				tail.setNext(new ListNode(edge));
				tail = tail.getNext();
				n++;
			}
			
			n++;
		}
	}
	
	public void recieve(ListNode node) {
		if(n==0) {
			head = new ListNode(node.getVertex());
			head.setEdgeList(new EdgeList());
			
			EdgeListNode e = node.getEdgeList().getHead();
			
			while(e != null) {
				head.getEdgeList().add(e.getEdge(), e.getDistance());
				e = e.getNext();
			}
			
			tail = head;
		} else {
			ListNode aux = new ListNode(node.getVertex());
			aux.setEdgeList(new EdgeList());
			
			EdgeListNode e = node.getEdgeList().getHead();
			
			while(e != null) {
				tail.getEdgeList().add(e.getEdge(), e.getDistance());
				e = e.getNext();
			}
			tail.setNext(aux);
			tail = tail.getNext();
		}
		n++;
	}
	
	public void printList() { 
		ListNode node = head;
		
		for(int i = 0; i < n; i++) {
			node.getEdgeList().printList(node.getVertex());
			node = node.getNext();
		}
	}
	
	public void selectMenu() {
		ListNode node = head;
		
		System.out.println("Vertices:");
		for(int i = 0; i < n; i++) {
			System.out.println(node.getVertex());
			node = node.getNext();
		}
	}
	
	public ListNode find(String key) {
		ListNode aux = head;
		
		while(aux != null) { 
			if(aux.getVertex().equals(key))
				return aux;
			aux = aux.getNext();
		}
		return null;
	}
	
}
