#Local mongo db export scripts.
#Write by Haipenge 2017.02.17
MONGO_DIR="/app/mongo/mongodb-linux-x86_64-3.0.1"
EXPORT_DIR="/data/bak/stock_mongo_db"
#Export Example:
#./mongoexport -d search -c search_article -o /data/bak/mongo/search_article_20140930.bak
$MONGO_DIR/bin/mongoexport -d search -c stock_accounting_element -o $EXPORT_DIR/stock_accounting_element.bak
$MONGO_DIR/bin/mongoexport -d search -c stock_accounting_subject -o $EXPORT_DIR/stock_accounting_subject.bak
$MONGO_DIR/bin/mongoexport -d search -c stock_report_category -o $EXPORT_DIR/stock_report_category.bak
$MONGO_DIR/bin/mongoexport -d search -c stock_stock -o $EXPORT_DIR/stock_stock.bak
$MONGO_DIR/bin/mongoexport -d search -c stock_category -o $EXPORT_DIR/stock_category.bak
$MONGO_DIR/bin/mongoexport -d search -c security_menu -o $EXPORT_DIR/security_menu.bak
$MONGO_DIR/bin/mongoexport -d search -c security_resource -o $EXPORT_DIR/security_resource.bak
$MONGO_DIR/bin/mongoexport -d search -c security_role -o $EXPORT_DIR/security_role.bak
$MONGO_DIR/bin/mongoexport -d search -c security_user -o $EXPORT_DIR/security_user.bak
$MONGO_DIR/bin/mongoexport -d search -c global_sequence -o $EXPORT_DIR/global_sequence.bak