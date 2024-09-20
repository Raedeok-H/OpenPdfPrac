package com.openpdf.service;

import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class PdfService_13 {

    // 테스트할 PDF 파일 경로 (하드코딩)
    private static final String INPUT_PDF_PATH = "src/main/resources/pdfs/sample_pdf.pdf";

    // PDF 최적화 및 압축 서비스 메서드
    public byte[] optimizePdf() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // 기존 PDF 파일 읽기
            PdfReader pdfReader = new PdfReader(new FileInputStream(INPUT_PDF_PATH));

            // PdfStamper를 사용하여 최적화 및 압축 수행
            PdfStamper stamper = new PdfStamper(pdfReader, outputStream, PdfWriter.VERSION_1_5);

            // 압축 설정
            stamper.setFullCompression();

            // 각 페이지에 대해 압축 레벨 설정
            int totalPages = pdfReader.getNumberOfPages();
            for (int i = 1; i <= totalPages; i++) {
                // 페이지 내 모든 스트림 요소를 탐색하여 압축 설정
                PdfStream content = pdfReader.getPageN(i).getAsStream(PdfName.CONTENTS);
                if (content != null) {
                    content.flateCompress(PdfStream.BEST_SPEED); // 최적의 압축 설정
                }
            }

            // 최적화된 PDF 파일 닫기
            stamper.close();
            pdfReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    // 기존 PDF 파일 크기 가져오기
    public long getOriginalFileSize() {
        try {
            return Files.size(Paths.get(INPUT_PDF_PATH));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
