#include<iostream>
#include<string>
#include<vector>
#define ud unsigned int
using namespace std;

string tmp;
ud N,sum,a;
void process();
int more();
int les();
int equal();


int main()
{
	cin >> N;
	while(cin >> tmp)
		process();
	return 0;
}

inline void process()
{
	sum=0,a=0;
	for(ud i=0;i<tmp.size();i++)
		if(tmp[i]=='1'){	sum+=i+1;	a++;	}
	if	(tmp.size()==N)		equal(); 
	else if	(tmp.size()<N)	les();
	else	 				more();
	cout << tmp << endl;
}

inline int equal()
{
	if(!(sum%(N+1)))	return 1;
	for(ud i=0;i<tmp.size();i++)
	{
		if(tmp[i]=='1')
		{
			tmp[i] = '0';
			if(!((sum-i-1)%(N+1)))	return 1;
			tmp[i]='1';
		}
	}	
	return 0;
}

inline int more()
{
	for(ud i=0;i<tmp.size();i++)
	{
		char c = tmp[i];
		tmp.erase(tmp.begin()+i);
		if(c=='0')
		{	if(!((sum-a)%(N+1)))	return 1;}
		else if(c=='1')
		{	if(!((sum-a-i)%(N+1)))	return 1;}
		tmp.insert(tmp.begin()+i,c);
		if(c=='1')	a--;
	} 	
	return 0;
}

inline int les()
{
	for(ud i=0;i<=tmp.size();i++)
	{
		tmp.insert(tmp.begin()+i,'0');
		if(!((sum+a)%(N+1)))	return 1;
		tmp[i] = '1';
		if(!((sum+a+i+1)%(N+1)))	return 1;
		tmp.erase(tmp.begin()+i);
		if(tmp[i]=='1')	a--;
	}	return 0;
}


