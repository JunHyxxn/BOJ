#include <iostream>
using namespace std;
int main() {
	int num;
	cin >> num;
	for (auto i = 0; i < num; i++) {
		for (auto j = 0; j < i + 1; j++) {
			cout << "*";
		}
		cout << endl;
	}
}