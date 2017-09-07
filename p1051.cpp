#include<iostream>
#include<string>
#include<vector>
using namespace std;

int main()
{
	int m,n;
	cin >> m >> n;
	if(m==1||n==1)
	{
		cout << (m+n)/2 << endl;
		return 1;
	}
	if((m%3)*(n%3))
		cout << 1 << endl;
	else cout << 2 << endl;
	return 0;
}