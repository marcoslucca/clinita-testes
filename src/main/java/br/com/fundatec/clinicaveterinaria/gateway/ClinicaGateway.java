package br.com.fundatec.clinicaveterinaria.gateway;

import br.com.fundatec.clinicaveterinaria.gateway.resources.CollectionProjection;
import br.com.fundatec.clinicaveterinaria.gateway.resources.Consulta;
import br.com.fundatec.clinicaveterinaria.gateway.resources.ConsultaRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ClinicaGateway {

    @Value("${gateway.clinica.url}")
    private String url;

    public Consulta createConsulta(ConsultaRequest consulta) {
        WebClient webClient = WebClient.create(url);

        return webClient.post()
                .uri("/clinica/consultas")
                .body(Mono.just(consulta), ConsultaRequest.class)
                .retrieve()
                .bodyToMono(Consulta.class)
                .block();
    }

    public CollectionProjection getConsultas() {
        WebClient webClient = WebClient.create(url);

        return webClient.get()
                .uri("/clinica/consultas")
                .retrieve()
                .bodyToMono(CollectionProjection.class)
                .block();
    }
}
