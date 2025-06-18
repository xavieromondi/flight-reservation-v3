package com.okwatch.flightreservation.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {
    @GetMapping("/download/{reservationId}")
    public ResponseEntity<InputStreamResource> downloadTicket(@PathVariable Long reservationId) throws IOException, FileNotFoundException {
        String tempDir = System.getProperty("java.io.tmpdir");
        String filePath = tempDir + "/reservation_" + reservationId + ".pdf";

        File file = new File(filePath);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reservation_" + reservationId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

}
