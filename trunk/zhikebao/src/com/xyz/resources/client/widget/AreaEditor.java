package com.xyz.resources.client.widget;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.xyz.resources.model.Area;

public class AreaEditor extends CellEditor{
	
    public AreaEditor(AreaComboBox acb) {
		super(acb);
	}
	
	@Override
	public Object preProcessValue(Object value) {
		if (value == null) {
			return value;
		}
		AreaComboBox acb = (AreaComboBox)getField();
		return acb.getStore().findModel("areaName", value.toString());
	}

	@Override
	public Object postProcessValue(Object value) {
		if (value == null) {
			return value;
		}
		BeanModel bm = (BeanModel)value;
		Area area=(Area)bm.getBean();
		if(area!=null)
		   return area.getAreaName();
		else
			return null;
	}

}
