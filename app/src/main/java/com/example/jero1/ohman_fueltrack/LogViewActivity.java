package com.example.jero1.ohman_fueltrack;

import android.content.Intent;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;


public class LogViewActivity extends AppCompatActivity {
    /*
    The view that displays the log data.
    This also loads from and saves to the data file.
    Add/Edit view can be accessed from this view.
     */

    private static final String FILENAME = "fueltrack_log.sav";
    private ListView logDisplay;
    private Button addButton;
    private TextView costDisplay;
    private ArrayAdapter<LogEntry> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logview);
        logDisplay = (ListView)findViewById(R.id.listView);
        addButton = (Button)findViewById(R.id.btnAddEntry);
        costDisplay = (TextView)findViewById(R.id.txtTotalCost);

        loadFile();

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addEntry(v);
            }
        });
        logDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                editEntry(view, position);
            }
        });
        adapter = new ArrayAdapter<LogEntry>(this, android.R.layout.simple_list_item_1, LogData.GetLog());
        logDisplay.setAdapter(adapter);
        updateView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    protected void loadFile() {
        try {
            FileInputStream fileInput = openFileInput(FILENAME);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            LogData.LoadLog((ArrayList<LogEntry>)(objectInput.readObject()));
            objectInput.close();
            fileInput.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    protected void saveFile() {
        try {
            FileOutputStream fileOutput = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(LogData.GetLog());
            objectOutput.close();
            fileOutput.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    //Called when clicking the Add Entry button
    protected void addEntry(View view) {
        Intent intentAdd = new Intent(this, EditLogActivity.class);
        intentAdd.putExtra("editState", -1); //Edit state is an int representing the nature of the edit. -1 is add, and n>0 is an edit of item n.
        startActivity(intentAdd);
    }
    //Called when clicking the Edit Entry button
    protected void editEntry(View view, int selectionIndex) {
        if (selectionIndex < 0) { return; }
        Intent intentAdd = new Intent(this, EditLogActivity.class);
        intentAdd.putExtra("editState", selectionIndex);
        startActivity(intentAdd);
    }

    protected void updateView() {
        //An update occurs whenever this view is created/resumed (eg. after an add/edit takes place).
        adapter.notifyDataSetChanged();
        costDisplay.setText("Total Fuel Cost: $" + new DecimalFormat("#0.00").format(LogData.GetTotalCost()));
        saveFile(); //The file is saved on each update.
    }
}
