import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.modelAndView;
import static spark.Spark.post;

/**
 * Created by nilmor on 10/27/2016.
 */
public class View {

    Deque<Modal> registerUsers = new ArrayDeque<Modal>();
    Deque<Modal> loginUsers = new ArrayDeque<Modal>();
    public  void RenderHomeView(){
        Controller renderView = new Controller();
        get("/Home", (req, res) -> {renderView.htmlToString("Webshop/HTML/Index.html");
            Map<String, Object> attributes = new HashMap<String, Object>();
            String currentUser = req.session().attribute("User");
            Controller checkUserLevel = new Controller();
            String currentUserLevel = checkUserLevel.checkUserLevel(currentUser);
            attributes.put("userlevel", currentUserLevel);
            System.out.println(currentUserLevel);
        return modelAndView(attributes, "Webshop/HTML/Index.html");
        },new VelocityTemplateEngine());

        post("/Home", (req,res)-> {
        String LoginUsername = req.queryParams("LoginUsername");
        String LoginPassword = req.queryParams("LoginPassword");
        Modal loginUser = new Modal();
        loginUser.LoginModal(LoginUsername,LoginPassword);
        loginUsers.addFirst(loginUser);
        String LoginUser = new Controller().LoginUser(loginUsers);
        req.session().attribute("User",LoginUser);
        System.out.println(req.session().attribute("User")+ " Shamala");

        Map<String, Object> attributes = new HashMap<String, Object>();
        String currentUser = req.session().attribute("User");
        Controller checkUserLevel = new Controller();
        String currentUserLevel = checkUserLevel.checkUserLevel(currentUser);
        attributes.put("userlevel", currentUserLevel);
        System.out.println(currentUserLevel+ "Shamala123");
            return modelAndView(attributes, "Webshop/HTML/Index.html");
        },new VelocityTemplateEngine());
    }
    public  void RenderRegisterView(){
        Controller renderView = new Controller();
        get("/Register", (req, res) -> renderView.htmlToString("Webshop/register.vm"));
        post("/Register", (request, response) -> {
            Map<String, Object> attributes = new HashMap<String, Object>();
            String RegUsername = request.queryParams("RegUsername");
            String RegPassword = request.queryParams("RegPassword");
            String RegEmail = request.queryParams("RegEmail");
            String RegFName = request.queryParams("RegFName");
            String RegLName = request.queryParams("RegLName");
            String RegAge = request.queryParams("RegAge");
            String RegStreet = request.queryParams("RegStreet");
            String RegStreetNumber = request.queryParams("RegStreetNumber");
            String RegCountry = request.queryParams("RegCountry");
            String RegPostalCode = request.queryParams("RegPostalCode");
            String LoginUsername = request.queryParams("LoginUsername");
            String LoginPassword = request.queryParams("LoginPassword");


            Modal loginUser = new Modal();
            loginUser.LoginModal(LoginUsername,LoginPassword);
            loginUsers.addFirst(loginUser);
            String LoginUser = new Controller().LoginUser(loginUsers);
            request.session().attribute("User",LoginUser);
            System.out.println(request.session().attribute("User")+ " Shamala");
            //response.redirect("/Home");

            if(RegUsername.isEmpty()){
                attributes.put("message", "Username is empty");
                return modelAndView(attributes, "Webshop/register.vm");
            }
            if(RegPassword.isEmpty()){
                attributes.put("message", "Password is empty");
                return modelAndView(attributes, "Webshop/register.vm");
            }
            if(RegEmail.isEmpty()){
                attributes.put("message", "Email is empty");
                return modelAndView(attributes, "Webshop/register.vm");
            }
            if(RegFName.isEmpty()){
                attributes.put("message", "First name is empty");
                return modelAndView(attributes, "Webshop/register.vm");
            }
            if(RegLName.isEmpty()){
                attributes.put("message", "Last name is empty");
                return modelAndView(attributes, "Webshop/register.vm");
            }
            if(RegAge.isEmpty()){
                attributes.put("message", "Age is empty");
                return modelAndView(attributes, "Webshop/register.vm");
            }
            if(RegStreet.isEmpty()){
                attributes.put("message", "Street is empty");
                return modelAndView(attributes, "Webshop/register.vm");
            }
            if(RegStreetNumber.isEmpty()){
                attributes.put("message", "Street number is empty");
                return modelAndView(attributes, "Webshop/register.vm");
            }
            if(RegCountry.isEmpty()){
                attributes.put("message", "Country is empty");
                return modelAndView(attributes, "Webshop/register.vm");
            }
            if(RegPostalCode.isEmpty()){
                attributes.put("message", "Postal code is empty");
                return modelAndView(attributes, "Webshop/register.vm");
            }

            Modal registerUser = new Modal();
            registerUser.RegisterModal(RegUsername, RegPassword, RegEmail,RegFName,RegLName,RegAge,RegStreet,RegStreetNumber,RegCountry,RegPostalCode);
            registerUsers.addFirst(registerUser);
            String SaveUser = new Controller().RegisterUser(registerUsers);


            attributes.put("message", SaveUser);
            /*if (SaveUser.contains("Detail: Key (username)")){
                System.out.println("Account not registered account name is already taken");
            }
            else if(SaveUser.contains("Detail: Key (email)")){
                System.out.println("Account not registered account email is already taken");
            }
            else if (SaveUser.contains("Done")){
                System.out.println("Account succesfully registered");
            }
            else {
                System.out.println("Something went horribly wrong please contact us");
            }*/


            return modelAndView(attributes, "Webshop/register.vm");
        },new VelocityTemplateEngine());
    }
}
