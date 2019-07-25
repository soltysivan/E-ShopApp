create sequence hibernate_sequence start 1 increment 1;

create table article (
  id  bigserial not null,
  creation_date timestamp,
  name varchar(45),
  text varchar(255),
  product_id int8,
  primary key (id)
);

create table category (
  id  bigserial not null,
  description varchar(255),
  name varchar(45),
  primary key (id)
);

create table comment (
  id  bigserial not null,
  creation_date timestamp,
  text varchar(255),
  author_comments_id int8,
  primary key (id)
);

create table order_item (
  id  bigserial not null,
  quantity int4 not null,
  order_id int8,
  product_id int8,
  primary key (id)
);

create table ordr (
  id  bigserial not null,
  comment varchar(255),
  creation_date timestamp,
  buyer_id int8,
  primary key (id)
);

create table product (
  id  bigserial not null,
  description varchar(255),
  name varchar(45),
  price int4 not null,
  quantity int4 not null,
  category_id int8,
  primary key (id)
);

create table products_comments (
  product_id int8 not null,
  comment_id int8 not null
);

create table user_role (
  user_id int8 not null,
  roles varchar(255)
);

create table usr (
  id  bigserial not null,
  active boolean not null,
  date timestamp,
  login varchar(45),
  password varchar(255),
  primary key (id)
);

alter table if exists category add constraint UK_46ccwnsi9409t36lurvtyljak unique (name);
alter table if exists article add constraint FKbxy24ht14wxvuhu2fo3w4de3b foreign key (product_id) references product;
alter table if exists comment add constraint FKdbmtnq17mxdwx6m48tdn4pqtf foreign key (author_comments_id) references usr;
alter table if exists order_item add constraint FKursq6n3dgp95xae6u4062xdj foreign key (order_id) references ordr;
alter table if exists order_item add constraint FK551losx9j75ss5d6bfsqvijna foreign key (product_id) references product;
alter table if exists ordr add constraint FKi12xax68htckb12h3b1hsj8i3 foreign key (buyer_id) references usr;
alter table if exists product add constraint FK1mtsbur82frn64de7balymq9s foreign key (category_id) references category;
alter table if exists products_comments add constraint FKdv791wjore0rnip39v1ri5s8i foreign key (comment_id) references comment;
alter table if exists products_comments add constraint FKaplyj3lx8oyhjosbkg13qp67u foreign key (product_id) references product;
alter table if exists user_role add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr;