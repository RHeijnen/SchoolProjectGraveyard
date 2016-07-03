package info.androidhive.materialnavbar;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mark on 14-6-15.
 */
public class FavoriteManager implements Serializable {
    private ArrayList<Fact> Favorites;
    private Context MainContext;
    private final String FILENAME = "favorites.fav";
    private int duration = Toast.LENGTH_LONG;

    /**
     * @return THe list of all the facts saved as favorite
     */
    public ArrayList<Fact> getFavorites() {
        return Favorites;
    }

    public FavoriteManager(Context x) {
        Favorites = new ArrayList<Fact>();
        MainContext = x;
        serializeRead();

    }
    public void noInternetMessage()
    {


    }


    private void Test() {
        Log.i("TEST : ", String.valueOf(Favorites.size()));
    }

    /**
     * Adds a fact to the list and saves it on the device
     * @param f The fact that needs to be added to favorites
     */
    public void addFact(Fact f) {
        serializeRead();
        if (!removeExistingFact(f)) {
            Favorites.add(f);
            Toast toast = Toast.makeText(MainContext, "Added to favorites", duration);
            toast.show();
            Test();
            serializeWrite();


        }
    }

    /**
     * Checks if a fact is a favorite
     * @param f The fact that needs to be checked
     * @return Returns true when the fact is a favorite.
     */
    public boolean isFavorite(Fact f) {

        String description = f.getDescription();
        for (int i = 0; i < Favorites.size(); i++) {
            Fact factFromList = Favorites.get(i);
            if (description.equals(factFromList.getDescription())) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if fact is already a favorite. If so, it removes it.
     *
     * @param f The fact in question
     * @return Returns true when the fact already exists
     */

    private boolean removeExistingFact(Fact f) {
        String description = f.getDescription();

        for (int i = 0; i < Favorites.size(); i++) {
            Fact factFromList = Favorites.get(i);
            if (description.equals(factFromList.getDescription())) {
                Favorites.remove(i);
                serializeWrite();
                Test();
                Toast toast = Toast.makeText(MainContext,"Removed from favorites" , duration);
                toast.show();
                return true;

            }
        }
        return false;
    }

    /**
     * Saves the current favorites list
     */
    private void serializeWrite() {

        try {
            FileOutputStream fos = MainContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(Favorites);
            os.close();

        } catch (IOException e) {
            Log.e("SER ERROR", e.toString());
        }


    }

    /**
     * Reads the saved list and adds it to the defined ArrayList
     */
    private void serializeRead() {
        try {

            FileInputStream fis = MainContext.openFileInput(FILENAME);
            ObjectInputStream is = new ObjectInputStream(fis);
            ArrayList<Fact> facts = (ArrayList<Fact>) is.readObject();
            is.close();
            Favorites = facts;

        } catch (IOException e) {
            Log.e("ERROR : ", e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR : ", e.toString());
        }
    }
}