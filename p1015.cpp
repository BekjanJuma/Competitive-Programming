#include<iostream>
#include<vector>
#include<string>
#define ud unsigned int
using namespace std;

class dice
{
	public:
		int samescheme(const dice&);
		void rotater()
		{	int tmp=left; 	left=forward;	forward=right;	right=backward;		backward=tmp;	}
		void rotated()	
		{	int tmp=top;	top=forward;	forward=bottom;	bottom=backward;	backward=tmp;	}
		int number;
		int left,right,top,forward,bottom,backward;
};

int main()
{
	vector<dice> V;
	vector<vector<dice> > T;
	int N;
	cin >> N;
	V.resize(N);
	for(ud i=0;i<V.size();i++)
	{
		int a=1;
		cin >> V[i].left >> V[i].right >> V[i].top >> V[i].forward >> V[i].bottom >> V[i].backward;
		V[i].number = i+1;
		for(ud j=0;j<T.size();j++)
		{
			if(T[j][0].samescheme(V[i]))	
			{	a=0;	T[j].push_back(V[i]);	break;	}
		}
		if(a)
		{
			vector<dice> x;
			x.push_back(V[i]);
			T.push_back(x);
		}
	}
	cout << T.size() << endl;
	for(ud i=0;i<T.size();i++)
	{
		for(ud j=0;j<T[i].size();j++)
			cout << T[i][j].number << " ";
		cout << endl;
	}
	return 0;
}

int dice::samescheme(const dice& D)
{
	int a=0;
	dice tmp=D;
	for(int i=0;i<4;i++)
	{
		for(int j=0;j<4;j++)
		{
			if(left==tmp.left)		{	a++;	break;	}
			else tmp.rotater();
		}
		if(a) break;
		tmp.rotated();
	}
	if(right!=tmp.right)		return 0;
	for(int i=0;i<4;i++)
	{
		if(top==tmp.top)	break;
		else tmp.rotated();
	}
	if(!(bottom==tmp.bottom && forward==tmp.forward))		return 0;
	else return 1;
}















