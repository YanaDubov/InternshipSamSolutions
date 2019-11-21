# InternshipSamSolutions
## Travel Apartments
Travel Apartments is a platform for travelers to stay in comfortable place during their vactions. It provides us with a large number of homelike apartments in 10 different countries. Using platform travelers can find, view photos and descriptions, book their future travel apartments.

## Steps to setup app
1. Clone the application or download zip:
  `git clone https://github.com/YanaDubov/InternshipSamSolutions.git `
2. Create MySQL Database todo: which Mysql version we have to use?
  ` create database testdatabase `
3. Change MySQL username and password as per your MySQL installation
  + open ` /src/main/resources/database.properties ` file todo: probably we also should edit flyway.properties
  + change ` jdbc.username ` , ` jdbc.password ` and ` jdbc.url ` as in your mysql installation
4. Run `mvn clean install`
5. Edit configuration Tomcat Remote( choose ` yana-internship-app:war ` for deployment) todo: Please describe it in more detail: where we should configure `Tomcat Remote`? Probably in IDE. Why we should start Tomcat in this case? Which url we have to open? Would be good to describe deployment only via tomcat web-admin (You can just insert a link from external resource)
6. Run Tomcat server todo: which version of Tomcat we have to use?
