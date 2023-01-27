# MeteorologicalSensor_REST_API

<!-- ABOUT THE PROJECT -->
## О проекте/About project

  Реализация серверной стороны REST API приложения для метеорологической службы. 
  Имеет следующие возможности:
  1. Принимает с клиентской стороны json с данными сенсора и регистрирует его
  2. Принимает с клиентской стороны json с данными измерения температур и дождливости и записывать его к определенному сенсору 
  3. Отправляет в ответ на запрос все записи измерении
  4. Отправляет в ответ на запрос количество дождливых дней

  Implementation of the server side of the REST API of the application for the meteorological service. 
  It has the following features:
  1. Receives a request with json of sensor data from the client side and registers it
  2. Receives a request with json of temperature and rain measurement data from the client side and records it to a specific sensor 
  3. Sends all measurement records as response to the request
  4. Sends the number of rainy days as response to the request

## Реализация серверной стороны/Implementation of the server-side
  На серверной стороне принимается запросы содержащие SensorDTO или MeasurementDTO в json формате они перезаписываются как Sensor или Measurement и далее сохраняются в БД. Также предусмотрена обработка исключений.  
  
  On the server side, requests containing SensorDTO or MeasurementDTO in json format are accepted, they are mapped as Sensor or Measurement and then stored in the database. Exception handling is also provided.

![Gif1](https://user-images.githubusercontent.com/114854020/215170329-aa5c3e8f-19e6-4e12-817b-cef4a0ae7e28.gif)

![Gif2](https://user-images.githubusercontent.com/114854020/215170354-8bbbec37-cef8-4ccc-a449-ddab7ea5319e.gif)

## Схема базы данных/Database scheme

![MeteorologicalDB](https://user-images.githubusercontent.com/114854020/215170494-51e642ae-dc54-4643-b371-a936158dfac8.png)

