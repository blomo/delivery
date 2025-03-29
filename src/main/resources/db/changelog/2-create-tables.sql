--liquibase formatted sql
--changeset blomo:1 logicalFilePath:2-create-tables.sql

create table courier
(
    id           uuid    not null,
    location_x   integer not null,
    location_y   integer not null,
    name         varchar(255),
    status       varchar(255),
    transport_id uuid,
    primary key (id)
);

create table order_table
(
    id         uuid    not null,
    courier_id uuid,
    location_x integer not null,
    location_y integer not null,
    status     varchar(255),
    primary key (id)
);

create table transport_entity
(
    id    uuid         not null,
    name  varchar(255) not null,
    speed integer      not null,
    primary key (id)
);

alter table if exists courier
    drop constraint if exists courier_transport_id_uk;

alter table if exists courier
    add constraint courier_transport_id_uk unique (transport_id);

alter table if exists courier
    add constraint courier_transport_id_fk foreign key (transport_id) references transport_entity;
