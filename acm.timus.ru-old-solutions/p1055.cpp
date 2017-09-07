#include<iostream>
#include<cmath>
#include<string>
#include<vector>
#define SIZE 100000
using namespace std;

int A[SIZE];
void generate();

int main()
{
	int power[6000];
	int N,M,count=0;
	for(int i=0;i<6000;i++)	power[i]=0;
	generate();
	vector<int> B;
	for(int i=0;i<SIZE;i++)
		if(A[i]) B.push_back(A[i]);
	cin >> N >> M;
	for(int i=0;i<6000;i++)	
	{
		int k=N/B[i];
		for(int j=0;k;j++,k/=B[i])
			power[i]+=k;
		k=M/B[i];
		for(int j=0;k;j++,k/=B[i])
			power[i]-=k;
		k=(N-M)/B[i];
		for(int j=0;k;j++,k/=B[i])
			power[i]-=k;
	}
	for(int i=0;i<6000;i++)
		if(power[i])	count++;
	cout << count << endl;

}

void generate()
{
	A[0]=2;
	for(int i=1;i<SIZE;i++)
		A[i]=2*i+1;
	int sz = int(sqrt(float(SIZE)));
	for(int i=0;i<sz;i++)
		if(A[i])
			for(int j=i+1;j<SIZE;j++)
				if(A[j]%A[i]==0)
					A[j]=0;
}