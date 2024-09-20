package com.openpdf.controller;

import com.openpdf.service.PdfService_12;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController_12 {

    private final PdfService_12 pdfService;

    // PDF 생성 및 보안 설정 테스트
    @GetMapping("/create-pdf-secure")
    public ResponseEntity<byte[]> createSecurePdf() {
        // 필수 파라미터들을 하드코딩으로 설정
        String title = "PDF 제목 예시";
        String author = "PDF 작성자 예시";
        String subject = "PDF 주제 예시";
        String keywords = "키워드1, 키워드2, 키워드3";
        String creator = "PDF 생성자 예시";
        String userPassword = "user1234"; // 사용자 암호
        String ownerPassword = "owner1234"; // 소유자 암호

        // PDF 생성 서비스 호출
        byte[] pdfContent = pdfService.createSecurePdf(
                title, author, subject, keywords, creator, userPassword, ownerPassword);

        // PDF 파일로 응답 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "secure-sample.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
