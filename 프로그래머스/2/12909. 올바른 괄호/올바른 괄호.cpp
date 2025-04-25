#include <string>
#include <stack>

using namespace std;

bool solution(string s)
{
    stack<int> stack;
    for (auto c : s) {
        if (c == '(') {
            stack.emplace(c);
        }
        else {
            if (stack.empty()) return false;
            stack.pop();
            
        } 
    }
    return stack.empty();
}