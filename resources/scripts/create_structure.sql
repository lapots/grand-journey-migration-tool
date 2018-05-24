create schema if not exists generator;
create table generator.generators (
        part_group_name varchar(128) not null,
        part_name varchar(128) not null,
        part_value varchar(255) not null,
        part_order int not null,
        precede_space bool default false
);
