package com.openpdf.controller;

import com.openpdf.service.PdfService_8;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController_8 {

    private final PdfService_8 pdfService;

    @GetMapping("/create-custom-header-footer-pdf")
    public ResponseEntity<byte[]> createPdfWithHeaderFooter(@RequestParam String title, @RequestParam String footer) {
        byte[] pdfContent = pdfService.createPdfWithHeaderFooter(title, footer);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "header-footer-sample.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
