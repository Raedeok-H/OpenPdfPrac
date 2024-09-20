package com.openpdf.service;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService_10 {

    // 한글 폰트 경로 설정
    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";

    public byte[] createDynamicTablePdf(int rows, int cols) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // PdfWriter와 Document 연결
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open(); // Document 열기

            // 한글 폰트 설정
            BaseFont koreanBaseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font koreanFont = new Font(koreanBaseFont, 12);

            // 동적 테이블 생성
            PdfPTable table = new PdfPTable(cols); // 열의 수를 사용자가 지정
            table.setWidthPercentage(100); // 테이블 너비 100% 설정

            // 테이블 헤더 추가
            for (int col = 0; col < cols; col++) {
                PdfPCell headerCell = new PdfPCell(new Phrase("헤더 " + (col + 1), koreanFont));
                headerCell.setBackgroundColor(Color.LIGHT_GRAY); // 헤더 셀 배경색 설정
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(headerCell);
            }

            // 테이블 내용 동적으로 추가
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    PdfPCell cell = new PdfPCell(new Phrase("셀 " + (row + 1) + "-" + (col + 1), koreanFont));
                    cell.setPadding(10); // 셀 내 여백 설정
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER); // 셀 내 텍스트 중앙 정렬
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    if ((row + col) % 2 == 0) {
                        cell.setBackgroundColor(new Color(230, 230, 250)); // 교차 색상 설정
                    }
                    table.addCell(cell);
                }
            }

            document.add(table); // 문서에 테이블 추가

            document.close(); // 문서 닫기
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
