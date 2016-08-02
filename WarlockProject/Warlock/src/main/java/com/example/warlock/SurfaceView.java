package com.example.warlock;


        import android.app.Activity;
        import android.content.Context;
        import android.graphics.Point;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.net.wifi.WifiInfo;
        import android.net.wifi.WifiManager;
        import android.opengl.GLSurfaceView;
        import android.util.DisplayMetrics;
        import android.view.Display;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.WindowManager;
        import android.os.Vibrator;

        import org.apache.http.HttpResponse;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.params.BasicHttpParams;
        import org.apache.http.params.HttpConnectionParams;
        import org.apache.http.params.HttpParams;

        import java.math.BigDecimal;
        import java.text.DecimalFormat;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.concurrent.ScheduledThreadPoolExecutor;
        import java.util.concurrent.TimeUnit;


/**
 * A view container where OpenGL ES graphics can be drawn on screen.
 * This view can also be used to capture touch events, such as a user
 * interacting with drawn objects.
 */
public class SurfaceView extends GLSurfaceView {

    public final Rend mRenderer;
    private Vibrator vibrator;


    public SurfaceView(Context context) {
        super(context);
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new Rend(context);
        //float posTemp[]=f;
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

    }

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;


    @Override
    public boolean onTouchEvent(MotionEvent e) {

        float x = e.getX();
        float y = e.getY();

        switch (e.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:

                vibrator.vibrate(200);
            case MotionEvent.ACTION_MOVE:

            case MotionEvent.ACTION_UP:


        }
        return true;
    }

}