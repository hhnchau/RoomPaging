package com.hanet.myapplication.model;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.hanet.myapplication.db.CarDao;

public class CarViewModel extends ViewModel {

    public LiveData<PagedList<Car>> carList;

    public MutableLiveData<String> filterText = new MutableLiveData<>();

    public CarViewModel() {
    }

//    public void getData(CarDao carDao) {
//        PagedList.Config pagedListConfig =
//                (new PagedList.Config.Builder()).setEnablePlaceholders(true)
//                        .setPageSize(200).build();
//
//        carList = (new LivePagedListBuilder(carDao.getCar(), pagedListConfig))
//                .build();
//
//    }

    public void getData(final CarDao carDao) {
        PagedList.Config config = (new PagedList.Config.Builder())
                .setPageSize(10)
                .build();

//        carList = Transformations.switchMap(filterText, input -> {
//            if (input == null || input.equals("") || input.equals("%%")) {
//                return new LivePagedListBuilder<>(
//                        carDao.getCar(), config)
//                        .build();
//            } else {
//                return new LivePagedListBuilder<>(
//                        carDao.getCarByName(input), config)
//                        .build();
//            }
//
//        });

        carList = Transformations.switchMap(filterText, new Function<String, LiveData<PagedList<Car>>>() {
            @Override
            public LiveData<PagedList<Car>> apply(String input) {
                if (input == null || input.equals("") || input.equals("%%")) {
                    return new LivePagedListBuilder<>(
                            carDao.getCar(), config)
                            .build();
                } else {
                    return new LivePagedListBuilder<>(
                            carDao.getCarByName(input), config)
                            .build();
                }
            }
        });

    }
}
