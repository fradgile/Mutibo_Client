package org.magnum.videoup.client;
import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DbHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "movieQuiz";
	// movie table name
	private static final String TABLE_QUESTIONS = "questions";
	// movie Table Columns names
	
	private static final String COMBINATION_ID = "combination";
	private static final String MEDIA_TYPE = "media_type";
	private static final String QUESTION_TYPE = "question_type";
	private static final String COMMON_ATTRIBUTE = "common_attribute";
	private static final String COMMON_RELATIONSHIP_TEXT = "common_relationship_text";
	private static final String TITLE1 = "title1";
	private static final String TITLE2 = "title2";
	private static final String TITLE3 = "title3";
	private static final String ODD_TITLE = "odd_title";
	private static final String ODD_ATTRIBUTE = "odd_attribute";
	private static final String ODD_RELATIONSHIP_TEXT = "odd_relationship_text";	
	private static final String LIKES = "likes";	

	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUESTIONS + " ( "
				    + COMBINATION_ID 	+ " INTEGER PRIMARY KEY, " 
				    + MEDIA_TYPE 		+ " TEXT, " 
				    + QUESTION_TYPE 	+ " TEXT, "
				    + COMMON_ATTRIBUTE 	+ " TEXT, "
				    + COMMON_RELATIONSHIP_TEXT 	+ " TEXT, "
				    + TITLE1 			+ " TEXT, "
				    + TITLE2 			+ " TEXT, "				    
				    + TITLE3 			+ " TEXT, "
				    + ODD_TITLE 		+ " TEXT, "
				    + ODD_ATTRIBUTE 	+ " TEXT, "				    
				    + ODD_RELATIONSHIP_TEXT 	+ " TEXT, "				    
				    + LIKES	+ " LONG)";
		db.execSQL(sql);		
		//addQuestions();
		//db.close();
	}
	public void addQuestions()
	{
		QuizSet q1=new QuizSet("MOVIE","GENRE","Science Fiction","Belongs to the Science Fiction genre.","ET","Ghost in the Shell: Stand Alone Complex - The Laughing Man","Guardians of the Galaxy","Fury","War","Belongs to the War genre.",0);
		this.addQuizSet(q1);		
		QuizSet q2=new QuizSet("MOVIE","GENRE","Science Fiction","Belongs to the Science Fiction genre.","ET","Ghost in the Shell: Stand Alone Complex - The Laughing Man","Guardians of the Galaxy","The Guns of Navarone","War","Belongs to the War genre.",0);
		this.addQuizSet(q2);		
		QuizSet q3=new QuizSet("MOVIE","GENRE","Science Fiction","Belongs to the Science Fiction genre.","ET","Ghost in the Shell: Stand Alone Complex - The Laughing Man","Guardians of the Galaxy","Army of Shadows","War","Belongs to the War genre.",0);
		this.addQuizSet(q3);		
		QuizSet q4=new QuizSet("MOVIE","GENRE","Science Fiction","Belongs to the Science Fiction genre.","ET","Ghost in the Shell: Stand Alone Complex - The Laughing Man","Guardians of the Galaxy","The Enemy Below","War","Belongs to the War genre.",0);
		this.addQuizSet(q4);
		QuizSet q5=new QuizSet("MOVIE","GENRE","Science Fiction","Belongs to the Science Fiction genre.","ET","Ghost in the Shell: Stand Alone Complex - The Laughing Man","Guardians of the Galaxy","Don't Look Now: We're Being Shot At","War","Belongs to the War genre.",0);
		this.addQuizSet(q5);
		QuizSet q6=new QuizSet("MOVIE","GENRE","Science Fiction","Belongs to the Science Fiction genre.","ET","Ghost in the Shell: Stand Alone Complex - The Laughing Man","Guardians of the Galaxy","Underground","War","Belongs to the War genre.",0);
		this.addQuizSet(q6);
		QuizSet q7=new QuizSet("MOVIE","GENRE","Science Fiction","Belongs to the Science Fiction genre.","ET","Ghost in the Shell: Stand Alone Complex - The Laughing Man","Guardians of the Galaxy","Land and Freedom","War","Belongs to the War genre.",0);
		this.addQuizSet(q7);		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
		// Create tables again
		onCreate(db);
	}
	// Adding new question
	

	public void addQuizSet(QuizSet question) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
//		values.put(COMBINATION_ID, question.getCombination());
		values.put(MEDIA_TYPE, question.getMedia_Type());
		values.put(QUESTION_TYPE, question.getQuestion_Type());		
		values.put(COMMON_ATTRIBUTE, question.getCommon_Attribute());
		values.put(COMMON_RELATIONSHIP_TEXT, question.getCommon_Relationship_Text());
		values.put(TITLE1, question.getTitle1());		
		values.put(TITLE2, question.getTitle2());
		values.put(TITLE3, question.getTitle3());
		values.put(ODD_TITLE, question.getOdd_Title());
		values.put(ODD_ATTRIBUTE, question.getOdd_Attribute());
		values.put(ODD_RELATIONSHIP_TEXT, question.getOdd_Relationship_Text());		
		values.put(LIKES, question.getLikes());		
		// Inserting Row
		db.insert(TABLE_QUESTIONS, null, values);		
		db.close();
	}
	public List<QuizSet> getAllQuestions() {
		List<QuizSet> quesList = new ArrayList<QuizSet>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS;
		SQLiteDatabase db =this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {

				QuizSet question = new QuizSet();
				question.setCombination(cursor.getInt(0));
				question.setMedia_Type(cursor.getString(1));
				question.setQuestion_Type(cursor.getString(2));				
				question.setCommon_Attribute(cursor.getString(3));
				question.setCommon_Relationship_Text(cursor.getString(4));
				question.setTitle1(cursor.getString(5));
				question.setTitle2(cursor.getString(6));
				question.setTitle3(cursor.getString(7));				
				question.setOdd_Title(cursor.getString(8));				
				question.setOdd_Attribute(cursor.getString(9));
				question.setOdd_Relationship_Text(cursor.getString(10));							
//				question.setLikes(cursor.getInt(11));
				question.setLikes(0);				
				
				quesList.add(question);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	
	public void deleteAllQuestions() {
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABLE_QUESTIONS, null, null); 
	    db.close();
	}
	
	public void updateQuizSet(QuizSet question) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(LIKES, question.getLikes());		
		db.update(TABLE_QUESTIONS, values, COMBINATION_ID + "=" + Long.toString(question.getCombination()), null );		
		db.close();
	}
	
	public int rowcount()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		cursor.close();
		return row;
	}
		
}
