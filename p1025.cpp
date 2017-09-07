#include<iostream>
using namespace std;
void sort(int *N,int s);
int main()
{	int n,*N,sum=0;
	cin >> n;
	N=new int[n];
	for(int i=0;i<n;i++)
		cin >> N[i];
	sort(N,n);
	for(int i=0;i<(n/2+1);i++)
		sum+=(N[i]/2+1);
	cout << sum << endl;

	return 0;
}
void sort(int *N,int n)
{	for(int i=0;i<n;i++)
	{	for(int j=0;j<n;j++)
		{	if(N[i]<N[j])
			swap(N[i],N[j]);
		}
	}
}
void swap(int &x,int &y)
{	x=x+y;
	y=x-y;
	x=x-y;
}