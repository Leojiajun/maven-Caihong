package libs;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import utils.TopDo;


public class ScreenShotOnFailure {
    private final static String SCREEN_SHOT_PATH = "test-output/screen-shot";  
    private static String SCREEN_SHOT_NAME = null;  
  
    public static void takeScreentShot() throws IOException {  
        File screenShotDir = new File(SCREEN_SHOT_PATH);  
        if (!screenShotDir.exists()) {  
            screenShotDir.mkdirs();  
        }  
        SimpleDateFormat sdf = new SimpleDateFormat();
		String layout = "yyyyMMddHHmmss";
		sdf.applyPattern(layout);
		Calendar c1 = Calendar.getInstance();
		String dateStr = sdf.format(c1.getTime());
        SCREEN_SHOT_NAME = dateStr + ".jpg";  
      //SCREEN_SHOT_NAME = String.valueOf(new Date().getTime()) + ".jpg"; 
        FileUtils.copyFile( ((TakesScreenshot) TopDo.getDriver()).getScreenshotAs(OutputType.FILE), 
                new File(SCREEN_SHOT_PATH + "/" + SCREEN_SHOT_NAME));  
    }  
  
    public static String getScreenShotPath() {  
        return SCREEN_SHOT_PATH;  
    }  
  
    public static String getScreenShotName() {  
        return SCREEN_SHOT_NAME;  
    }  
}
