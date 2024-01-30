# audit-queries

/api/products

  GET
  - /all (obtiene todos los resultados)
  - /user/{userId} (obtiene todos los resultados de un usuario expecifico)
  - /user/{userId}/{status} (Obtiene todos los resultados con status true/false de un usuario expecifico)
  
  PUT
  - /paid/{id}?metodoDePago={paymentMethod} (Actualiza el status a true, actualiza paidDate con la fecha actual y añade el metodo de pago expecificado en un producto)

```
    {
        "userId": ,
        "apiUrl": ,
        "path": ,
        "status": ,
        "date": ,
        "paidDate": ,
        "paymentMethod": ,
        "apiResponse": 
          }
