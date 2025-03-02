package com.acme.springbootapirest.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderRequestDto {
    @NotNull
    private ShipOrderDto enviarPedido;

    public ShipOrderDto getEnviarPedido() {
        return enviarPedido;
    }

    public void setEnviarPedido(ShipOrderDto enviarPedido) {
        this.enviarPedido = enviarPedido;
    }

    @Override
    public String toString() {
        return "OrderRequestDto{" +
                "enviarPedido=" + (enviarPedido != null ? enviarPedido.toString() : "null") +
                '}';
    }

}