#include<iostream>
#include<vector>
using namespace std;

int GCD(int x,int y)
{
	while(1)
	{
		if(x==0)
			return y;
		if(y==0)
			return x;
		if(x>y)
			x%=y;
		else y%=x;
	}
}
int main()
{
	vector<int> A;
	int n;
	cin >> n;
	A.resize(n);
	for(int i=0;i<n;i++)
		cin >> A[i];
	if(n==1)
		cout << A[0] << endl;
	else
	{
		int gcd = GCD(A[0],A[1]);
		for(int i=2;i<n;i++)
			gcd = GCD(gcd,A[i]);
		cout << gcd << endl;
	}
	return 0;
}