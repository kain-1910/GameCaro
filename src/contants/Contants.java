package contants;

public class Contants {
	
	public static class BoardContants {
		public static final int SIZE_CELL = 20;
		public static final int BOARD_ROW = 30;
		public static final int BOARD_COL = 30;
		public static final int BOARD_WIDTH = SIZE_CELL*BOARD_COL;
		public static final int BOARD_HEIGHT = SIZE_CELL*BOARD_ROW;
		
	}
	
	public static class GameResult {
		public static final int WIN = 1;
		public static final int DRAW = 0;
		public static final int NORMAL = 2;
	}
	
	public static class State {
		public static final int MENU = 1;
		public static final int PLAYING = 0;
	}
}
