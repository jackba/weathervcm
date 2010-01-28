/*==============================================================*/
/* DBMS name:      MySQL 4.0                                    */
/* Created on:     12/3/2009 12:27:14 AM                        */
/*==============================================================*/


drop table if exists bulletin_board;

drop table if exists conf_party;

drop table if exists conf_template;

drop table if exists conf_template_x_unit;

drop table if exists conf_unit;

drop table if exists conference;

drop table if exists field_desc;

drop table if exists log;

drop table if exists message_board;

drop table if exists privilege;

drop table if exists role;

drop table if exists role_privilege;

drop table if exists service_template;

drop table if exists terminal;

drop table if exists unit;

drop table if exists user;

drop table if exists user_role;

drop table if exists user_unit;

drop table if exists recurring_meeting_info;

drop table if exists recurrence_conf;

drop table if exists recurrence_unit;

drop table if exists send_message;

/*==============================================================*/
/* Table: bulletin_board                                        */
/*==============================================================*/
create table bulletin_board
(
   bulletin_id                    int                            not null AUTO_INCREMENT,
   user_id                        varchar(128),
   title                          varchar(100)                   not null,
   content                        text,
   effective_time                 datetime                       not null,
   expired_time                   datetime,
   create_time                    datetime                       not null,
   update_time                    datetime                       not null,
   status                         tinyint                        not null,
   primary key (bulletin_id)
)
type = InnoDB;

/*==============================================================*/
/* Table: conf_party                                            */
/*==============================================================*/
create table conf_party
(
   conf_party_id                  int                            not null AUTO_INCREMENT,
   conference_id                  int                            not null,
   party_id                       varchar(32)                    not null,
   primary key (conf_party_id)
)
type = InnoDB;

/*==============================================================*/
/* Table: conf_template                                         */
/*==============================================================*/
create table conf_template
(
   conf_template_id               int                            not null AUTO_INCREMENT,
   conf_template_name             varchar(40)                    not null,
   user_id                        varchar(128)                   not null,
   service_template_id            varchar(32),
   virtual_conf_id                varchar(20),
   time_long                      int,
   member_id                      varchar(32)                    not null,
   description                    text,
   password                       varchar(255),
   control_pin                    varchar(255),
   status                         tinyint,
   ports_num                      int,
   subject                        varchar(40),
   create_time                    datetime,
   delete_time                    datetime,
   init_unit                      varchar(200),
   main_unit                      int,
   presider                       varchar(40),
   principal_mobile               varchar(15),
   reserve_code                   varchar(10),
   contact_method                 varchar(200),
   principal                      varchar(40),
   is_default                     decimal(1,0),
   primary key (conf_template_id)
)
comment = "会议表单模板"
type = InnoDB;

/*==============================================================*/
/* Table: conf_template_x_unit                                  */
/*==============================================================*/
create table conf_template_x_unit
(
   conf_template_unit_id          int                            not null AUTO_INCREMENT,
   conf_template_id           int,
   unit_id                    int,
   primary key (conf_template_unit_id)
)
comment = "会议表单模板与参会单位的对应关系"
type = InnoDB;

/*==============================================================*/
/* Table: conf_unit                                             */
/*==============================================================*/
create table conf_unit
(
   conf_unit_id                   int                            not null AUTO_INCREMENT,
   conference_id                  int                            not null,
   unit_id                        int                            not null,
   primary key (conf_unit_id)
)
comment = "会议与参会单位的对应关系"
type = InnoDB;

/*==============================================================*/
/* Table: conference                                            */
/*==============================================================*/
create table conference
(
   conference_id                  int                            not null AUTO_INCREMENT,
   user_id                        varchar(128),
   service_template_id            varchar(32),
   conf_template_id               int,
   rad_conference_id              varchar(20),
   dialable_number                varchar(20),
   virtual_conf_id                varchar(20),
   start_time                     decimal(15,0),
   time_long                      int,
   member_id                      varchar(32),
   description                    text,
   password                       varchar(255),
   control_pin                    varchar(255),
   status                         tinyint                        not null,
   ports_num                      int,
   subject                        varchar(40)                    not null,
   create_time                    datetime                       not null,
   cancel_time                    datetime,
   update_time                    datetime                       not null,
   cancel_reason                  varchar(200),
   init_unit                      varchar(200),
   main_unit                      int,
   presider                       varchar(40),
   principal_mobile               varchar(15),
   reserve_code                   varchar(10),
   contact_method                 varchar(200),
   principal                      varchar(40),
   conf_type			  tinyint			  not null,
   is_broadcast			  tinyint,
   is_support			  tinyint,
   is_record			  tinyint,
   primary key (conference_id)
)
type = InnoDB;

/*==============================================================*/
/* Table: field_desc                                            */
/*==============================================================*/
create table field_desc
(
   table_name                     varchar(20)                    not null,
   field_name                     varchar(20)                    not null,
   field_value                    int                            not null,
   field_desc                     varchar(40)                    not null
)
type = InnoDB;

/*==============================================================*/
/* Table: log                                                   */
/*==============================================================*/
create table log
(
   log_id                         int                            not null AUTO_INCREMENT,
   user_id                        varchar(128),
   log_type                       tinyint                        not null,
   create_time                    datetime                       not null,
   description                    varchar(256),
   primary key (log_id)
)
type = InnoDB;

/*==============================================================*/
/* Table: message_board                                         */
/*==============================================================*/
create table message_board
(
   message_id                     int                            not null AUTO_INCREMENT,
   user_id                        varchar(128),
   title                          varchar(100),
   content                        varchar(512),
   status                         tinyint                        not null,
   create_time                    datetime                       not null,
   primary key (message_id)
)
type = InnoDB;

/*==============================================================*/
/* Table: privilege                                             */
/*==============================================================*/
create table privilege
(
   privilege_id                   int                            not null AUTO_INCREMENT,
   name                           varchar(40)                    not null,
   url                            varchar(400),
   description                    varchar(200),
   code                           varchar(4)                     not null,
   primary key (privilege_id)
)
type = InnoDB;

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   role_id                        int                            not null AUTO_INCREMENT,
   role_name                      varchar(30)                    not null,
   description                    varchar(100),
   status                         tinyint                        not null,
   primary key (role_id)
)
type = InnoDB;

/*==============================================================*/
/* Table: role_privilege                                        */
/*==============================================================*/
create table role_privilege
(
   role_id                        int                            not null,
   privilege_id                   int                            not null,
   primary key (role_id, privilege_id)
)
type = InnoDB;

/*==============================================================*/
/* Table: service_template                                      */
/*==============================================================*/
create table service_template
(
   service_template_id            varchar(32)                    not null,
   service_prefix                 varchar(32),
   service_template_name          varchar(64),
   active_flag                    decimal(1,0),
   audio_only                     decimal(1,0),
   slave                          decimal(1,0),
   matching_rate                  decimal(9,0),
   has_symmetric                  decimal(1,0),
   is_uploaded                    decimal(1,0),
   is_from_device                 decimal(1,0),
   is_uploadable                  decimal(1,0),
   service_template_desc          varchar(255),
   device_string                  varchar(32),
   backup_1                       varchar(32),
   backup_2                       varchar(64),
   lecture_mode_sign              char(1),
   built_in_token                 varchar(32),
   service_type                   decimal(8,0),
   switching_mode                 decimal(8,0),
   is_hd_guaranteed               decimal(8,0),
   is_hd_capable                  decimal(8,0),
   ivr_prefix                     varchar(20),
   primary key (service_template_id)
)
type = InnoDB;

/*==============================================================*/
/* Table: terminal                                              */
/*==============================================================*/
create table terminal
(
   terminalId                     varchar(32)                    not null,
   terminalName                   varchar(60),
   terminalNumber                 varchar(40),
   terminalProtocol               decimal(4,0)                   not null,
   timeZoneId                     varchar(100),
   zonePrefix                     varchar(10),
   terminalEmail                  varchar(128),
   statusId                       decimal(1,0),
   registerGKId                   varchar(32),
   nodeId                         varchar(32),
   maxBandwidth                   decimal(9,0),
   isdnMaxBandwidth               decimal(9,0),
   isVoiceOnly                    decimal(1,0),
   ip                             varchar(20),
   e164                           varchar(50),
   detailProtocol                 decimal(4,0),
   defaultRoomId                  varchar(32),
   countryCode                    varchar(10),
   areaCode                       varchar(10),
   primary key (terminalId)
)
type = InnoDB;

/*==============================================================*/
/* Table: unit                                                  */
/*==============================================================*/
create table unit
(
   unit_id                        int                            not null AUTO_INCREMENT,
   unit_name                      varchar(40)                    not null,
   party_id                       varchar(20),
   description                    varchar(200),
   primary key (unit_id)
)
type = InnoDB;

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id                        varchar(128)                   not null,
   login_id                       varchar(100)                   not null,
   user_type                      tinyint,
   user_name                      varchar(64)                    not null,
   company                        tinyint,
   email                          varchar(40),
   home_telephone                 varchar(20),
   office_telephone               varchar(20),
   mobile                         varchar(15),
   sex                            tinyint                        not null,
   address                        varchar(128),
   description                    varchar(400),
   status                         tinyint                        not null,
   create_time                    datetime                       not null,
   update_time                    datetime                       not null,
   password                       varchar(28),
   default_unit_id                int                            default 0,
   primary key (user_id)
)
type = InnoDB;

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role
(
   role_id                        int                            not null,
   user_id                        varchar(128)                   not null
)
type = InnoDB;
/*==============================================================*/
/* Table: user_unit                                             */
/*==============================================================*/
create table user_unit
(
   user_id                        varchar(128),
   unit_id                        int
)
comment = "用户可选主会场"
type = InnoDB;

/*==============================================================*/
/* Table: recurring_meeting_info                                */
/*==============================================================*/
create table recurring_meeting_info
(
   recurrence_id                  int                            not null AUTO_INCREMENT,
   user_id                        varchar(128),
   service_template_id            varchar(32),
   conf_template_id               int,
   rad_recurrence_id              varchar(20),
   dialable_number                varchar(20),
   virtual_conf_id                varchar(20),
   start_time                     decimal(15,0),
   time_long                      int,
   member_id                      varchar(32),
   description                    text,
   password                       varchar(255),
   control_pin                    varchar(255),
   status                         tinyint                        not null,
   ports_num                      int,
   subject                        varchar(40)                    not null,
   create_time                    datetime                       not null,
   cancel_time                    datetime,
   update_time                    datetime                       not null,
   cancel_reason                  varchar(200),
   init_unit                      varchar(200),
   main_unit                      int,
   presider                       varchar(40),
   principal_mobile               varchar(15),
   reserve_code                   varchar(10),
   contact_method                 varchar(200),
   principal                      varchar(40),
   conf_type                      tinyint                        not null,
   is_broadcast                   tinyint,
   is_support                     tinyint,
   is_record                      tinyint,
   recurrence_type                int,
   day_interval                   int,
   week_interval                  int,
   week_day                       int,
   month_interval                 int,
   month_day                      int,
   start_date                     decimal(15,0),
   end_type                       int,
   end_date                       decimal(15,0),
   end_after_number               int,
   primary key (recurrence_id)
)
type = InnoDB;

/*==============================================================*/
/* Table: recurrence_conf                                       */
/*==============================================================*/
create table recurrence_conf
(
   recurrence_conf_id             int                            not null AUTO_INCREMENT,
   recurrence_id                  int                            not null,
   conference_id                  int                            not null,
   primary key (recurrence_conf_id)
)
comment = "会议与例会的对应关系"
type = InnoDB;

/*==============================================================*/
/* Table: recurrence_unit                                       */
/*==============================================================*/
create table recurrence_unit
(
   recurrence_unit_id             int                            not null AUTO_INCREMENT,
   recurrence_id                  int                            not null,
   unit_id                        int                            not null,
   primary key (recurrence_unit_id)
)
comment = "例会与参会单位的对应关系"
type = InnoDB;

/*==============================================================*/
/* Table: send_message                                          */
/*==============================================================*/
CREATE TABLE send_message (
  msg_id int(11) AUTO_INCREMENT,
  message varchar(140) NOT NULL COMMENT '短消息内容',
  msisdn varchar(15) NOT NULL COMMENT '手机号',
  send_time datetime NOT NULL COMMENT '发送时间',
  com_port int(11) NOT NULL DEFAULT 0 COMMENT '端口号，数值型，设定发送本条信息所使用的端口号，为0或不填时为自动端口号',
  status tinyint NOT NULL COMMENT '发送状态，0-发送成功，1-发送失败',
  error_desc varchar(200) COMMENT '发送失败原因',
  PRIMARY KEY (msg_id)
) ENGINE=InnoDB COMMENT='短信发送表';