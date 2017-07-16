package com.example.gautamnarayanan.t3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText Gtext1;
    String built_path;
    ImageButton Gbutton,Gbutton2;
    String pokemon;
    String jsonString;
    ArrayList<String> names;
    ArrayList<PokeObject> pokeobjects;
    static final String BaseURL = "http://pokeapi.co/api/v2/pokemon/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gtext1 = (EditText) findViewById(R.id.Gtext);
        Gbutton = (ImageButton) findViewById(R.id.Gbutton);
        Gbutton2=(ImageButton) findViewById(R.id.Gbutton2);
        names = new ArrayList<String>();
        pokeobjects=new ArrayList<PokeObject>();
        Gbutton.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        pokemon = Gtext1.getText().toString().toLowerCase();
                        pokemon=pokemon.replaceAll("\\s","");
                        names.add(pokemon);
                        if (!TextUtils.isEmpty(pokemon)) {
                            built_path = BaseURL+pokemon+"/" ;
                            new Retrieve().execute(built_path);

                        }
                    }
                }
        );

        Gbutton2.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View view){
                        Intent i = new Intent(MainActivity.this, RecyclerActivity.class);
                        i.putExtra("pokemon", pokeobjects);
                        startActivity(i);
                    }
                }
        );


    }



    class Retrieve extends AsyncTask<String,Void,JSONObject>
    {
        HttpURLConnection urlconnection;
        BufferedReader reader;
        URL pokeurl=null;
        StringBuffer s=null;
        EditText Gtext2,Gtext3,Gtext4,Gheight,Gweight;
        ImageView Gimage;
        Bitmap bitmap;
        String url;



        @Override
        protected JSONObject doInBackground(String... params) {
            try {
                pokeurl = new URL(params[0]);
                urlconnection=(HttpURLConnection)pokeurl.openConnection();
                urlconnection.setRequestMethod("GET");
                urlconnection.connect();
                reader=new BufferedReader(new InputStreamReader(pokeurl.openStream()));
                String sResponse;
                s = new StringBuffer();

                while ((sResponse = reader.readLine()) != null) {
                    s = s.append(sResponse+"\n");
                }
                if(s.length()==0)
                {return null;}
                reader.close();
                JSONObject pokeobject = new JSONObject(s.toString());
                jsonString=s.toString();
                return pokeobject;



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                urlconnection.disconnect();
            }
            return null;

        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            try {
                if(jsonObject!=null)
                {String name=jsonObject.getString("name");
                    Gtext4 = (EditText) findViewById(R.id.Gtext4);
                    Gimage=(ImageView)findViewById(R.id.Gimage);
                    Gtext3 = (EditText)findViewById(R.id.Gtext3);
                    Gtext2 = (EditText)findViewById(R.id.Gtext2);
                    Gheight=(EditText)findViewById(R.id.Gheight);
                    Gweight=(EditText)findViewById(R.id.Gweight);
                    Gtext2.setText(null);
                    Gtext3.setText(null);
                    Gtext4.setText(null);
                    Gheight.setText(null);
                    Gweight.setText(null);
                    JSONArray jsonArray=jsonObject.getJSONArray("types");
                    int length=jsonArray.length();
                    JSONObject object1 = jsonArray.getJSONObject(0);
                    JSONObject type1 = object1.getJSONObject("type");
                    String t1 = type1.getString("name");
                    Gtext2.setText(t1);
                    Gtext2.setEnabled(false);
                    Gtext3.setEnabled(false);
                    Gtext4.setEnabled(false);
                    Gheight.setEnabled(false);
                    Gweight.setEnabled(false);

                    if(length>1) {
                        JSONObject object2 = jsonArray.getJSONObject(1);
                        JSONObject type2 = object2.getJSONObject("type");
                        String t2 = type2.getString("name");
                        Gtext3.setText(t2);
                    }
                    Gtext4.setText(name);
                    String height=jsonObject.getString("height");
                    String weight=jsonObject.getString("weight");
                    Gweight.setText(weight);
                    Gheight.setText(height);
                    JSONObject imgobj = jsonObject.getJSONObject("sprites");
                    url = imgobj.getString("front_default");
                    PokeObject obj = new PokeObject(name,url);
                    pokeobjects.add(obj);
                    Gimage=(ImageView)findViewById(R.id.Gimage);
                    Picasso.with(MainActivity.this).load(url).resize(350,350).into(Gimage);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}
