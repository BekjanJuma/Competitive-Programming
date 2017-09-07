import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;


class Triple{
	int a, b;
	String c;
	Triple(int _a, int _b, String _c){
		a = _a;
		b = _b;
		c = _c;
	}
	public void print(){
		System.out.println(a + " " + b);
	}
}

class SegmentTree{
	SegmentTree l, r;
	Triple s;
	public Triple getMinimum(){
		if(s == null) return null;
		if(l != null){
			if(l.s != null )
				return l.getMinimum();
		}
		if( r != null){
			if(r.s != null){
				Triple tmp = s;
				s = r.s;
				l = r.l;
				r = r.r;
				return tmp;
			}
		}
		Triple tmp = s;
		s = null;
		return tmp;
	}
	
	public void add(Triple t ){
		// if s is null then t to the segment tree
		if(s == null ){
			s = t;
			return;
		}
		else if(t.a>=s.b){ // completely on the right
			if(r == null) r = new SegmentTree();
				r.add(t);
		}
		else if (t.b<=s.a){ // completely on the left
			if(l == null) l = new SegmentTree();
			l.add(t);
		}
		else if(t.a>=s.a && t.b<=s.b ){
			return; // completely inside
		}
		else if ((t.a>=s.a && t.a<=s.b) && t.b>=s.b){ // left - inside, right - right
			t.a = s.b;
			if(r == null) r = new SegmentTree();
			r.add(t);
		}
		else if(t.a<s.a && (t.b<=s.b && t.b>=s.a)){ // right - inside, left - left
			t.b = s.a;
			if(l == null) l = new SegmentTree();
			l.add(t);
		}
		else if((t.a<s.a)&&(t.b>s.b)){ // completely covers
			Triple tl = new Triple(t.a, s.a, t.c);
			if(l == null) l = new SegmentTree();
			l.add(tl);
			
			Triple tr = new Triple(s.b, t.b, t.c);
			if(r == null) r = new SegmentTree();
			r.add(tr);
		}
	}
	
	public void printInorder(){
		if(s != null) s.print();
		if(l != null)
			l.printInorder();
		if(r != null)
			r.printInorder();
	}
	
}

public class LinePainting {
	public static int N;
	public static SegmentTree segments;
	public static Vector<Triple> sortedSegments;
	public static Stack<Triple> colorings;
	public static void main(String[] args) {
		
		segments = new SegmentTree();
		colorings = new Stack<Triple>();
		sortedSegments = new Vector<Triple>();
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		colorings.push(new Triple(0, 1000000000, "w"));
		for(int i=0; i<N; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			String c = sc.next();
			colorings.push(new Triple(a, b, c));
		}
		
		while( !colorings.empty() ){
			Triple t = colorings.pop();
			segments.add(t);
		}
		
//		segments.printInorder();
		
		// Put them to sorted to vector
		while(segments.s != null ){
			Triple tmp = segments.getMinimum();
			sortedSegments.add(new Triple(tmp.a, tmp.b, tmp.c));
		}
		
		// Now print sorted by left side
//		for(int i=0; i<sortedSegments.size(); i++)
//			sortedSegments.elementAt(i).print();
		
		// Merge neighbours of the same color
		for(int i=0; i<(sortedSegments.size()-1); i++){
			if(sortedSegments.elementAt(i).c.equals(sortedSegments.elementAt(i+1).c)){
				if(sortedSegments.elementAt(i).b == sortedSegments.elementAt(i+1).a){
					sortedSegments.elementAt(i).b = sortedSegments.elementAt(i+1).b;
					sortedSegments.remove(i+1);
					i--;
				}
			}
		}
		
//		System.out.println("Print segments after merging:");
//		for(int i=0; i<sortedSegments.size(); i++)
//			sortedSegments.elementAt(i).print();
		
		
		// Now find the maximum white interval
		int maxInterval = 0;
		Triple maxTriple = sortedSegments.elementAt(0);
		for(int i=0; i<sortedSegments.size(); i++){
			if(sortedSegments.elementAt(i).c.equals("w")){
				if(maxInterval < (sortedSegments.elementAt(i).b - sortedSegments.elementAt(i).a)){
					maxInterval = sortedSegments.elementAt(i).b - sortedSegments.elementAt(i).a;
					maxTriple = sortedSegments.elementAt(i);
				}
			}
		}
//		System.out.println("Max triple is:");
		maxTriple.print();
//		System.out.println("And interval = " + maxInterval);
		
	}

}