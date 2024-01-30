# audit-queries

/api/products

  GET
  - /all (obtiene todos los resultados)
  - /user/{userId} (obtiene todos los resultados de un usuario expecifico)
  - /user/{userId}/{status} (Obtiene todos los resultados con status true/false de un usuario expecifico)
  
  PUT
  - /paid/{id}?metodoDePago={paymentMethod} (Actualiza el status a true, actualiza paidDate con la fecha actual y añade el metodo de pago expecificado en un producto)

    {
        "userId": "116701448821985266594",
        "apiUrl": "https://v3.football.api-sports.io/leagues",
        "path": "football",
        "status": false,
        "date": "2024-01-29T12:22:05.623+00:00",
        "paidDate": "1970-01-01T00:00:00.000+00:00",
        "paymentMethod": "null",
        "apiResponse":
          }
