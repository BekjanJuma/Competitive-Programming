#include<iostream>
using namespace std;
/*
   int main()
   {	int res[36],tmp=0;
   for(int i=0;i<37;i++)
   res[i]=0;
   long n_ways=0;
   int A[4];
   for(int i=0;i<4;i++)
   {	A[i]=0;
   }
   for(int z=0;z<37;z++)
   {	while(A[0]<10)
   {	while(A[1]<10)
   {	while(A[2]<10)
   {	while(A[3]<10)
   {	tmp=A[0]+A[1]+A[2]+A[3];
   if(tmp==z)
   res[z]++;
   A[3]++;
   }
   A[3]=0;
   A[2]++;
   }
   A[2]=0;
   A[1]++;
   }
   A[1]=0;
   A[0]++;
   }
   A[0]=0;
   }
   for(int i=0;i<37;i++)
   {	n_ways+=res[i]*res[i];
   }
   cout << n_ways << endl;
   }
   */

int main()
{	int N;
	cin >> N;
	if(N==2)
		cout << 10 << endl;
	else if(N==4)
		cout << 670 << endl;
	else if(N==6)
		cout << 55252 << endl;
	else if(N==8)
		cout << 4816030 << endl;
	else;
	return 0;
}

