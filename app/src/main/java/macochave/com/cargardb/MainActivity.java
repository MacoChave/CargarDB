package macochave.com.cargardb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private int posicion;
    private Button verOpcion, verCategoria;
    private EditText newOpcion;
    private Spinner categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verOpcion = (Button) findViewById(R.id.btnOpciones);
        verCategoria = (Button) findViewById(R.id.btnCategoria);
        newOpcion = (EditText) findViewById(R.id.edtOpcion);
        categoria = (Spinner) findViewById(R.id.spnCategoria);

        try {
            dbManager manager = new dbManager(getApplicationContext());
            ArrayList<Categoria> list = manager.selectCategoria();
            Log.i("Spinner", "ArrayList llenada con exito");
            ArrayAdapter<Categoria> adaptador = new ArrayAdapter<Categoria>(getApplicationContext(), R.layout.spinner_item_totto, list);
            Log.i("Spinner", "ArrayAdapter llenado con exito");
            adaptador.setDropDownViewResource(R.layout.spinner_dropdown_list_totto);
            Log.i("Spinner", "Setear ArrayAdapter con exito");

            categoria.setAdapter(adaptador);
            Log.i("Spinner", "Spinner lleno con el adaptador");
            categoria.setOnItemSelectedListener(this);
        } catch (Exception e) {
            Log.i("Error Spinner", "Error: " + e.getMessage());
        }

        verOpcion.setOnClickListener(this);
        verCategoria.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOpciones:
                vOpcion();
                break;
            case R.id.btnCategoria:
                vCategoria();
                break;
        }
    }

    private void vCategoria() {
        try {
            dbManager manager = new dbManager(getApplicationContext());
            ArrayList<Categoria> list = manager.selectCategoria();
            Log.i("Lista opciones", "Tamaño: " + list.size());

            for (int i = 0; i < list.size(); i++) {
                Log.i("Lista opciones", "id: " + list.get(i).getId() + " | nombre: " + list.get(i).getCategoria());
            }
        } catch (Exception e) {
            Log.i("Error Lista Opciones", e.getMessage());
        }
        Log.i("Lista opciones", "--------------------");
    }

    private void vOpcion() {
        if (newOpcion.getText().toString().isEmpty()) {
            try {
                dbManager manager = new dbManager(getApplicationContext());
                ArrayList<Opcion> list = manager.selectOpcion();
                Log.i("Lista opciones", "Tamaño: " + list.size());

                for (int i = 0; i < list.size(); i++) {
                    Log.i("Lista opciones", "id: " + list.get(i).getId() + " | nombre: " + list.get(i).getNombre() + " | foto: " + list.get(i).getFoto() + " | id categoria: " + list.get(i).getId_categoria());
                }
            } catch (Exception e) {
                Log.i("Error Lista Opciones", e.getMessage());
            }
            Log.i("Lista opciones", "--------------------");
        } else {
            try {
                dbManager manager = new dbManager(getApplicationContext());
                ArrayList<Opcion> list = manager.selectOpcionCategoria(newOpcion.getText().toString());
                Log.i("Lista opciones", "Tamaño: " + list.size());

                for (int i = 0; i < list.size(); i++) {
                    Log.i("Lista opciones", "id: " + list.get(i).getId() + " | nombre: " + list.get(i).getNombre() + " | foto: " + list.get(i).getFoto() + " | id categoria: " + list.get(i).getId_categoria());
                }
            } catch (Exception e) {
                Log.i("Error Lista Opciones", e.getMessage());
            }
            Log.i("Lista opciones", "--------------------");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.posicion = position;
        String seleccion = parent.getItemAtPosition(position).toString();
        dbManager manager = new dbManager(getApplicationContext());
        Log.i("Spinner seleccion", "Seleccion actual: " + seleccion + " id: " + manager.selectIdCategoria(seleccion));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}