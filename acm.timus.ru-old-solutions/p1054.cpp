#include <iostream>
using namespace std;

int mypower( int a, int n )
{
	int p = 1;
	for( int i=0; i<n; i++ )
		p = p*a;
	return p;		
}

int main()
{
	int N;
	int H[40];
	cin >> N;
	for( int i=0; i<N; i++) cin >> H[i];
	int from = 1, to = 2, temp = 3;
	int sum = 0;
	for( int i=N-1; i>=0; i-- )
	{
		if( H[i] == temp )
		{
			cout << -1 << endl;
			return 0;
		}
		else if( H[i] == from )
		{
			sum = sum + mypower( 2, i );
			int t = temp;
			temp = to;
			to = t;
		}
		else if( H[i] == to )
		{
			int t = temp;
			temp = from;
			from = t;
		}		
	}
	cout << mypower( 2, N ) - sum -1 << endl;
	return 1;
}