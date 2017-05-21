package com.kenji1947.realmfit.model;

/**
 * Created by kenji1947 on 21.05.2017.
 */

public class LocalUtil {

    public static String getString(LocaleSensitiveResource resource, String lang, boolean isUser) {

        if (isUser)
            return resource.getEn(); //user data will always go in "en" field

        switch (lang){
            case "ru":
                return resource.getRu();
            default:
                return resource.getEn();
        }
    }
}
