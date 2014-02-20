// getSum(parentList, 1) to call the method below
// int[].class

private int getSum(Object list, int depth) {
        if (list == null)
            return 0;

        int sum = 0;
        if (list.getClass() == ArrayList.class) {
            for (Object nestedList : (ArrayList<Object>) list) {
                if (nestedList.getClass() == ArrayList.class)
                    sum += getSum(nestedList, depth + 1);
                else
                    sum += getSum(nestedList, depth);
            }
        } else {
            sum += (Integer) list * depth;
            //System.out.println("CurrentSum => " + sum + " integer => " + list// if need printing out
              //      + " Depth => " + depth);
        }
        return sum;
}
