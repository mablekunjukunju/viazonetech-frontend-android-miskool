package com.zone.android.miskool_View;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Adapters.informationAdapter;
import com.zone.android.miskool_Adapters.slidingPanelAdapter;
import com.zone.android.miskool_Entitiy.Attributes;
import com.zone.android.miskool_Entitiy.Person_det;
import com.zone.android.miskool_Presenter.PagerPresClass;
import com.zone.android.miskool_Presenter.PagerPresInterface;
import com.zone.android.miskool_Presenter.informationPresClass;
import com.zone.android.miskool_Presenter.informationPresInterface;
import com.zone.android.miskool_Util.Methods;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import az.plainpie.PieView;

/**
 * Created by Inspiron on 23-03-2018.
 */

public class informationViewClass extends AppCompatActivity implements informationViewInterface {

    viePagerAdapter viePagerAdapter;
    ViewPager mViewPager;
    List<Person_det> names;
   informationPresInterface informationPresInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_informationnew);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Info");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        informationPresInterface=new informationPresClass(this);
        names=new ArrayList<>();
        names.clear();

        informationPresInterface.getPersonList(getApplicationContext());

        Methods.copyFile(getApplicationContext());


    }

    @Override
    public void setList(List<Person_det> person_dets) {



        viePagerAdapter =
                new viePagerAdapter(getSupportFragmentManager(),person_dets);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(viePagerAdapter);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager, true);

    }

    public class viePagerAdapter extends FragmentStatePagerAdapter{
        List<Person_det> nameList;

        public viePagerAdapter(FragmentManager fm,List<Person_det> names) {

            super(fm);
            nameList=names;


        }


        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            ViewPagerFragment fragment = new ViewPagerFragment();
            Bundle args = new Bundle();
            // Our object is just an integer :-P



            args.putInt("object", position + 1);
            args.putString("name",nameList.get(position).getFirstName().toString());

            String identity=nameList.get(position).getIdentitiy().toString();
            String stdId= nameList.get(position).getStudentId().toString();

            String name=nameList.get(position).getFirstName().toString();
            if(identity.contains("P")){
                identity="Parent";
            }else{
                identity="Student";
            }

            args.putString("identitiy",identity);
            args.putString("name",name);

            //stdId
            args.putString("stdId",stdId);


            fragment.setArguments(args);
            return  fragment;
        }

        @Override
        public int getCount()

        {

            return nameList.size();
        }
    }

    public static class ViewPagerFragment extends android.support.v4.app.Fragment implements pagerViewInterface {
        public  final String ARG_OBJECT = "object";

        public  final String ARG_NAME = "name";

        public  final String ARG_ID = "identitiy";
        PagerPresInterface pagerPresInterface =new PagerPresClass(ViewPagerFragment.this);
        RecyclerView recyclerView;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
          super.onCreateView(inflater, container, savedInstanceState);

            View rootview= inflater.inflate(R.layout.acitivity_viewpgr,container,false);
            PieView pieView = (PieView) rootview.findViewById(R.id.pieView);
            TextView tv_Name=(TextView) rootview.findViewById(R.id.tv_Name);
            pieView.setPercentageBackgroundColor(getResources().getColor(R.color.colorAccent));
            pieView.setMainBackgroundColor(getResources().getColor(R.color.colorPrimaryGreen));
            pieView.setPercentage(50);
            pieView.setInnerBackgroundColor(getResources().getColor(R.color.colorPrimaryLightest));


            Bundle args = getArguments();
            String stdId= args.getString("stdId");

         //   List<Attributes>attsList= Methods.getAttributes(container.getContext(),stdId);
            pagerPresInterface.getAttList(container.getContext(),stdId);

            String name=args.getString("name");


            recyclerView=(RecyclerView)rootview.findViewById(R.id.recyclerView);


            return rootview;
     }

        @Override
        public void setAttList(final List<Attributes> attList) {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setHasFixedSize(true);
                    LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(MyLayoutManager);
                    RecyclerView.Adapter adapter = new informationAdapter(getContext(),attList);
                    // slidingPanelAdapter=new slidingPanelAdapter(personList);
                    recyclerView.setAdapter(adapter);

                }
            });


        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                informationViewClass.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    }


