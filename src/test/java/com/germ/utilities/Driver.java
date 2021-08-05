package com.germ.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {
    private Driver(){}

    /**
     * Making our driver instance private so that it is not reachable from outside of the class
     * We make it static, because we want it to run before everything else, and also we will use it in a static method
     */

    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    /**
     * Creating re-usable utility method that will return same 'driver' instance everytime we cal it
     * @return
     */

    public static WebDriver getDriverPool(){
        if(driverPool.get() == null){
            /**
             * We read the browser type from configuration.properties file using .ConfigurationReader.getProperty("browser");
             */
            String browserType = ConfigurationReader.getProperty("browser");

            /**
             * Depending on the browser type our switch statement will determine to open specific type of browser/type
             */

            switch (browserType){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "remote-chrome":
                    try {
                        String myIp = ConfigurationReader.getProperty("myIp");
                        URL url = new URL("http://"+myIp+":4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("chrome");
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

            }
        }
        /**
         Same driver instance will be returned every time we call Driver.getDriver(); method
         */

        return driverPool.get();
    }

    /*
   This method makes sure we have some form of driver sesion or driver id has.
   Either null or not null it must exist.
    */
    public static void closeDriver(){
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
