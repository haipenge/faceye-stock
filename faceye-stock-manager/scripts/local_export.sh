#Local mongo db export scripts.
#Write by Haipenge 2017.02.17
MONGO_DIR="/tools/mongodb/mongodb-osx-x86_64-3.0.1"
EXPORT_DIR="/work/project/faceye-stock/faceye-stock-manager/data/db"
#Export Example:
#./mongoexport -d search -c search_article -o /data/bak/mongo/search_article_20140930.bak
$MONGO_DIR/bin/mongoexport -d search -c stock_accounting_element -o $EXPORT_DIR/stock_accounting_element.bak
$MONGO_DIR/bin/mongoexport -d search -c stock_accounting_subject -o $EXPORT_DIR/stock_accounting_subject.bak
$MONGO_DIR/bin/mongoexport -d search -c stock_report_category -o $EXPORT_DIR/stock_report_category.bak
