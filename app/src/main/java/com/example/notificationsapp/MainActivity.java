package com.example.notificationsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.AlarmManagerCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
//        displaySimpleNotification(title, message);

        title = getResources().getString(R.string.notification_title);
        message = getResources().getString(R.string.notification_text);

        displayBigPictureNotification(title, message);

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
        // TODO - Create and show picture notification

        final NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.setBigContentTitle(title);
        bigPictureStyle.setSummaryText(message);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID_2);
        builder.setContentTitle(title)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setStyle(bigPictureStyle);

        Glide.with(this).asBitmap().load(R.drawable.table_mountain_hike).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                bigPictureStyle.bigPicture(resource);
                NotificationManagerCompat.from(MainActivity.this).notify(1, builder.build());
                return true;
            }
        }).submit(400, 200);
    }

    private void createNotificationChannel(String name, String id, String description) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(Color.BLUE);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
