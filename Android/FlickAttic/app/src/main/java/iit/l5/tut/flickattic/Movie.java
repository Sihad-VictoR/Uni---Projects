package iit.l5.tut.flickattic;

import java.io.Serializable;

//POJO Class
public class Movie implements Serializable {
    private long mId;
    private String mTitle;
    private String mYear;
    private String mDirector;
    private String mActs;
    private String mRating;
    private String mReview;
    private boolean mFavorites;

    public Movie(long mId, String mTitle, String mYear, String mDirector, String mActs, String mRating, String mReview, boolean mFavorites) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mYear = mYear;
        this.mDirector = mDirector;
        this.mActs = mActs;
        this.mRating = mRating;
        this.mReview = mReview;
        this.mFavorites = mFavorites;
    }

    public long getMId() {
        return mId;
    }

    public void setMId(long mId) {
        this.mId = mId;
    }

    public String getMTitle() {
        return mTitle;
    }

    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getMYear() {
        return mYear;
    }

    public void setMYear(String mYear) {
        this.mYear = mYear;
    }

    public String getMDirector() {
        return mDirector;
    }

    public void setMDirector(String mDirector) {
        this.mDirector = mDirector;
    }

    public String getMActs() {
        return mActs;
    }

    public void setMActs(String mActs) {
        this.mActs = mActs;
    }

    public String getMRating() {
        return mRating;
    }

    public void setMRating(String mRating) {
        this.mRating = mRating;
    }

    public String getMReview() {
        return mReview;
    }

    public void setMReview(String mReview) {
        this.mReview = mReview;
    }

    public boolean isMFavorites() {
        return mFavorites;
    }

    public void setMFavorites(boolean mFavorites) {
        this.mFavorites = mFavorites;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "mTitle='" + mTitle + '\'' +
                ", mYear='" + mYear + '\'' +
                ", mDirector='" + mDirector + '\'' +
                ", mActs='" + mActs + '\'' +
                ", mRating='" + mRating + '\'' +
                ", mReview='" + mReview + '\'' +
                ", mFavorites=" + mFavorites +
                '}';
    }

}
