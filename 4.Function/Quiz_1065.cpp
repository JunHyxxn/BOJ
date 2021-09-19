//#include <iostream>
//using namespace std;
//int find(int num) {
//	int result = 0;
//
//	for (int i = 1; i <= num; i++) {
//		int fourth = i / 1000;
//		i = i % 1000;
//	
//		int third = i / 100;
//		i = i % 100;
//
//		int second = i / 100;
//		i = i % 10;
//
//		int first = i;
//
//		if ((first - second) == (second - third) == (third - fourth)) {
//			result++;
//		}
//	}
//	return result;
//}
//int main() {
//	ios::sync_with_stdio(false);
//	cin.tie(NULL);
//
//	int N;
//	cin >> N;
//
//	cout << find(N);
//	return 0;
//}