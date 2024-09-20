package com.openpdf.controller;

import com.openpdf.service.PdfService_13;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController_13 {

    private final PdfService_13 pdfService;

    // PDF 최적화 요청
    @GetMapping("/optimize-pdf")
    public ResponseEntity<byte[]> optimizePdf() {
        // 기존 파일 크기
        long originalSize = pdfService.getOriginalFileSize();

        // PDF 파일 최적화
        byte[] optimizedPdf = pdfService.optimizePdf();

        // 최적화된 파일 크기
        long optimizedSize = optimizedPdf.length;

        // 로그 출력 (옵션)
        System.out.println("Original File Size: " + originalSize + " bytes");
        System.out.println("Optimized File Size: " + optimizedSize + " bytes");

        // PDF 응답 반환
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "optimized_sample.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(optimizedPdf);
    }
}
