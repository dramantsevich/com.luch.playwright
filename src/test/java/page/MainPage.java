package page;

import com.microsoft.playwright.Page;
import model.User;

public class MainPage {
    private final Page page;

    private String oneClickOrderButton = "//div[@class='owl-item active']//span[@class='oneclick-btn _big _js-b-pop-oneclick']";
    private String clientNameInput = "input[name=\"form_text_38\"]";
    private String clientPhoneInput = "input[name=\"form_text_39\"]";
    private String clientEmailInput = "[placeholder=\"mail@domen.com\"]";
    private String submitOneClickOrderFormButton = "text=Send form";
    private String formOneClickOrderSuccessfullMessage = "text=Your message was sent successfully";

    public MainPage(Page page){
        this.page = page;
    }

    public void clickWatchesToOneClickOrder(int index){
        page.click(oneClickOrderButton + "["+ index +"]");
    }

    public void inputFieldsInOneClickOrderPopup(User user){
        page.fill(clientNameInput, user.getUsername());
        page.fill(clientPhoneInput, user.getPhone());
        page.fill(clientEmailInput, user.getEmail());
    }

    public void inputClientPhoneField(User user){
        page.fill(clientPhoneInput, user.getPhone());
    }

    public void inputClientNameField(User user){
        page.fill(clientNameInput, user.getUsername());
    }

    public void submitFormOneClickOrder() {
        page.click(submitOneClickOrderFormButton);
    }

    public String getFormOneClickOrderSuccessfullMessage(){
        return page.innerText(formOneClickOrderSuccessfullMessage);
    }
}
