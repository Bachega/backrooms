package project;

public class PriorityQueue {
	private int n;
	private HeapNode[] heap;
	
	public PriorityQueue(int n) {
		heap = new HeapNode[(n*n)];
		this.n = 0;
	}
		
	public void insert(ListNode v, int cost) {
		heap[n] = new HeapNode(v, cost);
		if(n!=0) {
			int f = n;
			HeapNode aux = null;
			while (f > 0 && (heap[f/2].getCost() > heap[f].getCost())) {
				aux = heap[f/2];
				heap[f/2] = heap[f];
				heap[f] = aux;
				f /= 2;
			}
		}
		n++;
	}
	
	public HeapNode pop() {
		if(n == 0) return null;
		
		HeapNode res = heap[0];
		heap[0] = heap[n-1];
		n--;
		
		HeapNode aux;
		int p = 0;
		int child;
		while(true) {
			if((2*p + 1 < n) && (heap[2*p + 1].getCost() <= heap[2*p + 2].getCost()))
				child = 2*p + 1;
			else if(2*p + 2 < n)
				child = 2*p + 2;
			else
				break;
			
			aux = heap[p];
			heap[p] = heap[child];
			heap[child] = aux;
			p = child;
		}
		
		return res;
	}
	
	public boolean isEmpty() {
		return (n == 0);
	}
	
	public void print() {
		for(int i = 0; i < n; i++)
			System.out.println("Vertex: " + heap[i].getVertex().getVertex() + " | Cost: " + heap[i].getCost());
		System.out.println("N: " + n);
	}
	
	//Each ListNode is a vertex
}
