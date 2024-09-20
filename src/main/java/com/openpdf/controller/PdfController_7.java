package com.openpdf.controller;

import com.openpdf.service.PdfService_7;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController_7 {

    private final PdfService_7 pdfService;

    @GetMapping("/structured-pdf")
    public ResponseEntity<byte[]> createStructuredPdf() {
        byte[] pdfContent = pdfService.createStructuredPdf();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "structured_sample.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
