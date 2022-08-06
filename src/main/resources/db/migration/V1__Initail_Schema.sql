create table app_user
(
    id            bigint auto_increment
        primary key,
    active        bit          not null,
    created_at    datetime     null,
    custom_field1 varchar(255) null,
    custom_field2 varchar(255) null,
    custom_field3 varchar(255) null,
    email         varchar(255) null,
    first_name    varchar(255) null,
    last_name     varchar(255) null,
    password      varchar(255) null,
    phone         varchar(255) not null,
    updated_at    datetime     null,
    constraint UK_exslcon9jmfy0xhclbtpf26vo
        unique (phone)
);

create table app_user_roles
(
    app_user_id bigint not null,
    roles       int    null,
    FOREIGN KEY (app_user_id) REFERENCES app_user (id)
);

create table expense_category
(
    id            bigint auto_increment
        primary key,
    active        bit          not null,
    created_at    datetime     null,
    custom_field1 varchar(255) null,
    custom_field2 varchar(255) null,
    custom_field3 varchar(255) null,
    description   varchar(255) null,
    name          varchar(255) null,
    updated_at    datetime     null
);

INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Housing', null);
INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Transportation', null);
INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Food', null);
INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Utilities', null);
INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Clothing', null);
INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Medical/Healthcare', null);
INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Insurance', null);
INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Household Items/Supplies', null);
INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Personal', null);
INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Debt', null);
INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Retirement', null);
INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Education', null);
INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Savings', null);
INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Gifts/Donations', null);
INSERT INTO expense_category (active, created_at, custom_field1, custom_field2, custom_field3, description, name,
                              updated_at)
VALUES (true, '2022-06-09 17:33:35', null, null, null, null, 'Entertainment ', null);

create table user_expense_category
(
    id                         bigint auto_increment
        primary key,
    active                     bit          not null,
    created_at                 datetime     null,
    custom_field3              varchar(255) null,
    description                varchar(255) null,
    name                       varchar(255) null,
    type                       int          null,
    updated_at                 datetime     null,
    parent_expense_category_id bigint       null,
    user_id                    bigint       null,
    FOREIGN KEY (parent_expense_category_id) REFERENCES expense_category (id),
    FOREIGN KEY (user_id) REFERENCES app_user (id)
);

create table user_profile
(
    id      bigint auto_increment
        primary key,
    user_id bigint null,
    FOREIGN KEY (user_id) REFERENCES app_user (id)
);

create table user_profile_user_expense_categories
(
    user_profile_id            bigint not null,
    user_expense_categories_id bigint not null,
    UNIQUE KEY (user_expense_categories_id),
    FOREIGN KEY (user_expense_categories_id) REFERENCES user_expense_category (id),
    FOREIGN KEY (user_profile_id) REFERENCES user_profile (id)
);

create table user_balance_sheet
(
    id                       bigint auto_increment
        primary key,
    amount                   decimal(19, 2) null,
    created_at               datetime       null,
    user_id                  bigint         null,
    user_expense_category_id bigint         null,
    FOREIGN KEY (user_expense_category_id) REFERENCES user_expense_category (id),
    FOREIGN KEY (user_id) REFERENCES app_user (id)
);
