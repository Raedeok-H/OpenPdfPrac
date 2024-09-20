package com.openpdf.controller;

import com.openpdf.service.PdfService_5;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController_5 {

    private final PdfService_5 pdfService;

    @GetMapping("/create-table-pdf")
    public ResponseEntity<byte[]> createImagePdf() {
        byte[] pdfContent = pdfService.createPdfWithTable();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "image_sample.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
