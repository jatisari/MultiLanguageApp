package info.androidhive.multilanguageapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {
	private static final int REQUEST_CODE=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	/*	Locale locale = new Locale("fr"); // "fr" set default language as french, use above listed codes for different languages

		Locale.setDefault(locale);

		Configuration config = new Configuration();

		config.locale = locale;

		getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources()

				.getDisplayMetrics());

		Toast.makeText(MainActivity.this, "Locale in french", Toast.LENGTH_LONG).show();*/
		loadPrefference();
		setContentView(R.layout.activity_main);
	//	changeLanguage();

	}

	private  void loadPrefference(){
		SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
		String lang = pref.getString("lang","en");
		changeLanguage(lang);
	}
	public void changeLanguage(String lang){
		Locale locale = new Locale(lang); // "fr" set default language as french, use above listed codes for different languages

		Locale.setDefault(locale);

		Configuration config = new Configuration();

		config.locale = locale;

		getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources()

				.getDisplayMetrics());

		Toast.makeText(MainActivity.this, "Locale in "+lang, Toast.LENGTH_LONG).show();


	}

	public void openSetting(View view){
		Intent intent = new Intent(MainActivity.this, ChangeLanguageActivity.class);
		startActivityForResult(intent, REQUEST_CODE);
	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case REQUEST_CODE: {
				if (resultCode == RESULT_OK ) {
					Log.d("TAG","masuksini");
					recreate();
				}
				break;
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
