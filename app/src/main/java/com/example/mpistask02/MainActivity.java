package com.example.mpistask02;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    public TextView gestureText;
    public Button authorBtn;
    public FrameLayout frameLayout;
    public GestureDetector gestureDetector;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gestureText = findViewById(R.id.gestureText);
        gestureDetector = new GestureDetector(this, this);
        gestureDetector.setOnDoubleTapListener(this);

        frameLayout = findViewById(R.id.frameLayout);
        frameLayout.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));

        authorBtn = findViewById(R.id.authorBtn);

        authorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("Разработал", getString(R.string.author));
            }
        });
    }

    @Override
    public boolean onDown(@NonNull MotionEvent motionEvent) {
        gestureText.setText("Gesture: onDown");
        return true;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent motionEvent) {
        gestureText.setText("Gesture: onShowPress");
    }

    @Override
    public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        gestureText.setText("Gesture: onScroll");
        return true;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent motionEvent) {
        gestureText.setText("Gesture: onLongPress");
    }

    @Override
    public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        gestureText.setText("Gesture: onFling");
        return true;
    }

    @Override
    public boolean onDoubleTap(@NonNull MotionEvent motionEvent) {
        gestureText.setText("Gesture: onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(@NonNull MotionEvent motionEvent) {
        gestureText.setText("Gesture: onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
        gestureText.setText("Gesture: onSingleTapUp");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent motionEvent) {
        gestureText.setText("Gesture: onSingleTapConfirmed");
        return true;
    }

    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
}