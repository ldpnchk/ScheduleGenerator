package com.sg.docx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.sg.entity.extra.LessonTotal;
public class CreateDocx {

	public static File create(List<LessonTotal> result, int course, String specialty) throws IOException {


		String[] days = {"ПН", "ВТ", "СР", "ЧТ", "ПТ"};
		String[] periods = {"8.30-9.50", "10.00-11.20", "11.40-13.00", "13.30-14.50", "15.00-16.20", "16.30-17.50"};

		//Blank Document
		XWPFDocument document= new XWPFDocument();

		//Write the Document in file system
		String fileName = "schedule.docx";
		FileOutputStream out = new FileOutputStream(new File(fileName));

		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun paragraphOneRunOne = paragraph.createRun();
		paragraphOneRunOne.setBold(true);
		paragraphOneRunOne.setFontSize(18);
		paragraphOneRunOne.setText("Розклад");
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText(specialty);
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText(course + " курс");

		try {
			//create table
			XWPFTable table = document.createTable();

			CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();
			width.setType(STTblWidth.DXA);
			width.setW(BigInteger.valueOf(9072));

			CTTblPr tblpro = table.getCTTbl().getTblPr();

			CTTblBorders borders = tblpro.addNewTblBorders();
			CTBorder borderBottom = borders.addNewBottom();
			borderBottom.setSz(BigInteger.valueOf(40));
			borderBottom.setVal(STBorder.THICK); 
			CTBorder borderLeft = borders.addNewLeft();
			borderLeft.setSz(BigInteger.valueOf(40));
			borderLeft.setVal(STBorder.THICK);
			CTBorder borderRight = borders.addNewRight();
			borderRight.setSz(BigInteger.valueOf(40));
			borderRight.setVal(STBorder.THICK);
			CTBorder borderTop =  borders.addNewTop();
			borderTop.setSz(BigInteger.valueOf(40));
			borderTop.setVal(STBorder.THICK);
			//inner borders
			CTBorder borderInnerH = borders.addNewInsideH();
			borderInnerH.setSz(BigInteger.valueOf(20));
			borderInnerH.setVal(STBorder.THICK);
			CTBorder borderInnerV = borders.addNewInsideV();
			borderInnerV.setSz(BigInteger.valueOf(20));
			borderInnerV.setVal(STBorder.THICK);

			XWPFTableRow tableRow;

			for (int i = 0; i < 5; i++){
				for (int j = 0; j < 6; j++){
					if (i + j == 0){
						tableRow = table.getRow(0);
					}
					else{
						tableRow = table.createRow();
					}

					if (j == 0){
						paragraph = tableRow.getCell(0).addParagraph();
						tableRow.getCell(0).removeParagraph(0);
						paragraph.setAlignment(ParagraphAlignment.CENTER);
						XWPFRun run = paragraph.createRun();
						run.setBold(true);
						run.setFontSize(28);
						run.setText(days[i]);
						if (i + j == 0){
							tableRow.addNewTableCell();
							tableRow.addNewTableCell();
						}
					}
					else{
						tableRow.getCell(0).setText("");
					}

					paragraph = tableRow.getCell(1).addParagraph();
					tableRow.getCell(1).removeParagraph(0);
					paragraph.setAlignment(ParagraphAlignment.CENTER);
					XWPFRun run2 = paragraph.createRun();
					run2.setFontSize(18);
					run2.setText(periods[j]);

					String str = "   ";
					int t = 0;
					for (LessonTotal lm : result){
						if ((lm.getCondition().getDay().getId() == (i + 1)) && (lm.getCondition().getPeriod().getId() == (j + 1))){
							str += lm.getCondition().getClassroom().getBuilding() + "-" + lm.getCondition().getClassroom().getNumber() + " " + lm.getLesson().getName() + " " + lm.getLesson().getLecturer().getName();
						}
						if (!str.equals("   ")){
							if (t == 0){
								paragraph = tableRow.getCell(2).addParagraph();
								tableRow.getCell(2).removeParagraph(0);
								XWPFRun run3 = paragraph.createRun();
								run3.setBold(true);
								run3.setFontSize(12);
								run3.setText(str);
								t++;
							}
							else{
								XWPFRun run3 = tableRow.getCell(2).addParagraph().createRun();
								run3.setBold(true);
								run3.setFontSize(12);
								run3.setText(str);
							}
							str = "   ";

						}
					}
					t = 0;
				}
			}
		} catch(Exception e) {
			System.out.println("Schedule is empty!");
			paragraphOneRunOne.addBreak();
			paragraphOneRunOne.setText("Розклад порожній або ще не згенерований");
		}
		document.write(out);
		out.close();

		return new File(fileName);
	}
}
