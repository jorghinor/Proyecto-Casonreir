package com.casonreir.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Obtenemos la ruta absoluta de la carpeta 'uploads' de forma robusta
        String uploadPath = new File("uploads").getAbsolutePath();
        
        // Mapeamos la URL /uploads/** a la carpeta física con el prefijo file:
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadPath + "/");
                
        // También habilitamos recursos estáticos estándar por si acaso
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
