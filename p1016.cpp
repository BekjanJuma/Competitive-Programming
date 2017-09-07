#include <iostream>
#include <vector>
#include <string>
using namespace std;

#define UI unsigned int

struct Cube
{
	Cube() // first created cube, root
	{
		sum = BOTTOM;
		
		visited = 0;
		prev = NULL;
	}
	Cube( Cube* cb ) // generated cubes, children
	{
		sum = cb->sum;
		BOTTOM = cb->BOTTOM;
		TOP = cb->TOP;
		FRONT = cb->FRONT;
		BACK = cb->BACK;
		LEFT = cb->LEFT;
		RIGHT = cb->RIGHT;
		sum = cb->sum;
		x = cb->x;
		y = cb->y;
		
		visited = 0;
		prev = cb;
	}
	int BOTTOM;
	int TOP;
	int FRONT;
	int BACK;
	int LEFT;
	int RIGHT;
	int x, y;
	int sum;
	int visited;
	Cube* prev;
	
	void move_front()
	{
		int tmp = BOTTOM;
		BOTTOM = FRONT;
		FRONT = TOP;
		TOP = BACK;
		BACK = tmp;
		
		sum = sum+BOTTOM;
		y++;
//		cout << "front: new_sum = " << sum << ", bottom = " << BOTTOM << endl;

	}
	void move_back()
	{
		int tmp = BOTTOM;
		BOTTOM = BACK;
		BACK = TOP;
		TOP = FRONT;
		FRONT = tmp;
		
		sum = sum+BOTTOM;
		y--;
//		cout << "back: new_sum = " << sum << ", bottom = " << BOTTOM << endl;
	}
	void move_left()
	{
		int tmp = BOTTOM;
		BOTTOM = LEFT;
		LEFT = TOP;
		TOP = RIGHT;
		RIGHT = tmp;
		
		sum = sum+BOTTOM;
		x--;
//		cout << "left: new_sum = " << sum << ", bottom = " << BOTTOM << endl;
	}
	void move_right()
	{
		int tmp = BOTTOM;
		BOTTOM = RIGHT;
		RIGHT = TOP;
		TOP = LEFT;
		LEFT = tmp;
		
		sum = sum+BOTTOM;
		x++;
//		cout << "right: new_sum = " << sum << ", bottom = " << BOTTOM << endl;
	}
	bool equivalent( Cube* cb)
	{
		if( x==cb->x && y==cb->y &&
			TOP == cb->TOP && BOTTOM == cb->BOTTOM && FRONT == cb->FRONT &&
			BACK == cb->BACK && RIGHT == cb->RIGHT && LEFT == cb->LEFT )
		return true;
		else return false;
	}

};

int find_heuristics( Cube* cb, int dx, int dy )
{
	Cube* tcb = new Cube(cb);
	int x = tcb->x;
	int y = tcb->y;
	if( x < dx )
	{
		for( int i=x; i<dx; i++ )
			tcb->move_right();
	}
	else if( x > dx)
	{
		for( int i = x; i>dx; i-- )
			tcb->move_left();
	}
	if( y < dy )
	{
		for( int i=y; i<dy; i++ )
			tcb->move_front();
	}
	else if( y > dy)
	{
		for( int i = y; i>dy; i-- )
			tcb->move_back();
	}
	return tcb->sum;

	
}

int check_vis( vector<Cube*> &lst, Cube* cb )
{
	for( UI i=0; i<lst.size(); i++ )
	{
		if( lst[i]->equivalent(cb) )
			return 1;
	}
	return 0;
}

Cube* dijkstra( Cube* cb, int dx, int dy, int h_value )
{
	vector<Cube*> vcb;
	vcb.push_back(cb);
	vector<Cube*> visited;
	
	
	while( !vcb.empty() )
	{
		int mini = -1;
		int min_sum = 2000000000;
		for( UI i=0; i<vcb.size(); i++ )
		{
			if( min_sum > vcb[i]->sum && check_vis(visited, vcb[i]) == 0 )
			{
				mini = i;
				min_sum = vcb[i]->sum;
			}
		}
		if( mini == -1 ) break;
		Cube* mcb = vcb[mini];
//		cout << "tmp_sum = "  << mcb->sum << ", vcb.size() = " << vcb.size() << endl;
//		mcb->visited = 1;
		visited.push_back( mcb );
		int x = mcb->x;
		int y = mcb->y;
		if( x == dx && y == dy ) // destination found
		{
			return mcb;
		}		
		
		if( x>0 )
		{
			Cube* tcb = new Cube(mcb);
			tcb->move_left();
			if(tcb->sum <= h_value)
				vcb.push_back(tcb);
		}
		if( x<7 )
		{
			Cube* tcb = new Cube(mcb);
			tcb->move_right();
			if(tcb->sum <= h_value)
				vcb.push_back(tcb);
		}
		if( y>0 )
		{
			Cube* tcb = new Cube(mcb);
			tcb->move_back();
			if(tcb->sum <= h_value)
				vcb.push_back(tcb);
		}
		if( y<7 )
		{
			Cube* tcb = new Cube(mcb);
			tcb->move_front();
			if(tcb->sum <= h_value)
				vcb.push_back(tcb);		}
	
	}
	cout << "NULL" << endl;
	return NULL;
	
}

void print( Cube* cb )
{
	Cube* tcb = cb;
	vector<string> path; 
	while( tcb )
	{
		string tmp;
		
		tmp.push_back((char)((tcb->x)+'a')); 
		tmp.push_back((char)((8-tcb->y)+'0'));
		path.push_back(tmp);
		tcb = tcb->prev;
	}
	for( int i=path.size()-1; i>=0; i-- )
		cout << " " << path[i];
	cout << endl;
}


int main()
{
	char start[4], end[4];
	int dx, dy;
	Cube* cb = new Cube();
	cin >> start >> end >> cb->FRONT >> cb->BACK >> cb->TOP >> cb->RIGHT >> cb->BOTTOM >> cb->LEFT;
	cb->sum = cb->BOTTOM;
	cb->x = (int)start[0]-'a';
	cb->y = '8' - (int)start[1];
	dx = (int)end[0]-'a';
	dy = '8' - (int)end[1];
	

	
	int h_value = find_heuristics( cb, dx, dy );
	Cube* dest = dijkstra( cb, dx, dy, h_value );
	
	if( dest )
		cout << dest->sum;
	print(dest);
	
	return 0;
}