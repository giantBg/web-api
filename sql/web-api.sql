/*地图数据表*/
DROP TABLE IF EXISTS `map_chart_data`;
CREATE TABLE map_chart_data (
  id bigint(20) unsigned primary key not null auto_increment comment '主键id',
  goods varchar(20) not null comment '商品名称',
  city varchar(12) not null comment '城市',
  count int DEFAULT 0 comment '销量',
  ctime bigint(20) default null comment '创建时间',
  utime bigint(20) default null comment '更新时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地图数据表';

/*商品信息表*/
DROP TABLE IF EXISTS `goods`;
CREATE TABLE goods (
  id bigint(20) unsigned primary key not null auto_increment comment '主键id',
  name varchar(20) not null comment '商品名称',
  price numeric(10,2) not null default 0 comment '商品价格',
  count int default 0 comment '剩余数量',
  image varchar(18) default null comment '商品图标名称',
  goods_url varchar(128) default null comment '商品详情url',
  osk  int default 0 comment '是否下架',
  remark varchar(256) default null comment '商品描述',
  ctime bigint(20) default null comment '创建时间',
  utime bigint(20) default null comment '更新时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';

