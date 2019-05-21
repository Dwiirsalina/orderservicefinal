# orderservicefinal

## Contributors.

1. Dwi Irsalina (05111540000004)
2. Neny Lukitasari (05111540000018)
3. Anindya Hantari (05111540000116)

## Link API : http://orderservicehore.herokuapp.com

## End Point


**Order**
---
| Method | End Point | Parameter | Privilege | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
|GET| /orders| - | Customer | Get all order data |
|POST| /orderd/add |customer_id, deals_id, delivery_address, order_note, order_status, total_price, restaurant_id | Customer | Create order |
|GET| /orders/{id} |order_id| Customer | Get order data by id |
|PATCH| /orders/{id}/status | order_id | - | Update order_status |


**Order Detail**
---
| Method | End Point | Parameter | Privilege | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
|GET| /orders/{id}/details| id | Customer | Get order detail data by id |
