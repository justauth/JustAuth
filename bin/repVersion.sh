#!/bin/bash

#-----------------------------------------------------------
# 参考自 hutool 工具
# 此脚本用于每次升级justauth时替换相应位置的版本号
#-----------------------------------------------------------
pwd=$(pwd)
echo "当前路径：$pwd"

if [ -n "$1" ];then
    new_version="$1"
    old_version=`cat $pwd/bin/version.txt`
    echo "$old_version 替换为新版本 $new_version"
else
    # 参数错误，退出
    echo "ERROR: 请指定新版本！"
    exit
fi

if [ ! -n "$old_version" ]; then
    echo "ERROR: 旧版本不存在，请确认bin/version.txt中信息正确"
    exit
fi

# 替换README.md等文件中的版本
sed -i "s/${old_version}/${new_version}/g" $pwd/README.md
sed -i "s/${old_version}/${new_version}/g" $pwd/README.en-US.md

# 替换pom.xml中的版本
sed -i "s/${old_version}/${new_version}/g" $pwd/pom.xml

# 保留新版本号
echo "$new_version" > $pwd/bin/version.txt
