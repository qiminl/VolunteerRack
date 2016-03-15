package wala.volunteerrack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import wala.volunteerrack.fragment.testFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String Title = "Volunteer Rack";

    private int fragmentCounter = 0;
    private int item1_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Volunteer Rack");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d("debug","aMain activity created");

        DBHandler myDiaryDBHandler = new DBHandler(this);
        //myDiaryDBHandler.addComment();

        /**
         * In this case, we only handle newly created activity.
         */
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //tabBasicFragment fragment = new tabBasicFragment();
            testFragment fragment = new testFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
    }

    /**
     * For Menu options in Toolbar
     * @param menu Menu item.
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item1 = menu.findItem(R.id.item1);
        item1.setIcon(R.drawable.ic_tab_call);
        item1_id = item1.getItemId();
        MenuItem item2 = menu.findItem(R.id.item2);

        MenuItem item3 = menu.findItem(R.id.item3);

        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Log.d("debug", "home key pressed");
        }
        if(item.getItemId() == item1_id){
            Log.d("debug","Menu Item Selected :" + item.getItemId());

            Log.d("debug", "fragments # =" + getSupportFragmentManager().getFragments().size());
            List<Fragment> fl = getSupportFragmentManager().getFragments();
            for(Fragment f : fl)
                getSupportFragmentManager().beginTransaction().remove(f).commit();
        }

        Log.d("debug", "fragments # =" + getSupportFragmentManager().getFragments().size());

        return super.onOptionsItemSelected(item);
    }

    public void setTitle(String title) {
        Title = title;
        toolbar.setTitle(title);
        Log.d("debug","set title clicked");
    }

}
