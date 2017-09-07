#include<iostream>
#include<string>
#include<vector>
using namespace std;

int main()
{
	int A[50000],B[50000];
	int first,second;
	cin >> first;
	for(int i=0;i<first;i++)
		cin >> A[i];
	cin >> second;
	for(int i=0;i<second;i++)
		cin >> B[i];
	for(int i=0;i<first;i++)
	{
		int low=0,high=second-1,mid;
		while(low<=high)
		{
			mid = (low+high)/2;
			if(A[i]+B[mid] == 10000)
			{
				cout << "YES\n";
				return 1;
			}
			if(A[i]+B[mid] < 10000)
				high = mid-1;
			if(A[i]+B[mid] > 10000)
				low = mid+1;
		}
	}
	cout << "NO\n";
	return 0;
}
