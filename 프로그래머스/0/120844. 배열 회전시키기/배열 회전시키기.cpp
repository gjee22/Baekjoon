#include <string>
#include <vector>
#include <deque>

using namespace std;

vector<int> solution(vector<int> numbers, string direction) {
    deque<int> nums;
    for (int i = 1; i < size(numbers) - 1; i++) {
        nums.emplace_back(numbers[i]);
    }
    if (direction.compare("right") == 0) {
        nums.emplace_front(numbers[0]);
        nums.emplace_front(numbers[size(numbers) - 1]);
    } else {
        nums.emplace_back(numbers[size(numbers) - 1]);
        nums.emplace_back(numbers[0]);
    }
    return vector<int>(nums.begin(), nums.end());
}