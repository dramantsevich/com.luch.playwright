//package page;
//
//import com.microsoft.playwright.*;
//
//public class TestRunner {
//    static Browser chrome;
//    static BrowserContext context;
//    static Page page;
//
//    public static void launchBrowser(String URL){
//        try (Playwright playwright = Playwright.create()){
//            BrowserType browserType = playwright.chromium();
//            chrome = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
//            context = chrome.newContext();
//            page = context.newPage();
//            page.navigate(URL);
//            page.setDefaultTimeout(50000);
//        }
//    }
//
////    public static void closeBrowser(){
////        page.close();
////        context.close();
////        chrome.close();
////    }
//}
