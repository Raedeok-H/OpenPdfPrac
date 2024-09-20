package com.openpdf.controller;

import com.openpdf.service.PdfService_11;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController_11 {

    private final PdfService_11 pdfService;

    @GetMapping("/create-custom-pdf")
    public ResponseEntity<byte[]> createPdf(
            @RequestParam String text,
            @RequestParam String templateType) {

        byte[] pdfContent = pdfService.createCustomPdf(text, templateType);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "custom.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
