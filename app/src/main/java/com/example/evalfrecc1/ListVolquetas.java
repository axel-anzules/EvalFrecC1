package com.example.evalfrecc1; //

import android.os.Bundle;
import android.widget.EditText; // O android.widget.TextView si es un TextView
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class ListVolquetas extends AppCompatActivity {

    private EditText multiLineTextView; // O TextView multiLineTextView;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_volquetas); // Asegúrate que este sea tu layout

        // Inicializa el EditText/TextView (asegúrate que el ID sea correcto)
        multiLineTextView = findViewById(R.id.listmultivol); // Reemplaza con el ID de tu MultiLineText

        // Inicializa la cola de peticiones de Volley
        requestQueue = Volley.newRequestQueue(this);

        // Llama al método para obtener los datos de la API
        fetchDataFromApi();
    }

    private void fetchDataFromApi() {
        // Crea una petición StringRequest
        //
        String urlApi = "https://uteqia.com/api/volquetas";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlApi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // La respuesta de la API (en formato String)
                        // Aquí puedes procesar el JSON si es necesario antes de mostrarlo
                        multiLineTextView.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejo de errores de la petición
                        multiLineTextView.setText("Error al cargar datos: " + error.toString());
                        Toast.makeText(ListVolquetas.this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        // Añade la petición a la RequestQueue.
        requestQueue.add(stringRequest);
    }
}
