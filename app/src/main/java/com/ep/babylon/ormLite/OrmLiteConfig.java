package com.ep.babylon.ormLite;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;

/**
 * Created by Eduardo on 15/10/2015.
 */
public class OrmLiteConfig extends OrmLiteConfigUtil {
    private static final Class<?>[] CLASSES = new Class[] {
            ContactRow.class
    };
    public static final String CONFIG_FILE_PATH = "app/src/main/res/raw/ormlite_config.txt";

    public static void main(String[] args) throws Exception {
        writeConfigFile(new File(CONFIG_FILE_PATH), CLASSES);
    }
}
