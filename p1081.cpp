#include<iostream>
#include<vector>
#include<string>
using namespace std;

vector<int> F;
vector<int> P;
int n,k;
int calculate(int);

int main()
{
	F.resize(45);
	cin >> n >> k;
	F[0]=0;
	F[1]=2;
	F[2]=3;
	F[3]=5;
	for(int i=4;i<45;i++)
		F[i]=F[i-1]+F[i-2];
	calculate(0);
	int sz=P.size();
	if(sz>n) cout << -1;
	else 
	{
		for(int r=0;r<n-sz;r++)			cout << 0;
		for(int r=0;r<sz;r++)			cout << P[r];
	}
	cout << endl;
}


int calculate(int d)
{
	int i;
	for(i=1;i<45;i++)
		if(k<F[i]+1) break;
	k-=F[i-1];
	for(int j=1;j<d-i;j++)	P.push_back(0);
	if((k==1||k==2)&&i==1)
	{
		P.push_back(k-1);
		return 0;
	}
	else
	{
		P.push_back(1);
		return calculate(i);
	}
}












