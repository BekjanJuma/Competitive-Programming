#include<iostream>
#include<string>
#include<vector>
using namespace std;

int main()
{
	vector<int> A,B,C;
	int n,k;
	cin >> n >> k;
	B.push_back(k-1);
	int x = k*(k-1);
	A.push_back(x%10);
	if(x/10) A.push_back(x/10);
	C.resize(A.size());
	for(int i=2;i<n;i++)
	{
		int tmp = 0,carry = 0,sz = A.size();
		if(A.size()>B.size())B.push_back(0);
		for(int j=0;j<sz;j++)
		{
			C[j] = (A[j] + B[j] + carry)%10;
			carry = (A[j] + B[j] + carry)/10;
		}
		if(carry) C.push_back(carry);
		sz = C.size(),carry = 0;
		for(int j=0;j<sz;j++)
		{
			tmp = (C[j]*(k-1)+carry)/10;
			C[j] = (C[j]*(k-1)+carry)%10;
			carry = tmp;
		}
		if(carry) C.push_back(carry);
		B = A;
		A = C;
	}
	int sz = A.size();
	for(int i=0;i<sz;i++)
		cout << A[sz-i-1];
	cout << endl;
	return 0;
}
