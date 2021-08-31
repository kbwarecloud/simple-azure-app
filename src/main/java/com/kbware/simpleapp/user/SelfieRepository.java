package com.kbware.simpleapp.user;

import com.azure.storage.blob.BlobServiceClientBuilder;
import com.kbware.simpleapp.user.model.Selfie;
import com.kbware.simpleapp.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SelfieRepository {

    private static final String SELFIE_STORAGE = "selfie-storage";

    private final BlobServiceClientBuilder blobServiceClientBuilder;

    public Selfie store(MultipartFile file) {

        try {
            var client = blobServiceClientBuilder.buildClient()
                    .getBlobContainerClient(SELFIE_STORAGE);

            var name = String.format("SELFIE_%s.jpg", UUID.randomUUID());

            client.getBlobClient(name).upload(file.getInputStream(), file.getSize());

            return new Selfie(client.getBlobContainerUrl() + "/" + name, false);
        } catch (IOException e) {
            log.error("Error storing file to blob storage", e);
        }

        return null;
    }
}
