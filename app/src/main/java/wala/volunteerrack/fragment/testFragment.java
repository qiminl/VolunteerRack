package wala.volunteerrack.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import wala.volunteerrack.DBHandler;
import wala.volunteerrack.MainActivity;
import wala.volunteerrack.R;
import wala.volunteerrack.StableArrayAdapter;

/**
 * Created by liuqi on 3/10/2016.
 */
public class testFragment extends Fragment{
    private ViewPager pager = null;
    private MainPagerAdapter pagerAdapter = null;

    private TabLayout tabLayout;
    private View opportunityView, opportunityDetailsView,volunteerRackView;
    private View eventView, collaboratorView, volunteerView;

    private int like_num;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {


        pagerAdapter = new MainPagerAdapter();
        pager = (ViewPager) view.findViewById(R.id.viewpager);
        pager.setAdapter (pagerAdapter);
        pager.setOffscreenPageLimit(1);
        // Create an initial view to display; must be a subclass of FrameLayout.

        setuptViews();
        //pagerAdapter.addView(eventView, 1);
        //pagerAdapter.addView(collaboratorView, 2);
        //pagerAdapter.addView(volunteerView, 3);
        pager.setCurrentItem(0, true);
        pagerAdapter.notifyDataSetChanged();


        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        //tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                String text = "";
                if (tab.getTag() != null) {
                    text = (String) tab.getTag();
                    switch (text) {
                        case "Opportunity":
                            ((MainActivity) getActivity()).setTitle("Opportunity");
                            pagerAdapter.removeView(pager, getCurrentPage());
                            pagerAdapter.notifyDataSetChanged();

                            pagerAdapter.addView(opportunityView, 0);
                            pager.setCurrentItem(0, true);
                            pagerAdapter.notifyDataSetChanged();
                            break;
                        case "Event":
                            ((MainActivity) getActivity()).setTitle("Event");
                            pagerAdapter.removeView(pager, getCurrentPage());
                            pagerAdapter.notifyDataSetChanged();

                            pagerAdapter.addView(eventView, 0);
                            pager.setCurrentItem(0, true);
                            pagerAdapter.notifyDataSetChanged();
                            break;
                        case "Collaborator":
                            ((MainActivity) getActivity()).setTitle("Collaborator");
                            pagerAdapter.removeView(pager, getCurrentPage());
                            pagerAdapter.notifyDataSetChanged();

                            pagerAdapter.addView(collaboratorView, 0);
                            pager.setCurrentItem(0, true);
                            pagerAdapter.notifyDataSetChanged();
                            break;
                        case "Volunteer":
                            ((MainActivity) getActivity()).setTitle("Volunteer");
                            pagerAdapter.removeView(pager, getCurrentPage());
                            pagerAdapter.notifyDataSetChanged();

                            pagerAdapter.addView(volunteerView, 0);
                            pager.setCurrentItem(0, true);
                            pagerAdapter.notifyDataSetChanged();
                            break;
                        default:
                            ((MainActivity) getActivity()).setTitle("Volunteer Rack");
                            pagerAdapter.removeView(pager, getCurrentPage());
                            pagerAdapter.notifyDataSetChanged();
                            pagerAdapter.addView(volunteerRackView, 0);
                            pager.setCurrentItem(0, true);
                            pagerAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setupTabIcons();
        //tabLayout.setupWithViewPager(pager);
    }

    private void setupTabIcons() {
        try{
            //create an hidden tab for start page if needed

            TextView tabOne = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            tabOne.setText("Opportunity");
            tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_favourite, 0, 0);
            tabLayout.addTab(tabLayout.newTab().setCustomView(tabOne));
            tabLayout.getTabAt(0).setTag("Opportunity");
            //tabLayout.getTabAt(0).setCustomView(tabOne);

            TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            tabTwo.setText("Event");
            tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_call, 0, 0);
            tabLayout.addTab(tabLayout.newTab().setCustomView(tabTwo));
            tabLayout.getTabAt(1).setTag("Event");
            //tabLayout.getTabAt(1).setCustomView(tabTwo);

            TextView tabThree = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            tabThree.setText("Collaborator");
            tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_contacts, 0, 0);
            tabLayout.addTab(tabLayout.newTab().setCustomView(tabThree));
            tabLayout.getTabAt(2).setTag("Collaborator");
            //tabLayout.getTabAt(2).setCustomView(tabThree);

            TextView tabFour = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            tabFour.setText("Volunteer");
            tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_contacts, 0, 0);
            tabLayout.addTab(tabLayout.newTab().setCustomView(tabFour));
            tabLayout.getTabAt(3).setTag("Volunteer");
            //tabLayout.getTabAt(3).setCustomView(tabFour);
        }catch (NullPointerException e){
            //todo handle null pointer exc
            Log.d("debug","null pointer @ setupTabIcons");
        }
    }

    private void setuptViews(){
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //opportunityView
        opportunityView = (View)  getActivity().getLayoutInflater().inflate (R.layout.opportunity_view, null);
        ArrayList<String> list = new ArrayList<>();
        list.add("Opportunity 1");list.add("Opportunity 2");list.add("Opportunity 3");list.add("Opportunity 4");
        //todo may change as using dynamic input
        StableArrayAdapter listAdapter = new StableArrayAdapter(getActivity(),R.layout.item_in_list,list);
        //ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_in_list, list);
        ListView listView = (ListView)opportunityView.findViewById(R.id.list);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pagerAdapter.removeView(pager, getCurrentPage());
                pagerAdapter.notifyDataSetChanged();
                pagerAdapter.addView(opportunityDetailsView, 0);
                pager.setCurrentItem(0, true);
                pagerAdapter.notifyDataSetChanged();
            }
        });


        //eventView
        eventView = (View) inflater.inflate (R.layout.frame_layout, null);
        TextView tv1 = (TextView) eventView.findViewById(R.id.text);
        tv1.setText("eventView \n \n \n eventView");


        // collaboratorView
        collaboratorView = (View) inflater.inflate (R.layout.collaborator_view, null);
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("Do What You Love");list2.add("Social diversity");list2.add("Tian -Jin Temple");
        list2.add("XinViteer");list2.add("CSCF");
        //todo may change as using dynamic input
        StableArrayAdapter listAdapter2 = new StableArrayAdapter(getActivity(),R.layout.item_in_list,list2);
        //ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_in_list, list);
        ListView listView2 = (ListView)collaboratorView.findViewById(R.id.list);
        listView2.setAdapter(listAdapter2);


        //volunteerView
        volunteerView = (View) inflater.inflate (R.layout.frame_layout, null);
        TextView tv3 = (TextView) volunteerView.findViewById(R.id.text);
        tv3.setText("volunteerView \n \n \n biubiubiu");


        //opportunityDetailsView
        like_num = 1024;
        opportunityDetailsView = (View) inflater.inflate (R.layout.details_view, null);
        FloatingActionButton fab = (FloatingActionButton) opportunityDetailsView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView tv = (TextView)opportunityDetailsView.findViewById(R.id.like);
                like_num += 1;
                tv.setText("Like: +" + like_num);
            }
        });
        FloatingActionButton comment = (FloatingActionButton) opportunityDetailsView.findViewById(R.id.comment);
        TextView like = (TextView) opportunityDetailsView.findViewById(R.id.like);
        like.setText("Like: +" + like_num);


        //volunteerRackView
        volunteerRackView = (View) inflater.inflate(R.layout.volunteer_rack, null);
        ImageView img =(ImageView) volunteerRackView.findViewById(R.id.img);
        img.setImageResource(R.drawable.volunteer_rack);


        pagerAdapter.addView(volunteerRackView, 0);
    }

    public void creatOpportunityDetais(){}

        //-----------------------------------------------------------------------------
    // Here's what the app should do to add a view to the ViewPager.
    public void addView (View newPage)
    {
        int pageIndex = pagerAdapter.addView(newPage);
        // You might want to make "newPage" the currently displayed page:
        pager.setCurrentItem (pageIndex, true);
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to remove a view from the ViewPager.
    public void removeView (View defunctPage)
    {
        int pageIndex = pagerAdapter.removeView(pager, defunctPage);
        // You might want to choose what page to display, if the current page was "defunctPage".
        if (pageIndex == pagerAdapter.getCount())
            pageIndex--;
        pager.setCurrentItem (pageIndex);
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to get the currently displayed page.
    public View getCurrentPage ()
    {
        return pagerAdapter.getView (pager.getCurrentItem());
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to set the currently displayed page.  "pageToShow" must
    // currently be in the adapter, or this will crash.
    public void setCurrentPage (View pageToShow)
    {
        pager.setCurrentItem (pagerAdapter.getItemPosition (pageToShow), true);
    }


}
