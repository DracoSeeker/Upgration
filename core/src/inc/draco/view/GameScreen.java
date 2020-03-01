package inc.draco.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import inc.draco.Draconerra;

public class GameScreen extends StandardScreen {
    private OrthographicCamera cam;
    private FitViewport viewport;
    private Box2DDebugRenderer renderer;
    private World world;
    private Body circle;
    private Body bod;


    public GameScreen() {
        cam = new OrthographicCamera(Draconerra.WORLD_WIDTH, Draconerra.WORLD_HEIGHT);
        cam.translate(Draconerra.WORLD_WIDTH / 2, Draconerra.WORLD_HEIGHT / 2);
        viewport = new FitViewport(Draconerra.WORLD_WIDTH, Draconerra.WORLD_HEIGHT, cam);

        world = new World(new Vector2(0, -9.8f) , true);

        renderer = new Box2DDebugRenderer();
        renderer.SHAPE_KINEMATIC.set(Color.BLUE);
        renderer.SHAPE_AWAKE.set(Color.RED);

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.KinematicBody;
        def.angularVelocity = -3;
        def.position.set(Draconerra.WORLD_WIDTH / 4, Draconerra.WORLD_HEIGHT / 4);

        bod = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Draconerra.WORLD_HEIGHT * .4f, Draconerra.WORLD_HEIGHT * .007f);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = .1f;
        fixDef.friction = .2f;
        fixDef.restitution = .5f;

        bod.createFixture(fixDef);

        shape.dispose();

        BodyDef def2 = new BodyDef();
        def2.type = BodyDef.BodyType.DynamicBody;
        def2.position.set(Draconerra.WORLD_WIDTH / 4, Draconerra.WORLD_HEIGHT / 2);

        circle = world.createBody(def2);

        CircleShape circ = new CircleShape();
        circ.setRadius(Draconerra.WORLD_HEIGHT * .075f);

        FixtureDef fixDef2 = new FixtureDef();
        fixDef2.restitution = .5f;
        fixDef2.friction = .5f;
        fixDef2.density = 100;
        fixDef2.shape = circ;

        circle.createFixture(fixDef2);

        circ.dispose();

    }

    private float timeCnt = 0;
    private float cnt = 0;
    @Override
    public void scRender(float delta) {
        Gdx.graphics.setTitle(Draconerra.TITLE + " - " + Gdx.graphics.getFramesPerSecond());

        timeCnt += delta;
        cnt += delta;
        bod.setAngularVelocity((float) -Math.abs(Math.sin(cnt) * 3));
        float tm = 2;
        if(timeCnt > tm) {
            circle.setTransform(Draconerra.WORLD_WIDTH / 4, Draconerra.WORLD_HEIGHT / 2, 0);
            timeCnt -= tm;
        }

        renderer.render(world, cam.combined);

        doPhysics(delta);
    }

    float accumulator = 0;
    private void doPhysics(float delta) {
        accumulator += (delta < 1/4f) ? delta : 1/4f;
//        accumulator += delta;
        while(accumulator >= Draconerra.TIME_STEP)   {
            world.step(Draconerra.TIME_STEP, Draconerra.VELOCITY_ITERATIONS, Draconerra.POSITION_ITERATIONS);
            accumulator -= Draconerra.TIME_STEP;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
