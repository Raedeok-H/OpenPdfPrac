package com.openpdf.service;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService_4 {

    // 한글 폰트 경로 설정
    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";
    private static final String IMAGE_PATH = "src/main/resources/images/image.jpg";

    public byte[] createPositionedPdf(String textContent) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // PdfWriter와 Document 연결
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);

            document.open(); // Document 열기

            // 한글 폰트 설정
            BaseFont koreanBaseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font koreanFont = new Font(koreanBaseFont, 12);

            // 상수에 f를 붙이는 이유는 파라미터가 float 이기 때문에 컴파일러를 한 번 더 거치지 않게 함과
            // 동시에 float 타입임을 명시적으로 표현하기 위함
            // 특정 좌표에 텍스트 추가
            Paragraph paragraph = new Paragraph(textContent, koreanFont);
            paragraph.setAlignment(Element.ALIGN_LEFT); // 텍스트 정렬 설정
            paragraph.setLeading(15f); // 줄 간격 설정
            paragraph.setIndentationLeft(200f); // 왼쪽 여백 설정
            document.add(paragraph);

            // 특정 좌표에 이미지 추가
            Image img = Image.getInstance(IMAGE_PATH);
            img.setAbsolutePosition(100f, 400f); // 이미지 좌표 설정 (x, y)
            img.scaleToFit(200, 200); // 이미지 크기 조정
            document.add(img);

            document.close(); // 문서 닫기
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
