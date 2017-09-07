#include<iostream>
#include<string>
#include<vector>
#include<algorithm>
using namespace std;

int main()
{
	string rubbish;
	int N,K,x;
	cin >> N;
	vector<int> V(N);
	for(int i=0;i<N;i++)
		cin >> V[i];
	cin >> rubbish >> K;
	sort(V.begin(),V.end());
	for(int i=0;i<K;i++)
	{
		cin >> x;
		cout << V[x-1] << endl;
	}
}
