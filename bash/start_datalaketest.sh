#!/bin/bash
base_dir=$(cd `dirname $0`;pwd)
log_dir=${base_dir}/log

function runner(){
	if [[ ! -d ${log_dir} ]];then
		mkdir -p ${log_dir}
	fi
	
	if [[ ! -f ${base_dir}/conf.properties ]];then
		echo "${base_dir}/conf.properties file not exits, please check!"
		exit
	else
		source ${base_dir}/conf.properties1
	fi
	
	log_date=`date "+%Y%m%d%H%M%S"`
	echo "------------------------------------------------------------------------------------"
	echo "test.mode=${test_mode}, threadPool.size=${threadPool_size}, write.mode=${write_mode}"
	echo "------------------------------------------------------------------------------------"
	nohup java -Dfile.encoding=UTF-8 -jar ${base_dir}/DataLakeTest-1.0-jar-with-dependencies.jar $1 > ${log_dir}/${test_mode}_${threadPool_size}_${log_date}.log 2>&1 &

}

runner ${base_dir}
