package ua.kavyar.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kavyar.model.Category;
import ua.kavyar.model.City;
import ua.kavyar.model.Feature;
import ua.kavyar.model.Photo;
import ua.kavyar.model.Product;
import ua.kavyar.service.CategoryService;
import ua.kavyar.service.CityService;
import ua.kavyar.service.FeatureService;
import ua.kavyar.service.PhotoService;
import ua.kavyar.service.ProductService;

@RestController
@RequestMapping("/inject")
public class InjectController {
    private final CityService cityService;
    private final CategoryService categoryService;
    private final FeatureService featureService;
    private final ProductService productService;
    private final PhotoService photoService;

    public InjectController(CityService cityService, CategoryService categoryService,
                            FeatureService featureService, ProductService productService,
                            PhotoService photoService) {
        this.cityService = cityService;
        this.categoryService = categoryService;
        this.featureService = featureService;
        this.productService = productService;
        this.photoService = photoService;
    }

    @GetMapping
    public String inject() {
        injectCity();
        injectCategoryAndProduct();
        injectFeature();
        injectPhoto();
        return "Done!";
    }

    private void injectPhoto() {
        Photo logo1 = new Photo();
        logo1.setUrl("logo1");
        photoService.create(logo1);

        Photo logo2 = new Photo();
        logo2.setUrl("logo2");
        photoService.create(logo1);

        Photo photo1 = new Photo();
        photo1.setUrl("photo1");
        photoService.create(photo1);

        Photo photo2 = new Photo();
        photo2.setUrl("photo2");
        photoService.create(photo2);

        Photo photo3 = new Photo();
        photo3.setUrl("photo3");
        photoService.create(photo3);

        Photo photo4 = new Photo();
        photo4.setUrl("photo4");
        photoService.create(photo4);
    }

    private void injectFeature() {
        Feature bombShelter = new Feature();
        bombShelter.setName("BombShelter");
        bombShelter.setDescription("BombShelter");
        featureService.create(bombShelter);
        Feature homePets = new Feature();
        homePets.setName("Home pets");
        homePets.setDescription("Home pets");
        featureService.create(homePets);
        Feature gasGenerator = new Feature();
        gasGenerator.setName("Gas generator");
        gasGenerator.setDescription("Gas generator");
        featureService.create(gasGenerator);
    }

    private void injectCategoryAndProduct() {
        Category coffee = new Category();
        coffee.setName("Coffee");
        categoryService.create(coffee);

        Category coffeeToGo = new Category();
        coffeeToGo.setName("Coffee ToGo");
        categoryService.create(coffeeToGo);

        Category coffeeBeans = new Category();
        coffeeBeans.setName("Coffee Beans");
        categoryService.create(coffeeBeans);

        Category dessert = new Category();
        dessert.setName("Desserts");
        categoryService.create(dessert);

        Product espresso = new Product();
        espresso.setName("Espresso");
        espresso.setDescription("Espresso");
        espresso.setCategory(coffee);
        productService.create(espresso);

        Product americano = new Product();
        americano.setName("Americano");
        americano.setDescription("Americano");
        americano.setCategory(coffee);
        productService.create(americano);

        Product cappuccino = new Product();
        cappuccino.setName("Cappuccino");
        cappuccino.setDescription("Cappuccino");
        cappuccino.setCategory(coffee);
        productService.create(cappuccino);

        Product brownie = new Product();
        brownie.setName("Brownie");
        brownie.setDescription("Brownie");
        brownie.setCategory(dessert);
        productService.create(brownie);

        Product cheesecake = new Product();
        cheesecake.setName("Cheesecake");
        cheesecake.setDescription("Cheesecake");
        cheesecake.setCategory(dessert);
        productService.create(cheesecake);
    }

    private void injectCity() {
        City kharkiv = new City();
        kharkiv.setName("Kharkiv");
        cityService.create(kharkiv);
        City kyiv = new City();
        kyiv.setName("Kyiv");
        cityService.create(kyiv);
        City uzhgorod = new City();
        uzhgorod.setName("Uzhgorod");
        cityService.create(uzhgorod);
    }
}
