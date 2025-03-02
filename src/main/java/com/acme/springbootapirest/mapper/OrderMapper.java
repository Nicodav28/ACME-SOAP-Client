package com.acme.springbootapirest.mapper;

import com.acme.springbootapirest.dto.OrderRequestDto;
import com.acme.springbootapirest.dto.ShipOrderDto;
import com.acme.springbootapirest.model.OrderRequestXML;
import com.acme.springbootapirest.model.OrdersXML;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrdersXML dtoToXml(ShipOrderDto dto);
}