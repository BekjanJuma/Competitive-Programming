#include<iostream>
#include<vector>
#include<string>
#define UI unsigned int
using namespace std;
void reverse(vector<int>&);
vector<int> add(const vector<int>& A,const vector<int>& B)
{
	vector<int> tmp;
	UI m = max(A.size(),B.size());
	int carry = 0;
	for(UI i=0;i<m;i++)
	{
		int a,b;
		if(A.size()<=i)		a=0;
		else a = A[i];
		if(B.size()<=i)		b=0;
		else b = B[i];
		tmp.push_back((carry+a+b)%10);
		carry=(a+b+carry)/10;
	}
	if(carry)	tmp.push_back(carry);
	return tmp;
}

vector<int> mul(const vector<int>& A,const vector<int>& B)
{
	vector<int> tmp1;
	for(UI i=0;i<A.size();i++)
	{
		int carry=0;
		vector<int> tmp2;
		for(UI j=0;j<B.size();j++)
		{
			int x;
			x=(A[i]*B[j]+carry)%10;
			carry = (A[i]*B[j]+carry)/10;
			tmp2.push_back(x);
		}
		if(carry)	tmp2.push_back(carry);
		for(UI k=0;k<i;k++)	tmp2.insert(tmp2.begin(),0);
		tmp1=add(tmp1,tmp2);
	}
	return tmp1;
}

void reverse(vector<int>& A)
{
	int sz = A.size();
	for(int i=0;i<sz/2;i++)
	{
		int tmp = A[i];
		A[i]=A[sz-i-1];
		A[sz-i-1]=tmp;
	}		
}

void print(const vector<int>& A)
{
	int sz = A.size();
	for(int i=0;i<sz;i++)
		cout << A[sz-i-1];
	cout << endl;
}

int main()
{
	vector<vector<int> >A,B;
	int n,m;
	cin >> n >> m;
	if(m%2||(m/2)>9*n) {	cout << 0 << endl; return 1;	}
	else m/=2;
	int t=min(9,m);
	for(int i=0;i<=t;i++)
	{
		vector<int> u;
		u.push_back(1);
		A.push_back(u);
	}
	for(int j=t+1;j<=m;j++)
	{
		vector<int> u;
		u.push_back(0);
		A.push_back(u);
	}
	for(int r=2;r<=n;r++)// iterate over 	2,3,...,n-the elemnt we need
	{
		B.clear();
		for(int i=0;i<=m;i++)// finds out B[0],B[1],...,B[m]
		{
			int k=min(9,i);
			vector<int> sum;
			for(int j=0;j<=k;j++)// sum=A[i]+A[i-1]+...+A[i-k] gives r'th term with sum m
				sum=add(sum,A[i-j]);
			B.push_back(sum);// B is the r'th term
		}
		A.clear();
		for(UI f=0;f<B.size();f++)						// now A is the r'th term
			A.push_back(B[f]);
	}
	print(mul(A.back(),A.back()));

}

