#include<iostream>
#include<stdio.h>
using namespace std;
int main()
{
	int n,*A,*Sum;
	cin >> n;
	if(n==1)
	{	cin >> n;
		cout <<1 <<endl<< n << endl;
		return 1;
	}
	A=new int[n+1];
	Sum=new int[n+1];
	Sum[0]=0;
	for(int i=0;i<n;i++)
	{	scanf("%d",&A[i]);
		Sum[i+1]=(Sum[i]+A[i])%n;
		for(int j=0;j<i+1;j++)
		{//	cout << "S[i+1] = "<<Sum[i+1] << "\t" <<"S[i] = " <<Sum[j] << endl;
			if((Sum[i+1]-Sum[j])==0)
			{	cout << abs(i-j+1) << endl;
				for(int k=j;k<i+1;k++)
					cout << A[k] << endl;
				return 1;
			}
		}
	}
	return 0;
}