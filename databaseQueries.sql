create SEQUENCE users_seq
START WITH 1000
INCREMENT BY 1
NOCACHE
NOCYCLE;

create table USER_DETAILS
(
user_id NUMBER(8,0),
first_name VARCHAR2(255) not null,
last_name VARCHAR2(255) not null,
username VARCHAR2(255) not null,
password VARCHAR2(255) not null,
email_Id VARCHAR2(255) not null,
birth_date date not null,
gender char(1) not null,
role VARCHAR2(20) not null,
phone VARCHAR2(255) not null,
profile varchar2(255) not null,
status varchar2(255) default 'PENDING' not null,
is_online NUMBER(1,0) default '0' not null,
enabled NUMBER(1,0) default '1' not null,

CONSTRAINT pk_USER_DETAILS_USER_ID PRIMARY KEY (USER_ID)
);

create SEQUENCE blog_seq
START WITH 1000
INCREMENT BY 1
NOCACHE
NOCYCLE;

create table Blog
(
blog_id NUMBER(8,0),
blog_name VARCHAR2(255) not null,
status VARCHAR2(50) DEFAULT 'PENDING',
create_date DATE default sysDate,
description CLOB not null,
no_of_likes NUMBER(5),
no_of_comments NUMBER(5),
no_of_views NUMBER(5),
user_id NUMBER(8,0),
username VARCHAR2(50) not null,

CONSTRAINT pk_blog_blog_id PRIMARY KEY (blog_id),
CONSTRAINT fk_blog_user_id FOREIGN KEY (user_id) REFERENCES USER_DETAILS (user_id)
);

create SEQUENCE blog_comment_seq
START WITH 1000
INCREMENT BY 1
NOCACHE
NOCYCLE;

create table BLOG_COMMENT
(
blog_cid NUMBER(8,0),
blog_id NUMBER(8,0) NOT NULL,
comment_date DATE default sysDate,
user_id Number(8,0) not null,
user_name VARCHAR(255) not null,
user_profileId varchar2(255) not null,
title varchar2(50) not null,
blog_comment CLOB not null,

CONSTRAINT pk_BLOG_COMMENT_BLOG_CID PRIMARY KEY (BLOG_CID),
CONSTRAINT fk_BLOG_COMMENT_BLOG_ID FOREIGN KEY (BLOG_ID) REFERENCES BLOG (BLOG_ID),
CONSTRAINT fk_BLOG_COMMENT_USER_ID FOREIGN KEY (USER_ID) REFERENCES USER_DETAILS (USER_ID)
);


create SEQUENCE forum_seq
START WITH 1000
INCREMENT BY 1
NOCACHE
NOCYCLE;


create table FORUM
(
forum_id NUMBER(8,0),
forum_name VARCHAR2(255),
description CLOB not null,
status VARCHAR2(50) default 'PENDING' not null,
number_of_posts NUMBER(5),
create_date Date default sysDate,
user_id NUMBER(8,0) not null,
user_name varchar2(255) not null,

CONSTRAINT pk_FORUM_FORUM_ID PRIMARY KEY (FORUM_ID),
CONSTRAINT fk_FORUM_USER_ID FOREIGN KEY (USER_ID) REFERENCES USER_DETAILS (USER_ID)
);

create SEQUENCE forum_post_seq
START WITH 1000
INCREMENT BY 1
NOCACHE
NOCYCLE;

create table FORUM_POST
(
forum_pid NUMBER(8,0),
forum_id NUMBER(8,0) not null,
user_id NUMBER(8,0) not null,
user_name VARCHAR2(255) not null,
user_profileId VARCHAR2(255) not null,
title VARCHAR2(50) not null,
forum_post_content CLOB not null,
post_date DATE default sysDate,

CONSTRAINT pk_FORUM_POST_FORUM_PID PRIMARY KEY (FORUM_PID),
CONSTRAINT fk_FORUM_POST_FORUM_ID FOREIGN KEY (FORUM_ID) REFERENCES FORUM (FORUM_ID),
CONSTRAINT fk_FORUM_POST_USER_ID FOREIGN KEY (USER_ID) REFERENCES USER_DETAILS (USER_ID)
);

create SEQUENCE forum_req_seq
START WITH 1000
INCREMENT BY 1
NOCACHE
NOCYCLE;

create table FORUM_REQUEST
(
request_id NUMBER(8,0),
forum_id NUMBER(8,0) not null,
user_id NUMBER(8,0) not null,
user_name VARCHAR2(255) not null,
status VARCHAR2(50) default 'PENDING' not null,

CONSTRAINT pk_FORUM_REQUEST_REQUEST_ID PRIMARY KEY (REQUEST_ID),
CONSTRAINT fk_FORUM_REQUEST_FORUM_ID FOREIGN KEY (FORUM_ID) REFERENCES FORUM (FORUM_ID),
CONSTRAINT fk_FORUM_REQUEST_USER_ID FOREIGN KEY (USER_ID) REFERENCES USER_DETAILS (USER_ID)
);

create SEQUENCE job_detail_seq
START WITH 1000
INCREMENT BY 1
NOCACHE
NOCYCLE;

create table JOB_DETAIL
(
job_id NUMBER(8,0),
company_name VARCHAR2(100) not null,
sub_title VARCHAR2(255) not null,
about VARCHAR2(1000) not null,
job_profile VARCHAR2(500) not null,
qualification VARCHAR2(500) not null,
contact_info VARCHAR2(1000) not null,
status VARCHAR2(50) default 'PENDING' not null,
post_date DATE default sysDate,
user_id NUMBER(8,0) not null,
user_name VARCHAR(255) not null,

CONSTRAINT pK_JOB_DETAIL_JOB_ID PRIMARY KEY (JOB_ID),
CONSTRAINT fk_JOB_DETAIL_USER_ID FOREIGN KEY (USER_ID) REFERENCES USER_DETAILS(USER_ID)
);


create SEQUENCE job_applied_seq
START WITH 1000
INCREMENT BY 1
NOCACHE
NOCYCLE;

create table job_applied
(
applied_id NUMBER(8,0),
job_id NUMBER(8,0) not null,
user_id NUMBER(8,0) not null,
user_name VARCHAR2(50) not null,
applied_date DATE default SYSDATE,
status VARCHAR2(50) default 'PENDING' not null,

CONSTRAINT pk_JOB_APPLIED_APPLIED_ID PRIMARY KEY(APPLIED_ID),
CONSTRAINT fk_JOB_APPLIED_JOB_ID FOREIGN KEY(JOB_ID) REFERENCES JOB_DETAIL(JOB_ID),
CONSTRAINT fk_JOB_APPLIED_USER_ID FOREIGN KEY(USER_ID) REFERENCES USER_DETAILS(USER_ID)
);

create SEQUENCE events_seq
START WITH 1000
INCREMENT BY 1
NOCACHE
NOCYCLE;

create table Events
(
event_id NUMBER(8,0),
user_id NUMBER(8,0) not null,
user_name VARCHAR2(50) not null,
event_name VARCHAR2(255) not null,
venue VARCHAR2(100) not null,
description CLOB not null,
status VARCHAR2(50) default 'PENDING' not null,
start_date DATE not null,
end_date DATE not null,
post_date DATE default sysDate,

CONSTRAINT  pk_EVENTS_EVENT_ID PRIMARY KEY (EVENT_ID),
CONSTRAINT fk_EVENTS_USER_ID FOREIGN KEY (USER_ID) REFERENCES USER_DETAILS(USER_ID)
);

create SEQUENCE event_join_seq 
START WITH 1000
INCREMENT BY 1
NOCACHE
NOCYCLE;

create table event_joined
(
joined_id NUMBER(8,0),
event_id NUMBER(8,0) not null,
user_id NUMBER(8,0) not null,
user_name VARCHAR2(50) not null,
joined_date DATE default sysDate,
status VARCHAR2(50) default 'PENDING' not null,

CONSTRAINT pk_EVENT_JOINED_JOINED_ID PRIMARY KEY (JOINED_ID),
CONSTRAINT fk_EVENT_JOINED_EVENT_ID FOREIGN KEY (EVENT_ID) REFERENCES EVENTS (EVENT_ID),
CONSTRAINT fk_EVENT_JOINED_USER_ID FOREIGN KEY (USER_ID) REFERENCES USER_DETAILS (USER_ID)
);

create SEQUENCE friend_list_seq
START WITH 1000
INCREMENT BY 1
NOCACHE
NOCYCLE;

create table FRIENDS_LIST
(
id NUMBER(8,0),
initiator_id NUMBER(8,0)not null,
friend_id NUMBER(8,0) not null,
status VARCHAR(50) default 'PENDING' not null,

CONSTRAINT pk_FRIENDS_LIST_ID PRIMARY KEY (ID)
);


