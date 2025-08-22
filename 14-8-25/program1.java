class Point {
    private int x;
    private int y;
    public void setX(int x){
        this.x = (x > 79 ? 79 : (x < 0 ? 0 : x));
    }
    public void setY(int y){
        this.y = (y > 79 ? 79 : (y < 0 ? 0 : y));
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}

class program1 {
    public static void main(String[] args) {
        int a, b;
        Point p1 = new Point();

        p1.setX(22);
        p1.setY(44);

        a = p1.getX();
        b = p1.getY();

        System.out.println("The value of a is " + a);
        System.out.println("The value of b is " + b);
    }
}
