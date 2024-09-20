package com.openpdf.service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService_7 {

    // 한글 폰트 경로 설정
    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";

    public byte[] createStructuredPdf() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // PDF Document 및 Writer 생성
            Document document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50); // 페이지를 가로로 설정하고 여백 지정
            PdfWriter.getInstance(document, outputStream);

            // 문서 열기
            document.open();

            // 한글 폰트 설정
            BaseFont koreanBaseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font koreanFont = new Font(koreanBaseFont, 12);

            // 첫 번째 페이지 내용 추가
            document.add(new Paragraph("첫 번째 페이지입니다.", koreanFont));

            // 두 번째 페이지로 전환
            document.newPage();
            document.add(new Paragraph("두 번째 페이지입니다.", koreanFont));

            // 세 번째 페이지로 전환
            document.newPage();
            document.add(new Paragraph("세 번째 페이지입니다.", koreanFont));

            // 문서 닫기
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
