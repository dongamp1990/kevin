create table customer_info(
	id int(11) primary key AUTO_INCREMENT,
	qt_name varchar(100) comment 'QT名',
	area varchar(100) comment '地区',
	qq_number varchar(100) comment 'QQ号',
	level VARCHAR(50) comment '级别',
	level_code int(11) comment '级别名称',
	phone_number VARCHAR(50) comment '手机号码',
	illegality_record varchar(500) comment '违规记录',
	illegality_punish VARCHAR(500) comment '违规处罚',
	create_date TIMESTAMP
);

create table user(
	user_id int(11) primary key AUTO_INCREMENT,
	login_name varchar(50) UNIQUE,
	user_name varchar(50) UNIQUE,
	password varchar(200),
	create_date TIMESTAMP
);

create table level_code(
	code_id int(11) primary key AUTO_INCREMENT,
	code_name varchar(50) UNIQUE
);

INSERT INTO `qt`.`level_code` (`code_id`, `code_name`) VALUES ('1', '下马甲');
INSERT INTO `qt`.`level_code` (`code_id`, `code_name`) VALUES ('2', '绿马');
INSERT INTO `qt`.`level_code` (`code_id`, `code_name`) VALUES ('3', '蓝马');
INSERT INTO `qt`.`level_code` (`code_id`, `code_name`) VALUES ('4', '红马');
INSERT INTO `qt`.`level_code` (`code_id`, `code_name`) VALUES ('5', '皇马');
