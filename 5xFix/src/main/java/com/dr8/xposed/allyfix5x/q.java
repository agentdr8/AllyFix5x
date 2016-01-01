package com.dr8.xposed.allyfix5x;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by dlocal on 12/31/2015 in AllyFix5x.
 */
public final class q
{
    private static String a = "Utilities-TypefaceCache";
    private static final Hashtable b = new Hashtable();
    private static String str;

    public static Typeface a(AssetManager paramAssetManager, int paramInt)
    {
        Hashtable localHashtable = b;
        switch (paramInt)
        {
            default:
                str = "fonts/Roboto-Thin.ttf";
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
        }
        while (true)
        {
            try
            {
                boolean bool = b.containsKey(str);
                if (!bool);
                try
                {
                    Typeface localTypeface2 = Typeface.createFromAsset(paramAssetManager, str);
                    b.put(str, localTypeface2);
                    Typeface localTypeface1 = (Typeface)b.get(str);
                    return localTypeface1;
                }
                catch (Exception localException)
                {
                    new StringBuilder("Could not get typeface '").append(str).append("' because ").append(localException.getMessage());
                    return null;
                }
            }
            finally
            {
            }
        }
    }
}