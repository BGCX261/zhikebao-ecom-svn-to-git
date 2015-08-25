package com.xyz.trade.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.xyz.trade.model.TradeModel;
import com.xyz.trade.service.ITradeService;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;

/**
 * 基于POI导出Excel文件的Action.
 * 
 * @author calvin
 */
@Controller
public class ExcelExportAction {

	@Autowired
	private ITradeService ts;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 生成Excel格式的内容.
	 */
	@RequestMapping("/exporttradexsl")
	public ModelAndView exportXsl(TradeModel tm, HttpServletRequest request,
			HttpServletResponse response) {

		// 输出Excel文件.
		Page<TradeModel> page = new Page<TradeModel>(100);
		page.setStart(0);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		/*
		 * 设置查询条件
		 */
		if (tm != null) {
			if (tm.getBuyerNick() != null
					&& tm.getBuyerNick().trim().length() > 0)
				filters.add(new PropertyFilter("EQ_buyerNick", tm
						.getBuyerNick().trim()));
			if (tm.getCreated() != null)
				filters.add(new PropertyFilter("GE_created", tm.getCreated()));
			if (tm.getModified() != null)
				filters.add(new PropertyFilter("LE_created", tm.getModified()));
			if (tm.getIid() != null && tm.getIid().trim().length() > 0)
				filters.add(new PropertyFilter("EQ_tid", tm.getIid().trim()));
			if (tm.getStatus() != null && tm.getStatus().trim().length() > 0)
				filters.add(new PropertyFilter("EQ_status", tm.getStatus()
						.trim()));
			if (tm.getOwnerId() != null && tm.getOwnerId() > 0)
				filters.add(new PropertyFilter("EQ_ownerId", tm.getOwnerId()));
		}
		page = ts.queryPage(page, filters);
		List<TradeModel> lf = page.getResult();
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("trades", lf);
	    return new ModelAndView(new WidgetListExcelView(), model);
	}
}

class WidgetListExcelView extends AbstractExcelView {

	private Map<String, CellStyle> styles;
	private int rowIndex = 0;

	private void generateTotals(Sheet s) {

		Row r = s.createRow(rowIndex++);
		CellStyle totalStyle = styles.get("total");

		// 分行的Cell
		Cell c1 = r.createCell(0);
		c1.setCellStyle(totalStyle);
		c1.setCellValue("合\n计");

		// 合计公式的Cell
		Cell c2 = r.createCell(1);
		c2.setCellStyle(totalStyle);
		c2.setCellFormula("SUM(B3:B32)");

		Cell c3 = r.createCell(2);
		c3.setCellStyle(totalStyle);
		c3.setCellFormula("SUM(C3:C32)");
	}

	private Map<String, CellStyle> createStyles(Workbook wb) {
		styles = new HashMap<String, CellStyle>();
		DataFormat df = wb.createDataFormat();

		// 字体设定 //

		// 普通字体
		Font normalFont = wb.createFont();
		normalFont.setFontHeightInPoints((short) 10);

		// 加粗字体
		Font boldFont = wb.createFont();
		boldFont.setFontHeightInPoints((short) 10);
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// 蓝色加粗字体
		Font blueBoldFont = wb.createFont();
		blueBoldFont.setFontHeightInPoints((short) 10);
		blueBoldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		blueBoldFont.setColor(IndexedColors.BLUE.getIndex());

		// Cell Style设定 //

		// 标题格式
		CellStyle headerStyle = wb.createCellStyle();
		headerStyle.setFont(boldFont);
		styles.put("header", headerStyle);

		// 日期格式
		CellStyle dateCellStyle = wb.createCellStyle();
		dateCellStyle.setFont(normalFont);
		dateCellStyle.setDataFormat(df.getFormat("yyyy"));
		setBorderAll(dateCellStyle);
		styles.put("dateCell", dateCellStyle);

		// 数字格式
		CellStyle numberCellStyle = wb.createCellStyle();
		numberCellStyle.setFont(normalFont);
		numberCellStyle.setDataFormat(df.getFormat("#,##0.00"));
		setBorderAll(numberCellStyle);
		styles.put("numberCell", numberCellStyle);

		// 合计列格式
		CellStyle totalStyle = wb.createCellStyle();
		totalStyle.setFont(blueBoldFont);
		totalStyle.setWrapText(true);
		totalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		setBorderAll(totalStyle);
		styles.put("total", totalStyle);
		return styles;
	}

	private void setBorderAll(CellStyle style) {
		// 设置边框
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook wb, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception {
		// 创建所有Cell Style
		createStyles(wb);

		// 创建工作表.
		Sheet s = wb.createSheet("temperature anomaly");

		// 设定冻结表头
		s.createFreezePane(0, 2, 0, 2);

		// 设定所有Column宽度自动配合内容宽度
		s.autoSizeColumn(0);
		s.autoSizeColumn(1);
		s.autoSizeColumn(2);

		// 产生标题
		generateTitle(s);
		// 产生表头
		generateHeader(s);
		// 产生内容
		generateContent(s, model);
		// 产生合计
		generateTotals(s);

	}

	private void generateTitle(Sheet s) {
		Row r = s.createRow(rowIndex++);
		Cell c1 = r.createCell(0);
		c1.setCellValue("Temperature Anomaly");
		c1.setCellStyle(styles.get("header"));
		// 合并单元格
		s.addMergedRegion(CellRangeAddress.valueOf("$A$1:$C$1"));
	}

	private void generateHeader(Sheet s) {

		Row r = s.createRow(rowIndex++);
		CellStyle headerStyle = styles.get("header");

		Cell c1 = r.createCell(0);
		c1.setCellValue("Year");
		c1.setCellStyle(headerStyle);

		Cell c2 = r.createCell(1);
		c2.setCellValue("Anomaly");
		c2.setCellStyle(headerStyle);

		Cell c3 = r.createCell(2);
		c3.setCellValue("Smoothed");
		c3.setCellStyle(headerStyle);
	}

	private void generateContent(Sheet s, Map<String, Object> model) {
		CellStyle dateCellStyle = styles.get("dateCell");
		CellStyle numberCellStyle = styles.get("numberCell");
		List<TradeModel> lt = (List) model.get("trades");
		for (TradeModel tm : lt) {
			Row r = s.createRow(rowIndex++);

			Cell c1 = r.createCell(0);
			c1.setCellValue(tm.getCreated());
			c1.setCellStyle(dateCellStyle);

			Cell c2 = r.createCell(1);
			c2.setCellValue(tm.getTitle());
			c2.setCellStyle(numberCellStyle);

			Cell c3 = r.createCell(2);
			c3.setCellValue(tm.getBuyerNick());
			c3.setCellStyle(numberCellStyle);
		}
	}

}
