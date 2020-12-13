package a.event_handling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView first_tv;
    TextView second_tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.click_btn);
        first_tv = findViewById(R.id.first_tv);
        second_tv = findViewById(R.id.second_tv);
        btn.setVisibility(View.GONE);
        first_tv.setVisibility(View.GONE);
        second_tv.setVisibility(View.GONE);
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
                second_tv.setVisibility(View.GONE);
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
                btn.setVisibility(View.GONE);
                first_tv.setVisibility(View.VISIBLE);
                second_tv.setVisibility(View.VISIBLE);
                first_tv.setText("Пустое поле");
                second_tv.setText("Пустое поле");
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
                btn.setVisibility(View.GONE);
                first_tv.setVisibility(View.VISIBLE);
                second_tv.setVisibility(View.GONE);
                second_tv.setText("Пустое поле");
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
                second_tv.setText(touchStatus);
            else
                first_tv.setText(touchStatus);
        }
    }
}