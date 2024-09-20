package com.openpdf.controller;

import com.openpdf.service.PdfService_10;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfController_10 {

    private final PdfService_10 pdfService;

    @GetMapping("/create-dynamic-table-pdf")
    public ResponseEntity<byte[]> createTablePdf(@RequestParam int rows, @RequestParam int cols) {
        byte[] pdfContent = pdfService.createDynamicTablePdf(rows, cols);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "dynamic-table-sample.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
