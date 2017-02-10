package heat.kf.com.tinkenlibrary.tools;

import android.os.Environment;

import java.io.File;

/**
 * Created by "sinlov" on 2017/2/3.
 */
public interface MyConstants {

    String SDCard_FILEPATH = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Android"+File.separator+"date";
    String TINKER_DEX = "tinker_dex";
    String OPT_DEX = "opt_dex";

}
