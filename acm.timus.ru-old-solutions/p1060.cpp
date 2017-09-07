#include<iostream>
#include<string>
#include<vector>
#include<cmath>
#define ud unsigned int
using namespace std;

class Flip
{
	public:
		Flip(){}
		void rn(const vector<string> &B) 
		{
			A.resize(B.size());
			for(ud i=0;i<B.size();i++)
				A[i]=B[i];
		}
		void print()
		{
			for(ud i=0;i<A.size();i++)
				cout << A[i] << endl;
		}
		int check();
		int flip(int x,int y);
		void subflip(int x,int y);
		int valid(int x,int y);
		vector<string> A;
};

int main()
{
	int min=1000000;
	vector<string> B(4);
	cin >> B[0] >> B[1] >> B[2] >> B[3];
	Flip F;
	F.rn(B);
	int k=int(pow(2.0,16.0));
	for(int i=0;i<k;i++)
	{
		int x=0,count=0,j=i;
		while(j)
		{
			if(j%2)
			{
				F.flip(x/4,x%4);
				count++;
			}
			j/=2;
			x++;
		}
		if(F.check())
			if(count<min)
				min=count;
		F.rn(B);
	}
	if(min>10000)
		cout << "Impossible\n";
	else cout << min << endl;
	return 0;
}


int Flip::valid(int x,int y)
{	return ((x>-1)&&(y>-1)&&(x<(int)A.size())&&(y<(int)A.size()));
}

int Flip::check()
{
	char c;
	if(!A.empty()) c=A[0][0];
	for(ud i=0;i<A.size();i++)
		for(ud j=0;j<A.size();j++)
			if(A[i][j]!=c)
				return 0;
	return 1;
}

void Flip::subflip(int x,int y)
{
	if(valid(x,y))
		if(A[x][y]=='w') A[x][y]='b';
		else A[x][y]='w';
}

int Flip::flip(int x,int y)
{
	if(!(valid(x,y))) return 0;
	subflip(x,y);
	subflip(x+1,y);
	subflip(x-1,y);
	subflip(x,y+1);
	subflip(x,y-1);
	return 1;
}



