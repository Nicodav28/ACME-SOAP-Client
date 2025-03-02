

# **ACME SOAP Client - Prueba Técnica** 🚀

Este proyecto es un cliente SOAP en **Spring Boot**, que recibe solicitudes en **JSON**, las transforma a **XML** y las envía a un servicio **SOAP** de la compañía ACME. Luego, recibe la respuesta en **XML**, la transforma de vuelta a **JSON** y la devuelve al cliente.

## **📌 Características**
✅ Implementación de **Spring Boot** para el servicio REST.  
✅ Uso de **JAXB** para la conversión entre **JSON ↔ XML**.  
✅ Simulación de respuestas SOAP en ausencia de un endpoint real.  
✅ Contenerización con **Docker**.

---

## **📂 Estructura del Proyecto**
```
acme-soap-client/
│── src/
│   ├── main/
│   │   ├── java/com/acme/springbootapirest/
│   │   │   ├── dto/                # Data Transfer Objects
│   │   │   ├── mapper/             # Mapeador de objetos
│   │   │   ├── model/              # Modelos JAXB para XML
│   │   │   ├── soap/               # Cliente SOAP
│   │   │   ├── controller/         # Controladores REST
│   │   │   ├── service/            # Logica de negocio
│   │   │   ├── config/             # Configuración general
│   │   │   ├── SpringBootApiRestApplication    # Clase principal
│   ├── resources/
│   │   ├── application.yml         # Configuración de Spring Boot
│── Dockerfile                      # Definición de la imagen Docker
│── pom.xml                          # Dependencias y configuración de Maven
│── README.md                        # Documentación del proyecto
```

---

## **🚀 Requisitos**
Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- **JDK 23**
- **Maven** (`mvn -v` para verificar)
- **Docker**

---

## **⚙️ Configuración y Ejecución Local**
### **1️⃣ Clonar el Repositorio**
```sh
git clone https://github.com/Nicodav28/ACME-SOAP-Client.git
cd acme-soap-client
```

### **2️⃣ Construir el Proyecto**
Ejecuta el siguiente comando en la raíz del proyecto para compilarlo:

```sh
mvn clean package
```

Esto generará un archivo **JAR** en `target/`.

### **3️⃣ Ejecutar la Aplicación**
Ejecuta el siguiente comando para iniciar el servicio:

```sh
java -jar target/acme-soap-client.jar
```

El servicio estará disponible en:  
📌 **http://localhost:8080**

---

## **🐳 Ejecución con Docker**
Si prefieres usar **Docker**, sigue estos pasos:

### **1️⃣ Construir la Imagen Docker**
```sh
docker build -t acme-soap-client .
```

### **2️⃣ Ejecutar el Contenedor**
```sh
docker run -p 8080:8080 acme-soap-client
```

### **3️⃣ Verificar el Contenedor**
Para confirmar que está corriendo, usa:

```sh
docker ps
```

---

## **🛠️ Endpoints**
### **1️⃣ Enviar una Orden (POST)**
📌 **URL:** `http://localhost:8080/api/orders`  
📄 **Ejemplo de Request (JSON):**
```json
{
  "enviarPedido": {
    "numPedido": "75630275",
    "cantidadPedido": "1",
    "codigoEAN": "00110000765191002104587",
    "nombreProducto": "Armario INVAL",
    "numDocumento": "1113987400",
    "direccion": "CR 72B 45 12 APT 301"
  }
}
```

📄 **Ejemplo de Respuesta (JSON):**
```json
{
  "shipmentResponse": {
    "shippingId": "12345",
    "shippingStatus": "Entregado exitosamente al cliente"
  }
}
```

---

## **📜 Tecnologías Utilizadas**
✅ **Spring Boot** - Framework principal  
✅ **Spring Web Services** - Cliente SOAP  
✅ **JAXB** - Conversión XML ↔ JSON  
✅ **Maven** - Gestión de dependencias  
✅ **Docker** - Contenerización

---
🚀 ¡Gracias por revisar mi prueba técnica!