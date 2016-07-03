package info.androidhive.materialnavbar.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import info.androidhive.materialnavbar.AndroidDate;
import info.androidhive.materialnavbar.CardItem;
import info.androidhive.materialnavbar.Fact;
import info.androidhive.materialnavbar.FavoriteManager;
import info.androidhive.materialnavbar.R;
import info.androidhive.materialnavbar.ReportDialog;
import info.androidhive.materialnavbar.ViewAdapters.ListViewMenuAdapter;
import info.androidhive.materialnavbar.fragment.InformationFragment;
import info.androidhive.materialnavbar.fragment.NavigationDrawerFragment;

public class MainActivity extends AppCompatActivity {

    //ez toast
    /* als je iets wilt ''toasten'' :
       Toast toast = Toast.makeText(context, text, duration);
        toast.show();
     */
    //
    private Context context = this;
    private int duration = Toast.LENGTH_LONG;
    private ListViewMenuAdapter adapter = new ListViewMenuAdapter();
    private List<CardItem> cardItems;
    private DrawerLayout mDrawerLayout;
    private InformationFragment informationFragment = new InformationFragment();
    public static int cardcounter = 0;
    private FavoriteManager favoriteManager = null;
    private Toolbar toolbar;
    private boolean isLoved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create fragment and give it an argument specifying the article it should show
        //Initialize the toolbar
        setFragmentInfo(informationFragment);
        initToolbar();
        ///Set categories for the navigation drawer in a listview
        setListMenuItems();


    }

    private void initializeFavorites() {
        favoriteManager = new FavoriteManager(getBaseContext());
    }

    private void setFragmentInfo(InformationFragment f) {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_category, f);
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
    }

    private void loadContent(int type)

    {
        final int t = type;
        new Thread(new Runnable() {
            public void run() {
                informationFragment.getCardData(t, favoriteManager);
            }
        }).start();
    }

    private void setListMenuItems() {
        // zet de list carditem naar die van het slide menu
        final ListView codeLearnLessons = (ListView) findViewById(R.id.listViewId);

        initializeFavorites();
        codeLearnLessons.setAdapter(adapter);
        final TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_title);

        codeLearnLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                long menulist = a.getAdapter().getItemId(position);
                switch ((int) menulist) {
                    case 0:
                        InformationFragment.refresh = false;
//                        informationFragment.getCardData(0, favoriteManager);
                        loadContent(0);
                        textView.setText("Today");
                        break;
                    case 1:
                        InformationFragment.refresh = false;
                        loadContent(1);
//                        informationFragment.getCardData(1, favoriteManager);
                        textView.setText("Facts");
                        break;
                    case 2:
                        InformationFragment.refresh = false;
                        loadContent(2);
//                        informationFragment.getCardData(2, favoriteManager);
                        textView.setText("History");
                        break;
                    case 3:
                        InformationFragment.refresh = false;
                        loadContent(3);
//                        informationFragment.getCardData(3, favoriteManager);
                        textView.setText("Birthdays");
                        break;
                    case 4:
                        InformationFragment.refresh = false;
                        loadContent(4);
//                        informationFragment.getCardData(4, favoriteManager);
                        textView.setText("Lifehacks");
                        break;
                    case 5:
                        InformationFragment.refresh = false;
                        loadContent(5);
//                        informationFragment.getCardData(5, favoriteManager);
                        textView.setText("Quotes");
                        break;
                    case 6:
                        InformationFragment.refresh = false;
                        loadContent(6);
//                        informationFragment.getCardData(6, favoriteManager);
                        textView.setText("Favorites");
                        break;
                }
                adapter.notifyDataSetChanged();
                //codeLearnLessons.setItemChecked(position, true);
                // v.setBackgroundColor(Color.parseColor("#27000000"));
                //adapter.notifyDataSetChanged();
                closeDrawer();
            }
        });
    }

    private void closeDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawers();
    }

    /**
     * Check if there is a Internet Connection available
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


    /**
     * Set the Toolbar as Actionbar
     */
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.drawer_fragment);
        drawerFragment.setUp(R.id.drawer_fragment, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
    }

    // report button
    public void ReportAction(View view) {
        View view1 = (View) view.getParent().getParent(); //Get the Parent(CardView) from the clicked button
        RelativeLayout rv = (RelativeLayout) view1.findViewById(R.id.cardInfo);
        final TextView t1 = (TextView) rv.findViewById(R.id.weetjeContent);
        final TextView t2 = (TextView) rv.findViewById(R.id.weetjeTitel);

        // Alert dialog/PopUp
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Report Article"); // title
        alert.setMessage("Comment :"); // content
        // nieuwe text area
        final EditText input = new EditText(this);
        // zet text area in alert
        alert.setView(input);
        // send button
        /**
         * Create onclicklistener for the pop up
         * Send starts new Thread which sends the comment including the whole fact to PHP script
         */
        alert.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                final String value = input.getText().toString();
                final AndroidDate date = new AndroidDate();
                final ReportDialog report = new ReportDialog();
                new Thread(new Runnable() {
                    public void run() {
                        report.reportFact(String.valueOf(t1.getText()),
                                String.valueOf(date.getDayNumber() + "-" + date.getFullmonthNumber()),
                                String.valueOf(t2.getText()), value);
                    }
                }).start();
                Toast toast = Toast.makeText(context, "Thanks for your feedback!", duration);
                toast.show();
            }
        });
        // cancel button
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        }); // show allert
        alert.show();
    }

    /**
     * Saves or deletes favorite when the favorite button is tapped
     * @param view
     */
    public void ManageFavorites(View view) {
        View favButton = (View) view.getParent().getParent();
        if(favButton == null)
        {
            throw new NullPointerException("Favbutton is null");
        }
        ImageButton imgb = (ImageButton) view.findViewById(R.id.favButton);

        String Title = ((TextView) favButton.findViewById(R.id.weetjeTitel)).getText().toString();
        String description = ((TextView) favButton.findViewById(R.id.weetjeContent)).getText().toString();
        Fact fact = new Fact(Title, description);
        favoriteManager.addFact(fact);
        if(favoriteManager.isFavorite(fact))
        {
            imgb.setImageResource(R.drawable.ic_fav_black);
        }
        else
        {
            imgb.setImageResource(R.drawable.ic_favorites);
        }
        if (informationFragment.getCurrentType() == 6) {
            informationFragment.refreshFragment();
        }
    }

}

