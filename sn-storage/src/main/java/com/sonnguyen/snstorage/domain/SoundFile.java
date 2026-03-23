package com.sonnguyen.snstorage.domain;

import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import com.sonnguyen.common.util.FileUtils;
import com.sonnguyen.snstorage.domain.cmd.FileVariantCmd;
import com.sonnguyen.snstorage.domain.cmd.ImageUploadCmd;
import com.sonnguyen.snstorage.infrastructure.support.constant.ImageColorMode;
import com.sonnguyen.snstorage.infrastructure.support.constant.ImageFormat;
import com.sonnguyen.snstorage.infrastructure.support.constant.VisualDefinition;
import com.sonnguyen.snstorage.infrastructure.support.enums.StorageProvider;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@SuperBuilder(toBuilder = true)
public class SoundFile extends File {

    public SoundFile() {
        super();
    }

    public SoundFile(MultipartFile fileData, ImageUploadCmd cmd, StorageProvider provider) {
        super(fileData, cmd, provider);
        List<ImageColorMode> colorModes = Objects.requireNonNullElse(cmd.getColorModes(), new ArrayList<>());
        List<ImageFormat> formats = Objects.requireNonNullElse(cmd.getFormats(), new ArrayList<>());
        List<VisualDefinition> resolutions = Objects.requireNonNullElse(cmd.getResolutions(), new ArrayList<>());

        String extension = FileUtils.getExtension(fileData.getOriginalFilename());
        Mimetype mimetype = FileUtils.getMimeType(extension);

        List<FileVariantCmd> fileVariantCmds = new ArrayList<>();

        FileVariantCmd defaultVariant = this.extractDefaultVariantFromFile(fileData);

        for (ImageColorMode imageColorMode : colorModes) {
            for (ImageFormat imageFormat : formats) {
                for (VisualDefinition visualDefinition : resolutions) {
                    ImageMetadata imageMetadata = ImageMetadata.builder()
                            .colorMode(imageColorMode)
                            .format(imageFormat)
                            .resolution(visualDefinition)
                            .build();
                    FileVariantCmd fileVariant = FileVariantCmd.builder()
                            .isOriginal(false)
                            .isThumbnail(false)
                            .accessCounter(0)
                            .sizeBytes(BigInteger.valueOf(fileData.getSize()))
                            .mimetype(mimetype)
                            .build();
                    fileVariantCmds.add(fileVariant);
                }
            }
        }
    }

    @Override
    public FileVariantCmd extractDefaultVariantFromFile(MultipartFile fileData) {
        return super.extractDefaultVariantFromFile(fileData);
    }
}
