package com.example.carlosantonio.encuesta;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlosantonio.encuesta.Model.Modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Antonio on 04/01/2016.
 */
public class Fragmento_lista_encuesta extends Fragment implements View.OnClickListener {

    ListView lvEncuesta;
    Button btrefresh;

    public static Fragmento_lista_encuesta newInstance() {
        Fragmento_lista_encuesta fragment = new Fragmento_lista_encuesta();
        return fragment;
    }

    public Fragmento_lista_encuesta() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.encuestas_realizadas, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lvEncuesta = (ListView) getView().findViewById(R.id.lvEncuestas);
        btrefresh = (Button) getView().findViewById(R.id.btrefreshencuesta);
        btrefresh.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btrefreshencuesta:
                Lista();
                break;
        }

    }



    public void Lista(){

        try {

            DatosEncuesta db = new DatosEncuesta(getActivity());
            db.open();
            Cursor cur = db.getData();

            List<Modelo> ListaEncuesta = new ArrayList<Modelo>();
            Modelo Encuesta;


            String classes2[];


            while(cur.moveToNext()){

                Encuesta = new Modelo();

                int id = cur.getInt(cur.getColumnIndex("_id"));

                String nombre = cur.getString(cur.getColumnIndex("Nombre_data"));

                String numero = cur.getString(cur.getColumnIndex("Numero_data"));

                String comentario = cur.getString(cur.getColumnIndex("Comentario_data"));

                //int check = cursor.getInt(cursor.getColumnIndex("estado"));

                Encuesta.setID(id);
                Encuesta.setNombre(nombre);
                Encuesta.setNumero(numero);
                Encuesta.setComentario(comentario);
                //pedido.estado = check;
                //pedido.estado = check;
                ListaEncuesta.add(Encuesta);

            }

            cur.close();
            db.close();

            //lvEncuesta.setAdapter(new ArrayAdapter(Encuesta_Tab.this, android.R.layout.simple_list_item_1, ListaEncuesta));

            EncuestaArrayAdapter adapter = new EncuestaArrayAdapter(this,R.layout.encuesta_contenido,ListaEncuesta);
            lvEncuesta.setAdapter(adapter);

            Toast.makeText(getActivity().getApplicationContext(), "Adaptado", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            e.printStackTrace();

            Toast.makeText(getActivity().getApplicationContext(), "Adaptado Error", Toast.LENGTH_SHORT).show();
        }


    }




    public class EncuestaArrayAdapter extends ArrayAdapter<Modelo> {

        private List<Modelo> listaEncuesta;
        private int resource;
        //private FragmentActivity inflator;
        private LayoutInflater inflater;

        private Activity context;


        public EncuestaArrayAdapter(Fragment context, int resource, List<Modelo> objects) {
            super(context.getActivity(), resource, objects);
            //el layout
            this.resource = resource;
            listaEncuesta = objects;
            this.context = context.getActivity();
            //inflator = context.getActivity();

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            inflater = context.getLayoutInflater();

            if(convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(resource, null);

                holder.nombreLista = (TextView) convertView.findViewById(R.id.tvDetalleEncuesta);
                holder.bdetalle = (Button) convertView.findViewById(R.id.bdetallencuesta);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.nombreLista.setText(listaEncuesta.get(position).getNombre());


            holder.bdetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String DataNombre = listaEncuesta.get(position).getNombre();
                    String DataNumero = listaEncuesta.get(position).getNumero();
                    String DataComentario = listaEncuesta.get(position).getComentario();
                    Bundle DataGroup = new Bundle();
                    DataGroup.putString("DNombre",DataNombre);
                    DataGroup.putString("DNumero",DataNumero);
                    DataGroup.putString("DComentario",DataComentario);
                    Intent a = new Intent(getActivity(),EncuestaDetalles.class);
                    a.putExtras(DataGroup);
                    startActivity(a);

                }
            });



            return convertView;
        }



        class ViewHolder{

            private TextView nombreLista;
            private Button bdetalle;

        }
    }



}
