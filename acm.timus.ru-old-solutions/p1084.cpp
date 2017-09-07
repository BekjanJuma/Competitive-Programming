#include<iostream>
#include <stdio.h>
#include <math.h>
using namespace std;
int main()
{	int N,R;
	cin >> N >> R;
	long double n,r,alpha,PI=3.1415926535897;
	n=N*1.0;
	r=R*1.0;
	if(sqrt(2.0)*n/2.0<r)
		printf("%0.3Lf\n",n*n);
	else if(2.0*r<=n)
		printf("%0.3Lf\n",PI*r*r);
	else
	{
		alpha=acos(n/(2.0*r));
		printf("%0.3Lf\n",r*r*(PI-4*alpha+2.0*sin(2.0*alpha)));
	}	
	return 0;
}