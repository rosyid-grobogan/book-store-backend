# Book Store Project - Backend with Microservices Style

Demo:  https://bookstore-restapi.herokuapp.com/swagger-ui.html

## Microservices Style
### Struktur Package
- account: user, authorization and authentication
- catalog: product, favourite and category
- shipment: shipment
- transaction: cart, transaction, report
- configglobal: swagger, minio, pagination, websecurity

## User

1. Register, Login, Logout (done)
2. HomePage
3. List Buku
4. Search
5. Add to Favorite (done)
6. Detail Buku  (done)
7. Add to Cart (done)
8. Checkout (done)
9. Payment via Wallet
10. Konfirmasi Pembelian Page (done)
11. Status Pembelian Page

## Admin

1. Register, Login, Logout
2. CRUD Buku/Product (done) 
3. CRUD kategori (done)
4. CRUD Daftar Pembelian
5. Status Pendapatan


## HTTP Header
Content-Type : [{"key":"Content-Type","value":"application/json","description":"","type":"text","enabled":true}]

Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb3N5aWQiLCJpYXQiOjE1ODgxMjExMzIsImV4cCI6MTU4ODEyMTEzN30.foHbKUF5Edw3eCfB1wsmGIz1t-GGpG2zU2LaBZJ9HGa0BoXnRqgmQpvkyzTISB7nZLbHREcvcqOjtEijjW-VoQ

## Resources Naming
|      | API Name           | HTTP Method | Path                  | Status Code    | Desciption                        |
| ---- | ------------------ | ----------- | --------------------- | -------------- | --------------------------------- |
| 1    | GET Products       | GET         | /api/v1/products      | 200 OK         | All product resources are fetched |
| 2    | POST Products      | POST        | /api/v1/products      | 201 Created    | A new product resource is created |
| 3    | GET Products       | GET         | /api/v1/products/{id} | 200 OK         | One product resource is fetched   |
| 4    | PUT/PATCH Products | PUT/PATCH   | /api/v1/products/{id} | 200 OK         | Product resource is updated       |
| 5    | DELETE Products    | DELETE      | /api/v1/products/{id} | 204 No Content | Product resource is deleted       |



## Entity
## Update Data 
- CreatedBy and CreatedAt are not changed
- Id cannot changed

## Insert Examples
### Admin Page
Create new product
```
# 2 Sample
{
  "categoryId": 1,
  "description": "Buku rekomendasi buat Anda",
  "name": "Belajar Java",
  "photoId": 0,
  "price": 120000,
  "productCategoryId": { "id": 1  },
  "productStatus": "FOR_SELL",
  "quantity": 10,
  "slug": "belajar-java",
    "productVisibility": "VISIBLE"
}
{
  "categoryId": 1,
  "description": "Buku rekomendasi buat Anda",
  "name": "Belajar PHP",
  "photoId": 0,
  "price": 124000,
  "productCategoryId": { "id": 1  },
  "productStatus": "OUT_OF_STOCK",
  "quantity": 5,
  "slug": "belajar-php",
    "productVisibility": "VISIBLE"
}
```
productVisibility": "VISIBLE" merupakan fitur untuk aktif dan menon-aktikan product.
Karena terkadang pengguna tidak ingin menghapus tapi hanya menon-aktifkan product.

Value of productStatus:
- FOR_SELL : Button is actived and it's available for selling.
- OUT_OF_STOCK : Disable button.
- HIDDEN : Product price is not visible.


### Langkah Persiapan
- Download / Clone melalui GitHub
- Buat Database
- Sesuaikan konfigurasi Databasenya: Edit file application.properties

### Buat Akun
```json
{
  "username": "rosyidgrobogan"
  "email": "rosyid@bookstore.com",
  "fullName": "Rosyid Grobogan",
  "password": "rahasia",
  "role": [
    "ADMIN", "USER"
  ]
}
```
Key "role" bernilai array, misal
- ["USER]
- ["ADMIN", "USER"]

### Dapatkan Token 
- Silakan SigIn dengan username dan password yang sudah didaftarkan
