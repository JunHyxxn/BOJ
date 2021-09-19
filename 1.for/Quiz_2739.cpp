#include <iostream>
int main() {
	int num;
	std::cin >> num;
	for (auto i = 1; i < 10; i++)
		std::cout << num << " * " << i << " = " << num * i << std::endl;
}