
import org.apache.log4j.BasicConfigurator;
import spark.Spark;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Spark.staticFileLocation("/Webshop");
        String layout = "Css/RegisterScreen.css";
        View createPage= new View();
        createPage.RenderHomeView();
        createPage.RenderRegisterView();
        createPage.RenderAdminpageView();
        createPage.RenderModifyView();
        createPage.RenderShopView();
        createPage.RenderProfileView();
    }

}

