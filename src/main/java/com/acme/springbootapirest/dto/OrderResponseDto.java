package com.acme.springbootapirest.dto;

import lombok.Data;

@Data
public class OrderResponseDto {
    private EnviarPedidoRespuesta enviarPedidoRespuesta;

    public EnviarPedidoRespuesta getEnviarPedidoRespuesta() {
        return enviarPedidoRespuesta;
    }

    public void setEnviarPedidoRespuesta(EnviarPedidoRespuesta enviarPedidoRespuesta) {
        this.enviarPedidoRespuesta = enviarPedidoRespuesta;
    }

    @Data
    public static class EnviarPedidoRespuesta {
        private String codigoEnvio;
        private String estado;

        public String getCodigoEnvio() {
            return codigoEnvio;
        }

        public void setCodigoEnvio(String codigoEnvio) {
            this.codigoEnvio = codigoEnvio;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }
}
