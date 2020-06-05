package xueqiu;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StockPageTest {

    static MainPage mainPage;
    static StockPage stockPage;

    @BeforeAll
    static void beforeAll(){
        mainPage = new MainPage();
        stockPage = mainPage.toStock();
    }

    //清空股票
    @Order(1)
    @Test
    void clear() {
        //无数据时提示
        String tips = "添加感兴趣的股票，查看\n" + "实时行情";
        stockPage.clear();
        assertEquals(tips,stockPage.getTips());
    }

    //添加自选
    @Order(2)
    @ParameterizedTest
    @ValueSource(strings = {"阿里巴巴","京东","格力电器"})
    void searchToAdd(String keyword) {
        stockPage.searchToAdd(keyword);
        assertEquals(keyword, stockPage.getStockList().get(0));
    }

    @AfterAll
    static void afterAll(){
        stockPage.quit();
    }
}