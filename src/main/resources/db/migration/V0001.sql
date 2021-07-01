create table associado (id bigint not null auto_increment, cpf varchar(255), nome varchar(255), primary key (id)) engine=InnoDB;
create table pauta (id bigint not null auto_increment, data_criacao datetime, nome_pauta varchar(255), primary key (id)) engine=InnoDB;
create table sessao_votacao (id bigint not null auto_increment, fim_sessao datetime, primary key (id)) engine=InnoDB;
alter table pauta drop index UK_rr5ejdhj9i47wt4wer2ksq14s;
alter table pauta add constraint UK_rr5ejdhj9i47wt4wer2ksq14s unique (nome_pauta);

alter table sessao_votacao add column data_criacao datetime;
alter table sessao_votacao add column pauta_id bigint;
alter table sessao_votacao add constraint FKf74f8sm5id28fb93vh3eew3ff foreign key (pauta_id) references pauta (id);