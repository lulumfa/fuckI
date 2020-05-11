class Point {
    String value;
    int orginalIndex;

    public Point (String value, int orginalIndex) {
        this.value = value;
        this.orginalIndex = orginalIndex;
    }

    @Override
    public String toString() {
        return value + " (" + orginalIndex + ")";
    }
}
