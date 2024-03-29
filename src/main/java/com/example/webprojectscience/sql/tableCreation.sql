CREATE TABLE "User" (
    id bigserial primary key,
    login varchar NOT NULL unique,
    password varchar NOT NULL,
    admin boolean default false,
    name varchar not null,
    link varchar not null unique,
    profile_photo_path varchar,
    description varchar
);

CREATE TABLE Token (
    id bigserial primary key,
    value varchar not null unique,
    user_id bigint not null,
    ip_address varchar not null,
    session bool not null default false,

    foreign key (user_id) references "User" (id)
);

CREATE TABLE Theme (
    id bigserial primary key,
    name varchar not null unique,
    picture_path varchar
);

CREATE TABLE Subscription (
    id bigserial primary key,
    user_id bigint not null,
    subscriber_id bigint not null,

    foreign key (user_id) references "User" (id),
    foreign key (subscriber_id) references "User" (id)
);

CREATE TABLE Post (
    id bigserial primary key,
    user_id bigint not null,
    path_in_storage varchar not null,
    theme_id bigint not null,
    tags varchar(30)[],
    date date not null default CURRENT_TIMESTAMP,
    title varchar,
    link varchar,

    foreign key (user_id) references "User" (id),
    foreign key (theme_id) references Theme (id)
);

CREATE TABLE Message (
    id bigserial primary key,
    from_user_id bigint not null,
    to_user_id bigint not null,
    text varchar(300),
    date date not null,
    read boolean default false,

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

    foreign key (user_id) references "User" (id),
    foreign key (post_id) references Post (id)
);

CREATE TABLE Forum_Question (
    id bigserial primary key,
    user_id bigint not null,
    date date not null default CURRENT_TIMESTAMP,
    text varchar not null,
    tags varchar(30)[],
    theme_id bigint not null,
    link varchar not null,
    main_question varchar not null,
    answered_answer_id bigint,

    foreign key (user_id) references "User" (id),
    foreign key (theme_id) references Theme (id),
    foreign key (answered_answer_id) references Forum_Answer (id)
);

CREATE TABLE Forum_Answer (
      id bigserial primary key,
      user_id bigint not null,
      question_id bigint not null,
      text varchar(300) not null,
      date date not null default CURRENT_TIMESTAMP,

      foreign key (user_id) references "User" (id),
      foreign key (question_id) references Forum_Question (id)
)