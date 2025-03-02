package com.acme.springbootapirest.soap;

import com.acme.springbootapirest.model.OrderRequestXML;
import com.acme.springbootapirest.model.OrderResponseAcme;
import jakarta.xml.bind.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.client.core.WebServiceTemplate;


@Service
public class SoapClient {

    private static final Logger logger = LoggerFactory.getLogger(SoapClient.class);
    private static final JAXBContext REQUEST_CONTEXT;
    private static final JAXBContext RESPONSE_CONTEXT;

    private final WebServiceTemplate webServiceTemplate;

    @Autowired
    public SoapClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    static {
        try {
            REQUEST_CONTEXT = JAXBContext.newInstance(OrderRequestXML.class);
            RESPONSE_CONTEXT = JAXBContext.newInstance(OrderResponseAcme.class);
        } catch (JAXBException e) {
            throw new RuntimeException("Error initializing JAXB Contexts", e);
        }
    }

    public OrderResponseAcme sendOrder(OrderRequestXML orderRequest) throws Exception {
        try {
            logger.info("Sending Order: {}", orderRequest.toString());
            OrderResponseAcme xmlResponse = (OrderResponseAcme) webServiceTemplate.marshalSendAndReceive(orderRequest);

            if (xmlResponse == null) {
                logger.warn("Received null response from SOAP service.");
                return new OrderResponseAcme();
            }

            logger.info("SOAP Response Received: {}", xmlResponse.toString());
            return  xmlResponse;
        } catch (WebServiceIOException e) {
            logger.error("SOAP request failed due to connection issues", e);
        } catch (Exception e) {
            logger.error("Unexpected error during SOAP request", e);
        }

        return null;
    }

}
