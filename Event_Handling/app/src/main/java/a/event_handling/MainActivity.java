package a.event_handling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener
{

    TextView first_tv; //BtnClick
    private TextView second_tv; //Gesture
    TextView tv_mtn1; //Mtn_event
    TextView tv_mtn2; //Mtn_event
    Button btn;
    private GestureDetectorCompat gDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.click_btn);
        first_tv = findViewById(R.id.first_tv);
        second_tv = findViewById(R.id.second_tv);
        tv_mtn1 = findViewById(R.id.tv_mtn1); //Mtn_event
        tv_mtn2 = findViewById(R.id.tv_mtn2); //Mtn_event
        btn.setVisibility(View.INVISIBLE);
        first_tv.setVisibility(View.INVISIBLE);
        second_tv.setVisibility(View.INVISIBLE);
        tv_mtn1.setVisibility(View.INVISIBLE);
        tv_mtn2.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.btn_click:
                setTitle("ButtonClick");
                btn.setVisibility(View.VISIBLE);
                first_tv.setVisibility(View.VISIBLE);
                second_tv.setVisibility(View.INVISIBLE);
                tv_mtn1.setVisibility(View.INVISIBLE);
                tv_mtn2.setVisibility(View.INVISIBLE);
                first_tv.setText("Пустое поле");
                btn.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v) {
                        first_tv.setText("На меня нажали");
                    }
                }
                );
                btn.setOnLongClickListener(new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        first_tv.setText("Произошло длительное нажатие");
                        return false;
                    }
                }
                );
                return true;
            case R.id.mtn_event:
                setTitle("MotionEvent");
                btn.setVisibility(View.INVISIBLE);
                first_tv.setVisibility(View.INVISIBLE);
                second_tv.setVisibility(View.INVISIBLE);
                tv_mtn1.setVisibility(View.VISIBLE);
                tv_mtn2.setVisibility(View.VISIBLE);
                tv_mtn1.setText("Пустое поле");
                tv_mtn2.setText("Пустое поле");
                ConstraintLayout myLayout = findViewById(R.id.activity_main);
                myLayout.setOnTouchListener(new ConstraintLayout.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent m) {
                        handleTouch(m);
                        return true;
                    }
                }
                );
                return true;
            case R.id.com_gest:
                setTitle("CommonGestures");
                btn.setVisibility(View.INVISIBLE);
                first_tv.setVisibility(View.INVISIBLE);
                second_tv.setVisibility(View.VISIBLE);
                tv_mtn1.setVisibility(View.INVISIBLE);
                tv_mtn2.setVisibility(View.INVISIBLE);
                second_tv.setText("Пустое поле");
                ConstraintLayout myLayout1 = findViewById(R.id.activity_main);
                myLayout1.setOnTouchListener(View::onTouchEvent);
                gDetector = new GestureDetectorCompat(this,this);
                gDetector.setOnDoubleTapListener(this);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void handleTouch(MotionEvent m) {
        int pointerCount = m.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {
            int x = (int) m.getX(i);
            int y = (int) m.getY(i);
            int id = m.getPointerId(i);
            int action = m.getActionMasked();
            int actionIndex = m.getActionIndex();
            String actionString;


            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    actionString = "DOWN";
                    break;
                case MotionEvent.ACTION_UP:
                    actionString = "UP";
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    actionString = "PNTR DOWN";
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    actionString = "PNTR UP";
                    break;
                case MotionEvent.ACTION_MOVE:
                    actionString = "MOVE";
                    break;
                default:
                    actionString = "";
            }

            String touchStatus = "Action: " + actionString + " Index: " +
                    actionIndex + " ID: " + id + " X: " + x + " Y: " + y;
            if (id == 0)
                tv_mtn1.setText(touchStatus);
            else
                tv_mtn2.setText(touchStatus);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        second_tv.setText("onSingleTapConfirmed");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        second_tv.setText("onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        second_tv.setText("onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        second_tv.setText("onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        second_tv.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        second_tv.setText("onSingleTapUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        second_tv.setText("onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        second_tv.setText("onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        second_tv.setText("onFling");
        return true;
    }
}