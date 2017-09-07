#include <iostream>
using namespace std;

int main()
{
	int N;
	long long int f[501];
	cin >> N;
	for(int i=0;i<=500;i++) f[i]=0;
	f[0]=1;
	for(int i=1;i<=N;i++)
		for(int j=N;j>=i;j--)
			f[j]+=f[j-i];
	cout << f[N]-1 << endl;	
}