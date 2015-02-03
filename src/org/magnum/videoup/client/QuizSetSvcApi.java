package org.magnum.videoup.client;

import java.util.Collection;


import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * This interface defines an API for a VideoSvc. The
 * interface is used to provide a contract for client/server
 * interactions. The interface is annotated with Retrofit
 * annotations so that clients can automatically convert the
 * 
 * 
 * @author jules
 *
 */
public interface QuizSetSvcApi {
	
	public static final String PASSWORD_PARAMETER = "password";

	public static final String USERNAME_PARAMETER = "username";

	public static final String TITLE_PARAMETER = "title";
	
	public static final String DURATION_PARAMETER = "duration";
	
	public static final String TOKEN_PATH = "/oauth/token";
	
	// The path where we expect the VideoSvc to live
	//public static final String VIDEO_SVC_PATH = "/video";
	
	public static final String MOVIESET_SVC_PATH = "/movie";
	

	// The path to search videos by title
//	public static final String VIDEO_TITLE_SEARCH_PATH = MOVIESET_SVC_PATH + "/search/findByName";
	
	// The path to search videos by title
	//	public static final String VIDEO_DURATION_SEARCH_PATH = MOVIESET_SVC_PATH + "/search/findByDurationLessThan";

	@GET(MOVIESET_SVC_PATH)
	public Collection<QuizSet> getQuizSetList();
	
	//@GET(MOVIESET_SVC_PATH + "/{id}")
	//public QuizSet getVideoById(@Path("id") long id);
	
	//@POST(MOVIESET_SVC_PATH)
	//public QuizSet addVideo(@Body QuizSet v);
	
//	@POST(MOVIESET_SVC_PATH + "/{id}/like")
	//	public Void likeVideo(@Path("id") long id);

	//@POST(MOVIESET_SVC_PATH + "/{id}/dislike")
	//public Void disLikeVideo(@Path("id") long id);
	
	
	//@POST(MOVIESET_SVC_PATH + "/{id}/unlike")
	//public Void unlikeVideo(@Path("id") long id);
	
	//	@GET(VIDEO_TITLE_SEARCH_PATH)
	//public Collection<QuizSet> findByTitle(@Query(TITLE_PARAMETER) String title);
	
	//@GET(VIDEO_DURATION_SEARCH_PATH)
	//public Collection<QuizSet> findByDurationLessThan(@Query(DURATION_PARAMETER) long duration);
	
	//@GET(MOVIESET_SVC_PATH + "/{id}/likedby")
	//public Collection<String> getUsersWhoLikedQuizSet(@Path("id") long id);
	
}
