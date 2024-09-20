package com.openpdf.service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService_2 {

    // 한글 폰트 경로 설정
    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";

    public byte[] createStyledPdf(String textContent) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // PdfWriter와 Document 연결
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream); // PdfWriter를 통해 document와 연결

            document.open(); // Document 열기

            // 한글 폰트 설정
            BaseFont koreanBaseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font normalFont = new Font(koreanBaseFont, 12); // 기본 폰트
            Font boldFont = new Font(koreanBaseFont, 12, Font.BOLD); // 굵은 폰트
            Font italicFont = new Font(koreanBaseFont, 12, Font.ITALIC); // 기울임 폰트

            // 기본 텍스트 추가
            Paragraph paragraph1 = new Paragraph(textContent, normalFont);
            document.add(paragraph1);

            // 굵은 텍스트 추가
            Paragraph paragraph2 = new Paragraph("이것은 굵은 텍스트입니다.", boldFont);
            paragraph2.setAlignment(Paragraph.ALIGN_CENTER); // 중앙 정렬
            document.add(paragraph2);

            // 기울임 텍스트 추가
            Paragraph paragraph3 = new Paragraph("이것은 기울임 텍스트입니다.", italicFont);
            paragraph3.setAlignment(Paragraph.ALIGN_RIGHT); // 오른쪽 정렬
            document.add(paragraph3);

            document.close(); // 문서 닫기
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
