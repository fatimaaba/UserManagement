package ir.manage.manageofusers.serviceimpluserunittest;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

@UtilityClass
public class TestUtility {

    public static String randomEmail() {
        return RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomAlphabetic(3) + ".com";
    }
}
