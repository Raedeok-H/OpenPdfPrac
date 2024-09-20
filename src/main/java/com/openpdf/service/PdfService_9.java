package com.openpdf.service;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService_9 {

    // 한글 폰트 경로 설정
    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";

    public byte[] createPdfWithWatermark(String watermarkText) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // PdfWriter와 Document 연결
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            // 페이지 번호와 워터마크 이벤트 추가
            PageNumberWatermarkEvent event = new PageNumberWatermarkEvent(watermarkText);
            writer.setPageEvent(event);

            document.open(); // Document 열기

            // 본문 내용 추가
            BaseFont koreanBaseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font koreanFont = new Font(koreanBaseFont, 12);

            for (int i = 0; i < 5; i++) {
                Paragraph paragraph = new Paragraph("이것은 PDF의 본문 내용입니다. " + (i + 1), koreanFont);
                document.add(paragraph);
                document.newPage();
            }

            document.close(); // 문서 닫기
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    private static class PageNumberWatermarkEvent extends PdfPageEventHelper {
        private final String watermarkText;

        public PageNumberWatermarkEvent(String watermarkText) {
            this.watermarkText = watermarkText;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                BaseFont baseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font font = new Font(baseFont, 12, Font.NORMAL);
                Font watermarkFont = new Font(baseFont, 50, Font.BOLD, new Color(200, 200, 200));

                // 페이지 번호 추가
                ColumnText.showTextAligned(writer.getDirectContent(),
                        Element.ALIGN_CENTER, new Phrase("Page " + writer.getPageNumber(), font),
                        (document.left() + document.right()) / 2, document.bottom() - 20, 0);

                // 워터마크 추가
                ColumnText.showTextAligned(writer.getDirectContentUnder(),
                        Element.ALIGN_CENTER, new Phrase(watermarkText, watermarkFont),
                        297.5f, 421, 45); // 페이지 중앙 (A4 가로세로의 중앙에 회전된 상태로 위치)

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
