#include <string>
#include <vector>
#include <queue>

using namespace std;

struct Task {
    int start;
    int duration;
};

struct compare {
    bool operator()(struct Task a, struct Task b) {
        return a.start > b.start;
    }
};

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    priority_queue<Task, vector<Task>, compare> pq;
    for (int i = 0; i < size(progresses); i++) {
        int remaining = (100 - progresses[i]);
        Task t;
        t.start = i;
        t.duration = (remaining / speeds[i]) + (remaining % speeds[i] > 0 ? 1 : 0);
        pq.emplace(t);
    }
    int d = pq.top().duration;
    int i = -1;
    int count = 0;
    while (!pq.empty()) {
        Task cur = pq.top();
        pq.pop();        if (d < cur.duration) {
            answer.push_back(count);
            count = 1;
            d = cur.duration;
        } else {
            count++;
        }
    }
    answer.push_back(count);
    return answer;
}