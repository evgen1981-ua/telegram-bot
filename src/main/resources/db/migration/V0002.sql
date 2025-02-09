alter table tg_user add primary key (chat_id);

drop table if exists group_sub;
drop table if exists group_x_user;

create table group_sub(
id int,
title varchar(100),
last_article_id int,
primary key(id)
);

create table group_x_user(
group_sub_id int not null,
user_id varchar(100) not null,
foreign key(user_id) references tg_user(chat_id),
foreign key(group_sub_id) references group_sub(id),
unique (user_id,group_sub_id)
);
