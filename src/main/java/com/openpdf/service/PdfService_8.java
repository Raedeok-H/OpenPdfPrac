package com.openpdf.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService_8 {

    // 한글 폰트 경로 설정
    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";

    public byte[] createPdfWithHeaderFooter(String titleText, String footerText) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // PdfWriter와 Document 연결
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            // Header, Footer 이벤트 추가
            HeaderFooterEvent event = new HeaderFooterEvent(titleText, footerText);
            writer.setPageEvent(event);

            document.open(); // Document 열기

            // 본문 내용 추가
            BaseFont koreanBaseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font koreanFont = new Font(koreanBaseFont, 12);

            for (int i = 0; i < 3; i++) {
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

    private static class HeaderFooterEvent extends PdfPageEventHelper {
        private final String header;
        private final String footer;

        public HeaderFooterEvent(String header, String footer) {
            this.header = header;
            this.footer = footer;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                BaseFont baseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font font = new Font(baseFont, 12, Font.NORMAL);

                // Header
                ColumnText.showTextAligned(writer.getDirectContent(),
                        Element.ALIGN_CENTER, new Phrase(header, font),
                        (document.left() + document.right()) / 2, document.top() + 10, 0);

                // Footer
                ColumnText.showTextAligned(writer.getDirectContent(),
                        Element.ALIGN_CENTER, new Phrase(footer + " - " + writer.getPageNumber(), font),
                        (document.left() + document.right()) / 2, document.bottom() - 10, 0);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
