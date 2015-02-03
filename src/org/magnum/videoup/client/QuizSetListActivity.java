package org.magnum.videoup.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class QuizSetListActivity extends Activity {

	@InjectView(R.id.quizSetList)
	protected ListView quizSetList_;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.copyofactivity_video_list);

		ButterKnife.inject(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		refreshVideos();
	}

	private void refreshVideos() {
		final QuizSetSvcApi svc = QuizSetSvc.getOrShowLogin(this);

		if (svc != null) {
			CallableTask.invoke(new Callable<Collection<QuizSet>>() {

				@Override
				public Collection<QuizSet> call() throws Exception {
					return svc.getQuizSetList();
				}
			}, new TaskCallback<Collection<QuizSet>>() {

				@Override
				public void success(Collection<QuizSet> result) {
					List<String> names = new ArrayList<String>();
					for (QuizSet q : result) {
//						names.add(q.getName());
						names.add(q.getTitle1());						
					}
					quizSetList_.setAdapter(new ArrayAdapter<String>(
							QuizSetListActivity.this,
							android.R.layout.simple_list_item_1, names));
				}

				@Override
				public void error(Exception e) {
					Toast.makeText(
							QuizSetListActivity.this,
							"Unable to access Mutibo server, please try again.",
							Toast.LENGTH_SHORT).show();

					startActivity(new Intent(QuizSetListActivity.this,
							LoginScreenActivity.class));
				}
			});
		}
	}

}
