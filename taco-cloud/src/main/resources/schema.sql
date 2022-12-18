create table if not exists Ingredient (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null
);

create table if not exists Taco (
  id identity,
  name varchar(50) not null,
  createdAt timestamp not null
);

create table if not exists Taco_Ingredients (
  taco_id bigint not null,
  ingredients_id varchar(4) not null
);

alter table Taco_Ingredients add foreign key (taco_id) references Taco(id);
alter table Taco_Ingredients add foreign key (ingredients_id) references Ingredient(id);

create table if not exists Orders (
	id identity,
	name varchar(50) not null,
	street varchar(50) not null,
	city varchar(50) not null,
	state varchar(2) not null,
	zip varchar(10) not null,
	ccNumber varchar(16) not null,
	ccExpiration varchar(5) not null,
	ccCVV varchar(3) not null,
    placedAt timestamp not null
);

create table if not exists Orders_Tacos (
	orders_id bigint not null,
	taco_id bigint not null,
	primary key (orders_id, taco_id)
);

alter table Orders_Tacos add foreign key (orders_id) references Orders(id);
alter table Orders_Tacos add foreign key (taco_id) references Taco(id);