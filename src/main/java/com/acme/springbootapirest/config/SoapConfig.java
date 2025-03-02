package com.acme.springbootapirest.config;

import com.acme.springbootapirest.model.OrderRequestXML;
import com.acme.springbootapirest.model.OrderResponseAcme;
import com.acme.springbootapirest.model.OrderResponseXML;
import com.acme.springbootapirest.model.OrdersXML;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SoapConfig {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(
                OrderRequestXML.class,
                OrderResponseAcme.class,
                OrderResponseXML.class,
                OrdersXML.class
        );
        return marshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);
        webServiceTemplate.setDefaultUri("https://run.mocky.io/v3/ae7d2dc6-6924-4b1f-a485-bdec2081740b");
        return webServiceTemplate;
    }
}