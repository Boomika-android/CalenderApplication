package com.example.calenderapplication;

import android.app.Dialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView date;
    Button btn_create_event, view_events;
    Button btn_save;
    EditText edt_date, edt_title, edt_description;
    DatabaseHelper databaseHelper;
    ArrayList<Event> arrayList;

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

        btn_create_event = findViewById(R.id.btn_create_event);
        view_events = findViewById(R.id.view_events);
        date = findViewById(R.id.date);
        Dialog dialog = new Dialog(this);
        arrayList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);

        String date_formater = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        date.setVisibility(View.VISIBLE);
        date.setText("Today's Date: " + date_formater);
        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis();

        calendarView = findViewById(R.id.calender_view);
        calendarView.setDate(time, false, true);
        calendarView.setBackgroundColor(getResources().getColor(R.color.yellow));
        calendarView.setActivated(true);
        calendarView.setWeekDayTextAppearance(R.style.MyWeekButtonStyle);
        calendarView.setDateTextAppearance(R.style.MyDateButtonStyle);

        view_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewEventsActivity.class));
            }
        });

        btn_create_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.dialogbox_layout);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
                btn_save = dialog.findViewById(R.id.save);
                edt_date = dialog.findViewById(R.id.edt_date);
                edt_title = dialog.findViewById(R.id.edt_title);
                edt_description = dialog.findViewById(R.id.edt_description);
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String date = edt_date.getText().toString();
                        String title = edt_title.getText().toString();
                        String description = edt_description.getText().toString();
                        Event event = new Event(date, title, description);
                        boolean inserted_event = databaseHelper.save_event(event);
                        if (inserted_event){
                            Toast.makeText(MainActivity.this, "Event saved", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(MainActivity.this, "Event is not saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}