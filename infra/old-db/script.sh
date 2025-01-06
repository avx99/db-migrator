
mysql -u root -p root -D migration -e "CREATE USER 'pgloader_user'@'192.168.49.2' IDENTIFIED BY 'pgloader_pass'; GRANT ALL PRIVILEGES ON migration.* TO 'pgloader_user'@'192.168.49.2'; FLUSH PRIVILEGES;"
mysql -u root -p root -D migration -e "update mysql.user set plugin = 'mysql_native_password' where user = 'root';"
mysql -u root -p root -D migration -e "ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'root';"
mysql -u root -p root -D migration -e "FLUSH PRIVILEGES;"
mysql -u pgloader_user -p pgloader_pass -h 192.168.49.2 -e "CREATE USER 'pgloader_user'@'192.168.49.2' IDENTIFIED BY 'pgloader_pass'; GRANT ALL PRIVILEGES ON migration.* TO 'pgloader_user'@'192.168.49.2'; FLUSH PRIVILEGES;"


# Creating a database
mysql -u root -proot -e "CREATE DATABASE migration;"

# Creating a new user 'pgloader_user' and granting privileges
mysql -u root -proot -D migration -e "CREATE USER 'pgloader_user'@'192.168.49.2' IDENTIFIED BY 'pgloader_pass'; GRANT ALL PRIVILEGES ON migration.* TO 'pgloader_user'@'192.168.49.2'; FLUSH PRIVILEGES;"

# Setting the root user to use mysql_native_password plugin
#mysql -u root -proot -D migration -e "UPDATE mysql.user SET plugin = 'mysql_native_password' WHERE user = 'root';"

# Ensuring the root user password is configured with mysql_native_password
#mysql -u root -proot -D migration -e "ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'root';"

# Flushing privileges to apply changes
mysql -u root -proot -D migration -e "FLUSH PRIVILEGES;"

# Connecting with the newly created user to verify
mysql -u pgloader_user -ppgloader_pass -h 192.168.49.2 -e "SHOW DATABASES;"
