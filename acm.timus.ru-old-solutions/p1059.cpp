#include <iostream>
using namespace std;

int main()
{
	int N;
	cin >> N;
	cout << "0" << endl;
	for( int i=1; i<=N; i++ )
	{
		cout << "X" << endl;
		cout << "*" << endl;
		cout << i << endl;
		cout << "+" << endl;
	}	
	return 0;
}