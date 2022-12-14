package com.example.prac06

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.graphics.drawable.IconCompat
import com.example.prac06.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)

        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)

            channel.description = "My Channel One Description"
            manager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(this, channelId)
        }else{
            builder = NotificationCompat.Builder(this)
        }

        builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        builder.setWhen(System.currentTimeMillis())
        builder.setContentTitle("Content Title")
        builder.setContentText("Content Massage")

        binding.button1.setOnClickListener {
            val bigPicture = BitmapFactory.decodeResource(resources, R.drawable.test)
            val bigStyle = NotificationCompat.BigPictureStyle()
            bigStyle.bigPicture(bigPicture)
            builder.setStyle(bigStyle)

            manager.notify(11,builder.build())
        }

        binding.button2.setOnClickListener {
            val bigTextStyle = NotificationCompat.BigTextStyle()
            bigTextStyle.bigText(resources.getString(R.string.long_text))
            builder.setStyle(bigTextStyle)
            manager.notify(22,builder.build())
        }

        binding.button3.setOnClickListener {
            val style = NotificationCompat.InboxStyle()
            style.addLine("1코스 - 수락.불암산코스")
            style.addLine("2코스 - 용마.아차산코스")
            style.addLine("3코스 - 고덕.일자산코스")
            style.addLine("4코스 - 대모.우면산코스")
            builder.setStyle(style)
            manager.notify(33,builder.build())
        }

        binding.button4.setOnClickListener {
            val sender1: Person = Person.Builder()
                .setName("kkang")
                .setIcon(IconCompat.createWithResource(this, R.drawable.person1))
                .build()

            val sender2: Person = Person.Builder()
                .setName("kim")
                .setIcon(IconCompat.createWithResource(this, R.drawable.person2))
                .build()

            val message1 = NotificationCompat.MessagingStyle.Message(
                "hello",
                System.currentTimeMillis(),
                sender1
            )
            val message2 = NotificationCompat.MessagingStyle.Message(
                "world",
                System.currentTimeMillis(),
                sender2
            )

            val messageStyle = NotificationCompat.MessagingStyle(sender1)
                .addMessage(message1)
                .addMessage(message2)
            builder.setStyle(messageStyle)
            manager.notify(44,builder.build())
        }

    }
}