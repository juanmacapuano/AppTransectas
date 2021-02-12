package com.example.transectas.dialogos.ui.main;

import android.content.Context;

import com.example.transectas.R;
import com.example.transectas.dialogos.VegetationFragment;
import com.example.transectas.dialogos.EnvironmentSedFragment;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static int TAB_NUMBER = 2;
    private EnvironmentSedFragment ambienteSedFragment;
    private VegetationFragment vegetationFragment;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.PrimeraSeccion, R.string.SegundaSeccion};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        //Fragment fragment = null;
        switch (position) {

            case 0:
                ambienteSedFragment = new EnvironmentSedFragment();
                return ambienteSedFragment;

            case 1:
                vegetationFragment = new VegetationFragment();
                return vegetationFragment;




        }
        return null;//PlaceholderFragment.newInstance(position + 1);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return TAB_NUMBER;
    }

    public Fragment getItemFragment(int position) {
        //Fragment fragment = null;
        switch (position) {

            case 0:
                return ambienteSedFragment;

            case 1:
                return vegetationFragment;

        }
        return null;//PlaceholderFragment.newInstance(position + 1);

    }
}