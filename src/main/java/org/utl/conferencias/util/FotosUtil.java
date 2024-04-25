package org.utl.conferencias.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FotosUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FotosUtil.class);

    public static void guardar(String directorio, String foto, MultipartFile multipartFile) throws IOException {
        Path ruta = Paths.get(directorio);
        if (!Files.exists(ruta)) {
            Files.createDirectories(ruta);
        }
        try (InputStream inputStream = multipartFile.getInputStream();) {
            Path rutaFoto = ruta.resolve(foto);
            Files.copy(inputStream, rutaFoto, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            throw new IOException("No se puede guardar. " + foto, exception);
        }
    }

    public static void limpiar(String directorio) {
        Path rutaFoto = Paths.get(directorio);
        try {
            Files.list(rutaFoto).forEach(ruta -> {
                if (!Files.isDirectory(ruta)) {
                    try {
                        Files.delete(ruta);
                    } catch (IOException exception) {
                        LOGGER.error("No se puede borrar la foto " + ruta);
                    }
                }
            });
        } catch (IOException exception) {
            LOGGER.error("No se puede acceder al directorio " + rutaFoto);
        }
    }
}
