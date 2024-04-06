use chushi_wiki;

# 书籍表
drop table if exists `books`;
create table `books`(
    `id` int not null auto_increment primary key comment '书籍id',
    `book_name` varchar(255) not null comment '书籍名称',
    `category1_id` int comment '一级分类id',
    `category2_id` int comment '二级分类id',
    `cover` varchar(255) comment '封面',
    `description` varchar(255) comment '描述',
    `doc_count` int comment '文档数量',
    `view_count` int comment '浏览数量',
    `vote_count` int comment '点赞数量',
    `create_time` datetime default current_timestamp comment '创建时间',
    `update_time` datetime default current_timestamp on update current_timestamp comment '更新时间',
    `is_delete` int default 0 not null comment '是否删除 0-未删除 1-已删除'
) engine=InnoDB default charset=utf8mb4 comment '书籍表';


# 分类表
drop table if exists `category`;
create table `category`(
    `id` int not null auto_increment primary key comment '分类id',
    `parent_id` int comment '父分类id',
    `category_name` varchar(255) not null comment '分类名称',
    `sort` int comment '排序',
    `create_time` datetime default current_timestamp comment '创建时间',
    `update_time` datetime default current_timestamp on update current_timestamp comment '更新时间',
    `is_delete` int default 0 not null comment '是否删除 0-未删除 1-已删除'
) engine=InnoDB default charset=utf8mb4 comment '分类表';



# 文档表
drop table if exists `doc`;
create table `doc` (
    `id` int not null auto_increment primary key comment '文档id',
    `book_id` int not null default 0 comment '书籍id',
    `parent_id` int not null default 0 comment '父id',
    `doc_name` varchar(255) not null comment '文档名称',
    `sort` int comment '排序',
    `view_count` int default 0 comment '浏览量',
    `vote_count` int default 0 comment '点赞数',
    `create_time` datetime default current_timestamp comment '创建时间',
    `update_time` datetime default current_timestamp on update current_timestamp comment '更新时间',
    `is_delete` int default 0 not null comment '是否删除 0-未删除 1-已删除'
) engine=InnoDB default charset=utf8mb4 comment '文档表';


# 文档内容表
drop table if exists `content`;
create table `content`(
    `id` int not null primary key comment '内容id',
    `content` mediumtext comment '内容',
    `create_time` datetime default current_timestamp comment '创建时间',
    `update_time` datetime default current_timestamp on update current_timestamp comment '更新时间',
    `is_delete` int default 0 not null comment '是否删除 0-未删除 1-已删除'
) engine=InnoDB default charset=utf8mb4 comment '文档内容表';
