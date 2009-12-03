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
   conf_template_id               int                            not null,
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
comment = "�����ģ��"
type = InnoDB;

/*==============================================================*/
/* Table: conf_template_x_unit                                  */
/*==============================================================*/
create table conf_template_x_unit
(
   conf_template_unit_id          int                            not null AUTO_INCREMENT,
   con_conf_template_id           int,
   uni_unit_id                    int,
   primary key (conf_template_unit_id)
)
comment = "�����ģ����λᵥλ�Ķ�Ӧ��ϵ"
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
comment = "������λᵥλ�Ķ�Ӧ��ϵ"
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
