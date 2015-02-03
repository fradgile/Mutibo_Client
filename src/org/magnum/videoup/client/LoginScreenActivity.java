package org.magnum.videoup.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 
 * This application uses ButterKnife. AndroidStudio has better support for
 * ButterKnife than Eclipse, but Eclipse was used for consistency with the other
 * courses in the series. If you have trouble getting the login button to work,
 * please follow these directions to enable annotation processing for this
 * Eclipse project:
 * 
 * http://jakewharton.github.io/butterknife/ide-eclipse.html
 * 
 */
public class LoginScreenActivity extends Activity {

	private static final String TAG = "LoginScreenActivity";
	
	@InjectView(R.id.userName)
	protected EditText userName_;

	@InjectView(R.id.password)
	protected EditText password_;

	@InjectView(R.id.server)
	protected EditText server_;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);

		ButterKnife.inject(this);
	}

	@OnClick(R.id.loginButton)
	public void login() {
		String user = userName_.getText().toString();
		String pass = password_.getText().toString();
		String server = server_.getText().toString();

		final QuizSetSvcApi svc = QuizSetSvc.init(server, user, pass);

		CallableTask.invoke(new Callable<Collection<QuizSet>>() {

			@Override
			public Collection<QuizSet> call() throws Exception {
				return svc.getQuizSetList();
			}
		}, new TaskCallback<Collection<QuizSet>>() {

			
			@Override
			public void success(Collection<QuizSet> result) {
				// OAuth 2.0 grant was successful and we
				// can talk to the server
				
				ArrayList<QuizSet> QuizSetArray = new ArrayList<QuizSet >();
				for (QuizSet q : result) {
					QuizSetArray.add(q);					
				}				
				
				Bundle b = new Bundle();				
				b.putParcelableArrayList("Questions", QuizSetArray);				
				Intent intent = new Intent(LoginScreenActivity.this, QuizActivity.class);
				intent.putExtras(b);
/*				
				Bundle b = new Bundle();				
				b.putParcelable("Questions", result.iterator().next());				
				Intent intent = new Intent(LoginScreenActivity.this, QuizActivity.class);
				intent.putExtras(b);				
*/				
				startActivity(intent);
			}

			@Override
			public void error(Exception e) {
				Log.e(LoginScreenActivity.class.getName(), "Error logging in via OAuth.", e);
				
				Toast.makeText(
						LoginScreenActivity.this,
						"Login failed, check your Internet connection and credentials.",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}
