#include <iostream>
#include <cstdio>
using namespace std;

int main()
{
	int N;
	double x=0.0, y, A, B[1000];
	cin >> N >> A;

	if( N<3 ) return 0;

	for( int k=3; k<=N; k++ )
	{
		y = ( ((double)(k-2)) * (A-k+1) ) / ((double)(k-1));
		if( y>=x )	x = y;
	}

	B[1] = A;
	B[2] = x;
	for( int i=3; i<=N; i++ )
		B[i] = ((double)(i-1))*x - ((double)(i-2))*A + (double)((i-2)*(i-1));
	printf("%.2f\n", B[N]);
	return 0;
}