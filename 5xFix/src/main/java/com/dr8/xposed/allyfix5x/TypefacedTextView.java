package com.dr8.xposed.allyfix5x;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by dlocal on 12/31/2015 in AllyFix5x.
 */
public class TypefacedTextView extends TextView {

    public TypefacedTextView(Context context)
    {
        super(context);
    }

    public TypefacedTextView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        if (attributeset != null)
        {
            TypedArray typedarray = context.obtainStyledAttributes(attributeset, ae.TypefacedTextView);
            int i = typedarray.getInt(0, -1);
            typedarray.recycle();
            if (!isInEditMode())
            {
                Typeface typeface = q.a(context.getAssets(), i);
                if (getTypeface() == null || getTypeface() != null && !getTypeface().equals(typeface))
                {
                    setTypeface(typeface);
                    return;
                }
            }
        }
    }
}
