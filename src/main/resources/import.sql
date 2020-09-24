INSERT into state values (1, 'IL', 'Illinois', 6.25) ,(2,'TN','Tennessee', 0)

INSERT into city values (1,'Chicago',1) , (2, 'Memphis',2)

INSERT into Address values (1, '1024 s claremont' , 'apt 2', 60607,1,1)

INSERT into Item values (1,true,NULL,'Table',66.0), (2,true,NULL,'Chair',42.0)


INSERT into Payment_Method values (1,true,NULL,'Credit Card'),(2,true,NULL,'Debit Card'), (3,false,NULL,'PayPal'),(4,false,NULL,'Venmo')

INSERT into Shipping_Method values (1,true,NULL,'In store Pickup'), (2,true,NULL,'Curb Side Delivery'),(3,true,NULL,'Home Delivery'), (4,true,NULL,'Third party Delivery')

INSERT into Customer values (1,'2020-10-20 00:00:00','Mohammed Siddiq',1,1), (2,NULL,'Test',1,1)

INSERT into order_status values (1,'CREATED'), (2,'PAYMENT_PROCESSING'), (3,'COMPLETE')

INSERT into Card values (1,'1234567890', '2020-10-20 00:00:00','2026-10-20 00:00:00','Mohammed Siddiq'), (2,'1234567832', '2020-12-20 00:00:00','2025-10-20 00:00:00','Mohammed Siddiq')