#include <iostream>
#include <vector>
#include <functional>
#include <unordered_map>

using namespace std;


char O[4] = {'+', '-', '*', '/'};

void print(unordered_map<string, int>& mp) {
   for (auto v : mp) {
      cout << v.first << ":" << v.second << endl;
   }
   cout << endl;
}

void print(vector<string>& arr) {
   for (auto v : arr) {
      cout << v << endl;
   }
   cout << endl;
}

vector<string> solve(vector<int> arr, int t) {
   vector<string> res;
   int n = arr.size();
   function<unordered_map<string, int>(int, int)> dfs = [&](auto l, auto u) {
      unordered_map<string, int> mp;

      if (l == u) {
         mp[to_string(arr[l])] = arr[l];
         return mp;
      }

      int k = l+(u-l) / 2;
      auto ls = dfs(l, k), rs = dfs(k+1, u);
      for (auto left : ls) mp[left.first] = left.second;
      for (auto right : rs) mp[right.first] = right.second;
      for (auto left : ls) {
         for (auto right : rs) {
            for (int d = 0; d < 4; d++) {
               auto o = O[d];
               string s = '(' + left.first + o + right.first + ')';
               if (o == '+') mp[s] = left.second + right.second;
               else if (o == '-') mp[s] = left.second - right.second;
               else if (o == '*') mp[s] = left.second * right.second;
               else if (o == '/') if (right.second) mp[s] = left.second / right.second;

            }
         }
      }
      return mp;
   };

   
   auto tmp = dfs(0, n-1);
   for (auto& e : tmp) {
      if (e.second == t) res.push_back(e.first);
   }
   return res;
}

int main(int argc, char *argv[]){
   // auto res = solve({1, 3, 4, 8, 12, 45, 50}, 380);
   auto res = solve({0, 1, 5, 2}, 3);
   cout << "results" << endl;
   print(res);
   return 0;
}


(1-2):-1
(1/(5*2)):0
(1-(5*2)):-9
(1/(5-2)):0
(1/5):0
(1-(5-2)):-2
(1+(5-2)):4
(1-(5/2)):-1
(0/2):0
(0+2):2
(0+5):5
(0+(5+2)):7
(0/(5*2)):0
(0*(5*2)):0
(0-(5*2)):-10
(0/(5-2)):0
(0-(5-2)):-3
(0-5):-5
(0+(5-2)):3
(0/(5/2)):0
(0-(5/2)):-2
(0+(5/2)):2
((0*1)+2):2
((0*1)-5):-5
((0*1)+5):5
((0*1)/(5+2)):0
((0*1)*(5+2)):0
((0*1)-(5+2)):-7
((0*1)*(5*2)):0
(0*(5-2)):0
((0*1)-(5*2)):-10
(1/2):0
(0*2):0
((0*1)+(5*2)):10
((0*1)*(5-2)):0
(0*5):0
((0*1)-(5-2)):-3
((0*1)+(5-2)):3
((0*1)*(5/2)):0
((0*1)+(5/2)):2
((0+1)/2):0
((0*1)*5):0
((0+1)-2):-1
(0+(5*2)):10
((0+1)+2):3
((0+1)/5):0
((0+1)*5):5
((0/1)/(5+2)):0
((0-1)*2):-2
((0/1)*2):0
((0/1)/(5*2)):0
(1+2):3
((0+1)+(5/2)):3
((0/1)-(5*2)):-10
0:0
(1+(5+2)):8
((0/1)+(5*2)):10
(0-2):-2
((0/1)-2):-2
(1*(5/2)):2
((0/1)*(5/2)):0
(1*(5-2)):3
(5+2):7
((0/1)/(5-2)):0
(1+(5/2)):3
((0/1)-(5+2)):-7
((0/1)*(5-2)):0
((0/1)/5):0
((0/1)+(5-2)):3
1:1
((0/1)/2):0
((0/1)+2):2
(1-(5+2)):-6
((0*1)/5):0
((0/1)+(5/2)):2
(1*(5*2)):10
((0/1)*(5+2)):0
((0-1)*(5/2)):-2
(0-(5+2)):-7
((0/1)*5):0
5:5
(0-1):-1
((0+1)+5):6
(1*(5+2)):7
(0+1):1
((0/1)/(5/2)):0
((0-1)/(5*2)):0
((0+1)/(5+2)):0
(0/1):0
(0/5):0
((0/1)+5):5
((0+1)+(5-2)):4
((0-1)/(5-2)):0
((0/1)-(5-2)):-3
2:2
(5*2):10
((0/1)*(5*2)):0
((0*1)/(5/2)):0
((0/1)-(5/2)):-2
((0-1)+(5/2)):1
((0+1)*2):2
((0-1)-(5/2)):-3
((0-1)-2):-3
((0-1)/(5/2)):0
((0*1)/(5*2)):0
(5/2):2
((0-1)+(5-2)):2
(1+(5*2)):11
((0-1)+5):4
(1+5):6
((0*1)+(5+2)):7
((0-1)-(5-2)):-4
((0*1)/2):0
   
   results
(((1+3)-(4/8))*(45+50))
(((1-3)-8)*(12-50))
(((1/3)-(4-8))*(45+50))
((1*4)*(45+50))
(((1+3)+(4/8))*(45+50))
(4*(45+50))
(((1/3)+4)*(45+50))
((1+3)*(45+50))
