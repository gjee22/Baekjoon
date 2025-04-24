#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(vector<int> arr) 
{
    vector<int> answer = {};
    int prev = -1;
    for (auto n : arr) {
        if (n != prev) {
            answer.emplace_back(n);
        }
        prev = n;
    }
    return answer;
}