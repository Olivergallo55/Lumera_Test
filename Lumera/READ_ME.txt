This project has been done by Oiver Gallo.

This API takes a file and parses it into a payment bundle. At the moment there are two supported payment types, "_betalningsservice.txt" and "_inbetalningstjansten.txt".
The structure of the program is the following. The two payments have some general attributes in common such as payment type, clearings number, account numner, etc.
For this I created a super class called PaymentType that contains all the nececerry components needed for a payment, this also allows the possibility to create new unique 
payment type subclasses in future development. This is a clean way to achive more maintainable and less repetative code.

#ENUMS
Two enum classes has been created to check the type of a file, but also the content. This allows for another way for ensuring that only the right file type
can be parsed in its unique way. But also leaves the possibility to easily add new payment attributes or modify existing ones. '

#Parsers
A FileParser class is created to read a file and parse it into its unique payment bundle. In this class the payment type is checked to ensure that correct
payment object is returned. 

The FileReader reads the file and line by line and calles the FileParser in order to create the correct payment object. 

#RunHandler
Runhandler is the main handler class that class the parser and reads the file, in order to invoke the interface and start the bundle. 

#UnitTests 
Unit Tests has been created to ensure that the program works as it should. 

The Project has been structured so that it is easy to see what each packages do. This makes it easier to see through the project, maintain it and in the future
if so happens, further develop it. There are example files in the folder "Test_Files", in case any testing would take place. For further information and documentation
please see the code itself!