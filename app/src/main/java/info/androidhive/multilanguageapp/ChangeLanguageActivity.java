package info.androidhive.multilanguageapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ChangeLanguageActivity extends Activity {
    Spinner spinnerTaksi;
    Button buttonSave;
    String[] arraytaksi=new String[]{"English","France","Deutsch", "Japan","India","Indonesia"};
    String[] arraynotaksi=new String[]{"en","fr","de", "ja","hi","in"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);

        spinnerTaksi = (Spinner) findViewById(R.id.spinnerLanguage);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePilihan();

            }
        });

        isiSpinner();
    }

    private void isiSpinner(){
        // Creating adapter for spinner
        ArrayAdapter<String > dataAdapter =
                new ArrayAdapter< String >(this,
                        android.R.layout.simple_spinner_item
                        , arraytaksi);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerTaksi.setAdapter(dataAdapter);
        loadPrefference();

    }

    private  void loadPrefference(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String lang = pref.getString("lang","en");
        int position =0;
        for (int i=0; i<arraynotaksi.length; i++){
            if (arraynotaksi[i].equals(lang)){
                position=i;
            }
        }
        spinnerTaksi.setSelection(position);
    }

    private void savePilihan(){
        int position = spinnerTaksi.getSelectedItemPosition();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        Editor editor = pref.edit();

        editor.putString("lang", arraynotaksi[position]);  // Saving string

        editor.commit(); // commit changes
        finish();

    }





    @Override
    public void finish() {
        //Log.d("tag", "masuk finish");
        System.gc();
        Intent data = new Intent();
        data.putExtra("refreshflag", "1");

        setResult(RESULT_OK, data);
        super.finish();
    }


}
