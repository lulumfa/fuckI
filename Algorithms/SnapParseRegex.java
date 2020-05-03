// my own way, O(n), space (n)
// my output，need to confirm with interviewer if we need to handle " " and \s differently
(AnyCharacter, ONCE)
(AnyCharacter, ONCE_OR_MORE)
(Character(','), ONCE)
(Character(' '), ONCE)
(Character('h'), ONCE)
(Character('e'), ONCE)
(Character('l'), ONCE)
(Character('l'), ONCE)
(Character('o'), ONCE)
(Character(' '), ONCE)
(Character('w'), ONCE)
(Character('o'), ONCE)
(Character('r'), ONCE)
(Character('l'), ONCE)
(Character('d'), ONCE)
(Character('!'), ONCE)
(SPACE_OR_TAB, ZERO_OR_MORE)

// https://docs.google.com/document/d/1DZw2kBvWuECzrnfRHnz0p0X_T4oYaqn51ex0nuZtUaI/edit#
package snap;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

public class SnapRegexParser {

  public static void main(String[] args) throws Exception {
    String input = "..+, hello world!\\s*";

    SnapRegexParser snapRegexParser = new SnapRegexParser();

    for (Tuple tuple : snapRegexParser.parse(input)) {
      System.out.println(tuple);
    }
  }


  public List<Tuple> parse(String input) throws Exception {
    List<Tuple> res = new ArrayList<>();

    Tuple tuple = null;

    for (int i = 0; i < input.length(); i++) {
      char cur = input.charAt(i);

      if (cur == '+' || cur == '*') {
        if (tuple == null) {
          throw new Exception("there is no valid Matcher before modifier");
        }
        tuple.modifier = new Modifier(cur);
      } else {
        if (tuple != null) res.add(tuple);
        tuple = new Tuple();


        if (cur == '\\') {
          if (i == input.length() -1) {
            throw new Exception("there is no valid Matcher before modifier");
          }
          tuple.matcherType = new MatcherType(input.substring(i, ++i + 1));
        } else {
          tuple.matcherType = new MatcherType(String.valueOf(cur));
        }
      }
      // add last tuple to the res list here since no other modifier or matcherType would be found
      if (i == input.length() -1) {
        res.add(tuple);
      }
    }

    return res;
  }
}

class Tuple {
  MatcherType matcherType;
  Modifier modifier;

  public Tuple() {
    this.modifier = new Modifier();
  }

  @Override
  public String toString() {
    return "(" + matcherType + ", " + modifier + ")";
  }
}

class MatcherType {

  String type;
  String output;
  boolean specialCharacter = true;

  public MatcherType(String type) {
    this.type = type;

    switch (type) {
      case ".":
        output = "AnyCharacter";
        break;
      case "\\s":
        output = "SPACE_OR_TAB";
        break;
      case "\\.":
        output = "PERIOD";
        break;
      case "\\":
        output = "SINGLE_BACKSLASH";
        break;
      default:
        specialCharacter = false;
        output = "Character('" + type + "')";
    }
  }

  @Override
  public String toString() {
    return output;
  }
}

class Modifier {
  char modifier;
  String output = "ONCE";

  public Modifier() {

  }

  public Modifier(char modifier) {
    this.modifier = modifier;

    if (modifier == '+') {
      output = "ONCE_OR_MORE";
    } else if (modifier == '*') {
      output = "ZERO_OR_MORE";
    }
  }

  @Override
  public String toString() {
    return output;
  }
}


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
