




echo $PATH
OSNAME=`uname -s`
DB_PATH=/var/lib/mysql-files/applifire/db/DUYADDNHCZRMQFLFOYRZ5W
ART_CREATE_PATH=/var/lib/mysql-files/applifire/db/DUYADDNHCZRMQFLFOYRZ5W/art/create
AST_CREATE_PATH=/var/lib/mysql-files/applifire/db/DUYADDNHCZRMQFLFOYRZ5W/ast/create
MYSQL=/usr/bin
APPLFIREUSER=root
APPLFIREPASSWORD=Glass4#21
APPLFIREHOST=localhost

if [ $OSNAME = "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi



DB_NAME=appmob
USER=appmob
PASSWORD=appmob
PORT=3306
HOST=localhost


echo 'resetCounter Starts...'

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "ALTER TABLE AddressMap AUTO_INCREMENT = 1; ";

echo 'resetCounter ends...'

