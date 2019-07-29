create sequence hibernate_sequence start 1 increment 1;

create table articles (
  id  bigserial not null,
  created_at timestamp,
  name varchar(45),
  text varchar(255),
  product_id int8,
  primary key (id)
);

create table categories (
  id  bigserial not null,
  description varchar(255),
  name varchar(45),
  primary key (id)
);

create table comments (
  id  bigserial not null,
  comment_id int8,
  created_at timestamp,
  text varchar(255),
  product_id int8,
  user_id int8,
  primary key (id)
);

create table order_items (
  id  bigserial not null,
  quantity int4 not null,
  order_id int8,
  product_id int8,
  primary key (id)
);

create table orders (
  id  bigserial not null,
  comment varchar(255),
  created_at timestamp,
  user_id int8,
  primary key (id)
);

create table products (
  id  bigserial not null,
  description varchar(255),
  name varchar(45),
  photo varchar(255),
  price int4 not null,
  quantity int4 not null,
  category_id int8,
  primary key (id)
);

create table users (
  id  bigserial not null,
  active boolean not null,
  created_at timestamp,
  email varchar(255),
  first_name varchar(255),
  last_name varchar(255),
  username varchar(45),
  password varchar(255),
  role varchar(255),
  primary key (id)
);

alter table if exists categories add constraint UK_t8o6pivur7nn124jehx7cygw5 unique (name);
alter table if exists articles add constraint FKonic02d01sh9iqfrna4gsl2k7 foreign key (product_id) references products;
alter table if exists comments add constraint FK6uv0qku8gsu6x1r2jkrtqwjtn foreign key (product_id) references products;
alter table if exists comments add constraint FK8omq0tc18jd43bu5tjh6jvraq foreign key (user_id) references users;
alter table if exists order_items add constraint FKbioxgbv59vetrxe0ejfubep1w foreign key (order_id) references orders;
alter table if exists order_items add constraint FKocimc7dtr037rh4ls4l95nlfi foreign key (product_id) references products;
alter table if exists orders add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users;
alter table if exists products add constraint FKog2rp4qthbtt2lfyhfo32lsw9 foreign key (category_id) references categories;