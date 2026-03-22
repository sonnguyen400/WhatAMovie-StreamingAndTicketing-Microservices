package com.sonnguyen.snstorage.domain;

import com.sonnguyen.common.util.FileUtils;
import com.sonnguyen.snstorage.domain.cmd.FileVariantCmd;
import com.sonnguyen.snstorage.domain.cmd.ImageUploadCmd;
import com.sonnguyen.snstorage.infrastructure.support.constant.ImageColorMode;
import com.sonnguyen.snstorage.infrastructure.support.constant.ImageFormat;
import com.sonnguyen.snstorage.infrastructure.support.constant.VisualDefinition;
import com.sonnguyen.snstorage.infrastructure.support.enums.StorageProvider;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import net.bramp.ffmpeg.FFprobe;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@SuperBuilder(toBuilder = true)
public class VideoFile extends File{

    public VideoFile() {
        super();
    }

    public VideoFile(MultipartFile fileData, ImageUploadCmd cmd, StorageProvider provider) {
        super(fileData, cmd, provider);
        List<FileVariantCmd> fileVariants = new ArrayList<>();

        List<ImageColorMode> colorModes = Objects.requireNonNullElse(cmd.getColorModes(), new ArrayList<>());
        List<ImageFormat> formats = Objects.requireNonNullElse(cmd.getFormats(), new ArrayList<>());
        List<VisualDefinition> resolutions = Objects.requireNonNullElse(cmd.getResolutions(), new ArrayList<>());

        FileVariantCmd defaultVariant = this.extractDefaultVariantFromFile(fileData);
        ImageMetadata defaultMetadata = (ImageMetadata) defaultVariant.getMetaData();
        fileVariants.add(defaultVariant);

        if(!formats.isEmpty() || !resolutions.isEmpty()){
            if(formats.isEmpty()) formats.add(defaultMetadata.getFormat());
            if(resolutions.isEmpty()) resolutions.add(defaultMetadata.getResolution());

            for(ImageFormat imageFormat: formats){
                for(VisualDefinition visualDefinition: resolutions){
                    ImageMetadata imageMetadata = ImageMetadata.builder()
                            .colorMode(defaultMetadata.getColorMode())
                            .format(imageFormat)
                            .resolution(visualDefinition)
                            .build();
                    FileVariantCmd fileVariant = FileVariantCmd.builder()
                            .isOriginal(false)
                            .isThumbnail(true)
                            .accessCounter(0)
                            .mimetype(defaultVariant.getMimetype())
                            .metaData(imageMetadata)
                            .build();
                    fileVariants.add(fileVariant);
                }
            }
        } else if (cmd.getAutoReduceDefinition() && Objects.nonNull(defaultMetadata.getResolution())){
            List<VisualDefinition> lowerResolutions = VisualDefinition.getLowerResolutions(defaultMetadata.getResolution());
            for(VisualDefinition resolution: lowerResolutions){
                ImageMetadata imageMetadata = ImageMetadata.builder()
                        .colorMode(defaultMetadata.getColorMode())
                        .format(defaultMetadata.getFormat())
                        .resolution(resolution)
                        .build();
                FileVariantCmd fileVariant = FileVariantCmd.builder()
                        .isOriginal(false)
                        .isThumbnail(true)
                        .accessCounter(0)
                        .mimetype(defaultVariant.getMimetype())
                        .metaData(imageMetadata)
                        .build();
                fileVariants.add(fileVariant);
            }
        }
        this.variants = fileVariants.stream()
                .map(it -> new FileVariant(this, it))
                .toList();
    }

    @Override
    public FileVariantCmd extractDefaultVariantFromFile(MultipartFile fileData) {
        FileVariantCmd cmd = super.extractDefaultVariantFromFile(fileData);
        ImageMetadata imageMetadata = new ImageMetadata();

        String extension = FileUtils.getExtension(fileData.getOriginalFilename());
        imageMetadata.setFormat(ImageFormat.fromExtension(extension));

        try(InputStream inputStream = fileData.getInputStream()) {
            FFprobe fFprobe = new FFprobe();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read image metadata", e);
        }

        cmd.setIsOriginal(true);
        cmd.setSizeBytes(BigInteger.valueOf(fileData.getSize()));
        cmd.setMimetype(FileUtils.getMimeType(extension));
        cmd.setAccessCounter(0);
        cmd.setMetaData(imageMetadata);
        return cmd;
    }
}
