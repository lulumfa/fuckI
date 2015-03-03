// http://www.meetqun.com/thread-3384-1-1.html

//解法：O（N）
//顺着遍历一遍。每次记录每个字符串中的当前层的大小，进行对比。直接判断返回。总有不一样，否则就是完全相同，在最后返回相等

public class Solution {
  public int compareVersion(String version1, String version2) {) [- R+ ?5 E" e: u: S; r  r
          long a = 0, b =0;
          int v1len = version1.length(), v2len = version2.length();
+ [  w5 \1 ?( J8 G3 ?; Q        int i = 0, j = 0;
$ i8 |1 N" M3 V' Y# q        while (i < v1len || j < v2len) {
% P, ~4 M' d+ f* ?; I: O                a = 0; b =0;/ s: L# y  [- ?4 F
                while (i < v1len && version1.charAt(i) != '.') {
8 d  H- _/ _; N0 N9 B                        a = a * 10 + version1.charAt(i) - '0';7 w' m3 N' g( C6 g
                        ++i;
, ?% U% M4 N2 w' O5 B                }$ ?0 o" P' e5 s& W7 s2 C' h% g
                ++i;' a7 I  r* H1 f. N) a9 A
                while (j < v2len && version2.charAt(j) != '.') {
- H7 l' ?% g2 f6 Z                        b = b * 10 + version2.charAt(j) - '0';1 r. ^/ H/ N) a$ |
                        ++j;% x0 F( |" E+ g+ O
                }- J2 M2 u5 N0 Y7 w
                ++j;
" I3 y& }! I4 x) A7 N* ?5 i1 F                if (a > b) return 1;
! y' l# z* q0 \4 Z: Z) i9 M- k                if (a < b) return -1;
* F7 Q: q" G' z" i8 L' N        }
& \, w0 P$ L+ c9 H- |! Q            return 0;
6 I( ], M4 q2 }4 r- j    }
' ~. q! }, V; }7 s% {}
. M6 J& L5 [, I; u. X& o& |4 V) W. @7 g# m+ W# j) K
