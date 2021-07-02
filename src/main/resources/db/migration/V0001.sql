create table associate (id bigint not null auto_increment, cpf varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB
create table schedule (id bigint not null auto_increment, created_at datetime, title varchar(255) not null, primary key (id)) engine=InnoDB;
create table session_vote (id bigint not null auto_increment, created_at datetime, finish_at datetime, status_session varchar(255), schedule_id bigint, primary key (id)) engine=InnoDB;
create table vote (id bigint not null auto_increment, created_at datetime, vote varchar(255), associate_id bigint, schedule_id bigint, session_vote_id bigint, primary key (id)) engine=InnoDB;
alter table associate drop index UK_3dbmmbrmnk0rwhqff0oxaxlc6
alter table associate add constraint UK_3dbmmbrmnk0rwhqff0oxaxlc6 unique (cpf)
alter table schedule drop index UK_f9ef217udm40g1p2jggk7c1mo
alter table schedule add constraint UK_f9ef217udm40g1p2jggk7c1mo unique (title)
alter table vote drop index UKkr2arfslhh3nv2weiprn25hk1
alter table vote add constraint UKkr2arfslhh3nv2weiprn25hk1 unique (associate_id, schedule_id)
alter table session_vote add constraint FKmx19n0e8f6uc0obdyocoxeqm1 foreign key (schedule_id) references schedule (id)
alter table vote add constraint FK5ki8q5kgx4bmjd1jh473hqbra foreign key (associate_id) references associate (id)
alter table vote add constraint FKd935nmdmb0pasvgvrshdl21ek foreign key (schedule_id) references schedule (id)
alter table vote add constraint FK1f6wic8r88bdpv0ka47vdrwah foreign key (session_vote_id) references session_vote (id)