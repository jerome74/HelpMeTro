package it.wlp.android.dialog.handler.manage;

import static it.mygeo.project.constants.UTIL_GEO.DIALOG_CATEGORY_TEXT_STYLE_VALUE;
import static it.mygeo.project.constants.UTIL_GEO.SELECT_HELPMETRO_DESC;
import static it.mygeo.project.constants.UTIL_GEO.SELECT_MENU_EXIT;
import it.wlp.android.dialog.element.DialogElement;
import it.wlp.android.dialog.element.domain.DialogTextView;
import it.wlp.android.dialog.element.domain.M30MenuDialog;
import it.wlp.android.dialog.element.domain.MenuDialog;
import it.wlp.android.dialog.handler.DialogHandler;

public class ManageDialogHandler extends DialogHandler {

	@Override
	public DialogElement newElement(int id) 
	{
		switch (id) 
		{
		case DIALOG_CATEGORY_TEXT_STYLE_VALUE:
			return new DialogTextView();
			
		case SELECT_MENU_EXIT:
			return new MenuDialog();
			
		case SELECT_HELPMETRO_DESC:
			return new M30MenuDialog();

		default:
			return null;

		}
	}

}
