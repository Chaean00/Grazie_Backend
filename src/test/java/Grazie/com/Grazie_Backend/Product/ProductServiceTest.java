package Grazie.com.Grazie_Backend.Product;

import Grazie.com.Grazie_Backend.Product.dto.ProductDTO;
import Grazie.com.Grazie_Backend.Product.dto.ProductInformation;
import Grazie.com.Grazie_Backend.Product.service.ProductService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Transactional
@DisplayName("상품 관련 서비스 테스트")
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @BeforeEach
    public void before() {
        System.out.println("Test Before Test");

    }

    @AfterEach
    public void after(){
        System.out.println("Test After");
    }

    @Test
    @DisplayName("모든 상품 조회 테스트")
    public void getAllProductTest() {
        List<ProductDTO> products = productService.getAllProduct();
        for (ProductDTO dto : products) {
            System.out.println(dto.toString());
            System.out.println(dto.getInformation().toString());

            System.out.println();
        }
    }

//    @Test
//    @DisplayName("모든 상품 조회 (중복제거)")
//    public void 상품조회_중복제거() {
//        List<ProductDistinctDTO> dtos = productService.getAllProductDistinct();
//
//        System.out.println(dtos);
//    }

    @Test
    @DisplayName("상품 생성 테스트")
    public void createProductTest() {

        ProductInformation productInformation = new ProductInformation();
        productInformation.setCalories(10);
        productInformation.setSaturatedFat(9);
        productInformation.setProtein(8);
        productInformation.setSodium(7);
        productInformation.setSugar(6);
        productInformation.setCaffeine(5);



        ProductDTO allergy_test = new ProductDTO();
        allergy_test.setName("알러지_테스트_Name");
        allergy_test.setImage("알러지_테스트_Image");
        allergy_test.setSmallPrice(1);
        allergy_test.setMediumPrice(2);
        allergy_test.setLargePrice(3);
        allergy_test.setIceAble(true);
        allergy_test.setHotAble(true);
        allergy_test.setExplanation("알러지_테스트_Explanation");
        allergy_test.setInformation(productInformation);
        allergy_test.setAllergy("유제품, 땅콩");
        allergy_test.setCategory("Coffee");

        ProductInformation productInformation1 = new ProductInformation();
        productInformation1.setCalories(1);
        productInformation1.setSaturatedFat(2);
        productInformation1.setProtein(3);
        productInformation1.setSodium(4);
        productInformation1.setSugar(5);
        productInformation1.setCaffeine(6);

        ProductDTO at2 = new ProductDTO();
        at2.setName("알러지_테스트_Name2");
        at2.setImage("알러지_테스트_Image2");
        at2.setPrice(1023901293);
        at2.setExplanation("알러지_테스트_Explanation2");
        at2.setSize("grande");
        at2.setInformation(productInformation1);
        at2.setTemperature("both");
        at2.setAllergy("알러지, 싫어요");

//        ProductInformation productInformation2 = new ProductInformation();
//        productInformation2.setCalories(290);
//        productInformation2.setSaturatedFat(9);
//        productInformation2.setProtein(10);
//        productInformation2.setSodium(105);
//        productInformation2.setSugar(25);
//        productInformation2.setCaffeine(95);
//
//        ProductDTO caffe_mocha = new ProductDTO();
//        caffe_mocha.setName("카페 모카");
//        caffe_mocha.setImage("Caffe Mocha");
//        caffe_mocha.setPrice(5000);
//        caffe_mocha.setExplanation("진한 초콜릿 모카 시럽과 풍부한 에스프레소를 스팀 밀크와 섞어 휘핑크림으로 마무리한 음료로 진한 에스프레소와 초콜릿 맛이 어우러진 커피");
//        caffe_mocha.setSize("tall");
//        caffe_mocha.setInformation(productInformation2);
//        caffe_mocha.setTemperature("both");
//
//        ProductInformation productInformation3 = new ProductInformation();
//        productInformation3.setCalories(5);
//        productInformation3.setSaturatedFat(0);
//        productInformation3.setProtein(0);
//        productInformation3.setSodium(0);
//        productInformation3.setSugar(0);
//        productInformation3.setCaffeine(75);
//
//        ProductDTO espresso = new ProductDTO();
//        espresso.setName("에스프레소");
//        espresso.setImage("Espresso");
//        espresso.setPrice(3500);
//        espresso.setExplanation("스타벅스 에스프레소는 향기로운 크레마 층과 바디 층, 하트 층으로 이루어져 있으며, 입안 가득히 커피와 달콤한 카라멜 향이 느껴지는 커피 음료");
//        espresso.setSize("solo");
//        espresso.setInformation(productInformation3);
//        espresso.setTemperature("hot");
//
//        ProductInformation productInformation4 = new ProductInformation();
//        productInformation4.setCalories(110);
//        productInformation4.setSaturatedFat(3);
//        productInformation4.setProtein(6);
//        productInformation4.setSodium(70);
//        productInformation4.setSugar(8);
//        productInformation4.setCaffeine(75);
//
//        ProductDTO cappuccino = new ProductDTO();
//        cappuccino.setName("카푸치노");
//        cappuccino.setImage("Cappuccino");
//        cappuccino.setPrice(5500);
//        cappuccino.setExplanation("풍부하고 진한 에스프레소에 따뜻한 우유와 벨벳 같은 우유 거품이 1:1 비율로 어우러져 마무리된 커피 음료");
//        cappuccino.setSize("tall");
//        cappuccino.setInformation(productInformation4);
//        cappuccino.setTemperature("both");

//        productService.createProduct(americano);
//        productService.createProduct(caffe_latte);
//        productService.createProduct(caffe_mocha);
        productService.createProduct(allergy_test);
        productService.createProduct(at2);

    }

//    @Test
//    @DisplayName("모든 상품 조회_중복제거")
//    public void 모든상품조회_중복제거() {
//        List<ProductDistinctDTO> dtos = productService.getAllProductDistinct();
//
//        System.out.println(dtos);
//    }

    @Test
    @DisplayName("사이즈, 온도 조회")
    public void 이름으로사이즈온도조회() {
//        List<ProductSizeTempDTO> dtos = productService.getSizeTempByName("바닐라 카페라떼");
//
//        System.out.println(dtos);
    }
    @Test
    @DisplayName("상품 상세보기 테스트")
    public void getProductByIdTest() {
        ProductDTO dto = productService.getProductById(123L);

        System.out.println(dto.toString());
    }

    @Test
    @DisplayName("상품 수정 테스트")
    public void updateProductTest() {
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    public void deleteProductByIdTest() {
        boolean flag = productService.deleteProductById(190L);

        System.out.println(flag);
    }
}