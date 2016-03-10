package wala.volunteerrack.fragment;

import android.app.TabActivity;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import wala.volunteerrack.MainActivity;
import wala.volunteerrack.R;
import wala.volunteerrack.StableArrayAdapter;

/**
 * Created by liuqi on 3/9/2016.
 */
public class MyTabViews {


    private MainActivity mainActivity;
    private ViewGroup container;
    private View view;
    private tabBasicFragment.SamplePagerAdapter samplePagerAdapter;
    private int position;
    private String LOG_TAG = "debug";

    public MyTabViews(MainActivity mainActivity, tabBasicFragment.SamplePagerAdapter samplePagerAdapter,
                      ViewGroup container,  int position){
        this.mainActivity = mainActivity;
        this.samplePagerAdapter = samplePagerAdapter;
        this.container = container;
        this.position = position;
    }
    /**
     * View of Opportunity tab
     * @param view
     * @param position
     */
    public void opportunityView(View view, int position, final View currentView){
        mainActivity.setTitle("Opportunity");
        /**
         * todo Use linear layout instead of list view if needed scrollable inside scrollable
         */
        ArrayList<String> list = new ArrayList<>();
        list.add("comment 1");list.add("comment 2");list.add("comment 3");list.add("comment 4");
        //todo may change as using dynamic input
        StableArrayAdapter listAdapter = new StableArrayAdapter(mainActivity,R.layout.item_in_list,list);
        //ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_in_list, list);
        ListView listView = (ListView)view.findViewById(R.id.list);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            /**
             * position and id is the position inside list view, referring to list item
             * View is
             * parent is the AdapterView, as the container of the actual view page area.
             */
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                samplePagerAdapter.setOppertunityDetails(position);
                samplePagerAdapter.instantiateItem(container, 4);
            }
        });

        Log.i("debug", "instantiateItem() [position: " + position + "]");
        // Return the View

        container.addView(view);
    }

    /**
     * View of Opportunity tab
     * @param view
     * @param position
     */
    public void eventView(View view, int position){
        mainActivity.setTitle("Event");
        Log.d("debug","case " + position +" title: " +"Event" );

        // Retrieve a TextView from the inflated View, and update it's text
        TextView description = (TextView) view.findViewById(R.id.item_title);
        //title.setText(String.valueOf(position + 1));
        String tempDescription = "1!";
        description.setText(tempDescription);

        /**
         * todo Use linear layout instead of list view if needed scrollable inside scrollable
         */
        ArrayList<String> list = new ArrayList<>();
        list.add("comment 1");list.add("comment 2");list.add("comment 3");list.add("comment 4");
        //todo may change as using dynamic input
        StableArrayAdapter listAdapter = new StableArrayAdapter(mainActivity,R.layout.item_in_list,list);
        //ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_in_list, list);
        ListView listView = (ListView)view.findViewById(R.id.list);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            /**
             * position and id is the position inside list view, referring to list item
             * View is
             * parent is the AdapterView, as the container of the actual view page area.
             */
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(LOG_TAG, "On item click view tag is : " + view.getTag() );
                Log.i(LOG_TAG, "On item click view id is : " + view.getId() );
                samplePagerAdapter.instantiateItem((ViewGroup)parent.getParent(), 4);
            }
        });
        Log.i(LOG_TAG, "instantiateItem() [position: " + position + "]");
        // Return the View
        container.addView(view);
    }

    /**
     * View of Opportunity tab
     * @param view
     * @param position
     */
    public void collaboratorView(View view, int position){
        mainActivity.setTitle("Collaborator");
        Log.d("debug", "case " + position + " title: " + "Collaborator");

        /**
         * todo Use linear layout instead of list view if needed scrollable inside scrollable
         */
        ArrayList<String> list = new ArrayList<>();
        list.add("comment 1");list.add("comment 2");list.add("comment 3");list.add("comment 4");
        //todo may change as using dynamic input
        StableArrayAdapter listAdapter = new StableArrayAdapter(mainActivity,R.layout.item_in_list,list);
        //ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_in_list, list);
        ListView listView = (ListView)view.findViewById(R.id.list);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            /**
             * position and id is the position inside list view, referring to list item
             * View is
             * parent is the AdapterView, as the container of the actual view page area.
             */
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int old = samplePagerAdapter.getItemPosition(view);
                Log.i(LOG_TAG, "On item click view position : " + old);
                samplePagerAdapter.instantiateItem((ViewGroup) parent.getParent(), 4);
            }
        });
        Log.i(LOG_TAG, "instantiateItem() [position: " + position + "]");
        // Return the View
        container.addView(view);
    }

    /**
     * View of Opportunity tab
     * @param view
     * @param position
     */
    public void volunteerView(View view, int position){
        mainActivity.setTitle("Volunteer");
        Log.d("debug","case " + position +" title: " +"Volunteer" );

        // Retrieve a TextView from the inflated View, and update it's text
        TextView description = (TextView) view.findViewById(R.id.item_title);
        //title.setText(String.valueOf(position + 1));
        String tempDescription = "1!";
        description.setText(tempDescription);

        /**
         * todo Use linear layout instead of list view if needed scrollable inside scrollable
         */
        ArrayList<String> list = new ArrayList<>();
        list.add("comment 1");list.add("comment 2");list.add("comment 3");list.add("comment 4");
        //todo may change as using dynamic input
        StableArrayAdapter listAdapter = new StableArrayAdapter(mainActivity,R.layout.item_in_list,list);
        //ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_in_list, list);
        ListView listView = (ListView)view.findViewById(R.id.list);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            /**
             * position and id is the position inside list view, referring to list item
             * View is
             * parent is the AdapterView, as the container of the actual view page area.
             */
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(LOG_TAG, "On item click view tag is : " + view.getTag() );
                Log.i(LOG_TAG, "On item click view id is : " + view.getId() );
                samplePagerAdapter.instantiateItem((ViewGroup)parent.getParent(), 4);
            }
        });
        Log.i(LOG_TAG, "instantiateItem() [position: " + position + "]");
        // Return the View
        container.addView(view);
    }

    /**
     * View of Opportunity tab
     * @param view
     * @param position
     */
    public void detailsView(View view, int position , int oppertunityDetails, final View currentView ){
        mainActivity.setTitle("Details");
        Log.d("debug","case " + position +" title: " +"Details" );

        // Retrieve a TextView from the inflated View, and update it's text
        TextView description = (TextView) view.findViewById(R.id.item_title);
        //title.setText(String.valueOf(position + 1));
        String tempDescription = "1!";
        description.setText(tempDescription);

        /**
         * todo Use linear layout instead of list view if needed scrollable inside scrollable
         */
        ArrayList<String> list = new ArrayList<>();
        list.add("comment 1");list.add("comment 2");list.add("comment 3");list.add("comment 4");
        //todo may change as using dynamic input
        StableArrayAdapter listAdapter = new StableArrayAdapter(mainActivity,R.layout.item_in_list,list);
        //ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_in_list, list);
        ListView listView = (ListView)view.findViewById(R.id.list);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            /**
             * position and id is the position inside list view, referring to list item
             * View is
             * parent is the AdapterView, as the container of the actual view page area.
             */
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(LOG_TAG, "On item click view tag is : " + view.getTag());
                Log.i(LOG_TAG, "On item click view id is : " + view.getId());
                samplePagerAdapter.instantiateItem((ViewGroup) parent.getParent(), 4);
            }
        });
        // Return the View
        container.addView(view);
    }
}
