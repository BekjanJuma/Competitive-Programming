#include <iostream>
#include <vector>
#include <string>
#include <cstdio>
#include <cctype>

using namespace std;

#define UI unsigned int


int search( const vector<string> &voc, char* s, UI sz )
{
	for( UI i=0; i<voc.size(); i++ )
	{
		if(voc[i].size() == sz)
		{
			int diff = 0;
			for( UI j=0; j<voc[i].size(); j++ )
			{
				if(voc[i][j] != s[j])
				diff++;
			}
			if( diff == 1 )
			{
				for( UI p = 0; p<sz; p++ )
					s[p] = voc[i][p];
				return 1;
			}
		}
	}
	return 0;
}


int main()
{
	vector<string> voc;
	
	while(1)
	{
		string s;
		cin >> s;
		if( s=="#" )
			break;
		voc.push_back(s);
	}
	int num = 0;
	UI sz = 0;
	char word[1000];
	
	// a new line after #
	getchar();
	
	while(1)
	{
		char c;
		c = getchar();
		if( c==EOF ) break;
		if( isalpha(c) == 0 )
		{
			if(sz)
			{
				num = num+search(voc, word, sz);
				word[sz] = '\0';
				cout << word;
				sz = 0;
			}
			cout << c;
			continue;
		}
		else
		{
			word[sz++] = c;
		}
	}
	cout << num << endl;
	
	return 0;
}