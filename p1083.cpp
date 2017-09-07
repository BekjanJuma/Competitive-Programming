#include<iostream>
#include<string>
using namespace std;
int main()
{	int N,size=0;
	long answer=1;
	string s;
	cin >> N;
	cin >> s;
	size=s.size();
	while(N>0)
	{	answer*=N;
		N-=size;
	}
	cout << answer << endl;
	return 0;
}