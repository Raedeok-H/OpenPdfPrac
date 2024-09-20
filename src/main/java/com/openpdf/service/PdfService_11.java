package com.openpdf.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService_11 {

    // 한글 폰트 경로 설정
    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";

    // 하드코딩된 이미지 경로 설정
    private static final String IMAGE_PATH = "src/main/resources/images/image.jpg";

    public byte[] createCustomPdf(String textContent, String templateType) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // Document 객체 생성
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // 한글 폰트 설정
            BaseFont koreanBaseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font koreanFont = new Font(koreanBaseFont, 12);

            // 템플릿 유형에 따라 다른 레이아웃 적용
            if ("template1".equals(templateType)) {
                // 템플릿 1: 텍스트 상단, 이미지 하단 배치
                document.add(new Paragraph(textContent, koreanFont));
                Image image = Image.getInstance(IMAGE_PATH);
                image.scaleToFit(400, 300);
                image.setAlignment(Image.ALIGN_CENTER);
                document.add(image);
            } else if ("template2".equals(templateType)) {
                // 템플릿 2: 이미지 상단, 텍스트 하단 배치
                Image image = Image.getInstance(IMAGE_PATH);
                image.scaleToFit(400, 300);
                image.setAlignment(Image.ALIGN_CENTER);
                document.add(image);
                document.add(new Paragraph(textContent, koreanFont));
            } else {
                // 기본 레이아웃
                document.add(new Paragraph("기본 템플릿", koreanFont));
                document.add(new Paragraph(textContent, koreanFont));
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
