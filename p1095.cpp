#include<iostream>
#include<vector>
#include<string>
#define UI unsigned int
using namespace std;

int make_divisible(string&,int,int);
vector<string> P;
int res[6]={1,3,2,6,4,5};
vector<string> A;
void pushA();
int main()
{
	pushA();
	int n;
	cin >> n;
	for(int i=0;i<n;i++)
	{
		string B;
		cin >> B;
		int szz=B.size();
		A.push_back(B);
		int a=1,b=1,c=1,d=1;
		for(UI j=0;j<A[i].size();)
		{
			if(a&&(A[i][j]=='1'))			{	A[i].erase(A[i].begin()+j); a=0;	continue;	}
			if(b&&(A[i][j]=='2'))			{	A[i].erase(A[i].begin()+j); b=0;	continue;	}
			if(c&&(A[i][j]=='3'))			{	A[i].erase(A[i].begin()+j); c=0;	continue;	}
			if(d&&(A[i][j]=='4'))			{	A[i].erase(A[i].begin()+j);	d=0;	continue;	}
			j++;
		}
		B.clear();
		for(UI k=0;k<A[i].size();k++)
			if(A[i][k]!='0')
				B.push_back(A[i][k]);
		make_divisible(B,i,szz);	
	}
	for(int i=0;i<n;i++)
		cout << A[i] << endl;
	return 0;
}

int make_divisible(string& B,int t,int szz)
{
	int dv=0,k=B.size();
	B.append(P[0]);
//	cout << B << endl;
	if(szz-B.size()>0)
	{
		string tmp(szz-B.size(),'0');
		B.append(tmp);
	}
//	cout << B << endl;
	int sz=B.size();
	while(!dv)
	{
		for(int i=0;i<7;i++)
		{
			B.replace(B.begin()+k,B.begin()+k+4,P[i]);
	//		cout << "B[" << i << "]=" << B << endl;
			int sum=0;
			for(int j=0;j<sz;j++)
				sum+=((int)B[sz-j-1]-48)*res[j%6];
			if(sum%7==0)
			{	A[t]=B;	return 0;	}
		}
	}
	return 0;
}

void pushA()
{
	P.push_back("4123");	// 0
	P.push_back("2143");	// 1
	P.push_back("1234");	// 2
	P.push_back("2341");	// 3
	P.push_back("1243");	// 4
	P.push_back("1342");	// 5
	P.push_back("2134");	// 6
	
	




}
