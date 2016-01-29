package com.example.carlosantonio.encuesta.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.carlosantonio.encuesta.Fragmento_formulario;
import com.example.carlosantonio.encuesta.Fragmento_lista_encuesta;

/**
 * Created by Carlos Antonio on 04/01/2016.
 */
public class MiFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] =
            new String[] { "Formulario", "Encuestas"};

    public MiFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment f = null;

        switch(position) {
            case 0:
                f = Fragmento_formulario.newInstance();
                break;
            case 1:
                f = Fragmento_lista_encuesta.newInstance();
                break;
        }

        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
