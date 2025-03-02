package com.acme.springbootapirest.soap;

import com.acme.springbootapirest.model.OrderRequestXML;
import com.acme.springbootapirest.model.OrderResponseAcme;
import jakarta.xml.bind.*;
import jakarta.xml.soap.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.w3c.dom.Node;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

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
        String xmlRequest = convertToXML(orderRequest);
        logger.info("Sending Order: {}", xmlRequest);

        // SOAP Consumption ignored since there isn't a functional endpoint.
        // String xmlResponse = (String) webServiceTemplate.marshalSendAndReceive(xmlRequest);

        // Simulation instead.
        String xmlResponse = simulateSoapResponse(orderRequest.getOrders().getFirst().getNumPedido());
        logger.info("SOAP Response Received: {}", xmlResponse);

        return convertToObject(xmlResponse);
    }

    private String convertToXML(OrderRequestXML orderRequest) throws JAXBException {
        Marshaller marshaller = REQUEST_CONTEXT.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter writer = new StringWriter();
        marshaller.marshal(orderRequest, writer);
        return writer.toString();
    }

    private OrderResponseAcme convertToObject(String xml) throws Exception {
        SOAPMessage soapMessage = parseSoapMessage(xml);
        SOAPBody soapBody = soapMessage.getSOAPBody();
        Node responseNode = soapBody.getFirstChild();

        return unmarshalResponse(responseNode);
    }

    private SOAPMessage parseSoapMessage(String xml) throws Exception {
        MessageFactory factory = MessageFactory.newInstance();
        return factory.createMessage(null, new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
    }

    private OrderResponseAcme unmarshalResponse(Node responseNode) throws JAXBException {
        Unmarshaller unmarshaller = RESPONSE_CONTEXT.createUnmarshaller();
        JAXBElement<OrderResponseAcme> jaxbElement = unmarshaller.unmarshal(responseNode, OrderResponseAcme.class);
        return jaxbElement.getValue();
    }

    private String simulateSoapResponse(String orderId) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:env=\"http://WSDLs/EnvioPedidos/EnvioPedidosAcme\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<env:EnvioPedidoAcmeResponse>" +
                "<EnvioPedidoResponse>" +
                "<Codigo>" + orderId + "</Codigo>" +
                "<Mensaje>Entregado exitosamente al cliente</Mensaje>" +
                "</EnvioPedidoResponse>" +
                "</env:EnvioPedidoAcmeResponse>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";
    }
}
