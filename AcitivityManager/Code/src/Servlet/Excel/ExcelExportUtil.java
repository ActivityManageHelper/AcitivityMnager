package servlet;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ExcelExportUtil {
    //表头
    private String title;
    //各个列的表头
    private String[] heardList;
    //各个列的元素key值
    private String[] heardKey;
    //需要填充的数据信息
    private List<Map<String,String>> data;
    //字体大小
    private int fontSize = 14;
    //行高
    private int rowHeight = 30;
    //列宽
    private int columWidth = 200;
    //工作表
    private String sheetName = "sheet1";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getHeardList() {
        return heardList;
    }

    public void setHeardList(String[] heardList) {
        this.heardList = heardList;
    }

    public String[] getHeardKey() {
        return heardKey;
    }

    public void setHeardKey(String[] heardKey) {
        this.heardKey = heardKey;
    }

    public List<Map<String,String>> getData() {
        return data;
    }

    public void setData(List<Map<String,String>> data) {
        this.data = data;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    public int getColumWidth() {
        return columWidth;
    }

    public void setColumWidth(int columWidth) {
        this.columWidth = columWidth;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    /**
     * 开始导出数据信息
     *
     */
    public byte[] exportExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //检查参数配置信息
        checkConfig();
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建工作表
        HSSFSheet wbSheet = wb.createSheet(this.sheetName);
        //设置默认行宽
        wbSheet.setDefaultColumnWidth(20);

        // 标题样式（加粗，垂直居中）
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setBold(true);   //加粗
        fontStyle.setFontHeightInPoints((short)16);  //设置标题字体大小
        cellStyle.setFont(fontStyle);

        //在第0行创建rows  (表标题)
        HSSFRow title = wbSheet.createRow(0);
        title.setHeightInPoints(30);//行高
        HSSFCell cellValue = title.createCell(0);
        cellValue.setCellValue(this.title);
        cellValue.setCellStyle(cellStyle);
        //合并单元格，CellRangeAddress(起始行号，终止行号， 起始列号，终止列号）
        wbSheet.addMergedRegion(new CellRangeAddress(0,0,0,(this.heardList.length-1)));

        //设置列名的样式
        HSSFCellStyle style = wb.createCellStyle();
        //设置单元格样式
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) this.fontSize);
        style.setFont(font);
        //在第1行创建rows
        HSSFRow row = wbSheet.createRow(1);
        //设置列头元素
        HSSFCell cellHead = null;
        for (int i = 0; i < heardList.length; i++) {
            cellHead = row.createCell(i);
            cellHead.setCellValue(heardList[i]);
            cellHead.setCellStyle(style);
        }

        //设置每格数据的样式 （字体红色）没用
        HSSFCellStyle cellParamStyle = wb.createCellStyle();
        HSSFFont ParamFontStyle = wb.createFont();
        cellParamStyle.setAlignment(HorizontalAlignment.CENTER);
        cellParamStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        ParamFontStyle.setColor(HSSFColor.HSSFColorPredefined.DARK_RED.getIndex());   //设置字体颜色 (红色)
        ParamFontStyle.setFontHeightInPoints((short) this.fontSize);
        cellParamStyle.setFont(ParamFontStyle);

        //设置每格数据的样式2（字体蓝色）  没用
        HSSFCellStyle cellParamStyle2 = wb.createCellStyle();
        cellParamStyle2.setAlignment(HorizontalAlignment.CENTER);
        cellParamStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont ParamFontStyle2 = wb.createFont();
        ParamFontStyle2.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());   //设置字体颜色 (蓝色)
        ParamFontStyle2.setFontHeightInPoints((short) this.fontSize);
        cellParamStyle2.setFont(ParamFontStyle2);

        //开始写入实体数据信息
        int a = 2;  //从第2行开始写入数据
        for (int i = 0; i < data.size(); i++) {
            HSSFRow roww = wbSheet.createRow(a);
            Map<String,String> map = data.get(i);
            HSSFCell cell = null;
            for (int j = 0; j < heardKey.length; j++) {
                cell = roww.createCell(j);
                cell.setCellStyle(style);
                String value = map.get(heardKey[j]);
                cell.setCellValue(value);
            }
            a++;
        }

        //导出数据
        try {
            System.out.println("开始执行excel输出!!");
            OutputStream output=response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=details.xls");
            response.setContentType("application/msexcel");
            wb.write(output);
            output.close();
            return wb.getBytes();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException("导出Excel出现严重异常，异常信息：" + ex.getMessage());
        }

    }

    /**
     * 检查数据配置问题
     *
     * @throws IOException 抛出数据异常类
     */
    protected void checkConfig() throws IOException {
        if (heardKey == null || heardList.length == 0) {
            throw new IOException("列名数组不能为空或者为NULL");
        }

        if (fontSize < 0 || rowHeight < 0 || columWidth < 0) {
            throw new IOException("字体、宽度或者高度不能为负值");
        }

        if (sheetName==null) {
            throw new IOException("工作表表名不能为NULL");
        }
    }
}
