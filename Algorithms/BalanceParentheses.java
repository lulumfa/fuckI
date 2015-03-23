// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=125084&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26sortid%3D311

public class BalanceUnbalancedParentheses {
    public static String balance(String str) {
        if (str == null || str.length() == 0) return "";
        Stack<Integer> stack = new Stack<Integer>();
        int top = 0, prev = 0;
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    top = 0;
                } else {
                    top = stack.pop();
                    if (top > prev) {
                        res += str.substring(top, i + 1);
                    } else {
                        res = str.substring(top, i + 1);
                    }-google 1point3acres
                    prev = top;
                }
            }
        }. more info on 1point3acres.com
        return res;. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
    }.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�

    public static void main(String[] args) {
        BalanceUnbalancedParentheses sol = new BalanceUnbalancedParentheses();
        System.out.println(sol.balance(")()"));
        System.out.println(sol.balance(")(())("));
        System.out.println(sol.balance("()"));. 1point 3acres 璁哄潧
        System.out.println(sol.balance("(((((("));
        System.out.println(sol.balance(")("));.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
        System.out.println(sol.balance("())()"));
        System.out.println(sol.balance("())())))()()"));
    }
}
