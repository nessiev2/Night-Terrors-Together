public class Thing {
    private int x;
    private int y;
    private int width;
    private int height;

    public int getX() {
        return x;
    }
    public int getY() { return y; }

    public int getWidth() {
        return width;
    }
    public int getHeight() { return height; }

    public void changeX(int x) { this.x += x; }
    public void changeY(int y) { this.y += y; }

    public void resetX(int x) { this.x = x; }
    public void resetY(int y) { this.y = y; }


    public Thing(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
