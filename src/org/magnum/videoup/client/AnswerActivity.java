package org.magnum.videoup.client;
//import com.coursera.mutibo.R;

import org.magnum.videoup.client.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
public class AnswerActivity extends Activity implements SwipeInterface{
	
	Button buttonReturn;
	ImageButton buttonUp, buttonDown;
	long likes = 0;
	
	private static final String TAG = "AnswerActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer);

		ActivitySwipeDetector swipe = new ActivitySwipeDetector(this);
		LinearLayout swipe_layout = (LinearLayout) findViewById(R.id.db1_root);
		swipe_layout.setOnTouchListener(swipe);
		
		
		TextView textTitle1=(TextView)findViewById(R.id.textTitle1);
		TextView textTitle2=(TextView)findViewById(R.id.textTitle2);
		TextView textTitle3=(TextView)findViewById(R.id.textTitle3);		
		TextView textOddTitle=(TextView)findViewById(R.id.textOddTitle);

		TextView textexplanationTitle=(TextView)findViewById(R.id.textExplanationTitle);
		TextView textexplanation=(TextView)findViewById(R.id.textexplanation);
		
		//get score
		Bundle b = getIntent().getExtras();
		
		String yourAnswer = b.getString("yourAnswer");

    	QuizSet question = (QuizSet) b.getParcelable("Question");
    	
		likes = b.getLong("likes");
		
		String title1 = question.getTitle1();
		String title2 = question.getTitle2();
		String title3 = question.getTitle3();
		String Common_Attribute = question.getCommon_Attribute();
		String Common_Relationship_Text = question.getCommon_Relationship_Text();
		
		String oddTitle = question.getOdd_Title();
		String Odd_Attribute = question.getOdd_Attribute();		
		String odd_Relationship_Text = question.getOdd_Relationship_Text();		
		
		textTitle1.setText(title1);
		textTitle2.setText(title2);
		textTitle3.setText(title3);		
		textOddTitle.setText(oddTitle);

		if(yourAnswer.equals(oddTitle)){
			textexplanationTitle.setText("Correct!");
		}else{
			textexplanationTitle.setText("Incorrect!");
		}
		
		textexplanation.setText(oddTitle + " is a " + Odd_Attribute + " movie. The others are " + Common_Attribute + " movies.");
		
		
		buttonReturn=(Button)findViewById(R.id.buttonNext);		
		buttonReturn.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				
				Intent resultIntent = new Intent();
				resultIntent.putExtra("LIKES", likes);
				setResult(Activity.RESULT_OK, resultIntent);
				finish();								
			
			}
		}
		);

		buttonUp=(ImageButton)findViewById(R.id.buttonUp);		
		buttonUp.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				// set likes ++
				likes++;			
				Toast.makeText(getApplicationContext(), "Thumbs Up!",
						   Toast.LENGTH_LONG).show();					
			}
		}
		);

		buttonDown=(ImageButton)findViewById(R.id.buttonDown);		
		buttonDown.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				// set likes --
				likes--;							
				Toast.makeText(getApplicationContext(), "Thumbs Down!",
						   Toast.LENGTH_LONG).show();				
			}
		}
		);
		
		
		
	}
/*	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}
*/	
	@Override
	public void bottom2top(View v) {
		Toast.makeText(getApplicationContext(), "Swipe RIGHT for Next Question",
				   Toast.LENGTH_LONG).show();
	}
	@Override
	public void left2right(View v) {
					
		Intent resultIntent = new Intent();
		resultIntent.putExtra("LIKES", likes);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();									
	}
	@Override
	public void right2left(View v) {
		Toast.makeText(getApplicationContext(), "Swipe RIGHT for Next Question",
				   Toast.LENGTH_LONG).show();					
		
	}
	@Override
	public void top2bottom(View v) {
		Toast.makeText(getApplicationContext(), "Swipe RIGHT for Next Question",
				   Toast.LENGTH_LONG).show();
	}
		
	
}
