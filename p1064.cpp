#include <iostream>
using namespace std;


#define MAXN 10000

int A[MAXN];
int N;

int BinarySearch(int x, int L1)
{
	int p, q, i, L;

	p = 0;   /* Left border of the search  */
	q = N-1; /* Right border of the search */
	L = 0;   /* Comparison counter         */
	while (p <= q) {
		i = (p + q) / 2;
		++L;
		if (A[i] == x) {
			if( L1 == L )
				return 1;
			else return 0;
		}
		if (x < A[i])
			q = i - 1;
		else
			p = i + 1;
	}
	return 0;
}


int main()
{
	int i,L;
	int B[MAXN+2];
	int X[MAXN+2][2];
	
	cin >> i >> L;
	
	for( int k=0; k<MAXN; k++ )
	{
		A[k] = k;
		B[k] = -5;
		X[k][0] = -5;
		X[k][1] = -5;
	}
	
	for( int j=0; j<=MAXN; j++ )
	{
		N = j;
		B[j] = BinarySearch( i, L );
	}
	
	int n=0;

	for( int k=0; k<=MAXN; k++ )
	{
		if( B[k] == 1 && X[n][1] == k-1 )
		{
			X[n][1] = k;
		}
		else if( B[k] == 1 && X[n][1] != k-1 )
		{
			n++;
			X[n][0] = k;
			X[n][1] = k;
		}
	}
	cout << n << endl;
	for( int k=1; k<=n; k++ )
	cout << X[k][0] << " " << X[k][1] << endl;
}