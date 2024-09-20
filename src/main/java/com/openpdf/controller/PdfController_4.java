package com.openpdf.controller;

import com.openpdf.service.PdfService_4;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController_4 {

    private final PdfService_4 pdfService;

    @GetMapping("/create-image-layout-pdf")
    public ResponseEntity<byte[]> createImagePdf(@RequestParam String text) {
        byte[] pdfContent = pdfService.createPositionedPdf(text);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "image_sample.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
