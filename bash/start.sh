#!/bin/bash
base_dir=$(cd `dirname $0`;pwd)
log_dir=${base_dir}/log

function runner(){
    if [[ ! -d ${log_dir} ]];then
       mkdir -p ${log_dir}
    fi
    log_date=`date "+%Y-%m-%d"`
    nohup java -jar ${base_dir}/DataLakeTest.jar $1 > ${log_dir}/${log_date}.log 2>&1 &

}
if [ $# == 1 ]; then
    runner $1
elif [ $# == 0 ]; then
    runner ${base_dir}
fi