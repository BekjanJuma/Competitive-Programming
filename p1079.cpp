#include<iostream>
using namespace std;
int main()
{	int n,A[100000];
	int max=0;
	for(int i=0;i<100000;i++)
	{
		A[0]=0;
		A[1]=1;
		if(i%2==0)
			A[i]=A[i/2];
		else
		{	A[i]=A[(i-1)/2]+A[(i-1)/2+1];
		}
	}
	while(1)
	{
		cin >> n;
		if(!n)
			break;
		max=0;
		for(int i=0;i<=n;i++)
		{	int tmp=A[i];
			if(tmp>max)
				max=tmp;
		}
		cout << max << endl;
	}
	return 0;
}