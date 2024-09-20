package com.openpdf.service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService_12 {

    // 한글 폰트 경로 설정
    private static final String FONT_PATH = "src/main/resources/fonts/NanumGothic.ttf";

    public byte[] createSecurePdf(String title, String author, String subject,
                                  String keywords, String creator, String userPassword, String ownerPassword) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // PDFWriter와 Document 연결
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            //TODO: 더 상세한 기능 학습 필요(일단 넘어감)
            // 보안 설정 (사용자 암호, 소유자 암호, 권한 설정)
            writer.setEncryption(
                    userPassword.getBytes(), // 사용자 암호
                    ownerPassword.getBytes(), // 소유자 암호
                    PdfWriter.ALLOW_PRINTING, // 인쇄만 허용하고 나머지 권한 제한
                    PdfWriter.ENCRYPTION_AES_128 | PdfWriter.DO_NOT_ENCRYPT_METADATA // AES 128비트 암호화 + 메타데이터 암호화 제외
            );

            // 문서 열기
            document.open();

            // PDF 메타데이터 설정
            document.addTitle(title); // 제목
            document.addAuthor(author); // 작성자
            document.addSubject(subject); // 주제
            document.addKeywords(keywords); // 키워드
            document.addCreator(creator); // 생성자

            // 한글 폰트 설정
            BaseFont koreanBaseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font koreanFont = new Font(koreanBaseFont, 12);

            // PDF 내용 추가 (한글 지원)
            Paragraph content = new Paragraph("이 문서는 보안이 설정된 PDF입니다.", koreanFont);
            document.add(content);

            // 문서 닫기
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
