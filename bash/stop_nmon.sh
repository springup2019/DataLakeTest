#!/bin/bash
nmon_pid=`ps -ef|grep nmon_result|grep -v grep|awk '{print $2}'`

if [[ -n ${nmon_pid} ]];then
	echo "nmon已经存在，PID=${nmon_pid}，kill进程..."
	kill -9 ${nmon_pid}
fi
