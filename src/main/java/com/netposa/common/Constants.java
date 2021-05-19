package com.netposa.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author JuHan
 * @date 2021-05-07
 */
public class Constants {
    public static final int ARGUMENTS_NUMBER = 1;
    public static final String ENGINS_STR = "hive,iceberg,hudi";
    public static final HashSet<String> ENGINS = new HashSet<>(Arrays.asList(ENGINS_STR.split(",")));
//    public static final HashSet<String> ENGINS2 =  Stream.of(ENGINS_STR.split(",")).collect(Collectors.toCollection(HashSet::new));

    public static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS %s.%s (record_id string ,business_id string ,info_kind int ,source_id string ,scene_img string ,device_id string ,plate_no string ,plate_no_confidence double ,plate_class string ,plate_color string ,traffic_status string ,vehicle_type string ,vehicle_color string ,vehicle_brand string ,vehicle_brand_confidence double ,vehicle_sub_brand string ,vehicle_sub_brand_confidence double ,face_num int ,sun_visor int ,seat_belt_status int ,annual_inspection_num int ,pendants_num int ,ornaments_num int ,vehicle_year string ,tissue_box_num int ,call_status int ,violation_type string ,abs_time bigint ,data_in_kafka_time bigint ,entry_time bigint ,vehicle_speed double ,marker_type int ,vehicle_headend int ,shield_face int ,task_type string ,info_source string ,algorithm_version string ,algorithm_vendor int ,eigenvector string ,is_secondary_structure int ,structure_time bigint ,structure_num int ,additional_info string ,source int ,driveway_no string ,location string ,plate_location string ,main_visor_position string ,vice_visor_position string ,main_seat_belt_position string ,vice_seat_belt_position string ,annual_inspection_position string ,pendant_location string ,tissue_box_location string ,plate_pic_url string ,move_direction int ,special_car int ,move_speed int ,sub_image_list string ,has_plate string ,access_time bigint ,structure_source int ,roof_items int ,plate_occlusion int ,facial_occlusion int ,tollgate_id string ,lane_no int ,whole_confidence double ,driver_phone_confidence double ,deputy_driver_phone_confidence double ,driver_seat_belt_confidence double ,deputy_driver_seat_belt_confidence double ,longitude double ,latitude double ,trace_info string ,ext_maps string ,trait_img string ,device_name string ,outreach string ,in_out_type int ,vehicle_id string ,relation_ids string ) PARTITIONED BY (dt_date string ) stored as orc";
    public static final String SQL_SELECT = "select record_id,business_id,info_kind,source_id,scene_img,device_id,plate_no,plate_no_confidence,plate_class,plate_color,traffic_status,vehicle_type,vehicle_color,vehicle_brand,vehicle_brand_confidence,vehicle_sub_brand,vehicle_sub_brand_confidence,face_num,sun_visor,seat_belt_status,annual_inspection_num,pendants_num,ornaments_num,vehicle_year,tissue_box_num,call_status,violation_type,abs_time,data_in_kafka_time,entry_time,vehicle_speed,marker_type,vehicle_headend,shield_face,task_type,info_source,algorithm_version,algorithm_vendor,eigenvector,is_secondary_structure,structure_time,structure_num,additional_info,source,driveway_no,location,plate_location,main_visor_position,vice_visor_position,main_seat_belt_position,vice_seat_belt_position,annual_inspection_position,pendant_location,tissue_box_location,plate_pic_url,move_direction,special_car,move_speed,sub_image_list,has_plate,access_time,structure_source,roof_items,plate_occlusion,facial_occlusion,tollgate_id,lane_no,whole_confidence,driver_phone_confidence,deputy_driver_phone_confidence,driver_seat_belt_confidence,deputy_driver_seat_belt_confidence,longitude,latitude,trace_info,ext_maps,trait_img,device_name,outreach,in_out_type,vehicle_id,relation_ids from %s limit %s";
}
