package com.example.notificationsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class NotificationActionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_action);

        Toast.makeText(this, "Notification action executed successfully!", Toast.LENGTH_SHORT).show();
    }
}
