#Import Mongo DB
#Write by Haipenge 2017.02.16
#Import example is :
#./mongoimport -d search -c search_article /data/bak/mongo/search_article_20140930.bak
MONGO_DIR=/app/mongo/mongodb-linux-x86_64-3.0.1
EXPORT_DIR=/app/build/faceye-stock/faceye-stock-manager/data/db
$MONGO_DIR/bin/mongoimport -d search -c stock_accounting_element $EXPORT_DIR/stock_accounting_element.bak
$MONGO_DIR/bin/mongoimport -d search -c stock_accounting_subject $EXPORT_DIR/stock_accounting_subject.bak
$MONGO_DIR/bin/mongoimport -d search -c stock_report_category $EXPORT_DIR/stock_report_category.bak