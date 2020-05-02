#include <bits/stdc++.h>
using namespace std;


string check(string s) {
    if (s == ".") return "AnyCharacter";
    if (s == "\\.") return "Character('.')";
    if (s == "\\S") return "Character(' ')";
    if (s == "\\\\") return "Character('\\')";
    return "Character('" + s + ");";
}

int main()
{
    string p = "..+, hello world!\\s*";
    
    int n = p.length();
    int i = 0;
    string res = "";
    while(i < n) {
        if (p[i] == '\\') {
            if (i+1 < n && p[i+1] == '+'){
                string tmp = check(p.substr(i, 2));
                res += ("(" + tmp + ", ONE_OR_MORE), ");
                i +=3;
            } else if (i+1 < n && p[i+1] == '*') {
                string tmp = check(p.substr(i, 2));
                res += ("(" + tmp + ", ZERO+OR_MORE), ");
                i+=3;
            } else {
                string tmp = check(p.substr(i, 2));
                res += ("(" + tmp + ", ONCE), ");
                i+=2;
            }
        } else {
            if (i+1 < n && p[i+1] == '+'){
                string tmp = check(p.substr(i, 1));
                res += ("(" + tmp + ", ONE_OR_MORE), ");
                i+=2;
            } else if (i+1 < n && p[i+1] == '*') {
                string tmp = check(p.substr(i, 1));
                res += ("(" + tmp + ", ZERO+OR_MORE), ");
                i+=2;
            }
            else {
                string tmp = check(p.substr(i, 1));
                res += ("(" + tmp + ", ONCE), ");
                i+=1;
            }
        }  
    }
    cout << res << endl;
}
