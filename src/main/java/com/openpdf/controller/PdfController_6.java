package com.openpdf.controller;

import com.openpdf.service.PdfService_6;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController_6 {

    private final PdfService_6 pdfService;

    @GetMapping("/create-image-table-pdf")
    public ResponseEntity<byte[]> createImagePdf() {
        byte[] pdfContent = pdfService.createPdfWithImageAndTable();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "image_sample.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
