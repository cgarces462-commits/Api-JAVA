package com.ficha.demo.config;

import com.ficha.demo.model.Medicamento;
import com.ficha.demo.repository.MedicamentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CONFIGURACIÓN INICIAL DE DATOS:
 * Inserta datos de prueba automáticamente al iniciar la aplicación para que
 * puedas probar de inmediato los endpoints sin tener la base de datos vacía.
 */
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(MedicamentoRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Medicamento("Paracetamol 500mg", "Analgésico y antipirético para alivio del dolor y la fiebre", 5.50, 100, "Genfar"));
                repository.save(new Medicamento("Ibuprofeno 400mg", "Antiinflamatorio no esteroideo (AINE)", 8.20, 75, "Bayer"));
                repository.save(new Medicamento("Amoxicilina 500mg", "Antibiótico de amplio espectro", 14.00, 50, "MK"));
                repository.save(new Medicamento("Loratadina 10mg", "Antihistamínico para alergias y rinitis", 6.80, 120, "Lafrancol"));
                System.out.println(">>> [DATOS DE PRUEBA CARGADOS]: 4 medicamentos insertados en la base de datos H2.");
            }
        };
    }
}


/**
 * Terminal 1: Inicia la aplicación Spring
 *     cd "/home/cesar/Trimestre-4/JAVA Local/Clase1(mia)/demo"
 *     ./gradlew bootRun
 *
 *  Terminal 2: En otra pestaña de terminal
 *     cd "/home/cesar/Trimestre-4/JAVA Local/Clase1(mia)/demo"
 *     ./gradlew h2Shell -q --console=plain
 *
 *
 */