package com.example.simplify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_CONTENT= "EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "EXTRA_PRIORITY";
    public static final String EXTRA_HOUR = "EXTRA_HOUR";
    public static final String EXTRA_MINUTE = "EXTRA_MINUTE";
    private EditText editTextTitle;
    private EditText editTextContent;
    private TextView timeTextView;
    private NumberPicker numberPickerPriority;
    private int hour=0;
    private int minute=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextContent = findViewById(R.id.edit_text_content);
        timeTextView =findViewById(R.id.alarm);
        LinearLayout timePicker = (LinearLayout) findViewById(R.id.time_picker);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextContent.setText(intent.getStringExtra(EXTRA_CONTENT));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
            timeTextView.setText("AT: "+intent.getIntExtra(EXTRA_HOUR,0)+":"+intent.getIntExtra(EXTRA_MINUTE,0));

        } else {
            setTitle("Add Note");
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
        hour=hourOfDay;
        minute=minutes;
        timeTextView.setText("AT: "+hour+":"+minute);
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String content = editTextContent.getText().toString();
        int priority = numberPickerPriority.getValue();
        if (title.trim().isEmpty() || content.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_CONTENT, content);
        intent.putExtra(EXTRA_PRIORITY, priority);
        intent.putExtra(EXTRA_HOUR,hour);
        intent.putExtra(EXTRA_MINUTE,minute);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            intent.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}