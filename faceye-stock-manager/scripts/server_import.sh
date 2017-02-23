#Import Mongo DB
#Write by Haipenge 2017.02.16
#Import example is :
#./mongoimport -d search -c search_article /data/bak/mongo/search_article_20140930.bak
MONGO_DIR=/app/mongo/mongodb-linux-x86_64-3.0.1
#EXPORT_DIR=/app/build/faceye-stock/faceye-stock-manager/data/db
EXPORT_DIR="/data/bak/stock_mongo_db"
$MONGO_DIR/bin/mongoimport -d stock -c stock_accounting_element $EXPORT_DIR/stock_accounting_element.bak
$MONGO_DIR/bin/mongoimport -d stock -c stock_accounting_subject $EXPORT_DIR/stock_accounting_subject.bak
$MONGO_DIR/bin/mongoimport -d stock -c stock_report_category $EXPORT_DIR/stock_report_category.bak
$MONGO_DIR/bin/mongoimport -d stock -c stock_stock $EXPORT_DIR/stock_stock.bak
$MONGO_DIR/bin/mongoimport -d stock -c stock_category $EXPORT_DIR/stock_category.bak
$MONGO_DIR/bin/mongoimport -d stock -c security_user $EXPORT_DIR/security_user.bak
$MONGO_DIR/bin/mongoimport -d stock -c security_resource $EXPORT_DIR/security_resource.bak
$MONGO_DIR/bin/mongoimport -d stock -c security_role $EXPORT_DIR/security_role.bak
$MONGO_DIR/bin/mongoimport -d stock -c security_menu $EXPORT_DIR/security_menu.bak
$MONGO_DIR/bin/mongoimport -d stock -c global_sequence $EXPORT_DIR/global_sequence.bak