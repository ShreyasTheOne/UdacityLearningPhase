package com.mdg.weather;

import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mdg.weather.models.Data;
import com.mdg.weather.models.Main;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    // Api key to access data fron an api like openWeatherApi
    private static final String APIKEY = "5753db64c667d9ed89962813328c19be";

    //Declaring an object of APIInterface
    APIInterface apiInterface;

    //Defining all the views that are in xml file activity_main.xml
    TextView pressure, temp, wind, desc, cityName;
    EditText editText;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding all the views from xml file
        //made a method below to keep the code clean
        instantiateViews();

        //Creating the object of APIInterface that we have created earlier, using ApiClient
        apiInterface = ApiClient.getClient().create(APIInterface.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting the data from the edittext
                String zipCode = editText.getText().toString();
                //clearing the data from edittext after getting it into a variable
                editText.getText().clear();
                //fetching data according to the entered zip code
                fetchData(zipCode);
            }
        });
    }

    //method to instantiate all the views
    private void instantiateViews() {
        pressure = findViewById(R.id.pressure);
        temp = findViewById(R.id.temperature);
        wind = findViewById(R.id.wind);
        desc = findViewById(R.id.description);
        cityName = findViewById(R.id.city_name);
        editText = findViewById(R.id.zipcode);
        submit = findViewById(R.id.submit);

    }

    //method that fetch the data using api
    private void fetchData(String zipCode) {
    Call<Data> call = apiInterface.getWeatherData(APIKEY, zipCode + ",in");
    call.enqueue(new Callback<Data>() {
        @Override
        public void onResponse(Call<Data> call, Response<Data> response) {
            if(response.code()==200){
                Data data = response.body();
                cityName.setText(data.getName());
                double tempCelcius = data.getMain().getTemp() - 2273.15;
                temp.setText(String.format("%.if", tempCelcius) + "\u2103");
                pressure.setText(String.valueOf(data.getMain().getPressure()));
                desc.setText(data.getWeather().get(0).getDescription());
                double windSpeed = data.getWind().getSpeed();
                wind.setText(String.valueOf(windSpeed));
            } else {
                Toast.makeText(MainActivity.this,"Invalid Zip Code", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<Data> call, Throwable t) {
            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

        }
    });

    }


}
