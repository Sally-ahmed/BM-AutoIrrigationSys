
# BM-AutoIrrigationSys

This is a spring boot application for automated irrigation system. 



# What you need.

1)Spring boot snapshot 2.7.11

2)java 11

3)maven


# Important Attachments. 

The sql script for the db and the postman collections exist in the following path:


\autoirrigationsys\autoirrigationsys\src\main\resources\static

you can use this link to open the h2 db file 
http://localhost:8081/h2-console/login.jsp?


# How to run the application. 

Fork the repository and clone the project to your favourite directory

Open the project directory using your favourite IDE. This is a maven project so you can import a maven project. 

Then run the application as a java application. 


# Endpoints. 

There are various endpoints in the project

1. Create plot endpoint. Here is the url to use

http://localhost:8081/plot/add

Please note that the port may change depending on what you configured in the application.properties file. 

Here is the payload for adding a plot. This is a POST Request

{ "size" : 3000.0, "crop" : "Banana", "location" : "Fayoum" }

Sample response with http status 200 Ok

{
    "plotId": 37,
    "size": 3000.0,
    "crop": "Banana",
    "location": "Fayoum",
    "plotDetails": null
}

2. Get All Plots Details

Make a Get Request to the following endpoint. 

http://localhost:8081/plot/all

Sample response:

[
  
    {
        "plotId": 1,
        "size": 3500.0,
        "crop": "Beans",
        "location": "Sinai",
        "plotDetails": {
            "detailsId": 2,
            "waterAmountLtr": 3000,
            "irrigationStartTime": "14:52:00",
            "irrigationEndTime": "16:00:00",
            "sensorNotified": false
        }
    },
    {
        "plotId": 35,
        "size": 3000.0,
        "crop": "Banana",
        "location": "Fayoum",
        "plotDetails": null
    },
    {
        "plotId": 36,
        "size": 3000.0,
        "crop": "Banana",
        "location": "Fayoum",
        "plotDetails": null
    }

]

3. Update Plot endpoint

Make a PUT request to this endpoint:

http://localhost:8081/plot/update/{id}

Payload:

{"size": 3500, "crop": "wheat", "location": "Sinai"}
    
    
Kindly ensure to use the correct plot id in the path variable. The plot id is indicated in the get all plots endpoint. 
    
	
 4. Configure Plot endpoint

Make a PUT request to the following endpoint

http://localhost:8081/plot/configure/{id}

sample payload

{ "waterAmountLtr": 3000, "irrigationStartTime": "14:52:00", "irrigationEndTime": "16:00:00" ,"sensorNotified" : "false"}


sample response
{
    "plotId": 1,
    "size": 3500.0,
    "crop": "Beans",
    "location": "Sinai",
    "plotDetails": {
        "detailsId": 2,
        "waterAmountLtr": 3000,
        "irrigationStartTime": "14:52:00",
        "irrigationEndTime": "16:00:00",
        "sensorNotified": false
    }
}

# Sensor Interface

There is a service for notifying the irrigation sensor. This service is in the PlotService.java file under the services package. 

The service is a scheduled job that runs after every 60 seconds. It fetches the configured plots from the database and checks the irrigation time for each. When the irrigation time is reached, the notifySensor method is called which invokes a HTTP POST request to the sensor interface. 

A dummy sensor interface is present in the SensorController.java file. This returns a dummy string saying the sensor has been notified. (Please note the purpose of this exercise was not to build the irrigation sensor interface.) 

The sensor uses a retry whose configurations are set in the application.properties file. When the sensor interface goes down, the reslient4j library implements the retry calls for no of attampts then if fails calls the fallback method which is the alerts service. This sends a message indicating that the service was unable to communicate to the sensor.

# Unit Tests

There is  a sample unit test in the tests package for testing the correctness of  unit of the application. 



