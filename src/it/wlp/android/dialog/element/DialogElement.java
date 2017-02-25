package it.wlp.android.dialog.element;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.view.View;


public interface DialogElement 
{
	 Dialog getDialog();
	 void createDialog();
	 void createDialog(View textView);
	 void setContext(Context context);
	 void setActivity(Activity activity);
	 void setBuilder(Builder builder);
	 void setExtraObj(Object object);
}
