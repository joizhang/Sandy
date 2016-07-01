package com.joizhang.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.joizhang.model.po.RmsUser;

public class ItextPdfTableUtil {
	
	/* 自定义字体 */
	public static final Font HEAD_FONT;
	public static final Font KEY_FONT;
	public final static Font TEXT_FONT;
	
	static {
		//System.out.println((new File("C:/Windows/Fonts/simsun.ttf")).exists());
		BaseFont SimSun = null;
		try {
			SimSun = BaseFont.createFont("C:/Windows/Fonts/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HEAD_FONT = new Font(SimSun, 16f, Font.BOLD);
		KEY_FONT = new Font(SimSun, 11.5f, Font.BOLD);
		TEXT_FONT = new Font(SimSun, 11.5f, Font.NORMAL);
	}
	
	/**
	 * 创建表格
	 * @param colNumber:列数
	 * @param TotalWidth:总长度
	 */
	public PdfPTable createTable(int colNumber, int TotalWidth) {
		PdfPTable table = new PdfPTable(colNumber);
		try {
			table.setTotalWidth(TotalWidth);
			//table.setWidths(new float[]{TotalWidth});
			table.setLockedWidth(true);
			// table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setBorderWidthRight(0);
			table.getDefaultCell().setBorder(1);
			//table.setWidthPercentage(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}
	
	/**
	 * 正常情况的cell
	 * @param value:输入数据
	 * @param font:字体
	 * @param boderFlag:是否有边框
	 */
	public PdfPCell createCell(String value, Font font, int align, boolean boderFlag) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setPhrase(new Phrase(value, font));
		if (!boderFlag) {
			cell.setBorder(0);
			cell.setPaddingTop(15.0f);
			cell.setPaddingBottom(15.0f);
		}
		return cell;
	}
	
	/**
	 * 创建合并列
	 * @param value:输入数据
	 * @param font:字体
	 * @param align:数据显示位置
	 * @param colspan:所占列数
	 * @param boderFlag:是否有边框
	 */
	public PdfPCell createColspanCell(String value, Font font,
			int align, int colspan, boolean boderFlag) {
		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase(value, font));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		cell.setPadding(5.0f);
		if (!boderFlag) {
			cell.setBorder(0);
			cell.setPaddingTop(15.0f);
			cell.setPaddingBottom(15.0f);
		}
		return cell;
	}
	
	/**
	 * 创建合并行
	 * @param value:输入数据
	 * @param font:字体
	 * @param verticalAlign:
	 * @param colspan:所占列数
	 * @param boderFlag:是否有边框
	 * @return
	 */
	public PdfPCell createRowspanCell(String value, Font font,
			int verticalAlign, int rowspan, boolean boderFlag) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(verticalAlign);
		cell.setRowspan(rowspan);
		cell.setPhrase(new Phrase(value, font));
		cell.setPadding(3.0f);
		if (!boderFlag) {
			cell.setBorder(0);
			cell.setPaddingTop(15.0f);
			cell.setPaddingBottom(15.0f);
		}
		return cell;
	}
	
	/**
	 * 创建合并行列
	 * @param value:输入数据
	 * @param font:字体
	 * @param align:数据显示位置
	 * @param colspan:所占列数
	 * @param boderFlag:是否有边框
	 * @return
	 */
	public PdfPCell createRowspanAndColspanCell(String value, Font font,
			int align, int rowspan, int colspan, boolean boderFlag) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setRowspan(rowspan);
		cell.setColspan(colspan);
		cell.setPhrase(new Phrase(value, font));
		cell.setPadding(3.0f);
		if (!boderFlag) {
			cell.setBorder(0);
			cell.setPaddingTop(15.0f);
			cell.setPaddingBottom(15.0f);
		}
		return cell;
	}
	
	/**
	 * 创建图像的合并行列
	 * @param image:图片
	 * @param align:数据显示位置
	 * @param colspan:所占列数
	 * @param boderFlag:是否有边框
	 * @return
	 */
	public PdfPCell createRowspanColspanCell(Image image, int align,
			int rowspan, int colspan, boolean boderFlag) {
		PdfPCell cell = new PdfPCell();
		PdfPCell imageCell = new PdfPCell();
		PdfPTable imageTable= new PdfPTable(1);
		//image.setWidthPercentage(10);
		image.scaleAbsolute(60, 90);
		//tImgCover.scalePercent(50);
		imageCell.setImage(image);
		//imageCell.setFixedHeight(50);
		imageCell.setBorder(0);
		imageTable.addCell(imageCell);
		
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setRowspan(rowspan);
		cell.setColspan(colspan);
		cell.addElement(imageTable);
		//cell.setPadding(3.0f);
		if (!boderFlag) {
			cell.setBorder(0);
		}
		return cell;
	}
	
	public void exportAllUser(List<RmsUser> list, String photoPath, String pdfDownload){
		//设置为A4纸
		Document document = new Document(PageSize.A4);
		try {
			//1、若目标文件存在则删除
			File targetFile = new File(pdfDownload + "test.pdf");
			if(targetFile.exists()) {
				targetFile.delete();
			}
			
			//2、
			PdfWriter.getInstance(document, new FileOutputStream(pdfDownload + "test.pdf"));
			document.open();
			
			ItextPdfTableUtil ipt = new ItextPdfTableUtil();
			
			//4、表格为9列，宽度为100%
			float[] widths = {0.1f,0.12f,0.1f,0.1f,0.15f,0.1f,0.18f,0.09f,0.06f};
			
			if(list.size() > 0){
				for(RmsUser u : list){
					//5、标题
					PdfPTable table = new PdfPTable(widths);
					table.setWidthPercentage(100);
					PdfPCell cell = new PdfPCell();
					cell = ipt.createColspanCell("2015年重庆市普通高职（专科）学生“专升本”申请推荐及考试报名表", ItextPdfTableUtil.HEAD_FONT,Element.ALIGN_CENTER, 9, false);
					table.addCell(cell);
					
					//样式定义行
					cell = ipt.createCell(" ", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, false);
					table.addCell(cell);
					cell = ipt.createCell(" ", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, false);
					table.addCell(cell);
					cell = ipt.createCell(" ", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, false);
					table.addCell(cell);
					cell = ipt.createCell(" ", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, false);
					table.addCell(cell);
					cell = ipt.createCell(" ", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, false);
					table.addCell(cell);
					cell = ipt.createCell(" ", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, false);
					table.addCell(cell);
					cell = ipt.createCell(" ", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, false);
					table.addCell(cell);
					cell = ipt.createCell(" ", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, false);
					table.addCell(cell);
					cell = ipt.createCell(" ", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, false);
					table.addCell(cell);
					
					//、姓名
					cell = ipt.createRowspanCell("姓名", ItextPdfTableUtil.KEY_FONT, Element.ALIGN_CENTER, 2, true);
					table.addCell(cell);
					cell = ipt.createRowspanCell(u.getUsername(), ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, 2, true);
					table.addCell(cell);
					
					//、性别
					cell = ipt.createRowspanCell("性别", ItextPdfTableUtil.KEY_FONT, Element.ALIGN_CENTER, 2, true);
					table.addCell(cell);
					cell = ipt.createRowspanCell("女", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, 2, true);
					table.addCell(cell);
					
					//、身份证号码
					cell = ipt.createCell("身份证号码", ItextPdfTableUtil.KEY_FONT, Element.ALIGN_CENTER, true);
					table.addCell(cell);
					cell = ipt.createColspanCell("500234199409116054", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, 2, true);
					table.addCell(cell);
					
					//、照片
					try {
						Image image = Image.getInstance("E:/重庆市招生考试服务有限责任公司/photo library/32090219921120006X.jpg");
						cell = ipt.createRowspanColspanCell(image, Element.ALIGN_CENTER, 4, 2, true);
						table.addCell(cell);
					} catch (Exception e) {
						//e.printStackTrace();
						System.out.println(e.toString());
						cell = ipt.createRowspanAndColspanCell("无照片", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, 4, 2, true);
						table.addCell(cell);
					}
					
					//、专升本报名号
					cell = ipt.createCell("专升本报名号", ItextPdfTableUtil.KEY_FONT, Element.ALIGN_CENTER, true);
					table.addCell(cell);
					cell = ipt.createColspanCell("12300210007", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, 2, true);
					table.addCell(cell);
					
					//、民族
					cell = ipt.createCell("民族", ItextPdfTableUtil.KEY_FONT, Element.ALIGN_CENTER, true);
					table.addCell(cell);
					cell = ipt.createCell("汉族", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, true);
					table.addCell(cell);
					
					//、政治面貌
					cell = ipt.createCell("政治面貌", ItextPdfTableUtil.KEY_FONT, Element.ALIGN_CENTER, true);
					table.addCell(cell);
					cell = ipt.createCell("共青团员", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, true);
					table.addCell(cell);
					
					//、生源地
					cell = ipt.createCell("生源地", ItextPdfTableUtil.KEY_FONT, Element.ALIGN_CENTER, true);
					table.addCell(cell);
					cell = ipt.createColspanCell("重庆", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, 2, true);
					table.addCell(cell);
					
					//、推荐学校及专业
					cell = ipt.createColspanCell("推荐学校及专业", ItextPdfTableUtil.KEY_FONT, Element.ALIGN_CENTER, 2, true);
					table.addCell(cell);
					cell = ipt.createColspanCell("推荐学校及专业", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, 5, true);
					table.addCell(cell);
					
					//、第一志愿学校及专业
					cell = ipt.createColspanCell("第一志愿学校及专业", ItextPdfTableUtil.KEY_FONT, Element.ALIGN_CENTER, 2, true);
					table.addCell(cell);
					cell = ipt.createColspanCell("推荐学校及专业", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, 3, true);
					table.addCell(cell);
					
					//、是否服从第一志愿学校调配
					cell = ipt.createColspanCell("是否服从第一志愿学校调配", ItextPdfTableUtil.KEY_FONT, Element.ALIGN_CENTER, 3, true);
					table.addCell(cell);
					cell = ipt.createCell("", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, true);
					table.addCell(cell);
					
					//、第二志愿学校及专业
					cell = ipt.createColspanCell("第二志愿学校及专业", ItextPdfTableUtil.KEY_FONT, Element.ALIGN_CENTER, 2, true);
					table.addCell(cell);
					cell = ipt.createColspanCell("", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, 3, true);
					table.addCell(cell);
					
					//、是否服从第二志愿学校和全市调配
					cell = ipt.createColspanCell("是否服从第二志愿学校和全市调配", ItextPdfTableUtil.KEY_FONT, Element.ALIGN_CENTER, 3, true);
					table.addCell(cell);
					cell = ipt.createCell("", ItextPdfTableUtil.TEXT_FONT, Element.ALIGN_CENTER, true);
					table.addCell(cell);
					
					//添加table
					document.newPage();
					document.add(table);
				}
			} else {
				PdfPTable table = new PdfPTable(widths);
				table.setWidthPercentage(100);
				PdfPCell cell = new PdfPCell();
				cell = ipt.createColspanCell("暂无数据", ItextPdfTableUtil.HEAD_FONT,Element.ALIGN_CENTER, 9, false);
				table.addCell(cell);
				
				//添加table
				document.newPage();
				document.add(table);
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		document.close();
	}
}
