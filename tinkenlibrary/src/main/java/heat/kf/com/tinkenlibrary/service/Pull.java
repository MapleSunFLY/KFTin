package heat.kf.com.tinkenlibrary.service;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * Created by "sinlov" on 2017/2/3.
 */
public class Pull {

    public static InputStream pull (Context context) throws IOException {

        InputStream pull = context.getResources().getAssets().open("classes.dex");// 获取assets下XM文件输出流
//        InputStream pull =  new FileInputStream();
        return pull;
    }
}
