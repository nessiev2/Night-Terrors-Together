public class ChalkBoard extends Thing{
    private static final int BOARD_WIDTH = Main.SCREEN_WIDTH, BOARD_HEIGHT = Main.SCREEN_WIDTH/4 - 80;
    public ChalkBoard(int x, int y){
        super(x, y, BOARD_WIDTH, BOARD_HEIGHT);
    }
}
