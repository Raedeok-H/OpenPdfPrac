package com.openpdf.controller;

import com.openpdf.service.PdfOverlayService;
import dto.OverlayDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pdf/overlay")
public class PdfIOverlayTextController {
    private final PdfOverlayService pdfInvoiceService;

    // 사진, 인보이스 좌표, 텍스트를 받아서 pdf 생성
    @PostMapping(
            value = "/create",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<byte[]> createPdf(@RequestPart OverlayDTO overlayDTO,
                                            @RequestPart MultipartFile file) {
        byte[] pdfBytes = pdfInvoiceService.createPdf(overlayDTO, file);

        // 브라우저 다운로드 vs inline 표시를 위해 Content-Disposition 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set("Content-Disposition", "inline; filename=\"invoice.pdf\"");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
