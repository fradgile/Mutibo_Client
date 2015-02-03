package org.magnum.videoup.client;

import com.google.common.base.Objects;
import java.util.HashSet;
import java.util.Set;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * A simple object to represent a QuizSet and its URL for viewing.
 * 
 * 
 */
public class QuizSet implements Parcelable {

	private long combination;

	private String media_Type;
	private String question_Type;
	private String common_Attribute;
	private String common_Relationship_Text;
	private String title1;
	private String title2;
	private String title3;
	private String odd_Title;
	private String odd_Attribute;
	private String odd_Relationship_Text;

	private long likes;
	
	private Set<String> likesUsernames = new HashSet<String>();
	private Set<String> disLikesUsernames = new HashSet<String>();
	
	
	public QuizSet(){};
	public QuizSet(String media_Type, String question_Type,
			String common_Attribute, String common_Relationship_Text,
			String title1, String title2, String title3, String odd_Title,
			String odd_Attribute, String odd_Relationship_Text, long likes) {
		super();
		this.media_Type = media_Type;
		this.question_Type = question_Type;
		this.common_Attribute = common_Attribute;
		this.common_Relationship_Text = common_Relationship_Text;
		this.title1 = title1;
		this.title2 = title2;
		this.title3 = title3;
		this.odd_Title = odd_Title;
		this.odd_Attribute = odd_Attribute;
		this.odd_Relationship_Text = odd_Relationship_Text;
		this.likes = likes;
	}

	public long getCombination() {
		return combination;
	}

	public String getMedia_Type() {
		return media_Type;
	}

	public String getQuestion_Type() {
		return question_Type;
	}

	public String getCommon_Attribute() {
		return common_Attribute;
	}

	public String getCommon_Relationship_Text() {
		return common_Relationship_Text;
	}

	public String getTitle1() {
		return title1;
	}

	public String getTitle2() {
		return title2;
	}

	public String getTitle3() {
		return title3;
	}

	public String getOdd_Title() {
		return odd_Title;
	}

	public String getOdd_Attribute() {
		return odd_Attribute;
	}

	public String getOdd_Relationship_Text() {
		return odd_Relationship_Text;
	}

	public long getLikes() {
		return likes;
	}

	public void setCombination(long combination) {
		this.combination = combination;
	}

	public void setMedia_Type(String media_Type) {
		this.media_Type = media_Type;
	}

	public void setQuestion_Type(String question_Type) {
		this.question_Type = question_Type;
	}

	public void setCommon_Attribute(String common_Attribute) {
		this.common_Attribute = common_Attribute;
	}

	public void setCommon_Relationship_Text(String common_Relationship_Text) {
		this.common_Relationship_Text = common_Relationship_Text;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public void setTitle3(String title3) {
		this.title3 = title3;
	}

	public void setOdd_Title(String odd_Title) {
		this.odd_Title = odd_Title;
	}

	public void setOdd_Attribute(String odd_Attribute) {
		this.odd_Attribute = odd_Attribute;
	}

	public void setOdd_Relationship_Text(String odd_Relationship_Text) {
		this.odd_Relationship_Text = odd_Relationship_Text;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.getCommon_Attribute());
		dest.writeString(this.getCommon_Relationship_Text());
		dest.writeString(this.getMedia_Type());		
		dest.writeString(this.getOdd_Attribute());
		dest.writeString(this.getOdd_Relationship_Text());
		dest.writeString(this.getOdd_Title());
		dest.writeString(this.getQuestion_Type());		
		dest.writeString(this.getTitle1());
		dest.writeString(this.getTitle2());		
		dest.writeString(this.getTitle3());
		dest.writeLong(this.getCombination());
		dest.writeLong(this.getLikes());
	}

	public static final Parcelable.Creator<QuizSet> CREATOR
    	= new Parcelable.Creator<QuizSet>() {
		public QuizSet createFromParcel(Parcel in) {
			return new QuizSet(in);
		}

		public QuizSet[] newArray(int size) {
			return new QuizSet[size];
		}
	};
	private QuizSet(Parcel in) {		
		this.common_Attribute = in.readString();
		this.common_Relationship_Text = in.readString();		
		this.media_Type = in.readString();
		this.odd_Attribute = in.readString();
		this.odd_Relationship_Text = in.readString();
		this.odd_Title = in.readString();		
		this.question_Type = in.readString();
		this.title1 = in.readString();
		this.title2 = in.readString();
		this.title3 = in.readString();
		this.combination = in.readLong();		
		this.likes = in.readLong();				
    }
	
	/**
	 * Two sets are considered equal if they have exactly the same values for
	 * title1 title2 title3 common_Attribute odd_Title odd_Relationship_Text
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof QuizSet) {
			QuizSet other = (QuizSet) obj;
			// Google Guava provides great utilities for equals too!
			return Objects.equal(title1, other.title1)
					&& Objects.equal(title2, other.title2)
					&& Objects.equal(title3, other.title3)
					&& Objects.equal(common_Attribute, other.common_Attribute)
					&& Objects.equal(odd_Title, other.odd_Title)
					&& odd_Relationship_Text == other.odd_Relationship_Text;
		} else {
			return false;
		}
	}
}