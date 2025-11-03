#### MySQL databse  infos:
The following description shows the necessary and recommended configurations for using mysql. It can be used when installing a local server. or with  any cloud provider (Azure, AWS, etc.)

Or can be run with docker in a local environment: https://hub.docker.com/_/mysql
(The steps are the same in this case as well)

##### Connect to MySQL as an Admin User

 Use third party app or command line: `mysql -h <server-name>.mysql.database.com -u <admin-user> -p`
 
 (In case of command line, it is necessary to install `mysql client`: https://www.bytebase.com/blog/how-to-install-mysql-client-on-mac-ubuntu-centos-windows/)
 
 
##### Create a New Database
 (It is also referred to as Schema in applications and interfaces in the case of MySQL)
 
 `CREATE DATABASE new_database_name;`
 
 - Verify that the database is created: `SHOW DATABASES;`
 - Create the Table: (SQL Table Schema)
 
 ```
 CREATE TABLE HIGHSCORES (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    TIMESTAMP TIMESTAMP NOT NULL,
    NAME VARCHAR(50) NOT NULL,
    SCORE INT NOT NULL
);
 ```
 
##### Create a Service User
 
 `CREATE USER 'service_user'@'%' IDENTIFIED BY 'strong_password';`
 
 - `@'%'`: Allows the user to connect from any host. You can restrict this to a specific IP or hostname for better security.
 
 Gives all rights to the Service user or restricts them based on the current use:
 
 - All: `GRANT ALL PRIVILEGES ON new_database_name.* TO 'service_user'@'%'; FLUSH PRIVILEGES;`
 - Specific Privileges: `GRANT SELECT, INSERT, DELETE ON new_database_name.* TO 'service_user'@'%'; FLUSH PRIVILEGES;`
 - Check the privileges assigned to the user: `SHOW GRANTS FOR 'service_user'@'%';`



