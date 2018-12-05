ROOT=$(dirname $(cd "$(dirname "$0")";pwd))
rm -rf /tmp/logs/faceye-stock-manager/*
cd $ROOT
git pull
echo `ps aux|grep jetty |grep -v grep  | awk '{print $2}'|xargs kill -9`
build_env='cloud'
if [[ -n $1 ]];then
	build_env=$1
fi
echo $build_env
cd $ROOT/faceye-stock-entity
mvn clean compile package install -D maven.test.skip=true -P product
cd $ROOT/faceye-stock-manager
mvn clean compile jetty:run -D maven.test.skip=true -D jetty.port=8888 -P $build_env -Duser.timezone=GMT+08 &
