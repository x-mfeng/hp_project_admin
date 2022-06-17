create table sys_dept
(
    dept_id     bigint auto_increment comment '部门id'
        primary key,
    parent_id   bigint      default 0   null comment '父部门id',
    ancestors   varchar(50) default ''  null comment '祖级列表',
    dept_name   varchar(30) default ''  null comment '部门名称',
    order_num   int         default 0   null comment '显示顺序',
    leader      varchar(20)             null comment '负责人',
    phone       varchar(11)             null comment '联系电话',
    email       varchar(50)             null comment '邮箱',
    status      char        default '1' null comment '部门状态（1正常 0停用）',
    del_flag    char        default '0' null comment '删除标志（0代表存在 1代表删除）',
    create_by   varchar(64) default ''  null comment '创建者',
    create_time datetime                null comment '创建时间',
    update_by   varchar(64) default ''  null comment '更新者',
    update_time datetime                null comment '更新时间'
)
    comment '部门表';

create table sys_role
(
    id          bigint auto_increment
        primary key,
    name        varchar(100)  null comment '名称',
    description varchar(500)  null comment '描述',
    admin_count int           null comment '后台用户数量',
    create_time datetime      null comment '创建时间',
    status      int default 1 null comment '启用状态：0->禁用；1->启用',
    sort        int default 0 null
)
    comment '后台用户角色表' charset = utf8;

create table sys_user
(
    id          bigint auto_increment
        primary key,
    dept_id     bigint           null comment '部门id',
    username    varchar(64)      null,
    password    varchar(64)      null,
    icon        varchar(500)     null comment '头像',
    email       varchar(100)     null comment '邮箱',
    nick_name   varchar(200)     null comment '昵称',
    note        varchar(500)     null comment '备注信息',
    create_time datetime         null comment '创建时间',
    login_time  datetime         null comment '最后登录时间',
    status      char default '1' null comment '帐号启用状态：0->禁用；1->启用'
)
    comment '后台用户表' charset = utf8;

