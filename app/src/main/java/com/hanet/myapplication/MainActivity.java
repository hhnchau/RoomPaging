package com.hanet.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hanet.myapplication.db.AppDatabase;
import com.hanet.myapplication.model.Car;
import com.hanet.myapplication.model.CarViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private TextView start, end, result;
    private AppDatabase appDatabase;
    private CarViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        start = findViewById(R.id.start);
        end = findViewById(R.id.end);
        result = findViewById(R.id.result);


        List<Car> lstCar = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            lstCar.add(new Car("Car " + i, "Color" + i, i));
        }
        appDatabase = AppDatabase.getAppDatabase(this);
        insertCar(appDatabase, lstCar);


        RecyclerView recyclerView = findViewById(R.id.rcv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        final MyAdapter adapter = new MyAdapter();

        viewModel = ViewModelProviders.of(this).get(CarViewModel.class);
        viewModel.filterText.setValue("");
        viewModel.getData(appDatabase.carDao());
        viewModel.carList.observe(this, new Observer<PagedList<Car>>() {
            @Override
            public void onChanged(PagedList<Car> cars) {
                adapter.submitList(cars);
            }
        });

        recyclerView.setAdapter(adapter);

    }


    static void insertCar(final AppDatabase appDatabase, final List<Car> lstCar) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.carDao().insertAll(lstCar);
                return null;
            }
        }.execute();
    }

    void searchCar(final AppDatabase appDatabase, final String name) {
        new AsyncTask<Void, Void, Car>() {
            @Override
            protected Car doInBackground(Void... voids) {
                //Car car = appDatabase.carDao().getCarByName(name);
                return null;
            }

            @Override
            protected void onPostExecute(Car car) {
                super.onPostExecute(car);
                long time = System.currentTimeMillis();
                end.setText(time + "");

                result.setText(car.getName());

            }
        }.execute();
    }

    public void onSearch(View view) {
//        long time = System.currentTimeMillis();
//        start.setText(time + "");
//        searchCar(appDatabase, text.getText().toString());

        viewModel.filterText.setValue("%" + text.getText().toString() + "%");


    }
}
