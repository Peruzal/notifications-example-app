package com.example.notificationsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_ID_1 = "notification_channel_1";
    public static final String CHANNEL_ID_2 = "notification_channel_2";

    private EditText titleEditText;
    private EditText messageEditText;
    private Button notifyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEditText = findViewById(R.id.edtTitle);
        messageEditText = findViewById(R.id.edtMessage);
        notifyButton = findViewById(R.id.btnNotify);

        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });

    }

    private void displayNotification() {
        String title = titleEditText.getText().toString();
        String message = messageEditText.getText().toString();

        if (title.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Notification incomplete!", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO - Display notification type

    }

    private void displayNormalNotification(String title, String message) {
        // TODO - Create and show normal text notification

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel()
        }

    }

    private void displayBigPictureNotification(String tile, String message) {
        // TODO - Create and show picture notification

    }

}
