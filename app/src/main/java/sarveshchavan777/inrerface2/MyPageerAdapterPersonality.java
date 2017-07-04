package sarveshchavan777.inrerface2;

import android.content.Context;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;



class MyPageerAdapterPersonality extends FragmentStatePagerAdapter {
    Context context;
    public String yourStringArray[] = new String[3];
    public int icon[] /*= {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher}*/;

    int numbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created



     MyPageerAdapterPersonality(FragmentManager fm, int micon[], int mNumOfTabs, Context context) {
        super(fm);
        this.context = context;
        this.icon=micon;
        this.numbOfTabs=mNumOfTabs;

    }

    /*@Override
    public Fragment getItem(int position) {
        MyFragment myFragment = MyFragment.getInstance(position);
        return myFragment;
    }*/
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab01.xml
        {
            Tab01 tab01 = new Tab01();
            return tab01;
        }
        else if (position==1)         // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab01.xml
        {
            Tab02 tab02 = new Tab02();
            return tab02;
        }
        else {
            Tab03 tab03=new Tab03();
            return tab03;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable = ContextCompat.getDrawable(context, icon[position]);
        drawable.setBounds(0, 0, 50, 50);
        ImageSpan imageSpan = new ImageSpan(drawable);
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }
}