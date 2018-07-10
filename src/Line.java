public class Line {
    public TYPE getType() {
        return type;
    }

    enum TYPE {
        HORIZON,VERTICAL,LEAN
    }
    private double k = 99999;
    private double b;
    private TYPE type = TYPE.VERTICAL;

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    Line(Point p1, Point p2) {
        if (p1.equals(p2)) {

            try {
                throw new Exception("重合的点");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }


        }


        type = p1.getX() == p2.getX() ? TYPE.VERTICAL : (p1.getY() == p2.getY() ? TYPE.HORIZON : TYPE.LEAN);
        switch (type) {
            case LEAN:
                k = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
                b = p1.getY() - k * p1.getX();
                break;
            case VERTICAL:
                b = p1.getX();
                break;
            case HORIZON:
                b = p1.getY();
                break;

        }
    }

    public void printLine() {

        switch (type) {
            case HORIZON:
                System.out.println("直线: y=" + b);
                break;
            case VERTICAL:
                System.out.println("直线：x=" + b);
                break;
            case LEAN:
                System.out.println("直线：y=" + k + "x+" + b);
                break;
        }
    }


//    public static void main(String[] args) {
//        Point point = new Point(1, 3);
//        Point point2 = new Point(2, 4);
//
//        Line line = new Line(point, point2);
//        line.printLine();
//    }
}
