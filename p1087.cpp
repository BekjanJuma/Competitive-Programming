#include <iostream>
#include <cstdio>
#include <cstdlib>
using namespace std;

int compare (const void * a, const void * b)
{
  return ( *(int*)a - *(int*)b );
}


int main()
{
	int A[10010];
	int n, m, k[60];
	cin >> n >> m;
	for( int i=0; i<m; i++ )
		cin >> k[i];
	qsort( k, m, sizeof(int), compare);
	
	for( int i=0; i<=(n+2); i++ )
		A[i] = -1;
	
	A[k[0]] = 2;	
	for( int i=0; i<m; i++ )
		A[k[i]+1] = 1;
	
	for( int i=0; i<=(n+1); i++ )
	{
		if(A[i] == -1)
		{
			for( int j=0; j<m; j++ )
			{
				if( i>k[j] )
				{
					if( A[i-k[j]] == 2 )
						A[i] = 1;
				}
			}
			if( A[i] == -1 )
			A[i] = 2;
		}
	}
	cout << A[n] << endl;
	
	return 0;
}