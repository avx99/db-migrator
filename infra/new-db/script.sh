apt-get update
apt-get install postgresql-17-mysql-fdw -y
psql -U migration -c "CREATE DATABASE migration;"
psql -U migration -d migration -c "CREATE EXTENSION mysql_fdw;"
psql -U migration -d migration -c "CREATE ROLE migration WITH LOGIN SUPERUSER PASSWORD 'migration';"
psql -U migration -d migration -c "CREATE SCHEMA mysql;"

apt update
apt install sbcl unzip libsqlite3-dev gawk curl make freetds-dev libzip-dev -y
curl -fsSLO https://github.com/dimitri/pgloader/archive/v3.6.2.tar.gz
tar xvf v3.6.2.tar.gz
cd pgloader-3.6.2/
make pgloader
mv ./build/bin/pgloader /usr/local/bin/
pgloader --version
psql -U migration -d migration -c "CREATE ROLE migration WITH LOGIN SUPERUSER PASSWORD 'migration';"
psql -U migration -c "CREATE DATABASE migration;"
psql -U migration -c "SET session_replication_role = replica;"
pgloader mysql://pgloader_user:pgloader_password@192.168.49.2:30036/migration postgresql://migration:migration@localhost:5432/migration 