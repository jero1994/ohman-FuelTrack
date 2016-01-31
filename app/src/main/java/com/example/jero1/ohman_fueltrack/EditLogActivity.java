package com.example.jero1.ohman_fueltrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditLogActivity extends AppCompatActivity {
    /*
    Manipulates the data in the LogData module.
    Allows the user to either add a log entr or edit an existing entry, depending on the editState parameter
     */

    private final int ADD = -1;
    private int editState = ADD;

    private TextView title;
    private EditText editDate;
    private EditText editStation;
    private EditText editOdometer;
    private EditText editGrade;
    private EditText editAmount;
    private EditText editCost;
    private TextView errorText;
    private Button setButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //If Add: All fields are blank, and the "Set" button adds a log entry.
        //If Edit: Fields have previous values, and the "Set" button updates the entry.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editlog);

        title = (TextView)findViewById(R.id.txtLogEditTitle);
        editDate = (EditText)findViewById(R.id.editDate);
        editStation = (EditText)findViewById(R.id.editStation);
        editOdometer = (EditText)findViewById(R.id.editOdometer);
        editGrade = (EditText)findViewById(R.id.editGrade);
        editAmount = (EditText)findViewById(R.id.editAmount);
        editCost = (EditText)findViewById(R.id.editCost);
        errorText = (TextView)findViewById(R.id.errorText);
        setButton = (Button)findViewById(R.id.setButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);

        setButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                processEdits();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener()  {
            public void onClick (View v){
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras!=null&&extras.get("editState")!=null) { editState = (int)(extras.get("editState")); }
        title.setText((editState == ADD) ? "Add Log Entry" : "Edit Log Entry");
        if (editState != ADD) {
            LogEntry logEntry = LogData.GetEntryAt(editState);
            editDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(logEntry.getDate()));
            editStation.setText(logEntry.getStation());
            editOdometer.setText(new DecimalFormat("#0.0").format(logEntry.getOdometer()));
            editGrade.setText(logEntry.getGrade());
            editAmount.setText( new DecimalFormat("#0.000").format(logEntry.getAmount()));
            editCost.setText( new DecimalFormat("#0.000").format(logEntry.getUnitCost()));
        }
    }

    private void processEdits() {
        /*
        Checks the entered data for format errors
        Displays an error message if necessary
        Sets the add/edit if all data is valid
         */
        Date date = new Date();
        String station = editStation.getText().toString(), grade = editGrade.getText().toString();
        double odometer = 0, amount = 0, cost = 0;
        ArrayList<String> errors = new ArrayList<>();
        String errorMsg = "";

        //Tests each data value for validity, and composes an error message to display in event of an error.
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try { date = df.parse(editDate.getText().toString()); }
        catch (ParseException e) {
            errors.add("Please enter a valid date (yyyy-mm-dd format).");
            editDate.setText("");
        }
        try { odometer = Double.parseDouble(editOdometer.getText().toString()); }
        catch (NumberFormatException e) {
            errors.add("Please enter a valid odometer reading.");
            editOdometer.setText("");
        }
        try { amount = Double.parseDouble(editAmount.getText().toString()); }
        catch (NumberFormatException e) {
            errors.add("Please enter a valid fuel amount.");
            editAmount.setText("");
        }
        try { cost = Double.parseDouble(editCost.getText().toString()); }
        catch (NumberFormatException e) {
            errors.add("Please enter a valid fuel cost.");
            editCost.setText("");
        }
        for (int i = 0; i < errors.size(); i++) {
            errorMsg += errors.get(i);
            if (i < errors.size() - 1) { errorMsg += "\n"; }
        }
        errorText.setText(errorMsg);

        if (errors.size() == 0) {
            //Adds a new log entry to the log for an add
            //Sets new data for an existing log entry for an edit
            LogEntry logEntry = new LogEntry(date, station, odometer, grade, amount, cost);
            if (editState == ADD) { LogData.AddLogEntry(logEntry); }
            else { LogData.GetEntryAt(editState).Set(logEntry); }
            finish();
        }
    }
}
