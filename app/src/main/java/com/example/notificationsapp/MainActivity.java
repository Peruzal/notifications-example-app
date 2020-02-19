package com.example.notificationsapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

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

        createNotificationChannel("Normal Notifications", CHANNEL_ID_1, "Simple notification");
        createNotificationChannel("Picture Notifications", CHANNEL_ID_2, "Notifications which displays an image");

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

    private void displaySimpleNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID_1).
                setSmallIcon(R.drawable.ic_android_black_24dp).
                setContentTitle(title).
                setContentText(message).
                setAutoCancel(true);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 23, new Intent(this, NotificationActionActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(R.drawable.ic_android_black_24dp, "OPEN", pendingIntent);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, builder.build());

    }

    private void displayBigPictureNotification(String title, String message) {

        // TODO - Create BigPicture notification style


        // TODO - Build notification and set the notification style


        // TODO - Display notification using the NotificationManager


    }

    private void createNotificationChannel(String name, String id, String description) {
        // TODO - Create notification channel

    }

}
