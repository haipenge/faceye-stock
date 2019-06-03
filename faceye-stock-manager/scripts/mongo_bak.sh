#!/bin/bash
bak_dir='/opt/bak/mongo'
date_now=`date +%Y%m%d%H%M`
every_day_bak_dir=$bak_dir/'stock_full_bak_'$date_now
mkdir $every_day_bak_dir
mongodump -h localhost -d stock -o $every_day_bak_dir
tar -zcvf $bak_dir'/stock_full_bak_'$date_now.tar.gz $every_day_bak_dir
rm -rf $every_day_bak_dir
find $bak_dir -type f -mtime +30 -exec rm -Rf {} \;
exit 0

#define crontab
#30 5 * * * /bin/sh /opt/bak/mongo_bak.sh