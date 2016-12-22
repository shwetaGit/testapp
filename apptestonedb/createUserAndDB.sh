




echo $PATH
OSNAME=`uname -s`
DB_PATH=/var/lib/mysql-files/applifire/db/TV0GYK21OSLS0Q4KJBTPW
MYSQL=/usr/bin
APPLFIREUSER=root
APPLFIREHOST=localhost

if [ $OSNAME = "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi

DB_NAME=apptestone
USER=apptestone
PASSWORD=apptestone
PORT=3306
HOST=localhost


echo 'grant previliges to user starts at ' $(date)
$MYSQL/mysql -h$APPLFIREHOST -u$APPLFIREUSER -e "SOURCE $DB_PATH/grant_previliges.sql";
echo 'grant previliges to user ends at ' $(date)

echo 'drop db starts at ' $(date)
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends at ' $(date)

echo 'create db starts at ' $(date)
$MYSQL/mysql -h$APPLFIREHOST -u$APPLFIREUSER -e "SOURCE $DB_PATH/create_db.sql";
echo 'create db ends at ' $(date)

echo 'create table starts at ' $(date)
$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $DB_PATH/ddl.sql;"
echo 'create table ends at ' $(date)

