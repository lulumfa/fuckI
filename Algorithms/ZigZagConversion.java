public class ZigZagConversion {
    public String convert(String s, int nRows) {
            if (nRows <= 1 || s.length() < 2) return s;
        ArrayList<StringBuilder> sbs = new ArrayList<StringBuilder>();
        for (int k = 0; k < nRows; k++) {
            sbs.add(new StringBuilder());
        }
        int nCount = 2 * (nRows - 1);
        for (int i = 0; i < s.length(); i++) {
            sbs.get(nRows - 1 - Math.abs(nRows - 1 - (i % nCount))).append(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < nRows; j++) {
            sb.append(sbs.get(j));
        }
        return sb.toString();
    }
}
