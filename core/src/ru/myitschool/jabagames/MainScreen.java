package ru.myitschool.jabagames;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class MainScreen implements Screen {

    SpriteBatch batch;
    OrthographicCamera camera;

    Texture StartButtonImg;
    Texture ExitButtonImg;
    Texture BackGroundimg;
    Sprite StartButtonSprite;
    Sprite ExitButtonSprite;
    Sprite BackGroundSprite;

    private static float BUTTON_RESIZE_FACTOR = 800f;
    private static float START_VERT_POSITION_FACTOR = 2.7f;
    private static float EXIT_VERT_POSITION_FACTOR = 4.2f;

    MainGame game;
    Vector3 temp = new Vector3(); // временный вектор для "захвата" входных координат

    public MainScreen(MainGame game){

        this.game = game;

        float height= Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();

        camera = new OrthographicCamera(width,height);

        camera.setToOrtho(false);
        batch = new SpriteBatch();

        // инициализируем текстуры и спрайты
        StartButtonImg = new Texture(Gdx.files.internal("StartButton.png"));
        ExitButtonImg = new Texture(Gdx.files.internal("ExitButton.png"));
        BackGroundimg = new Texture(Gdx.files.internal("BackGround.png"));
        StartButtonSprite = new Sprite(StartButtonImg);
        ExitButtonSprite = new Sprite(ExitButtonImg);
        BackGroundSprite = new Sprite(BackGroundimg);
        // устанавливаем размер и позиции
        StartButtonSprite.setSize(StartButtonSprite.getWidth()-50, StartButtonSprite.getHeight()-50);
        ExitButtonSprite.setSize(ExitButtonSprite.getWidth()-200, ExitButtonSprite.getHeight()-200);
        BackGroundSprite.setSize(width,height);
        StartButtonSprite.setPosition((width/2f -StartButtonSprite.getWidth()/2) , width/START_VERT_POSITION_FACTOR-400);
        ExitButtonSprite.setPosition((width/2f -ExitButtonSprite.getWidth()/2) , width/EXIT_VERT_POSITION_FACTOR-400);
    }


    private void HandTouch() {
        // Проверяем были ли касание по экрану?
        if (Gdx.input.justTouched()) {
            // Получаем координаты касания и устанавливаем эти значения в временный вектор
            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            // получаем координаты касания относительно области просмотра нашей камеры
            camera.unproject(temp);
            float touchX = temp.x;
            float touchY = temp.y;
            // обработка касания по кнопке Start
            if ((touchX >= StartButtonSprite.getX()) && touchX <= (StartButtonSprite.getX() + StartButtonSprite.getWidth()) && (touchY >= StartButtonSprite.getY()) && touchY <= (StartButtonSprite.getY() + StartButtonSprite.getHeight())) {
                Chess chess = new Chess();
                chess.create();
                game.setScreen(chess); // Переход к экрану игры
            }
            // обработка касания по кнопке Exit
            else if ((touchX >= ExitButtonSprite.getX()) && touchX <= (ExitButtonSprite.getX() + ExitButtonSprite.getWidth()) && (touchY >= ExitButtonSprite.getY()) && touchY <= (ExitButtonSprite.getY() + ExitButtonSprite.getHeight())) {
                Gdx.app.exit(); // выход из приложения
            } } }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        // Очищаем экран
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);// устанавливаем в экземпляр spritebatch вид с камеры (области просмотра)

        //отрисовка игровых объектов
        batch.begin();
        BackGroundSprite.draw(batch);
        StartButtonSprite.draw(batch);
        ExitButtonSprite.draw(batch);
        HandTouch();
        batch.end();
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {

        StartButtonImg.dispose();
        ExitButtonImg.dispose();
        batch.dispose();
    }

        }
