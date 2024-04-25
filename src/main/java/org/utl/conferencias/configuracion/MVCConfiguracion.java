package org.utl.conferencias.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MVCConfiguracion implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String rutaFoto = Paths.get("fotos").toFile().getAbsolutePath();
        registry.addResourceHandler("/fotos/**").addResourceLocations("file:/" + rutaFoto + "/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
