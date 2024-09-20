package com.openpdf.controller;

import com.openpdf.service.PdfService_9;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController_9 {

    private final PdfService_9 pdfService;

    @GetMapping("/create-pdf-with-watermark")
    public ResponseEntity<byte[]> createPdfWithWatermark(@RequestParam String watermarkText) {
        byte[] pdfContent = pdfService.createPdfWithWatermark(watermarkText);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "watermark-sample.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
