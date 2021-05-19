#!/bin/bash
shell_path=$(cd `dirname $0`; pwd)
time=$(date "+%Y%m%d%H%M%S")
cd ${shell_path}/nmon
S_IP=`ifconfig enp3s0f0 |grep -v 127.0.0.1|grep -v inet6|grep inet|awk '{print $2}'|tr -d "addr:"|head -1`
nmon_pid=`ps -ef|grep nmon_result|grep -v grep|awk '{print $2}'`

if [[ -n ${nmon_pid} ]];then
	echo "nmon已经存在，PID=${nmon_pid}，kill进程..."
	kill -9 ${nmon_pid}
fi

if [[ ! -d nmon_result ]];then
	mkdir nmon_result
fi

if [[ ! -f ${shell_path}/conf.properties1 ]];then
	echo "${shell_path}/conf.properties1 file not exits, please check!"
	exit
else
	source ${shell_path}/conf.properties1
fi

#-s 间隔1秒  
#-c 是总共采集的时长（此处是7200秒,2hours）  
#-F 输出文件名（此处起名为result_${time}.nmon）
echo "------------------------------------------------------------------------------------"
echo "启动nmon,test.mode=${test_mode}, threadPool.size=${threadPool_size}, write.mode=${write_mode}"
echo "------------------------------------------------------------------------------------"
./nmon -f -t -s 1 -c 7200 -F nmon_result/${S_IP}_${test_mode}_${threadPool_size}_result_${time}.nmon
