package com.acme.springbootapirest.service;

import com.acme.springbootapirest.dto.OrderRequestDto;
import com.acme.springbootapirest.dto.OrderResponseDto;
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

@Service
@RequiredArgsConstructor
@Validated
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final SoapClient soapClient;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(SoapClient soapClient) {
        this.soapClient = soapClient;
        this.orderMapper = OrderMapper.INSTANCE;
    }

    public OrderResponseDto sendOrder(OrderRequestDto orderDto) throws Exception {
        OrdersXML orderXml = orderMapper.dtoToXml(orderDto.getEnviarPedido());

        OrderRequestXML fullOrderRequest = new OrderRequestXML();
        fullOrderRequest.addOrder(orderXml);

        OrderResponseAcme responseAcme = soapClient.sendOrder(fullOrderRequest);

        OrderResponseDto responseDto = new OrderResponseDto();

        responseDto.setEnviarPedidoRespuesta(new OrderResponseDto.EnviarPedidoRespuesta());
        responseDto.getEnviarPedidoRespuesta().setCodigoEnvio(responseAcme.getShipmentResponse().getShippingId());
        responseDto.getEnviarPedidoRespuesta().setEstado(responseAcme.getShipmentResponse().getShippingStatus());

        return responseDto;
    }
}