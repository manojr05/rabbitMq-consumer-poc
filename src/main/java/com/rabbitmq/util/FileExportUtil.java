package com.rabbitmq.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;

@Service
@Slf4j
public class FileExportUtil {

    public void exportFile(byte[] fileContent){
        try {
            String fileNameWithExtension = "export.pdf";
            String fileName = Paths.get(fileNameWithExtension).getFileName().toString();

            String exportDirectoryPath = "src/main/resources/export/";

            File exportDirectory = new File(exportDirectoryPath);
            if (!exportDirectory.exists()) {
                exportDirectory.mkdirs(); // Creates the directory including any necessary but nonexistent parent directories
            }

            FileOutputStream outputStream = new FileOutputStream(exportDirectoryPath + fileName);
            outputStream.write(fileContent);
            outputStream.close();

            log.info("File '" + fileNameWithExtension + "' decrypted and exported successfully.");
        } catch (IOException e) {
            log.error("Error exporting file: " + e.getMessage());
        }
    }
}
