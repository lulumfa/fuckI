#include <iostream>
#include <vector>
#include <stack>
using namespace std;
typedef pair<string, int> psi;
string f[] = {"+", "-", "*", "/"};
int target;
stack<int> numbers;
stack<psi> operators;
bool st[100];

bool calculate() {
    int b = numbers.top(); numbers.pop();    int a = numbers.top(); numbers.pop();
    string c = operators.top().first; operators.pop();
    if (c == "+") numbers.push(a+b);
    if (c == "-") numbers.push(a-b);
    if (c == "*") numbers.push(a*b);
    if (c == "/") {
        if ( b== 0) return false;
        else numbers.push(a/b);
    }
    return true;
}

bool check(vector<string> func) {
    int level = 0;
    for (int i = 0;i < func.size(); i++) {
        if (func[i][0] >= '0' && func[i][0] <= '9') {
            numbers.push(stoi(func[i]));
            continue;
        }
        
        if (func[i] == "(") { level++; continue;}
        if (func[i] == ")") { level--; continue;}
        
        int opval = 0;
        if (func[i] == "+" || func[i] == "-") opval = 1;
        if (func[i] == "*" || func[i] == "/") opval = 2;
        
        while (operators.size() != 0 && (level+opval) <= operators.top().second) {
            if (!calculate()) return false;
        }
        operators.push({func[i], level});
    }
    cout << numbers.top() << endl;
    return numbers.top() == target;
}

bool dfs(vector<int> num, vector<bool> st, vector<string> func, int level) {
    if (check(func)) return true;
    for (int i = 0; i < num.size(); i++) {
        if (st[i]) continue;
        func.push_back(to_string(num[i]));
        st[i] = true;
        for (int j = 0; j < 4; j++) {
            func.push_back(f[j]);
            dfs(num, st, func, level);
        }
        st[i] = false;
    }
    return false;

}



int main()
{
    vector<int> input= {1, 3, 4, 8, 12, 45, 50};
    int target = 380;
    int n = input.size();
    vector<bool> st(n ,false);
    
    vector<char> op;
    

    return 0;
}
