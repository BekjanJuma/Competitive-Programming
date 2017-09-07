#include<iostream>
using namespace std;
long GCD(long a,long b);
int main()
{	long n,*N,*lcm,LCM;
	cin >> n;
	N=new long [n+1];
	lcm=new long [n+1];
	for(long i=1;i<=n;i++)
	{	cin >> N[i];
		lcm[i]=i;
	}
	for(long j=1;j<=n;j++)
	{
		long tmp=lcm[j];
		for(long i=1;i<=n;i++)
		{	lcm[j]=N[lcm[j]];
			if(lcm[j]==tmp)
			{	lcm[j]=i;
				break;
			}
		}
//		cout << lcm[j] << endl;
	}
	LCM=lcm[1];
	for(long i=2;i<n+1;i++)
	{	LCM=LCM/GCD(LCM,lcm[i])*lcm[i];
	}
	cout << LCM << endl;

	return 0;
}
long GCD(long a, long b)
{
	while(1)
	{	a=a%b;
		if(a==0)
			return b;
		b=b%a;
		if(b==0)
			return a;
	}
}