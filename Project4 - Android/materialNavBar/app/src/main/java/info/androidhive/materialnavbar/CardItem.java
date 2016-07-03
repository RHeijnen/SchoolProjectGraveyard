package info.androidhive.materialnavbar;

import android.util.Log;

public class CardItem {

    // dingen voor op de cards
    public String name;
    public String age;
    public int photoId;
    public int reportId;
    public int favId;
    public String catId;
    public String photoURL;

    public CardItem(String name, String age, int photoId, int reportId, int favId, String catId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
        this.reportId = reportId;
        this.favId = favId;
        this.catId = catId;
    }
    public CardItem(String name, String age, String photoURL, int reportId, int favId, String catId) {
        this.name = name;
        this.age = age;
        this.photoURL = photoURL;
        this.reportId = reportId;
        this.favId = favId;
        this.catId = catId;
    }
    public CardItem(String name, String age,int reportId, int favId, String catId) {
        this.name = name;
        this.age = age;
        this.reportId = reportId;
        this.favId = favId;
        this.catId = catId;
    }
}