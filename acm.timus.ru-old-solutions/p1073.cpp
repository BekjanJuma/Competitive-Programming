#include<iostream>
#include<math.h>
using namespace std;
bool issquare(int ,int);
bool check(int);
bool my2(int);
bool my3(int);
int number=1;
int main()
{
	int a;
	cin >> a;
	int min=4;
	for(int m=1;m<5;m++)
	{
		if(issquare(m,a))
			if(min>m)
				min=m;
	}
	if(issquare(min,a))
		cout << number << endl;
	return 0;
}
bool issquare(int m,int n)
{	
	if(m==1)
		return check(n);
	if(check(n))
		return true;
	for(int k=1;k<=(float)sqrt(n/(m*1.0));k++)
	{	if(issquare(m-1,n-k*k))
		{	number=m;
			return true;
		}
	}
	return false;
}
bool check(int n)
{	float x=(float)sqrt(n*1.0);
	if(n-x*x)
		return false;
	else return true;
}