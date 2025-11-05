package com.sonnguyen.snstorage.infrastructure.adaper.impl;

import com.sonnguyen.common.model.infrastructure.constant.Mimetype;
import com.sonnguyen.snstorage.infrastructure.configuration.MinIOConfiguration;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MinIOAdapter {
    MinioClient minioClient;

    public void uploadFile(String bucketName, String objectName, byte[] data, Mimetype mimetype) {
        try (InputStream inputStream = new ByteArrayInputStream(data)) {
            boolean found = this.minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                this.minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            ObjectWriteResponse objectWriteResponse = this.minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                                    inputStream, inputStream.available(), -1)
                            .contentType(mimetype.getValue())
                            .build());

        } catch (Exception e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
    }

    public byte[] getFile(String bucketName, String objectName){
        try{
            GetObjectResponse response = this.minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName).build());
            return response.readAllBytes();
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException e) {
            throw new RuntimeException(e);
        } catch (XmlParserException e) {
            throw new RuntimeException(e);
        } catch (InternalException e) {
            throw new RuntimeException(e);
        }
    }
}
