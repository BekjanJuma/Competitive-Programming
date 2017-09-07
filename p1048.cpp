#include<stdio.h>
#include<stdlib.h>
int main()
{
	int *A,*B,*R,i,n,tmp,carry = 0;
	scanf("%d",&n);
	A = (int *)malloc(n*sizeof(int));
	B = (int *)malloc(n*sizeof(int));
	R = (int *)malloc(n*sizeof(int));
	for(i=0;i<n;i++)
		scanf("%d %d",&A[i],&B[i]);
	for(i=0;i<n;i++)
	{
		tmp = A[n-i-1]+B[n-i-1];
		R[i] = (tmp+carry)%10;
		carry = (tmp+carry)/10;
	}
	if(carry) printf("%d",carry);
	for(i=0;i<n;i++)
		printf("%d", R[n-i-1]);
		printf("\n");

return 0;
}