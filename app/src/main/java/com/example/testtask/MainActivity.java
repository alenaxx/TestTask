package com.example.testtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button reload;
    public String joke;
    public int i;
    public int count;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView2);
        editText = (EditText) findViewById(R.id.editText);
        reload = (Button) findViewById(R.id.button);
        mQueue = Volley.newRequestQueue(this);
        textView.setMovementMethod(new ScrollingMovementMethod());



        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(null);
                if (editText.getText().toString().isEmpty()) {
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Please, input count!", Toast.LENGTH_SHORT).show();
                }
                else {
                    count = Integer.parseInt(editText.getText().toString());
                    String[] jokeList = new String[count + 1];
                    if (count > 0) {

                        for (i = 0; i < count; i++) {

                            jsonParse(i, jokeList);

                        }

                    }


                }
            }
        });

    }

    private void jsonParse(final int i, final String[] jokeList) {

        String url = "http://api.icndb.com/jokes/random";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject value = response.getJSONObject("value");
                            joke = value.getString("joke");
                            jokeList[i] =  joke.replaceAll("&quot", "");
                            if (jokeList[count - 1] != null) {
                                for (int i = 0; i < count; i++)
                                    textView.append("\n" + (i + 1) + ". " + jokeList[i] + "\n");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }

        });
        mQueue.add(request);
    }
}
