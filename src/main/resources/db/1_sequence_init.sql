create table user_tbl
(
    ID            VARCHAR2(36 char) not null primary key,
    NAME          VARCHAR2(36 char) not null,
    LAST_NAME     VARCHAR2(36 char) not null,
    EMAIL         VARCHAR2(36 char) not null,
    PASSWORD      VARCHAR2(36 char) not null,
    ADDRESS       VARCHAR2(200 char) not null,
    IS_ACTIVE     NUMBER(1),
    NATIONAL_CODE VARCHAR2(200 char) not null,
    EXTERNAL_ID   VARCHAR2(36 char) not null
)