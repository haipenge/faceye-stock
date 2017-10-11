#!/bin/bash
docker run -itd -p 9999:9999 -p 8881:8881 -p 8888:8888 -p 8887:8887 -p 8886:8886 -p 3333:3333 -v /etc/localtime:/etc/localtime faceye-app /bin/bash 