package com.example.demo.service;

import com.example.demo.model.City;
import com.example.demo.model.CoffeeShop;
import com.example.demo.model.Feature;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class DataInjectService {
    private final CityService cityService;
    private final CoffeeShopService coffeeShopService;
    private final FeatureService featureService;

    public DataInjectService(CityService cityService,
                             CoffeeShopService coffeeShopService,
                             FeatureService featureService) {
        this.cityService = cityService;
        this.coffeeShopService = coffeeShopService;
        this.featureService = featureService;
    }

    @PostConstruct
    public void inject(){
        City kh = new City();
        kh.setName("Kharkiv");
        City lv = new City();
        lv.setName("Lviv");
        City ki = new City();
        ki.setName("Kiev");
        City uz = new City();
        uz.setName("Uzhgorod");
        City[] cities = new City[]{cityService.create(kh), cityService.create(lv), cityService.create(ki), cityService.create(uz)};
        String[] titles = new String[]{"25coffee", "CoCoffee", "Simple", "Bourbon"};
        String[] phoneNums = new String[]{"1", "222", "333", "4444"};
        String[] openH = new String[]{"8", "8", "9", "11"};
        String[] closeH = new String[]{"18", "19", "20", "23"};
        List<CoffeeShop> coffeeShopList = new ArrayList<>();
        Feature toGo = new Feature();
        toGo.setName("ToGo");
        toGo.setDescription("ToGo Desc");
        Feature pets = new Feature();
        pets.setName("Pets");
        pets.setDescription("pets Desc");
        Feature coffeeHome = new Feature();
        coffeeHome.setName("CoffeeHome");
        coffeeHome.setDescription("CoffeeHome Desc");
        Feature shelter = new Feature();
        shelter.setName("shelter");
        shelter.setDescription("shelter Desc");
        List<Feature> features = new ArrayList<>();
        features.add(featureService.create(toGo));
        features.add(featureService.create(pets));
        features.add(featureService.create(coffeeHome));
        features.add(featureService.create(shelter));
        for (int i = 0; i < cities.length; i++) {
            CoffeeShop coffeeShop = new CoffeeShop();
            coffeeShop.setCity(cities[i]);
            coffeeShop.setTitle(titles[i]);
            coffeeShop.setDescription(titles[i]);
            coffeeShop.setPhone(phoneNums[i]);
            coffeeShop.setOpen(openH[i]);
            coffeeShop.setClose(closeH[i]);
            List<Feature> newFeatures = new ArrayList<>();
            Random random = new Random();
            newFeatures.add(features.get(random.nextInt(features.size())));
            coffeeShop.setFeatures(newFeatures);
            coffeeShopList.add(coffeeShop);
        }
        coffeeShopList.forEach(coffeeShopService::create);
    }
}
