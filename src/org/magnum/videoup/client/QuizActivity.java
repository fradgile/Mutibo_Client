package org.magnum.videoup.client;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.magnum.videoup.client.QuizSetSvc;
import org.magnum.videoup.client.QuizSetSvcApi;
import org.magnum.videoup.client.R;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
public class QuizActivity extends Activity  implements SwipeInterface{
	List<QuizSet> questionList;
	int score=0;
	int qid=0;
	int rowcount=0;
	int lives = 3;
	int activityResult;
	QuizSet currentQuestion;
	TextView txtQuestion;
	RadioButton rda, rdb, rdc, rdd;
	Button buttonNext;
	
	private static final String TAG = "QuizActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		
		ActivitySwipeDetector swipe = new ActivitySwipeDetector(this);
		RelativeLayout swipe_layout = (RelativeLayout) findViewById(R.id.quiz_activity_root);
		swipe_layout.setOnTouchListener(swipe);

		
		
		DbHelper db=new DbHelper(this);
		db.deleteAllQuestions();
		db.addQuestions();

		
		rowcount = db.rowcount();
		Log.v(TAG, "rowcount = " + db.rowcount());
		
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		
		ArrayList<QuizSet> QuizSetArray = b.getParcelableArrayList("Questions");
		
			
    	//Log.d(TAG,"question.getTitle1() = " + question.getTitle1());
				
    	questionList = QuizSetArray;
    	
		//questionList=db.getAllQuestions();
    	
    	
		currentQuestion=questionList.get(qid);
		txtQuestion=(TextView)findViewById(R.id.textView1);
		rda=(RadioButton)findViewById(R.id.radio0);
		rdb=(RadioButton)findViewById(R.id.radio1);
		rdc=(RadioButton)findViewById(R.id.radio2);
		rdd=(RadioButton)findViewById(R.id.radio3);		
		buttonNext=(Button)findViewById(R.id.button1);
		setQuestionView();
		buttonNext.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {				
				processAnswer();			
			}
		});
		
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			
			long likes = data.getLongExtra("LIKES", currentQuestion.getLikes());
			currentQuestion.setLikes(likes);
			questionList.set(qid - 1, currentQuestion);
			
//			DbHelper db=new DbHelper(this);
//			db.updateQuizSet(currentQuestion);
			
			Intent msgIntent = new Intent(QuizActivity.this, SimpleIntentService.class);
			msgIntent.putExtra("Question", questionList.get(qid - 1));
			startService(msgIntent);
	    				
        	if(qid<rowcount & lives > 0){					
				currentQuestion=questionList.get(qid);
				setQuestionView();
				
				Toast.makeText(getApplicationContext(), "Next Question",
						   Toast.LENGTH_LONG).show();
			}else{
				Intent intent = new Intent(QuizActivity.this, GameOverActivity.class);
				Bundle b = new Bundle();
				b.putInt("score", score); //Your score
				intent.putExtras(b); //Put your score to your next Intent
				startActivity(intent);
				finish();				
			}
    }	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_quiz, menu);
		return true;
	}*/
	private void setQuestionView()
	{
		txtQuestion.setText("Question: " + (qid + 1) + " | Score: " + score  + " | Lives left: " + lives);
		String [] titles = {currentQuestion.getTitle1(),currentQuestion.getTitle2(), currentQuestion.getTitle3(), currentQuestion.getOdd_Title() }; 
		ArrayList<String> titlesList = new ArrayList<String>(Arrays.asList(titles));
		Collections.shuffle(titlesList);		
		
		rda.setText(titlesList.get(0));
		rdb.setText(titlesList.get(1));
		rdc.setText(titlesList.get(2));
		rdd.setText(titlesList.get(3));		
		qid++;
	}

	@Override
	public void bottom2top(View v) {
		Toast.makeText(getApplicationContext(), "Swipe LEFT for Answer",
				   Toast.LENGTH_LONG).show();
	}

	@Override
	public void left2right(View v) {
		Toast.makeText(getApplicationContext(), "Swipe LEFT for Answer",
				   Toast.LENGTH_LONG).show();		
	}

	@Override
	public void right2left(View v) {
		processAnswer();		
	}

	@Override
	public void top2bottom(View v) {
		Toast.makeText(getApplicationContext(), "Swipe LEFT for Answer",
				   Toast.LENGTH_LONG).show();	
	}
	
	private void processAnswer(){
		RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
		RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
		if(currentQuestion.getOdd_Title().equals(answer.getText()))
		{
			score++;
		}else{
			lives--;
		}
		Log.d(TAG,"Correct answer = " + currentQuestion.getOdd_Title() + " | Your answer = " + answer.getText() + " | score = " + score + " | lives left = " + lives);				
		Intent answerIntent = new Intent(QuizActivity.this, AnswerActivity.class);
		
//		answerIntent.putExtra("Question", currentQuestion);
		
		Bundle b1 = new Bundle();
		b1.putString("yourAnswer", answer.getText().toString());
		b1.putParcelable("Question", currentQuestion);
/*		
		b1.putLong("combination", currentQuestion.getCombination());
		b1.putString("title1", currentQuestion.getTitle1());
		b1.putString("title2", currentQuestion.getTitle2());
		b1.putString("title3", currentQuestion.getTitle3());
		b1.putString("Common_Attribute", currentQuestion.getCommon_Attribute());				
		b1.putString("Common_Relationship_Text", currentQuestion.getCommon_Relationship_Text());				
		b1.putString("oddTitle", currentQuestion.getOdd_Title());		
		b1.putString("Odd_Attribute", currentQuestion.getOdd_Attribute());				
		b1.putString("odd_Relationship_Text", currentQuestion.getOdd_Relationship_Text());
		b1.putLong("likes", currentQuestion.getLikes());
*/								
		answerIntent.putExtras(b1); 
		
		startActivityForResult(answerIntent, activityResult);
	}
	
}
