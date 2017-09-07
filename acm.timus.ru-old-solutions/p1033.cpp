#include <iostream>
#include <map>
#include <utility>
using namespace std;


int G[44][44];
char L[40][40];
int num;
int n;

void explore( int x, int y)
{
//	cout << x << ":" << y << endl;
	if( x>0 && y>0 && x<=n && y<=n )
	{
		if(L[y][x] == '.' && G[y][x] == 0)
		{
			G[y][x] = 1;
			if(L[y-1][x] == '#')
			{
				num++;
			}
			if(L[y+1][x] == '#')
			{
				num++;
			}
			if(L[y][x-1] == '#')
			{
				num++;
			}
			if(L[y][x+1] == '#')
			{
				num++;
			}
			explore( x, y+1 );
			explore( x, y-1 );
			explore( x-1, y );
			explore( x+1, y );
		}	
	}
}

int main()
{

	num = 0;

	cin >> n;
	
	for( int i=0; i<44; i++ )
		for( int j=0; j<44; j++ )
			G[i][j] = 0;
	
	for( int i=1; i<=n; i++ )
		for( int j=1; j<=n; j++ )
			cin >> L[i][j];
	
	for( int i=0; i<=n+1; i++ )
		L[0][i] = '#';
	for( int i=0; i<=n+1; i++ )
		L[i][0] = '#';
	for( int i=0; i<=n+1; i++ )
		L[n+1][i] = '#';
	for( int i=0; i<=n+1; i++ )
		L[i][n+1] = '#';
	
	explore( 1, 1 );
	explore( n, n );
	
	cout << (num-4)*9 << endl;
	
	return 1;	
}