package com.acme.springbootapirest.service;

import com.acme.springbootapirest.dto.OrderRequestDto;
import com.acme.springbootapirest.dto.OrderResponseDto;
import com.acme.springbootapirest.dto.ShipOrderDto;
import com.acme.springbootapirest.mapper.OrderMapper;
import com.acme.springbootapirest.model.OrderRequestXML;
import com.acme.springbootapirest.model.OrderResponseAcme;
import com.acme.springbootapirest.model.OrdersXML;
import com.acme.springbootapirest.soap.SoapClient;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final SoapClient soapClient;
    private final OrderMapper orderMapper;
    private String shippingStatus;
    private String shippingId;

    @Autowired
    public OrderService(SoapClient soapClient) {
        this.soapClient = soapClient;
        this.orderMapper = OrderMapper.INSTANCE;
    }

    public OrderResponseDto sendOrder(OrderRequestDto orderDto) {
        try {
            this.defineDefaultResponseValues(orderDto);

            OrdersXML orderXml = orderMapper.dtoToXml(orderDto.getEnviarPedido());

            OrderResponseAcme responseAcme = this.executeSoapCall(orderXml);

            if (responseAcme != null) {
                Optional.ofNullable(responseAcme.getShipmentResponse()).ifPresent(shipment -> {
                    this.shippingStatus = shipment.getShippingStatus();
                    this.shippingId = shipment.getShippingId();
                });
            }

            return this.buildServiceResponse();
        } catch (Exception e) {
            logger.error("Error sending the order: ", e);
            return new OrderResponseDto();
        }
    }

    private void defineDefaultResponseValues(OrderRequestDto orderDto) {
        this.shippingStatus = "El estado del envío no pudo ser obtenido, por favor, inténtalo más tarde.";
        this.shippingId = Optional.ofNullable(orderDto.getEnviarPedido())
                .map(ShipOrderDto::getNumPedido)
                .orElse("N/A");
    }

    private OrderResponseAcme executeSoapCall(OrdersXML orderXml) {
        try {
            OrderRequestXML fullOrderRequest = new OrderRequestXML();
            fullOrderRequest.addOrder(orderXml);
            return soapClient.sendOrder(fullOrderRequest);
        } catch (Exception e) {
            logger.error("Error while calling the SOAP service: ", e);
            return null;
        }
    }

    private OrderResponseDto buildServiceResponse() {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setEnviarPedidoRespuesta(new OrderResponseDto.EnviarPedidoRespuesta());
        responseDto.getEnviarPedidoRespuesta().setCodigoEnvio(this.shippingId);
        responseDto.getEnviarPedidoRespuesta().setEstado(this.shippingStatus);
        return responseDto;
    }
}
