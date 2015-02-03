package org.magnum.videoup.client;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class SimpleIntentService extends IntentService {
    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";
 
	private static final String TAG = "SimpleIntentService";
    
    public SimpleIntentService() {
        super("SimpleIntentService");
    	Log.d(TAG,"Inside SimpleIntentService");        
    }
 
    @Override
    protected void onHandleIntent(Intent intent) {
 
    	Log.d(TAG,"Inside onHandleIntent");
    	
    	QuizSet question = (QuizSet) intent.getExtras().getParcelable("Question");

    	Log.d(TAG,"question.getTitle1() = " + question.getTitle1());
    	
    	DbHelper db=new DbHelper(this);
		db.updateQuizSet(question);
    	
        //String msg = intent.getStringExtra(PARAM_IN_MSG);
		//SystemClock.sleep(3000); // 30 seconds
		//String resultTxt = msg + " "
		//    + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis());
    }

}
