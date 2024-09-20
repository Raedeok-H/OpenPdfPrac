package com.openpdf.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService_5 {

    // 한글 폰트 경로 설정
    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";

    public byte[] createPdfWithTable() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // PdfWriter와 Document 연결
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream); // PdfWriter를 통해 document와 연결

            document.open(); // Document 열기

            // 한글 폰트 설정
            BaseFont koreanBaseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font koreanFont = new Font(koreanBaseFont, 12);

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
            for (int i = 1; i <= 9; i++) {
                PdfPCell cell = new PdfPCell(new Phrase("Cell " + i, koreanFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            // 테이블을 문서에 추가
            document.add(table);

            document.close(); // 문서 닫기
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
