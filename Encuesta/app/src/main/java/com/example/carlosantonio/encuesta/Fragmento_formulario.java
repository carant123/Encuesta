package com.example.carlosantonio.encuesta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by Carlos Antonio on 04/01/2016.
 */
public class Fragmento_formulario extends Fragment implements View.OnClickListener {

    EditText Nombre, Numero, Comentario;
    Button reg;

    public static Fragmento_formulario newInstance() {
        Fragmento_formulario fragment = new Fragmento_formulario();
        return fragment;
    }

    public Fragmento_formulario() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.formulario_encuesta, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Nombre = (EditText) getView().findViewById(R.id.etNombreEncuesta);
        Numero = (EditText) getView().findViewById(R.id.etNumeroEncuesta);
        Comentario = (EditText) getView().findViewById(R.id.etComentarioEncuesta);
        reg = (Button) getView().findViewById(R.id.btregistrarEncuesta);
        reg.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btregistrarEncuesta:

                try {

                    String name = Nombre.getText().toString();
                    String number = Numero.getText().toString();
                    String coment = Comentario.getText().toString();
                    //getActivity() tomas la actividad pero el fragmento
                    DatosEncuesta entry = new DatosEncuesta(getActivity());
                    entry.open();
                    entry.createEntry(name, number, coment);
                    entry.close();

                    Nombre.setText("");
                    Numero.setText("");
                    Comentario.setText("");

                } catch (SQLException e) {
                    e.printStackTrace();
                    //getActivity() tomas la actividad pero el fragmento
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }finally {
                    Toast.makeText(getActivity().getApplicationContext(), "Registro Completo", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
