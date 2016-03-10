package wala.volunteerrack.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

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
    private static int currentPage = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        samplePagerAdapter = new SamplePagerAdapter();

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        //set how many page should be load each time (including background view)
        mViewPager.setOffscreenPageLimit(1);
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

        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                String text = "";
                if (tab.getText() != null) {
                    text = (String) tab.getText();
                    switch (text) {
                        case "Opportunity":
                            ((MainActivity) getActivity()).setTitle("Opportunity");
                            Log.d("debug", "case " + tab.getText() + " title: " + "Opportunity");
                            break;
                        case "Event":
                            ((MainActivity) getActivity()).setTitle("Event");
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

    @Override
    public void onResume() {
        super.onResume();
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
            tabLayout.getTabAt(0).setCustomView(tabOne);

            TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            tabTwo.setText("Event");
            tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_call, 0, 0);
            tabLayout.getTabAt(1).setCustomView(tabTwo);

            TextView tabThree = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            tabThree.setText("Collaborator");
            tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_contacts, 0, 0);
            tabLayout.getTabAt(2).setCustomView(tabThree);

            TextView tabFour = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            tabFour.setText("Volunteer");
            tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_contacts, 0, 0);
            tabLayout.getTabAt(3).setCustomView(tabFour);

        }catch (NullPointerException e){
            //todo handle null pointer exc
            Log.d("debug","null pointer @ setupTabIcons");
        }
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
