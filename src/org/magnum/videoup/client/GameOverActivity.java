package org.magnum.videoup.client;


import org.magnum.videoup.client.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class GameOverActivity extends Activity {
	
	Button playAgain;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);

		TextView t=(TextView)findViewById(R.id.textScore);

		Bundle b = getIntent().getExtras();
		int score= b.getInt("score");
		t.setText("You scored " + score);
	
		playAgain=(Button)findViewById(R.id.button1);
		playAgain.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {				
				Intent intent = new Intent(GameOverActivity.this, QuizActivity.class);
				startActivity(intent);
				finish();	
			}
		});
		
		
	}
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}*/
}
