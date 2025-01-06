CREATE USER pgloader_userX WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE migration TO pgloader_userX;





psql -U migration -d migration -c "CREATE USER pgloader_userX WITH PASSWORD 'password';GRANT ALL PRIVILEGES ON DATABASE migration TO pgloader_userX;"


pgloader mysql://pgloader_user:your_password@192.168.49.2:30036/migration  postgresql://migration:migration@localhost:5432/migration 






pgloader --before "SET session_replication_role = replica;" --after "SET session_replication_role = origin;" mysql://pgloader_user:your_password@192.168.49.2:30036/migration postgresql://migration:migration@localhost:5432/migration



pgloader mysql://pgloader_user:your_password@192.168.49.2:30036/migration postgresql://migration:migration@localhost:5432/migration?search_path=mysql






