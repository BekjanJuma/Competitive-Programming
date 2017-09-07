import java.io.InputStream;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;


class Tree {
	Tree l, r;
	int i, w;
	Tree findChild( int _i){
		Tree ll=null;
		if(i == _i) return this;
		if(l != null) ll = l.findChild(_i);
		if(ll!= null) return ll;
		if(r != null) return r.findChild(_i);
		return null;
	}
	
	void addChild( int u, int v, int w ){
		Tree t;
		t = findChild(u);
		if( t != null ){
			Tree child = new Tree();
			child.i = v;
			child.w = w;
			if(t.l == null )	t.l = child;
			else t.r = child;
			return;
		}
		t = findChild(v);
		if( t != null ){
			Tree child = new Tree();
			child.i = u;
			child.w = w;
			if(t.l == null )	t.l = child;
			else t.r = child;
			return;
		}
		if( t == null ){
			i = u;
			l = new Tree();
			l.i = v;
			l.w = w;
		}
	}
	
	void printPreOrder(){
		System.out.println(i + ":" + w);
		if(l != null)	l.printPreOrder();
		if(r != null)	r.printPreOrder();
	}
	
	void printInOrder(){
		if(l != null)	l.printInOrder();
		System.out.println(i + ":" + w);
		if(r != null)	r.printInOrder();
	}

	void printPostOrder(){
		if(l != null)	l.printPostOrder();
		if(r != null)	r.printPostOrder();
		System.out.println(i + ":" + w);
	}
	
}

class Triple{
	int u, v, w;
	public void init(int _u, int _v, int _w){
		u = _u;
		v = _v;
		w = _w;
	}
	public void print(){
		System.out.println(u + " " + v + " " + w);
	}
}


public class AppleTree {
	
	private static int N;
	private static int Q;
	public static int Apples[][];
	public static Triple triples[];
	
	public static Vector<Integer> usedTripleIndexes;
	public static Stack<Integer> stack;
	
	
	public static void main(String[] args) {
	
		// Read input and create a tree
		Scanner sc = new Scanner( System.in);
		N = sc.nextInt();
		Q = sc.nextInt();
		triples = new Triple[N+1];
		usedTripleIndexes = new Vector<Integer>();
		stack = new Stack<Integer>();
		for(int i=0; i<(N+1); i++) triples[i] = new Triple();
		
		int u, v, w;
		Tree t = new Tree(); // Root of a tree
		for( int i=0; i<(N-1); i++){
			u = sc.nextInt();
			v = sc.nextInt();
			w = sc.nextInt();
			triples[i].init(u, v, w);
		}
		
		stack.push(1);
		
		while(stack.empty() != true){
			int currentInteger = stack.pop();
//			System.out.println(currentInteger);
			for(int i=0; i<(N-1); i++){
				if((triples[i].u == currentInteger) && (usedTripleIndexes.contains(i) == false)){
					t.addChild(currentInteger, triples[i].v, triples[i].w);
					usedTripleIndexes.add(i);
					stack.push(triples[i].v);
				}
				if((triples[i].v == currentInteger) && (usedTripleIndexes.contains(i) == false)){
					t.addChild(currentInteger, triples[i].u, triples[i].w);
					usedTripleIndexes.add(i);
					stack.push(triples[i].u);
				}
			}
		}
		
		
		
		
//		System.out.println("Preorder");
//		t.printPreOrder();
		
		// Initializations
		Apples = new int[Q+1][N+1];
		for(int j=0; j<(Q+1); j++) for (int i=0; i<(N+1); i++) Apples[j][i] = 0;
		
		// Base case Q = 1
		for(int i=1; i<=N; i++){
			Tree tt = t;
			int mMax = 0;
			tt = tt.findChild(i);
			if(tt.l != null) mMax = tt.l.w;
			if(tt.r != null){
				if(tt.r.w > mMax) mMax = tt.r.w;
			}
			Apples[1][i] = mMax;
			
		}
		
//		for(int i=1; i<=N; i++){
//			System.out.println(i + "---" + Apples[1][i]);
//			
//		}
		
		// General Case
		for(int j=2; j<=Q; j++){
			for(int i=1; i<=N; i++){
				Tree tt = t;
				int li = 0, ri = 0, lw = 0, rw = 0, s1 = 0, s2 = 0, s3 = 0;
				int myMax = 0;
				tt = tt.findChild(i);
				if(tt.l != null){
					li = tt.l.i;
					lw = tt.l.w;
					s1 = Apples[j-1][li] + lw;
				}
				if(tt.r != null){
					ri = tt.r.i;
					rw = tt.r.w;
					s2 = Apples[j-1][ri] + rw;
				}
				myMax = s1;
				if(myMax<s2) myMax = s2;
				for(int k=j-2; k>=0; k--){
					s3 = Apples[k][li] + Apples[j-k-2][ri] + lw + rw;
					if(s3 > myMax) myMax = s3;
				}
				Apples[j][i] = myMax;
			}
		}
		
		System.out.println( Apples[Q][t.i] );
		
//		for(int j=1; j<=Q; j++){
//			System.out.println( "Q = " + j );
//			for(int i=1; i<=N; i++){
//				System.out.print( i + "--" + Apples[j][i] + ", " );
//			}
//			System.out.println();
//		}
		
	}

}








