# Money Saver

### **1. all used libraries and their versions**

1). SpringBoot version: 2.5.4 

2). Java version: 11

3). MyBatis version: 2.2.0

4). Druid: 1.1.22

5). MySQL: 8.0.23

6). Junit: 4.13.2

7). okhttp: 2.2.0

8). httpcore: 4.4.13

9). Jedis: 3.2.0

10). json-simple: 1.1.1

11). Because it is a Spring Boot + Vue program, you also need to configure the Vue related environment locally. You can use the following methods, you need to install npm first

#### Project setup
```
npm install
```

#### Compiles and hot-reloads for development
```
npm run serve
```

#### Compiles and minifies for production
```
npm run build
```

#### Lints and fixes files
```
npm run lint
```

#### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

element-plus
https://element-plus.gitee.io/zh-CN/

12). Redis version: 6.2.2

In addition, Redis middleware is also used, so before starting the project, you also need to run the Redis server. First, you need to install Redis, and then use 
```
redis-server
```
to start the redis server.

### **List all working functionalities of the project**

1. Register a new account.
2. Login to the system.
3. Refresh the product list (retrieved from the API and added to the database).
4. According to the strength of the discount, see the popular products.
5. List all the products.
6. Get all brands.
7. Filter products by their brand.
8. Search for products by name.
9. Show product details.
10. Add like to their favorite products. 
11. Collect favorite products.
12. Cancel the collect.
13. Cancel the favorite.
14. Comment on the product.
15. Compare prices for two commodities.

16. Query the information of all coupons.
17. Filter for coupons according to the store.
18. Refresh the coupon list (retrieved from the API and added to the database).
19. The user clicks on the coupon to be redirected to the store's official website.
20. View the basic information of the three stores.

21. Edit personal information.
22. Display the collection of the user.
23. Redirect to the product's detail page when user clicks on that bar.
24. Log out of the system.

### **A quick guide on how to run your application**

1. Create a local database.
2. Connect to the local database and start MySQL (Change the url and password to yours in the 'application.properties' file).
3. Start Redis.
```
redis-server
```
4. To start the SpringBoot server, click the main function start button.
5. Start Vue
```
npm run serve
```
6. Open the 8000 port number through the browser.


### **Notes about coupon-related functions**

For the current version of the application, we need to replace the apikey(free-trail for 7 days - each account) related to the coupon. The presenter needs to register and log in to couponapi. In the shop selection interface, select the store to be displayed-‘amazon’, ‘nike’ and ‘joesnewbalanceoutlet’.
Then copy the apikey into the following format:

i.e.https://couponapi.org/api/getFeed/?API_KEY=23ed01fde79fef0effcf5a4f72e6a219&format=json

And paste it to line 34 in couponAPI.java:

String reqUrl = "https://couponapi.org/api/getFeed/?API_KEY=23ed01fde79fef0effcf5a4f72e6a219&format=json";