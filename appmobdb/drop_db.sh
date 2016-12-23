




echo $PATH
DB_PATH=/var/lib/mysql-files/applifire/db/DUYADDNHCZRMQFLFOYRZ5W
MYSQL=/usr/bin
USER=appmob
PASSWORD=appmob
PORT=3306
HOST=localhost
echo 'drop db starts at ' $(date)
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends at ' $(date)

