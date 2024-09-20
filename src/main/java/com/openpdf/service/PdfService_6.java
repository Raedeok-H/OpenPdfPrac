package com.openpdf.service;

import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService_6 {

    // 한글 폰트 경로 설정
    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";
    private static final String IMAGE_PATH = "src/main/resources/images/image.jpg"; // 이미지 경로 설정

    public byte[] createPdfWithImageAndTable() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // PdfWriter와 Document 연결
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);

            document.open(); // Document 열기

            // 한글 폰트 설정
            BaseFont koreanBaseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font koreanFont = new Font(koreanBaseFont, 12);

            // 이미지 추가
            Image image = Image.getInstance(IMAGE_PATH);
            image.scaleToFit(200, 200); // 이미지 크기 조정
            image.setAlignment(Element.ALIGN_CENTER); // 이미지 중앙 정렬
            document.add(image); // 문서에 이미지 추가

            // 줄 바꿈을 위한 빈 줄 추가
            document.add(new Paragraph("\n"));

            // 테이블 생성 (3열)
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100); // 테이블 너비를 페이지에 맞게 설정

            // 테이블 헤더 추가
            PdfPCell header1 = new PdfPCell(new Phrase("Header 1", koreanFont));
            header1.setHorizontalAlignment(Element.ALIGN_CENTER);
            header1.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(header1);

            PdfPCell header2 = new PdfPCell(new Phrase("Header 2", koreanFont));
            header2.setHorizontalAlignment(Element.ALIGN_CENTER);
            header2.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(header2);

            PdfPCell header3 = new PdfPCell(new Phrase("Header 3", koreanFont));
            header3.setHorizontalAlignment(Element.ALIGN_CENTER);
            header3.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(header3);

            // 테이블 내용 추가
            for (int i = 1; i <= 6; i++) {
                PdfPCell cell = new PdfPCell(new Phrase("Cell " + i, koreanFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            // 문서에 테이블 추가
            document.add(table);

            // 문서 닫기
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
