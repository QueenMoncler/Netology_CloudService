create table IF NOT EXISTS netology.files
(
    filename varchar(100) not null,
    date timestamp(6) not null,
    file_content oid not null,
    size bigint not null,
    user_name varchar(255),
    primary key (filename)
);