package ca.colincooke.furnace;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button on, middle, off;
    EditText onText, middleText, offText;
    RequestQueue queue;
    Context context;
    TextView errorText, responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        on = (Button) findViewById(R.id.onButton);
        middle = (Button) findViewById(R.id.middleButton);
        off = (Button) findViewById(R.id.offButton);
        onText = (EditText) findViewById(R.id.textOn);
        middleText = (EditText) findViewById(R.id.textMiddle);
        offText = (EditText) findViewById(R.id.textOff);
        errorText = (TextView) findViewById(R.id.errorText);
        responseText = (TextView) findViewById(R.id.responseText);

        on.setOnClickListener(this);
        middle.setOnClickListener(this);
        off.setOnClickListener(this);

        //TODO move this to background thread
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public void onClick(View v) {

        String text = null;
        int method = 0;
        switch (v.getId()) {
            case R.id.onButton:
                text = onText.getText().toString();
                method = Request.Method.GET;
                break;
            case R.id.middleButton:
                text = middleText.getText().toString();
                method = Request.Method.POST;
                break;
            case R.id.offButton:
                text = offText.getText().toString();
                method = Request.Method.PUT;
                break;
        }

        if(text != null){
            queue.add(new StringRequest(
                    method, text, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    responseText.setText(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    errorText.setText(error.getMessage());
                }
            }));
        }

    }

}
