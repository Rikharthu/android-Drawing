package com.example.android.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;

/* Drawing on Canvas
Canvas is a tool. Result is saved on Bitmap
How to draw:
1) Extend View class and override it's onDraw method (get's access to canvas). Uses UI thread.
2) Use SurfaceView. Uses separate thread.
 */

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        // using view
        drawView = new DrawView(this);
        setContentView(drawView);
        drawView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x, y;
                x = motionEvent.getX();
                y = motionEvent.getY();

                Log.d(LOG_TAG, "onTouch(), coordinates: x: " + x + ", y: " + y);

//                if(motionEvent.getAction()==MotionEvent.ACTION_MOVE) {
                    drawView.invalidate();
//                }

                return true;
            }
        });

        // using SurfaceView
//        setContentView(new DrawSurface(this));
    }

    class DrawView extends View {

        public DrawView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Log.d(LOG_TAG, "DrawView.onDraw(), bounds: " + canvas.getClipBounds());

//            DrawUtils.drawCourse1(canvas);
//            DrawUtils.drawCourse2(canvas);
            DrawUtils.drawCourse3(canvas);
        }

    }

    class DrawSurface extends SurfaceView implements SurfaceHolder.Callback {

        private DrawThread drawThread;

        public DrawSurface(Context context) {
            super(context);
            getHolder().addCallback(this);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            // not implemented
        }

        // surface is ready to display information
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // создаём поток прорисовки
            drawThread = new DrawThread(getHolder());
            drawThread.setRunning(true);
            drawThread.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            // stop the drawing thread since Surface is about to be destroyed
            drawThread.setRunning(false);
            // wait while draw thread is destroyed
            // else it will attempt to draw on unexisting surface
            while (retry) {
                try {
                    drawThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }

        /**
         * Поток прорисовки на SurfaceView
         */
        class DrawThread extends Thread {

            private boolean running = false;
            private SurfaceHolder surfaceHolder;

            public DrawThread(SurfaceHolder surfaceHolder) {
                // Draw on SurfaceView through SurfaceHolder
                this.surfaceHolder = surfaceHolder;
            }

            public void setRunning(boolean running) {
                this.running = running;
            }

            @Override
            public void run() {
                Canvas canvas;
                while (running) {
                    canvas = null;
                    try {
                        // get canvas through locking it from other edits
                        canvas = surfaceHolder.lockCanvas(null);
                        if (canvas == null)
                            continue;
//                        canvas.drawColor(Color.GREEN);
//                        canvas.drawColor(Color.RED);
                        draw(canvas);
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (canvas != null) {
                            // post changes to surface's canvas
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }

            private void draw(Canvas canvas) {
                int maxX = canvas.getWidth();
                int maxY = canvas.getHeight();

                Random random = new Random();
                int r = random.nextInt(256);
                int g = random.nextInt(256);
                int b = random.nextInt(256);
                int color = Color.rgb(r, g, b);
//                canvas.drawColor(color);
                Paint paint = new Paint();
                paint.setColor(color);
//                canvas.drawPoint(random.nextInt(maxX+1),random.nextInt(maxY+1),paint);
                canvas.drawCircle(random.nextInt(maxX + 1), random.nextInt(maxY + 1), random.nextInt(Math.min(maxX, maxY) / 2), paint);
            }

        }

    }

}
