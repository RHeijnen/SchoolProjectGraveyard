package info.androidhive.materialnavbar.ViewAdapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialnavbar.MenuList;
import info.androidhive.materialnavbar.R;

/**
 * Created by bart on 28-5-2015
 * 2015 - 05
 * Adapter for the ListView, 
 */


public class ListViewMenuAdapter extends BaseAdapter {


    private List<MenuList> codeLearnChapterList = getDataForMenu();



    /**
     * Passes the MenuList object to a List
     *
     * @return the List with MenuList objects
     */
    public List<MenuList> getDataForMenu() {
        String[] category = {"Today", "Facts", "History", "Birthdays", "Lifehacks", "Quotes","Favorites"}; //List of categories
        int[] icons = {R.drawable.ic_today, R.drawable.ic_facts, R.drawable.ic_history, R.drawable.ic_birthdays, R.drawable.ic_lifehacks, R.drawable.ic_quotes,R.drawable.ic_favorites}; //List of Menu icons

        List<MenuList> menuItems = new ArrayList<MenuList>();
        for (int i = 0; i < 7; i++) {
            MenuList m = new MenuList();
            m.setIcon(icons[i]);
            m.setCategory(category[i]);
            menuItems.add(m);
        }
        return menuItems;
    }


    /**
     * returns the size of the object list
     */
    @Override
    public int getCount() {
        return codeLearnChapterList.size();
    }

    @Override
    public Object getItem(int position) {
        return codeLearnChapterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * Method create's a view for the ListView
     * @return View contains object MenuList which get placed inside the ListView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //If the convertView equals null, there is no convertView already initialized
        //otherwise the getView method "recycles" the convertView
        //this is for the performance of the MenuList
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(parent.getContext()); //Inflate the layout if there is no convertView
            convertView = inflater.inflate(R.layout.custom_row, parent, false); //set the convertView to the inflate layout with XML custom_row
        }
        ImageView icon = (ImageView) convertView.findViewById(R.id.list_icon); //findTheElementById
        TextView category = (TextView) convertView.findViewById(R.id.list_title); //findTheElementById
        MenuList chapter = codeLearnChapterList.get(position);

        icon.setImageResource(chapter.getIcon());
        category.setText(chapter.getCategory());
        convertView.setBackgroundColor(Color.TRANSPARENT);


        return convertView; //return the content view with the object
    }

}
