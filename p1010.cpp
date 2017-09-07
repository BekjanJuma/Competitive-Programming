#include<iostream>
#include<vector>
#include<cmath>
#define ud unsigned int
using namespace std;

int main()
{
	int N,j=1;
	double max=0,x1,x2,tmp1,tmp2;
	cin >> N >> x1 >> x2;
	max = fabs(x1-x2);
	for(int i=2;i<N;i++)
	{
		cin >> tmp2;
		x1=x2;
		x2=tmp2;
		tmp1 = fabs(x1-x2);
		if(max<tmp1+0.5)
		{
			max = tmp1;
			j=i;
		}
	}
	cout << j << " " << j+1 << endl;
	return 0;
}