package heat.kf.com.tinkenlibrary.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import heat.kf.com.tinkenlibrary.tools.MyConstants;

/**
 * Created by "sinlov" on 2017/2/3.
 */
public class Judgment {


    public void fileDex(Context context){
        File fileDir = context.getDir(MyConstants.TINKER_DEX, Context.MODE_PRIVATE);

            //私有目录
            String filePath = fileDir.getAbsolutePath()+File.separator+"classes.dex";

            File file = new File(filePath);

            if (file.exists()){
                Log.d("FLY","sourceDex exists");
                //删除
                file.delete();
            }

            InputStream is = null;
            FileOutputStream os = null;

            try {
                is = Pull.pull(context);
                os = new FileOutputStream(filePath);

                int len = 0;
                byte []buffer = new byte[1024];

                while ((len = is.read(buffer))!=-1){
                    os.write(buffer,0,len);
                }
                File file1 = new File(filePath);
                if (file1.exists()){
                    Toast.makeText(context,"pull and override is complete",Toast.LENGTH_SHORT).show();
                    //注入
                    Injection.loadFixeDex(context);
                }




        } catch (IOException e) {
            e.printStackTrace();
            Log.d("FLY", "aaaaa");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.d("FLY", "ssss");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            Log.d("FLY", "bbbb");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Log.d("FLY", "fffff");
        } finally {

            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

