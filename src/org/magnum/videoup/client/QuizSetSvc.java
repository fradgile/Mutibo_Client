/* 
 **
 ** Copyright 2014, Jules White
 **
 ** 
 */
package org.magnum.videoup.client;

import org.magnum.videoup.client.oauth.SecuredRestBuilder;
import org.magnum.videoup.client.unsafe.EasyHttpClient;

import retrofit.RestAdapter.LogLevel;
import retrofit.client.ApacheClient;
import android.content.Context;
import android.content.Intent;

public class QuizSetSvc {

	public static final String CLIENT_ID = "mobile";

	private static QuizSetSvcApi quizSetSvc_;

	public static synchronized QuizSetSvcApi getOrShowLogin(Context ctx) {
		if (quizSetSvc_ != null) {
			return quizSetSvc_;
		} else {
			Intent i = new Intent(ctx, LoginScreenActivity.class);
			ctx.startActivity(i);
			return null;
		}
	}

	public static synchronized QuizSetSvcApi init(String server, 
												String user,
												String pass) 
	{
		quizSetSvc_ = new SecuredRestBuilder()
				.setLoginEndpoint(server + QuizSetSvcApi.TOKEN_PATH)
				.setUsername(user)
				.setPassword(pass)
				.setClientId(CLIENT_ID)
				.setClient(
						new ApacheClient(new EasyHttpClient()))
				.setEndpoint(server).setLogLevel(LogLevel.FULL).build()
				.create(QuizSetSvcApi.class);

		return quizSetSvc_;
	}
}
