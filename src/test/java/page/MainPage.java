package page;

import com.microsoft.playwright.Page;
import model.User;

public class MainPage {
    private final Page page;

    private String oneClickOrderButton = "(//div[@class='owl-item active']//span[@class='oneclick-btn _big _js-b-pop-oneclick'])";
    private String clientNameInput = "input[name=\"form_text_38\"]";
    private String clientPhoneInput = "input[name=\"form_text_39\"]";
    private String clientEmailInput = "[placeholder=\"mail@domen.com\"]";
    private String submitOneClickOrderFormButton = "text=Send form";
    private String formOneClickOrderSuccessfullMessage = "text=Your message was sent successfully";

    public MainPage(Page page){
        this.page = page;
    }

    public MainPage clickWatchesToOneClickOrder(int index){
        page.click(oneClickOrderButton + "["+ index +"]");

        return this;
    }

    public MainPage inputFieldsInOneClickOrderPopup(User user){
        page.fill(clientNameInput, user.getUsername());
        page.fill(clientPhoneInput, user.getPhone());
        page.fill(clientEmailInput, user.getEmail());

        return this;
    }

    public MainPage inputClientPhoneField(User user){
        page.fill(clientPhoneInput, user.getPhone());

        return this;
    }

    public MainPage inputClientNameField(User user){
        page.fill(clientNameInput, user.getUsername());

        return this;
    }

    public MainPage submitFormOneClickOrder() {
        page.click(submitOneClickOrderFormButton);

        return this;
    }

    public String getFormOneClickOrderSuccessfullMessage(){
        return page.innerText(formOneClickOrderSuccessfullMessage);
    }

}
