package wala.volunteerrack.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import wala.volunteerrack.MainActivity;
import wala.volunteerrack.R;
import wala.volunteerrack.SlidingTabLayout;

/**
 * Created by liuqi on 3/7/2016.
 */
public class tabBasicFragment extends Fragment {

    private final String LOG_TAG = "debug";
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private SamplePagerAdapter samplePagerAdapter;

    private ArrayList<View> view_list = new ArrayList<>();

    private MainPagerAdapter pagerAdapter = null;
    private static int currentPage = 0;

    private int opportunityDetails = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {


        // Get the ViewPager and set it's PagerAdapter so that it can display items
        //samplePagerAdapter = new SamplePagerAdapter();

        pagerAdapter = new MainPagerAdapter();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        FrameLayout v0 = (FrameLayout) inflater.inflate (R.layout.frame_layout, null);
        TextView tv = (TextView)v0.findViewById(R.id.text);
        tv.setText("diudiduiududidu\n diudiudiud\n");
        pagerAdapter.addView(v0, 0);
        FrameLayout v1 = (FrameLayout) inflater.inflate (R.layout.frame_layout, null);
        TextView tv1 = (TextView)v0.findViewById(R.id.text);
        tv.setText("diudiduiududidu\n diudiudiud\n");
        pagerAdapter.addView(v1, 1);

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        //set how many page should be load each time (including background view)
        //mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(samplePagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(LOG_TAG, "page selected " + position);
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //pagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(pagerAdapter.getItemPosition(v1), true);
        Log.d("debug", "item position of v0" + pagerAdapter.getItemPosition(v0));

        Log.d("debug","item numbers"+pagerAdapter.getCount());
        /*
        view_list = CreateViewList(mViewPager);
        for(View i :view_list)
            pagerAdapter.addView(i, view_list.indexOf(i));
        pagerAdapter.notifyDataSetChanged();
        setCurrentPage(view_list.get(0));
       */



        //for(View i:view_list)  pagerAdapter.addView(i);

        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        //tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                String text = "";
                if (tab.getTag() != null) {
                    text = (String) tab.getTag();
                    switch (text) {
                        case "Opportunity":
                            ((MainActivity) getActivity()).setTitle("Opportunity");
                            mViewPager.setCurrentItem(0);
                            Log.d("debug", "case " + tab.getText() + " title: " + "Opportunity");
                            break;
                        case "Event":
                            ((MainActivity) getActivity()).setTitle("Event");
                            mViewPager.setCurrentItem(1);
                            Log.d("debug", "case " + tab.getText() + " title: " + "Event");
                            break;
                        case "Collaborator":
                            ((MainActivity) getActivity()).setTitle("Collaborator");
                            Log.d("debug", "case " + tab.getText() + " title: " + "Collaborator");
                            break;
                        case "Volunteer":
                            ((MainActivity) getActivity()).setTitle("Volunteer");
                            Log.d("debug", "case " + tab.getText() + " title: " + "Volunteer");
                            break;
                        case "Details":
                            ((MainActivity) getActivity()).setTitle("Details");
                            Log.d("debug", "case " + tab.getText() + " title: " + "Details");
                            break;
                        default:
                            ((MainActivity) getActivity()).setTitle("Volunteer Rack");
                            Log.d("debug", "case " + tab.getText() + " title: " + "Volunteer Rack");
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
    }
    private ArrayList<View> CreateViewList(ViewGroup container){
        ArrayList<View> view_list = new ArrayList<>();
        MainActivity mainActivity = (MainActivity)getActivity();
        MyTabViews myTabViews = new MyTabViews(mainActivity, samplePagerAdapter);

        View opportunityView = getActivity().getLayoutInflater().inflate(R.layout.opportunity_view,
                        container, false);
        View eventView = getActivity().getLayoutInflater().inflate(R.layout.pager_item_test,
                container, false);
        View collaboratorView = getActivity().getLayoutInflater().inflate(R.layout.collaborator_view,
                container, false);
        View volunteerView = getActivity().getLayoutInflater().inflate(R.layout.pager_item_test,
                container, false);
        View detailsView = getActivity().getLayoutInflater().inflate(R.layout.pager_item_test4,
                container, false);

        view_list.add(0,myTabViews.opportunityView(opportunityView, 0));
        view_list.add(1,myTabViews.eventView(eventView, 1));
        view_list.add(2, myTabViews.collaboratorView(collaboratorView, 2));
        view_list.add(3,myTabViews.volunteerView(volunteerView, 3));
        view_list.add(4,myTabViews.detailsView(detailsView, 4, opportunityDetails));
        return view_list;
    }

    /**
     * Adding custom view to tab
     */
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

    //-----------------------------------------------------------------------------
    // Here's what the app should do to add a view to the ViewPager.
    public void addView (View newPage)
    {
        int pageIndex = pagerAdapter.addView (newPage);
        // You might want to make "newPage" the currently displayed page:
        mViewPager.setCurrentItem (pageIndex, true);
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to remove a view from the ViewPager.
    public void removeView (View defunctPage)
    {
        int pageIndex = pagerAdapter.removeView (mViewPager, defunctPage);
        // You might want to choose what page to display, if the current page was "defunctPage".
        if (pageIndex == pagerAdapter.getCount())
            pageIndex--;
        mViewPager.setCurrentItem (pageIndex);
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to get the currently displayed page.
    public View getCurrentPage ()
    {
        return pagerAdapter.getView (mViewPager.getCurrentItem());
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to set the currently displayed page.  "pageToShow" must
    // currently be in the adapter, or this will crash.
    public void setCurrentPage (View pageToShow)
    {
        Log.d("debug", " position of page to show"+pagerAdapter.getItemPosition (pageToShow));
        mViewPager.setCurrentItem(pagerAdapter.getItemPosition(pageToShow), true);
    }


    /**
     * The {@link android.support.v4.view.PagerAdapter} used to display pages in this sample.
     * The individual pages are simple and just display two lines of text. The important section of
     * this class is the {@link #getPageTitle(int)} method which controls what is displayed in the
     * {@link SlidingTabLayout}.
     *
     * Position in pagerAdapter :
     * #0~3 is tab in sequence
     * #4 is opportunity details
     * #5 is collaborator details
     */
    public class SamplePagerAdapter extends PagerAdapter {
        ArrayList< View> viewsList = new ArrayList<>();
        private View currentView;
        private int opportunityDetails = 0;

        public void setOppertunityDetails(int position){
            opportunityDetails = position;
        }

        //todo need to be modify in order to delete view correctly
        @Override
        public int getItemPosition(Object object) {
            return  POSITION_NONE;
            //return super.getItemPosition(object);
        }

        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount() {
            return 4;
        }

        /**
         * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
         * same object as the {@link View} added to the {@link ViewPager}.
         */
        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        // BEGIN_INCLUDE (pageradapter_getpagetitle)
        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link SlidingTabLayout}.
         * <p>
         * Here we construct one using the position value, but for real application the title should
         * refer to the item's contents.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            String title;
            switch (position){
                case 0:
                    title = "Opportunity";
                    break;
                case 1:
                    title = "Event";
                    break;
                case 2:
                    title = "Collaborator";
                    break;
                case 3:
                    title = "Volunteer";
                    break;
                case 4:
                    title = "Details";
                    break;
                default:
                    title = "Volunteer Rack";
            }
            return title;
            //return "Item " + (position + 1);
        }
        // END_INCLUDE (pageradapter_getpagetitle)

        /**
         * Instantiate the {@link View} which should be displayed at {@code position}. Here we
         * inflate a layout from the apps resources and then change the text view to signify the position.
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view;
            MainActivity mainActivity = (MainActivity)getActivity();
            MyTabViews myTabViews = new MyTabViews(mainActivity, samplePagerAdapter, container,position);
            switch (position){
                case 0:
                    view = getActivity().getLayoutInflater().inflate(R.layout.opportunity_view,
                            container, false);
                    myTabViews.opportunityView(view, position, currentView);
                    currentView = view;
                    break;
                case 1:
                    view = getActivity().getLayoutInflater().inflate(R.layout.pager_item_test,
                            container, false);
                    myTabViews.eventView(view, position);
                    currentView = view;
                    break;
                case 2:
                    view = getActivity().getLayoutInflater().inflate(R.layout.collaborator_view,
                            container, false);
                    myTabViews.collaboratorView(view, position);
                    currentView = view;
                    break;
                case 3:
                    view = getActivity().getLayoutInflater().inflate(R.layout.pager_item_test,
                            container, false);
                    myTabViews.volunteerView(view, position);
                    currentView = view;
                    break;
                case 4:
                    view = getActivity().getLayoutInflater().inflate(R.layout.pager_item_test4,
                            container, false);
                    myTabViews.detailsView(view, position, opportunityDetails, currentView);
                    //currentView = view;
                    break;
                default:
                    view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
                            container, false);
                    view.setTag("Volunteer Rack");
                    ((MainActivity)getActivity()).setTitle("Volunteer Rack");

                    Log.d("debug", "case " + position + " title: " + "Volunteer Rack");
                    break;
            }
            return view;
        }

        /**
         * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
         * {@link View}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            Log.i(LOG_TAG, "container.getChildCount() =  " + container.getChildCount());
            container.removeView((View) object);
            viewsList.remove((View) object);
            Log.i(LOG_TAG, "destroyItem() [position: " + position + "]");
        }
    }

}
