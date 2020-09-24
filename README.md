# Order Service

## Steps to build the docker image

After cloning this repo run the following to build the docker image:

```docker build -t  order-service-dev-build:1.0```

Run the docker image as:

```docker run -p 8080:8080 -t order-service-dev-build:1.0```

## Sample Curls

### Create a new order

    curl --location --request POST 'localhost:8080/api/order' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "customer": {
            "id": 1
        },
        "items": [
            {
                "id": 1,
                "quantity": 1
            },
            {
                "id": 2,
                "quantity": 1
            }
        ],
        "shippingMethod": {
            "id": 1
        },
        "shippingCharges": 10.95,
        "totalTax": 30,
        "subTotal": 50.35,
        "shippingAddress": {
            "id": 1
        },
        "billingAddress": {
            "id": 1
        },
        "paymentInfo": {
            "paymentMethod": {
                "id": 1
            },
            "cards": [
                {
                    "id": 1
                },
                {
                    "id": 2
                }
            ]
        }
    }

Response

     
   
   
   
      
