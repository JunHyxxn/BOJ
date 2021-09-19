//#include <iostream>
//using namespace std;
//
//int main() {
//	ios::sync_with_stdio(false);
//	cin.tie(NULL);
//
//	int N;
//	cin >> N;
//	int* arr = new int[N];
//	for (int i = 0; i < N; i++) {
//		cin >> arr[i];
//	}
//
//	int min = arr[0];
//	int max = arr[0];
//
//	for (int i = 0; i < N; i++) {
//		if (arr[i] <= min) min = arr[i];
//		if (arr[i] >= max) max = arr[i];
//	}
//
//	delete[] arr;
//
//	cout << min << " " << max << "\n";
//	return 0;
//}