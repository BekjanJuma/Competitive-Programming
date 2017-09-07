#include<iostream>
#include<stdio.h>
using namespace std;
int main()
{	int n;
	double a0,an1,*c;
	cin >> n;
	c=new double[n+1];
	cin >> a0 >> an1;
	double sum=0;
	for(int i=1;i<=n;i++)
	{	
		cin >> c[i];
		sum+=(n-i+1)*c[i];
	}
	sum=(an1+n*a0-2*sum)/(n+1);
	printf("%0.2f\n",sum);	
	return 0;
}