package ru.myitschool.jabagames;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Chess extends ApplicationAdapter implements Screen {
	public static final int SCR_WIDTH = 1280, SCR_HEIGHT = 720;
	public static final int DESK_START = (SCR_WIDTH-SCR_HEIGHT)/2;
	SpriteBatch batch;
	OrthographicCamera camera;
	Vector3 touch;
	Texture deskImg;
	Texture circleImg;

	Board board;
	private Chessman moving_chessman;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		touch = new Vector3();
        board = new Board();
        startPosition();
        circleImg = new Texture("circle.png");
		deskImg = new Texture("desk.png");
	}

	@Override
	public void render (float delta) {
		if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            int[] touchCoords = touchToCoord((int)touch.x, (int)touch.y);
            if (touchCoords[0] < 8 && touchCoords[0] >= 0) {
                if (moving_chessman == null) {
                    if(board.getChessman(touchCoords) != null && board.getChessman(touchCoords).isWhite() == board.isWhiteMove){
                        moving_chessman = board.getChessman(touchCoords);
                    }
                }else {
                    board.moveChessman(moving_chessman, touchCoords);
                    moving_chessman = null;
                }
            }else {
                moving_chessman = null;
            }
        }

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(deskImg, DESK_START, 0, SCR_HEIGHT, SCR_HEIGHT);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.chessmen[i][j] != null){
                	Texture chessmanImg = board.chessmen[i][j].isWhite()?board.getChessman(i, j).whiteTexture:board.getChessman(i, j).blackTexture;
                    batch.draw(chessmanImg, i * Chessman.getSize() + DESK_START, j * Chessman.getSize(), Chessman.getSize(), Chessman.getSize());
                }
            }
        }
        if (moving_chessman!= null){
			for (int[] move :
					(moving_chessman.getAvailableMoves())){
				if (board.getChessman(move) == null) {
					batch.draw(circleImg, move[0] * Chessman.getSize() + DESK_START, move[1] * Chessman.getSize(), Chessman.getSize(), Chessman.getSize());
				}
			}
		}
		batch.end();
	}

	@Override
	public void show() {

	}

	@Override
	public void render() {
        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            int[] touchCoords = touchToCoord((int)touch.x, (int)touch.y);
            if (touchCoords[0] < 8 && touchCoords[0] >= 0) {
                if (moving_chessman == null) {
                    if(board.getChessman(touchCoords) != null && board.getChessman(touchCoords).isWhite() == board.isWhiteMove){
                        moving_chessman = board.getChessman(touchCoords);
                    }
                }else {
                    board.moveChessman(moving_chessman, touchCoords);
                    moving_chessman = null;
                }
            }else {
                moving_chessman = null;
            }
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(deskImg, DESK_START, 0, SCR_HEIGHT, SCR_HEIGHT);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.chessmen[i][j] != null){
                    Texture chessmanImg = board.chessmen[i][j].isWhite()?board.getChessman(i, j).whiteTexture:board.getChessman(i, j).blackTexture;
                    batch.draw(chessmanImg, i * Chessman.getSize() + DESK_START, j * Chessman.getSize(), Chessman.getSize(), Chessman.getSize());
                }
            }
        }
        if (moving_chessman!= null){
            for (int[] move :
                    (moving_chessman.getAvailableMoves(board))){
                if (board.getChessman(move) == null) {
                    batch.draw(circleImg, move[0] * Chessman.getSize() + DESK_START, move[1] * Chessman.getSize(), Chessman.getSize(), Chessman.getSize());
                }
            }
        }
        batch.end();
	}

	@Override
	public void hide() {
     dispose();
	}

	@Override
	public void dispose () {
		batch.dispose();
		deskImg.dispose();
	}

	private int[] touchToCoord(int x, int y){
		int[] coord = new int[2];
		coord[0] = (x - DESK_START) / Chessman.getSize();
		coord[1] = y / Chessman.getSize();
		return coord;
	}
	private void startPosition(){
		board.whiteKing = new King(4, 0, true);
		board.blackKing = new King(4, 7, false);
	    board.addChessman(board.whiteKing);
	    board.addChessman(board.blackKing);

	    board.addChessman(new Queen(3, 0, true));
	    board.addChessman(new Queen(3, 7, false));

	    board.addChessman(new Rook(0, 0, true));
	    board.addChessman(new Rook(7, 0, true));
	    board.addChessman(new Rook(0, 7, false));
	    board.addChessman(new Rook(7, 7, false));

	    board.addChessman(new Knight(1, 0, true));
	    board.addChessman(new Knight(6, 0, true));
	    board.addChessman(new Knight(1, 7, false));
	    board.addChessman(new Knight(6, 7, false));

	    board.addChessman(new Bishop(2, 0, true));
	    board.addChessman(new Bishop(5, 0, true));
	    board.addChessman(new Bishop(2, 7, false));
	    board.addChessman(new Bishop(5, 7, false));

		for (int i = 0; i < 8; i++) {
			board.addChessman(new Pawn(i, 1, true));
		}
		for (int i = 0; i < 8; i++) {
			board.addChessman(new Pawn(i, 6, false));
		}
		board.isWhiteMove = true;
    }
}
