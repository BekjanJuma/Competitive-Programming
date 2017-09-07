#include<iostream>
#include<string>
#include<vector>
#include<list>
#define ud unsigned int
using namespace std;

vector <vector <int> > V;
vector<int> order,all;
int search(int x)
{
	for(ud i=0;i<all.size();i++)
		for(ud j=0;j<V[all[i]-1].size();j++)
			if(V[all[i]-1][j]==x)
				return 1;
	return 0;
}
int main()
{
	int n;
	cin >> n;
	for(int i=0;i<n;i++)
	{
		all.push_back(i+1);
		vector <int> tmp;
		int x;
		while(cin >> x)
		{
			if(!x) break;
			tmp.push_back(x);
		}
		V.push_back(tmp);
	}
	while(!all.empty())
	{
		for(ud i=0;i<all.size();i++)
			if(!search(all[i]))
			{
				order.push_back(all[i]);
				all.erase(all.begin()+i);
				break;
			}
	}
	for(ud i=0;i<order.size();i++)
		cout << order[i] << endl;
	return 0;
}