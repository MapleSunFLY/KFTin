package heat.kf.com.tinkenlibrary.service;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.PathClassLoader;
import heat.kf.com.tinkenlibrary.tools.MyConstants;

/**
 * Created by "sinlov" on 2017/2/3.
 */
public class Injection {

    private static ArrayList<File> loaderDex = new ArrayList<File>();
    private static final String DEX_SUFFIX = ".dex";
    static {
        loaderDex.clear();
    }

    public static  void loadFixeDex(Context context) throws Throwable {
        if (context == null){
            Log.d("FLY","loadFixeDex Context is null");
            return;
        }
        //Dex文件目录
        File fileDir = context.getDir(MyConstants.TINKER_DEX, Context.MODE_PRIVATE);
        File[] listFiles = fileDir.listFiles();
        for (File file : listFiles){
            //判断后缀名
            if (file.getName().endsWith(DEX_SUFFIX)){
                loaderDex.add(file);
            }

        }
        doDexInject(context,fileDir,loaderDex);
    }

    private static void doDexInject(Context context, File fileDir, List<File> loaderDex) throws Throwable {
        //系统ClassLoader
        PathClassLoader loader = (PathClassLoader) context.getClassLoader();
        //临时目录
        String optimizedDir = fileDir.getAbsolutePath()+File.separator+MyConstants.OPT_DEX;
        File optFile = new File(optimizedDir);
        if(!optFile.exists()){
            optFile.mkdirs();
        }

        SystemClassLoaderAdder.installDexes(loader,optFile,loaderDex);

//        for (File dex:loaderDex){
//            DexClassLoader dexClassLoader = new DexClassLoader(dex.getAbsolutePath(),optFile.getAbsolutePath(),null,context.getClassLoader());
//            inject(dexClassLoader,loader);
//        }
    }

//    private static void inject(DexClassLoader dexClassLoader, PathClassLoader loader) throws IllegalAccessException, NoSuchFieldException {
//
//
//        //my
//        //使用反射来得到loader的pathList字段
//        Field apathListField = MultiDex.findField(dexClassLoader, "pathList");
//        // 得到loader的pathList字段后，我们就可以得到这个字段的值，也就是DexPathList对象
//        Object adexPathList = apathListField.get(dexClassLoader);
//        Field adexElementsField = MultiDex.findField(adexPathList, "dexElements");
//        Object[] adexElements = (Object[]) adexElementsField.get(adexPathList);
//
//        //系统
//        //使用反射来得到loader的pathList字段
//        Field pathListField = MultiDex.findField(loader, "pathList");
//        // 得到loader的pathList字段后，我们就可以得到这个字段的值，也就是DexPathList对象
//        Object dexPathList = pathListField.get(loader);
//
//       MultiDex.expandFieldArray(dexPathList,"dexElements",adexElements);
//
//    }






}
