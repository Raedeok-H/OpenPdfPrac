package com.openpdf.service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService_3 {

    // 한글 폰트 경로 설정
    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";

    // 이미지 경로
    private static final String IMAGE_PATH = "src/main/resources/images/image.jpg";

    public byte[] createImagePdf(String textContent) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // PdfWriter와 Document 연결
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream); // PdfWriter를 통해 document와 연결

            document.open(); // Document 열기

            // 한글 폰트 설정
            BaseFont koreanBaseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font koreanFont = new Font(koreanBaseFont, 12);

            // 이미지 추가
            Image image = Image.getInstance(IMAGE_PATH);
            image.scaleToFit(500, 500); // 이미지 크기 조정
            image.setAlignment(Image.ALIGN_CENTER); // 중앙 정렬
            document.add(image);

            // 이미지 아래에 텍스트 추가
            Paragraph paragraph = new Paragraph(textContent, koreanFont);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER); // 중앙 정렬
            document.add(paragraph);

            // PdfContentByte를 사용하여 도형 그리기
            PdfContentByte canvas = writer.getDirectContent();
            canvas.setLineWidth(2f); // 선 굵기 설정
            canvas.rectangle(50, 500, 500, 100); // 사각형 그리기
            canvas.stroke(); // 그리기 실행

            // 문서 닫기
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
