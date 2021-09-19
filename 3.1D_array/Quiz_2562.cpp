//#include <iostream>
//using namespace std;
//
//int main() {
//	ios::sync_with_stdio(false);
//	cin.tie(NULL);
//
//	int* arr = new int[10]();
//	int max;
//	int max_index;
//
//
//	for (int i = 0; i < 9; i++) {
//		cin >> arr[i];
//	}
//
//	for (int j = 1; j < 9; j++) {
//		if (arr[j - 1] > arr[j]) {
//			max = arr[j - 1];
//			max_index = j;
//		}
//		else {
//			max = arr[j];
//			max_index = j + 1;
//		}
//	}
//	delete[] arr;
//
//	cout << max << "\n" << max_index;
//}