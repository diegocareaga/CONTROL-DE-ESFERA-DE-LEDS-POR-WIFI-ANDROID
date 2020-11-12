package com.example.consultasget_post;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    String url;
    TextView tvResponse;
    EditText etIPaddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);

        Button btnGet = (Button) findViewById(R.id.btnGet);
        EditText etMessage = (EditText) findViewById(R.id.etMessage);
        etIPaddress = (EditText) findViewById(R.id.etIP);
        tvResponse = (TextView) findViewById(R.id.tvResponse);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewRequest(etMessage.getText().toString());
            }
        });

    }

    public void NewRequest(String Message){
        url ="http://"+etIPaddress.getText().toString()+"/mensajeria?mensaje=" + Message;
        Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        tvResponse.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.toString());
            }
        });
        queue.add(stringRequest);
    }
}