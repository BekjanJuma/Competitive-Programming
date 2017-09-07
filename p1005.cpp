#include<iostream>
#include<cmath>
#include<stdio.h>
using namespace std;

int Sum(int *,int size);
int binary(int *,int );
//int abs(int x);
int main()
{
	int p[21]={1,2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384,32768,65536,131702,262144,524288,1048576};
	int *W;
	int d=1000000,size = 0;
	cin >> size;
	W = new int[size];
	int s = Sum(W,size);
	for(int i=0; i<p[size]; i++)
	{	int tmp=abs(s-2*binary(W,i));
//		cout << "tmp = " << tmp << endl;
		if(tmp<d)d=tmp;
	}
	cout << d << endl;
	return 0;
}


int Sum(int *W,int size)
{
	int s=0;
	for(int i=0;i<size;i++)
	{	cin >> W[i]; s+=W[i];
	}
	return s;
}

int binary(int *W,int number) 
{
	int sum=0;
	for(int i=0;number!=0;i++)
	{	if(number%2)
		sum+=W[i];
		number=number/2;
	}
	return sum;
}
/*
int abs(int x)
{	if(x<0)return -x;
	else return x;
}*/