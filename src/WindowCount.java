public enum WindowCount {

    ten,
    twenty,
    thirty;

    public static WindowCount getCount(int count) {
        switch (count) {
            case 0:
                return ten;
            case 1:
                return twenty;
            case 2:
                return thirty;
        }
        return null;
    }
}
