package usonsonate.com.viewpager;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private HorizontalScrollView hzScrollView;
    private ArrayList<DataModel> dataModels;
    private LinearLayout hzswItemContainer;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindData();
        setUIReference();
        bindHZSWData();
        bindViewPagerData();
    }


    private void bindData()
    {
        /*
            In this method, We are binding data for our ViewPager and HorizontalScrollView using a Model class.
            The model class's constructor take two params, String, String. (Modify it with your requirement...!)
        */
        dataModels = new ArrayList<>();
        dataModels.add(new DataModel("IMAGEN 1", R.drawable.image_1));
        dataModels.add(new DataModel("IMAGEN 2", R.drawable.image_2));
        dataModels.add(new DataModel("IMAGEN 3", R.drawable.image_3));
        dataModels.add(new DataModel("IMAGEN 4", R.drawable.image_4));
        dataModels.add(new DataModel("IMAGEN 5", R.drawable.image_5));
        dataModels.add(new DataModel("IMAGEN 6", R.drawable.image_6));
        dataModels.add(new DataModel("IMAGEN 7", R.drawable.image_7));
        dataModels.add(new DataModel("IMAGEN 8", R.drawable.image_8));
    }

    private void setUIReference()
    {
        hzScrollView = findViewById(R.id.horizontalScrollView);
        hzswItemContainer = findViewById(R.id.horizontalScrollViewItemContainer);
        viewPager = findViewById(R.id.viewPager);
    }

    public void onClick(View view)
    {
        if (!handleHZSWItemClick(view))
        {
            //handle other view clicks here
        }
    }

    private boolean handleHZSWItemClick(View view)
    {
        for (int i = 0; i < hzswItemContainer.getChildCount(); i++)
        {
            View viewChild = hzswItemContainer.getChildAt(i);

            if (viewChild instanceof ImageView)
            {
                if (viewChild.equals(view))
                {
                    //Toast.makeText(MainActivity.this,  ((ImageView) viewChild).getText()+" clicked", Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(i, true);
                    setHzScrollViewCenter(viewChild);
                    return true;
                }
            }
        }
        return false;
    }

    private void setHzScrollViewCenter(View view)
    {
        //smoothly set horizontalScrollView to centerLock
        int screenWidth = viewPager.getWidth();
        int scrollX = (view.getLeft() - (screenWidth / 2)) + (view.getWidth() / 2);
        hzScrollView.smoothScrollTo(scrollX, 0);
    }

    private void bindViewPagerData()
    {
        ArrayList<View> pages = new ArrayList<>();
        for (int i = 0; i < dataModels.size(); i++)
        {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(dataModels.get(i).getImage());
            imageView.setBackgroundColor(Color.BLACK);
            pages.add(imageView);
        }

        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(MainActivity.this, pages);
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
                for (int i = 0; i < dataModels.size(); i++)
                {
                    ImageView imageView = ((ImageView)hzswItemContainer.getChildAt(i));

                    if(i == position)
                    {
                        //set some different properties for selected item
                        //In this case we set bold typeface to TextView
                        imageView.setBackgroundColor(Color.parseColor("#FDFFFC"));

                        //And set our HorizontalScrollView to Center Lock
                        //If you don't want that, just mark below method calling as a comment!
                        setHzScrollViewCenter(imageView);
                    }
                    else
                    {
                        imageView.setBackgroundColor(Color.parseColor("#F71735"));
                    }
                }
            }

            @Override
            public void onPageSelected(int position){}

            @Override
            public void onPageScrollStateChanged(int state){}
        });
    }

    private void bindHZSWData()
    {
        //dynamically binding HorizontalScrollView

        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //layoutParams.gravity = Gravity.CENTER;
        //layoutParams.setMargins(30, 10, 30, 10);

        int textID = 8006;
        for (int i = 0; i < dataModels.size(); i++)
        {
            final int idimage = textID + i;
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(idimage);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(dataModels.get(i).getImage());
            //imageView.setTextColor(Color.WHITE);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(125,125));
            //imageView.setLayoutParams(layoutParams);
            imageView.setOnClickListener(this);

            hzswItemContainer.addView(imageView);
        }
    }

}
