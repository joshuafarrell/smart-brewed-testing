package io.symbolik.utils.data;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class StaticProvider {

    @DataProvider(name = "hardCodedBrowsers")
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
        return new Object[][]{
                //new Object[]{"internet explorer", "11", "Windows 8.1"},
                new Object[]{"chrome", "45", "Windows 7"},
                //new Object[]{"safari", "7", "OS X 10.9"},
                //new Object[]{"firefox", "41", "Windows 7"},
        };
    }
}
