This is a digital library application that is implemented using the spring boot 
framework. It implements 4 models each with the ability to perform all the CRUD operation.
The project uses mysql as it's database management system and so to connect to the database make 
sure you have mysql installed and also you have the service running. You also have to make sure you 
have a database with the same name as stated in the application property file or better still you can 
reconfigure the application property to use a database management system of choice and also a database of choice. The project implements 
4 model classes, controller classes, each for a specific model to make our request when running the application. In the application,
a jparesitory interface is created for each model. This is make sure our spring boot application is able to handle the CRUD requests as the
JpaRepository has all the necessary methods to help us in creating the CRUD operation. Also, we have a number of classes that helps in handling certain errors 
during the performance of certain Crud operations. The application property file is configured in a way that the application always updates the connected database. 
This will help prevent loss of data when you connect to the database.
When you start the application, you have to use an api platform like postman to make the necessary requests to 
the application. When creating an author, you have to provide the country code (eg. gha for Ghana and usa for United States of America)
as this code will be accessed by an external api to get details of the country and a wrong code is going to create an error.
To create an author, u call the specified link in the controller in addition to the host port on which the application is being run on and
pass the required data to save the data into the database. In order to perform all the other operations, you have to go through a similar way as stated above
by providing their required links.
