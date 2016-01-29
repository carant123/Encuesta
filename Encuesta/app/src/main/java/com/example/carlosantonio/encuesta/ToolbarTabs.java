package com.example.carlosantonio.encuesta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.example.carlosantonio.encuesta.Adapter.MiFragmentPagerAdapter;

/**
 * Created by Carlos Antonio on 04/01/2016.
 */
public class ToolbarTabs extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.toolbar_tab);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        //setSupportActionBar(toolbar);

        // se debe compilar  compile 'com.android.support:design:22.2.0' para utilizar
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        //toma el adaptador del adapter
        viewPager.setAdapter(new MiFragmentPagerAdapter(
                getSupportFragmentManager()));


        TabLayout tabLayout = (TabLayout) findViewById(R.id.appbartabs);
        //tipo de Tab a utilizar
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //Crear las pestañas necesarias en el TabLayout,
        // con sus títulos correspondientes, a partir de la información
        // porporcionada por el adaptador del ViewPager.

        //Asegurar el cambio de la pestaña seleccionada en el TabLayout
        // cuando el usuario se desplaza entre los fragments del ViewPager.
        // Para ello define automáticamente el listener ViewPager.OnPageChangeListener.

        //Y asegurar también la propagación de eventos en “sentido contrario”, es decir,
        // asegurará que cuando se selecciona directamente una pestaña del TabLayout,
        // el ViewPager muestra el fragment correspondiente. Para ello definirá el
        // listener TabLayout.OnTabSelectedListener.


        tabLayout.setupWithViewPager(viewPager);

    }

}
