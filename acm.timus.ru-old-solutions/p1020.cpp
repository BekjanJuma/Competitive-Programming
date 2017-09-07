#include<iostream>
#include <iomanip>
#include<cmath>
using namespace std;
const double MY_PI=3.141592653589793238462643383279;
double d(double X0,double Y0,double X1,double Y1)
{	return ((X0-X1)*(X0-X1)+(Y0-Y1)*(Y0-Y1));
}
int main()
{	int N;
	double R,*X,*Y,dist=0;
	cin >> N >> R;
	X=new double[N+1];
	Y=new double[N+1];
	for(int i=0;i<N;i++)
		cin >> X[i] >> Y[i];
	for(int i=0;i<N-1;i++)
	{	dist+=sqrt((double)d(X[i],Y[i],X[i+1],Y[i+1]));
	}
	dist+=sqrt((double)d(X[N-1],Y[N-1],X[0],Y[0]))+2*MY_PI*R;
	cout << fixed << setprecision(2)<< dist << endl;
	return 0;
}