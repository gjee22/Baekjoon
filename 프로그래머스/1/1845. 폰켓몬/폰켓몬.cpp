#include <vector>
#include <unordered_map>
using namespace std;

int solution(vector<int> nums)
{
    int answer = 0;
    unordered_map<int, int> map;
    for (auto i : nums) {
        if (map.find(i) == map.end()) {
            map.emplace(i, 0);
        }
        map[i]++;
    }
    return min(size(map), size(nums) / 2);
}