package com.openpdf.controller;

import com.openpdf.service.PdfService_2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController_2 {

    private final PdfService_2 pdfService;

    @GetMapping("/create-styled-pdf")
    public ResponseEntity<byte[]> createStyledPdf(@RequestParam String text) {
        byte[] pdfContent = pdfService.createStyledPdf(text);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "styled_sample.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
