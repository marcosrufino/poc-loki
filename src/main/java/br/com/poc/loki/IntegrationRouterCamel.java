package br.com.poc.loki;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IntegrationRouterCamel extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:foo?period=1m")
                .routeId("timer-init-router")
                .log("Log do Apache Camel")
                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .setHeader(Exchange.CONTENT_TYPE, simple("application/json"))
                .toD("https://dummyjson.com/products")
                .convertBodyTo(String.class)
                .unmarshal().json(JsonLibrary.Jackson, Map.class)
                .split().jsonpath("$.products")
                .parallelProcessing()
                .log("Processando item: ${body}")
                .to("direct:processItem")
        ;

        from("direct:processItem")
                .routeId("direct:processItem")
                .log("Iniciando o processamento do item.")
                .process(exchange -> {
                    double randomValue = Math.random(); // Gera um número aleatório entre 0.0 e 1.0
                    exchange.setProperty("randomValue", randomValue); // Armazena o valor aleatório nas propriedades do Exchange
                })
                .log("Valor aleatório gerado: ${exchangeProperty.randomValue}")
                .choice()
                .when(exchangeProperty("randomValue").isLessThan(0.3))
                .log("Valor aleatório está abaixo de 0.3. Lançando exceção...")
                .throwException(new RuntimeException("Exceção aleatória disparada!"))
                .otherwise()
                .log("Valor aleatório está acima de 0.3. Nenhuma exceção será lançada.")
                .end()
                .log("Processamento do item continuando normalmente.")
                .setBody(constant("{ \"title\": \"Produto Exemplo\" }"))
                .log("Corpo do item definido: ${body}")
                .setHeader(Exchange.HTTP_METHOD, constant("PUT"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .log("Método HTTP e Content-Type definidos.")
                .toD("https://dummyjson.com/products/1")
                .log("Requisição enviada. Resposta: ${body}");
    }
}
