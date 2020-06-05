package xueqiu;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * 行情
 */
public class StockPage extends BasePage {

    public StockPage(AndroidDriver driver) {
        super(driver);
    }

    //清空股票
    public StockPage clear(){
        //判断是否存在需要清理的股票
        if (isExist(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout"))) {
            //进入股票管理
            click(By.id("com.xueqiu.android:id/edit_group"));
            //获取已添加的股票列表
            List<MobileElement> elements = getList(By.xpath("//android.widget.ListView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.CheckBox"));
            for (MobileElement element : elements) {
                click(element);
            }
            //取消关注
            click(By.id("com.xueqiu.android:id/cancel_follow"));
            //确认
            click(By.id("com.xueqiu.android:id/tv_right"));
            //完成
            click(By.id("com.xueqiu.android:id/action_close"));
        }
        return this;
    }

    //添加自选
    public StockPage searchToAdd(String keyword){
        //搜索添加
        click(By.id("com.xueqiu.android:id/action_search"));
        //搜索输入关键字
        click(By.id("com.xueqiu.android:id/search_input_text"));
        sendKeys(By.id("com.xueqiu.android:id/search_input_text"),keyword);
        //添加自选
        click(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]"));
        click(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout[3]/android.widget.TextView"));
        //关闭搜索框，返回行情页
        click(By.id("com.xueqiu.android:id/action_close"));
        return this;
    }

    //获取自选股列表
    public List<String> getStockList(){
        List<String> nameList = new ArrayList<>();
        for(MobileElement element : getList(By.id("portfolio_stockName"))){
            nameList.add(element.getText());
        }
        return nameList;
    }

    //获取空列表页提示
    public String getTips(){
        return getText(By.id("tv_des"));
    }


}
