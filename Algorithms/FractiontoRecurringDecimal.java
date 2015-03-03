// http://www.meetqun.com/thread-3388-1-1.html

public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {3 G* U2 [) o9 R# Z3 X, ?( h1 D  ]
        if (numerator == 0) return new String("0");9 @6 M$ a! N( w
        boolean flag = (numerator < 0)^(denominator < 0);4 K$ h- `2 B  f' Z6 }/ ]. L6 y( D
        long Numerator = Math.abs((long)numerator);//转换成long，如果刚好是INT_MIN,就出现溢出了。
            long Denominator = Math.abs((long)denominator);
            StringBuffer res = new StringBuffer();0 F' H1 m/ D5 [) p( n
            if (flag == true) res.append('-');7 ^6 r2 ~4 C+ J( `5 K/ ^* O
            res.append(String.valueOf((Numerator / Denominator)));
            Numerator = Numerator % Denominator;
            if (Numerator == 0) return res.toString();2 \& v. \5 d" O4 k' A
            res.append('.');8 c6 G/ f; q5 C
            HashMap<Long, Integer> map = new HashMap<Long, Integer>();
            for (int i = res.length(); Numerator != 0; ++i) {
                    if (map.get(Numerator) != null) break;* e8 X% P1 k3 s. o1 R4 ~7 M* ?
                    map.put(Numerator, i);- ~7 K6 h- X& S( D1 u
                    Numerator *= 10;
                res.append(String.valueOf((Numerator / Denominator)));- A1 R5 w& d/ X
                Numerator %= Denominator;
            }) C) ~1 q" L2 w
            
            if (Numerator == 0) return res.toString();
            res.insert(map.get(Numerator),"(");
            res.append(')');6 i2 X0 b) l! G: x& K* `3 E3 x) j$ ^0 M
            return res.toString();
    }
}: F; V, U1 e# p9 X3 l9 F* r" f# m
