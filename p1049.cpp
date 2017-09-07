#include <iostream>
using namespace std;
int main()
{	int P=1;
	int A[10];
	int primes1[25]={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
	int number1[25];
	int primes2[10];
	int number2[10];
	for(int i=0;i<25;i++)
		number1[i]=0;
	for(int i=0;i<10;i++)
	{	primes2[i]=1;
		number2[i]=0;
		cin >> A[i];
	}
	int j=0;
	for(int i=0;i<10;i++)
	{	j=0;
		while(A[i]!=1)
		{	if(j==25)
			{	primes2[i]=A[i];	number2[i]=1;	break;}
			if(A[i]%primes1[j]==0)
			{	number1[j]++;
				A[i]/=primes1[j];
			}
			else j++;
		}
	}
	for(int i=0;i<10;i++)
	{	for(int j=0;j<i;j++)
		if(primes2[i]==primes2[j]&&primes2[i]!=1)
		{	primes2[i]=1;
			number2[i]=0;
			number2[j]++;
		}
	}
	for(int i=0;i<25;i++)
		P*=(number1[i]+1);
	for(int i=0;i<10;i++)
		P*=(number2[i]+1);
	cout << P%10 << endl;
	return 0;
}