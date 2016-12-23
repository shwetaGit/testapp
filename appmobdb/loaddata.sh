




echo $PATH
OSNAME=`uname -s`
ART_DATA_PATH=/var/lib/mysql-files/applifire/db/DUYADDNHCZRMQFLFOYRZ5W/art/data
AST_DATA_PATH=/var/lib/mysql-files/applifire/db/DUYADDNHCZRMQFLFOYRZ5W/ast/data
MYSQL=/usr/bin


if [ $OSNAME = "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi

DB_NAME=appmob
USER=appmob
PASSWORD=appmob
PORT=3306
HOST=localhost


echo ' load ART data starts at ' $(date)
$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_password_algorithm.csv' into table art_password_algorithm FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_password_policy.csv' into table art_password_policy FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/cloud_drive_file_content_type.csv' into table cloud_drive_file_content_type FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/cloud_drive_tags.csv' into table cloud_drive_tags FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_query.csv' into table art_query FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$ART_DATA_PATH/art_log_alarm.csv' into table art_log_alarm FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

echo 'load ART data ends at ' $(date)

echo ' load AST data starts at ' $(date)
$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Timezone.csv' into table Timezone FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Language.csv' into table Language FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Country.csv' into table Country FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/State.csv' into table State FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/City.csv' into table City FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/AddressType.csv' into table AddressType FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Address.csv' into table Address FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/ContactType.csv' into table ContactType FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Gender.csv' into table Gender FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Title.csv' into table Title FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/CoreContacts.csv' into table CoreContacts FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/AddressMap.csv' into table AddressMap FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/UserAccessLevel.csv' into table UserAccessLevel FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/UserAccessDomain.csv' into table UserAccessDomain FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/User.csv' into table UserDetail FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Login.csv' into table Login FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/LoginSession.csv' into table LoginSession FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/PassRecovery.csv' into table PassRecovery FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/UserData.csv' into table UserData FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/SessionData.csv' into table SessionData FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Roles.csv' into table Roles FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/UserRoleBridge.csv' into table UserRoleBridge FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

echo ' load AST data ends at ' $(date)


echo 'load Predefined AST data starts at ' $(date)
$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/AppMenus.csv' into table AppMenus FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (menuId,menuTreeId,menuIcon,menuAction,menuCommands,menuDisplay,menuHead,menuLabel,uiType,RefObjectId,menuAccessRights,appType,appId,autoSave,expiryDate   , createdBy, createdDate, updatedBy, updatedDate, versionId, activeStatus, txnAccessCode)";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/RoleMenuBridge.csv' into table RoleMenuBridge FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (roleMenuMapId,roleId,menuId,isRead,isWrite,isExecute     , createdBy, createdDate, updatedBy, updatedDate, versionId, activeStatus, txnAccessCode)";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/PasswordPolicy.csv' into table PasswordPolicy FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (policyId, policyName, policyDescription, maxPwdLength,  minPwdLength, minCapitalLetters, minSmallLetters, minNumericValues, minSpecialLetters, allowedSpecialLetters     , createdBy, createdDate, updatedBy, updatedDate, versionId, activeStatus, txnAccessCode)";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/Question.csv' into table Question FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (questionId, levelid, question, questionDetails, questionIcon     , createdBy, createdDate, updatedBy, updatedDate, versionId, activeStatus, txnAccessCode)";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data infile '$AST_DATA_PATH/PasswordAlgo.csv' into table PasswordAlgo FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (algoId, algorithm, algoName, algoDescription, algoIcon, algoHelp     , createdBy, createdDate, updatedBy, updatedDate, versionId, activeStatus, txnAccessCode)";

echo 'load Predefined AST data ends at ' $(date)
