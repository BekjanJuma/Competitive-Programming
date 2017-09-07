#include<iostream>
using namespace std;
int main()
{
	int n,sum = 0;
	cin >> n;
	if(n>0)
		sum = n*(n+1)/2;
	else if(n<0)
		sum = (2-n)*(n+1)/2;
	else if(n == 0)
		sum = 1;
	else;
	cout << sum << endl;
	return 0;
}