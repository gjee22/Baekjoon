#include <vector>
#include <unordered_set>
using namespace std;

int solution(vector<int> nums)
{
    int answer = 0;
    unordered_set<int> set (nums.begin(), nums.end());
    return min(size(set), size(nums) / 2);
}