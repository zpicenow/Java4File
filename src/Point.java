public class Point {
    private double x;
    private double y;
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass()!=Point.class)return false;
        return x == ((Point) obj).getX() && y == ((Point) obj).getY();
    }

    public void printPoint() {
        System.out.println("(" + x + "，" + y + ")");
    }

    Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

}
