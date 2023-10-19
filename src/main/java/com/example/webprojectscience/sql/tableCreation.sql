CREATE TABLE "User" (
    id bigserial primary key,
    login varchar NOT NULL,
    password varchar NOT NULL,
    admin boolean default false,
    name varchar not null,
    link varchar not null,
    profile_photo_path varchar,
    description varchar
);

CREATE TABLE Token (
    id bigserial primary key,
    value varchar not null,
    user_id bigint not null,
    ip_address varchar not null,

    foreign key (user_id) references "User" (id)
);

CREATE TABLE Theme (
    id bigserial primary key,
    name varchar not null,
    picture_path varchar
);

CREATE TABLE Subscription (
    id bigserial primary key,
    user_id bigint not null,
    subscribed_to_user_id bigint not null,

    foreign key (user_id) references "User" (id),
    foreign key (subscribed_to_user_id) references "User" (id)
);

CREATE TABLE Post (
    id bigserial primary key,
    user_id bigint not null,
    path_in_storage varchar not null,
    theme_id bigint not null,
    tags varchar(10)[],

    foreign key (user_id) references "User" (id),
    foreign key (theme_id) references Theme (id)
);

CREATE TABLE Message (
    id bigserial primary key,
    from_user_id bigint not null,
    to_user_id bigint not null,
    text varchar(300),
    date date not null,

    foreign key (from_user_id) references "User" (id),
    foreign key (to_user_id)references  "User" (id)
);

CREATE TABLE "Like" (
    id bigserial primary key,
    user_id bigint not null,
    post_id bigint not null,

    foreign key (user_id) references "User" (id),
    foreign key (post_id) references Post (id)
);

CREATE TABLE Friend (
    id bigserial primary key,
    first_user_id bigint not null,
    second_user_id bigint not null,

    foreign key (first_user_id) references "User" (id),
    foreign key (second_user_id) references "User" (id)
);

CREATE TABLE Comment (
    id bigserial primary key,
    user_id bigint not null,
    post_id bigint not null,
    text varchar(300) not null,
    date date not null,
    answered bigint,

    foreign key (user_id) references "User" (id),
    foreign key (post_id) references Post (id),
    foreign key (answered) references Comment (id)
);