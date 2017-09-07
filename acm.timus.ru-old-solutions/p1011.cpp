#include<iostream>
using namespace std;
int main()
{
	long double  P,Q,tmp;
	long tmp1,N=1;
	cin >> P >> Q;
	P/=100;
	Q/=100;
	while(!((P*N<tmp1)&&(Q*N>tmp1+0.00000000001)))
	{	
		N++;
		tmp = P*N;
		tmp1 = int(tmp)+1;
	//	cout << P*N <<"\t"<< tmp1 << "\t"<<Q*N << endl;
	}
//	cout << P*N <<"\t"<< tmp1 << "\t"<<Q*N << endl;
	cout << N << endl;
	return 0;
}