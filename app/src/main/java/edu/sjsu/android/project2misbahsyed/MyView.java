package edu.sjsu.android.project2misbahsyed;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class MyView extends View {
    private final int BALL_SIZE = 500;
    private Bitmap field, ball;
    private float originX, originY, horizontalBound, verticalBound;
    private Particle particle;
    private MyListener listener;
    private Paint paint;
    // Declare a constant for your name at the top of the class
    private final String NAME = "Misbah Syed";

    public MyView(Context context){
        super(context);
        listener = new MyListener(context);
        particle = new Particle();
        // initialize others Feb 25
        field = BitmapFactory.decodeResource(getResources(), R.drawable.field);  // Load your image
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);    // Load the ball image
        ball = Bitmap.createScaledBitmap(ball, BALL_SIZE, BALL_SIZE,false);
        paint = new Paint();
    }

    public MyListener getListener() {
        return listener;
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        field = Bitmap.createScaledBitmap(field, w, h, false);
        //origin is at the center
        originX = w / 2f;
        originY = h / 2f;
        // Bounds are the distances between ball edge and screen edge
        horizontalBound = (w - BALL_SIZE) / 2f;
        verticalBound = (h - BALL_SIZE) / 2f;
    }

    public void onDraw(@NonNull Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(field, 0, 0, null);

        canvas.drawBitmap(ball, originX - BALL_SIZE / 2f + particle.mPosX, originY - BALL_SIZE / 2f - particle.mPosY, null);
        particle.updatePosition(listener.getX(), listener.getY(), listener.getTimestamp());
        particle.resolveCollisionWithBounds(horizontalBound, verticalBound);

        // Draw your name on the screen
        paint.setTextSize(100); // Adjust size
        paint.setColor(0xFFFF0000); // Red color
        float textWidth = paint.measureText(NAME);
        //canvas.drawText(NAME, originX - textWidth / 2, originY + verticalBound - 100, paint);
        canvas.drawText(NAME, originX - textWidth / 2, 150, paint);

        invalidate();

    }
}
