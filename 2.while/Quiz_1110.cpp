//#include <iostream>
//using namespace std;
//
//int main() {
//	ios::sync_with_stdio(false);
//	cin.tie(NULL);
//	int N;
//	cin >> N;
//	int newNum = N;
//	int cycle = 0;
//	while (true) {
//		int a = newNum / 10;
//		int b = newNum % 10;
//		int c = (a + b) % 10;
//		newNum = 10 * b + c;
//		cycle++;
//		if (N == newNum) break;
//	}
//	cout << cycle << "\n";
//}