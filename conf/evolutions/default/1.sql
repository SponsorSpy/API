# Create Core SponsorSpy objects in DB (Account, UGC, UGC Creator, UGC Sponsor, Tag)
# --- !Ups

create table account (
    account_id serial primary key,
    oauth_id_token varchar(255) UNIQUE NOT NULL,
 -- Account Flags
    security_status varchar(10) DEFAULT 'BASIC',
    login_type varchar(10) DEFAULT 'OAUTH',
 -- Settings
    account_settings jsonb NOT NULL,
 -- Attributes
    account_info jsonb NOT NULL,
 -- Timestamps
    created_at timestamp,
    updated_at timestamp
);

create table user_generated_content (
    ugc_id serial primary key,
    title varchar(255),
    uri varchar(255),
    content_description varchar,
    ugc_creator_id integer references ugc_creator (ugc_creator_id),
    content_aggregator varchar(25) NOT NULL,
);

create table ugc_tag (
    ugc_tag_id serial primary key,
    ugc_id integer references user_generated_content (ugc_id),
    ugc_tag_content varchar,

);

create table ugc_sponsor (
    ugc_sponsor_id serial primary key,
);

create table ugc_creator (
    ugc_creator_id serial primary key,
);

# --- !Downs
drop table account cascade;
drop table user_generated_content cascade;
drop table ugc_tag cascade;
drop table ugc_sponsor cascade;
drop table ugc_creator cascade;
