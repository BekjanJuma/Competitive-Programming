#include<iostream>
using namespace std;
int main()
{
	int n,*P,tmp;
	P=new int[15001];
	P[1]=2;
	P[2]=3;
	for(int j=3;j<15001;j++)
	{
		tmp=P[j-1]+2;
		for(int i=1;i<j;i++)
		{
			if(tmp%P[i]==0)
			{
				i=1;
				tmp+=2;
			}
		}
		P[j]=tmp;
	}
	cin >> n;
	int *N=new int[n];
	for(int i=0;i<n;i++)
		cin >> N[i];
	for(int i=0;i<n;i++)
		cout << P[N[i]] << endl;
	return 0;
}