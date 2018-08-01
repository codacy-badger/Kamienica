FROM mysql/mysql-server

MAINTAINER macfol

# Copy the database initialize script:

# Contents of /docker-entrypoint-initdb.d are run on mysqld startup

#ADD  mysql/ /docker-entrypoint-initdb.d/