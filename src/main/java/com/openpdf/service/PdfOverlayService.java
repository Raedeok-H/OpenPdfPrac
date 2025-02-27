package com.openpdf.service;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import dto.OverlayDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class PdfOverlayService {

    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothicBold.ttf";

    public byte[] createPdf(OverlayDTO overlayDTO, MultipartFile file) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, bos);
            document.open();

            PdfContentByte cb = putImageToBackground(file, writer);

            float pdfWidth = PageSize.A4.getWidth();
            float pdfHeight = PageSize.A4.getHeight();

            float x = (overlayDTO.getX() / 100) * pdfWidth;
            float y = (overlayDTO.getY() / 100) * pdfHeight;
            float boxWidth = (overlayDTO.getWidth() / 100) * pdfWidth;
            float boxHeight = (overlayDTO.getHeight() / 100) * pdfHeight;

            float horizontalMargin = 5f;
            float verticalMargin = 5f;

            float contentWidth = boxWidth - (horizontalMargin * 2);
            float contentHeight = boxHeight - (verticalMargin * 2);

            if (contentWidth <= 0 || contentHeight <= 0) {
                log.error("Invalid box dimensions.");
                return null;
            }

            BaseFont bfKorean = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            String text = overlayDTO.getText() != null ? overlayDTO.getText() : "No Text";
            float fontSize = getFontSize(bfKorean, text, contentWidth, contentHeight);

            float textWidth = bfKorean.getWidthPoint(text, fontSize);
            // 가운데 정렬일 경우 사용
            float centerX = x + (boxWidth - textWidth) / 2;
            float leftX = x + horizontalMargin;
            float centerY = y + (boxHeight / 2) - (fontSize / 3);

            cb.setFontAndSize(bfKorean, fontSize);
            cb.beginText();
            cb.setRGBColorFill(0, 0, 0); // 글자 색상을 검정으로 설정
            cb.setTextMatrix(leftX, centerY);
            cb.showText(text);
            cb.endText();

//            // 빨간색 박스 그리기
//            cb.setRGBColorStroke(255, 0, 0);
//            cb.setLineWidth(1f);
//            cb.rectangle(x, y, boxWidth, boxHeight);
//            cb.stroke();

            document.close();
            writer.close();

            return bos.toByteArray();

        } catch (Exception e) {
            log.error("PDF 생성 오류", e);
            return null;
        }
    }

    private PdfContentByte putImageToBackground(MultipartFile file, PdfWriter writer) throws IOException {
        byte[] imageBytes = file.getBytes();
        Image bgImage = Image.getInstance(imageBytes);

        float docWidth = PageSize.A4.getWidth();
        float docHeight = PageSize.A4.getHeight();
        bgImage.scaleToFit(docWidth, docHeight);

        float scaledW = bgImage.getScaledWidth();
        float scaledH = bgImage.getScaledHeight();
        float posX = (docWidth - scaledW) / 2;
        float posY = (docHeight - scaledH) / 2;

        PdfContentByte cbUnder = writer.getDirectContentUnder();
        bgImage.setAbsolutePosition(posX, posY);
        cbUnder.addImage(bgImage);

        return writer.getDirectContent();
    }

    private float getFontSize(BaseFont bfKorean, String text, float boxWidth, float boxHeight) {
        float fontSize = 30f;
        float lineSpacingFactor = 1.2f;

        float textWidth = bfKorean.getWidthPoint(text, fontSize);
        float lineHeight = fontSize * lineSpacingFactor;

        while ((textWidth > boxWidth || lineHeight > boxHeight) && fontSize > 1f) {
            fontSize -= 0.5f;
            textWidth = bfKorean.getWidthPoint(text, fontSize);
            lineHeight = fontSize * lineSpacingFactor;
        }
        log.info("Calculated font size: {}", fontSize);
        return fontSize;
    }
}