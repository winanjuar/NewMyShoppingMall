package com.sugengwin.multimatics.myshoppingmall;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Multimatics on 20/07/2016.
 */
public class ImagePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> listImage;

    public ArrayList<String> getListImage() {
        return listImage;
    }

    public void setListImage(ArrayList<String> listImage) {
        this.listImage = listImage;
    }

    public ImagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ImageFragment mImageFragment = new ImageFragment();
        mImageFragment.setImageUrl(getListImage().get(position));
        return mImageFragment;
    }

    @Override
    public int getCount() {
        return getListImage().size();
    }
}
