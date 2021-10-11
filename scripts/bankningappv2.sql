PGDMP     3    8            	    y            parrot    11.5    12.1 f    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    45988    parrot    DATABASE     �   CREATE DATABASE parrot WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE parrot;
                postgres    false            �           0    0    DATABASE parrot    COMMENT     G   COMMENT ON DATABASE parrot IS 'Register and Manager User Invitations';
                   postgres    false    3002                        2615    45989    default    SCHEMA        CREATE SCHEMA "default";
    DROP SCHEMA "default";
                postgres    false            �            1259    46063    account    TABLE     ,  CREATE TABLE "default".account (
    id bigint NOT NULL,
    owner bigint NOT NULL,
    name character varying NOT NULL,
    created_on timestamp without time zone,
    updated_on timestamp without time zone,
    author_id bigint NOT NULL,
    updated_by bigint,
    external_id character varying
);
    DROP TABLE "default".account;
       default            postgres    false    6            �            1259    70564    account_id_seq    SEQUENCE     �   ALTER TABLE "default".account ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "default".account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            default          postgres    false    207    6            �            1259    54201    departments    TABLE     �   CREATE TABLE "default".departments (
    id bigint NOT NULL,
    name character varying NOT NULL,
    code character varying NOT NULL,
    office_id bigint NOT NULL,
    parent_department bigint
);
 "   DROP TABLE "default".departments;
       default            postgres    false    6            �            1259    54217    employee_station    TABLE     �   CREATE TABLE "default".employee_station (
    id bigint NOT NULL,
    employee_id bigint NOT NULL,
    office_id bigint NOT NULL,
    station_id bigint
);
 '   DROP TABLE "default".employee_station;
       default            postgres    false    6            �            1259    54209 	   employees    TABLE       CREATE TABLE "default".employees (
    id bigint NOT NULL,
    names character varying,
    gender character varying,
    created_on timestamp without time zone,
    updated_on timestamp without time zone,
    author_id bigint,
    updated_by bigint,
    account_id bigint
);
     DROP TABLE "default".employees;
       default            postgres    false    6            �            1259    168912    guest    TABLE     �   CREATE TABLE "default".guest (
    id bigint NOT NULL,
    "profile_-id" bigint,
    author_id bigint,
    created_on timestamp without time zone,
    updated_by bigint,
    date_updated timestamp without time zone
);
    DROP TABLE "default".guest;
       default            postgres    false    6            �            1259    54180    kiosks    TABLE     e  CREATE TABLE "default".kiosks (
    id bigint NOT NULL,
    reference character varying NOT NULL,
    details character varying,
    device_token character varying,
    created_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_on timestamp without time zone,
    author_id bigint,
    udated_by bigint,
    station_id bigint
);
    DROP TABLE "default".kiosks;
       default            postgres    false    6            �            1259    78758    kiosks_id_seq    SEQUENCE     �   ALTER TABLE "default".kiosks ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME "default".kiosks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            default          postgres    false    6    209            �            1259    54196    office_station    TABLE     x   CREATE TABLE "default".office_station (
    id bigint NOT NULL,
    office_id bigint NOT NULL,
    station_id bigint
);
 %   DROP TABLE "default".office_station;
       default            postgres    false    6            �            1259    54188    offices    TABLE     F  CREATE TABLE "default".offices (
    id bigint NOT NULL,
    name character varying NOT NULL,
    dateopened date NOT NULL,
    parent_office bigint,
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp without time zone,
    author_id bigint,
    updated_by bigint,
    account_id bigint NOT NULL
);
    DROP TABLE "default".offices;
       default            postgres    false    6            �            1259    111534    organisations    TABLE       CREATE TABLE "default".organisations (
    id bigint NOT NULL,
    name character varying(255),
    owner bigint,
    date_created timestamp with time zone,
    author_id bigint,
    date_updated timestamp with time zone,
    updated_by bigint,
    details character varying(500)
);
 $   DROP TABLE "default".organisations;
       default            postgres    false    6            �           0    0    TABLE organisations    COMMENT     D   COMMENT ON TABLE "default".organisations IS 'Manage Organisations';
          default          postgres    false    221            �            1259    111537    organisations_id_seq    SEQUENCE     �   ALTER TABLE "default".organisations ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME "default".organisations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            default          postgres    false    221    6            �            1259    46050    permission_scope    TABLE     |   CREATE TABLE "default".permission_scope (
    id bigint NOT NULL,
    key character varying,
    value character varying
);
 '   DROP TABLE "default".permission_scope;
       default            postgres    false    6            �            1259    46027    permissions    TABLE     �   CREATE TABLE "default".permissions (
    id bigint NOT NULL,
    code character varying,
    name character varying,
    "grouping" character varying
);
 "   DROP TABLE "default".permissions;
       default            postgres    false    6            �            1259    46025    permissions_id_seq    SEQUENCE     �   ALTER TABLE "default".permissions ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME "default".permissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            default          postgres    false    6    204            �            1259    62373    profile    TABLE     ^  CREATE TABLE "default".profile (
    id bigint NOT NULL,
    user_id bigint,
    surname character varying,
    other_names character varying,
    author_id bigint,
    created_on time without time zone NOT NULL,
    updated_by bigint,
    date_updated timestamp without time zone,
    gender character varying,
    profile_type character varying
);
    DROP TABLE "default".profile;
       default            postgres    false    6            �            1259    152528    profile_id_seq    SEQUENCE     �   ALTER TABLE "default".profile ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME "default".profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            default          postgres    false    6    215            �            1259    136144    resident    TABLE       CREATE TABLE "default".resident (
    id bigint NOT NULL,
    profile_id bigint,
    station_id bigint,
    join_date date,
    author_id bigint,
    created_on timestamp without time zone,
    updated_by bigint,
    date_updated timestamp without time zone
);
    DROP TABLE "default".resident;
       default            postgres    false    6            �            1259    144336    resident_id_seq    SEQUENCE     �   ALTER TABLE "default".resident ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "default".resident_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE
);
            default          postgres    false    223    6            �            1259    46000    role    TABLE     S  CREATE TABLE "default".role (
    id bigint NOT NULL,
    code character varying(50) NOT NULL,
    name character varying(200) NOT NULL,
    is_system boolean NOT NULL,
    created_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_on timestamp without time zone,
    author_id bigint,
    updated_by bigint
);
    DROP TABLE "default".role;
       default            postgres    false    6            �            1259    45998    role_id_seq    SEQUENCE     �   ALTER TABLE "default".role ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "default".role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            default          postgres    false    199    6            �            1259    46035    role_permission    TABLE     &  CREATE TABLE "default".role_permission (
    id bigint NOT NULL,
    role_id bigint NOT NULL,
    permission_id bigint NOT NULL,
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp without time zone,
    author_id bigint,
    updated_by bigint,
    scope_id bigint
);
 &   DROP TABLE "default".role_permission;
       default            postgres    false    6            �            1259    46086    stations    TABLE     �   CREATE TABLE "default".stations (
    id bigint NOT NULL,
    organisation_id bigint NOT NULL,
    name character varying,
    code character varying
);
    DROP TABLE "default".stations;
       default            postgres    false    6            �            1259    70566    stations_id_seq    SEQUENCE     �   ALTER TABLE "default".stations ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME "default".stations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            default          postgres    false    208    6            �            1259    103334    user    TABLE     z   CREATE TABLE "default"."user" (
    id bigint NOT NULL,
    username character varying,
    password character varying
);
    DROP TABLE "default"."user";
       default            postgres    false    6            �            1259    103342    user_id_seq    SEQUENCE     �   ALTER TABLE "default"."user" ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME "default".user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            default          postgres    false    6    219            �            1259    46010 	   user_role    TABLE     '  CREATE TABLE "default".user_role (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    status character varying(50) NOT NULL,
    author_id bigint,
    created_on timestamp without time zone,
    updated_on timestamp without time zone,
    updated_by bigint
);
     DROP TABLE "default".user_role;
       default            postgres    false    6            �            1259    46008    user_role_id_seq    SEQUENCE     �   ALTER TABLE "default".user_role ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME "default".user_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            default          postgres    false    202    6            �            1259    45990    users    TABLE     =  CREATE TABLE "default".users (
    id bigint NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    author_id bigint,
    created_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_on timestamp without time zone,
    updated_by bigint
);
    DROP TABLE "default".users;
       default            postgres    false    6            �           0    0    TABLE users    COMMENT     4   COMMENT ON TABLE "default".users IS 'Manage Users';
          default          postgres    false    197            �            1259    46006    users_id_seq    SEQUENCE     �   ALTER TABLE "default".users ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "default".users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            default          postgres    false    6    197            �          0    46063    account 
   TABLE DATA           q   COPY "default".account (id, owner, name, created_on, updated_on, author_id, updated_by, external_id) FROM stdin;
    default          postgres    false    207   l}       �          0    54201    departments 
   TABLE DATA           V   COPY "default".departments (id, name, code, office_id, parent_department) FROM stdin;
    default          postgres    false    212   �}       �          0    54217    employee_station 
   TABLE DATA           U   COPY "default".employee_station (id, employee_id, office_id, station_id) FROM stdin;
    default          postgres    false    214   �}       �          0    54209 	   employees 
   TABLE DATA           t   COPY "default".employees (id, names, gender, created_on, updated_on, author_id, updated_by, account_id) FROM stdin;
    default          postgres    false    213   �}       �          0    168912    guest 
   TABLE DATA           f   COPY "default".guest (id, "profile_-id", author_id, created_on, updated_by, date_updated) FROM stdin;
    default          postgres    false    226    ~       �          0    54180    kiosks 
   TABLE DATA           �   COPY "default".kiosks (id, reference, details, device_token, created_on, updated_on, author_id, udated_by, station_id) FROM stdin;
    default          postgres    false    209   ~       �          0    54196    office_station 
   TABLE DATA           F   COPY "default".office_station (id, office_id, station_id) FROM stdin;
    default          postgres    false    211          �          0    54188    offices 
   TABLE DATA           �   COPY "default".offices (id, name, dateopened, parent_office, created_on, updated_on, author_id, updated_by, account_id) FROM stdin;
    default          postgres    false    210   !       �          0    111534    organisations 
   TABLE DATA           w   COPY "default".organisations (id, name, owner, date_created, author_id, date_updated, updated_by, details) FROM stdin;
    default          postgres    false    221   >       �          0    46050    permission_scope 
   TABLE DATA           =   COPY "default".permission_scope (id, key, value) FROM stdin;
    default          postgres    false    206   �       �          0    46027    permissions 
   TABLE DATA           D   COPY "default".permissions (id, code, name, "grouping") FROM stdin;
    default          postgres    false    204   1�       �          0    62373    profile 
   TABLE DATA           �   COPY "default".profile (id, user_id, surname, other_names, author_id, created_on, updated_by, date_updated, gender, profile_type) FROM stdin;
    default          postgres    false    215   N�       �          0    136144    resident 
   TABLE DATA           }   COPY "default".resident (id, profile_id, station_id, join_date, author_id, created_on, updated_by, date_updated) FROM stdin;
    default          postgres    false    223   �       �          0    46000    role 
   TABLE DATA           k   COPY "default".role (id, code, name, is_system, created_on, updated_on, author_id, updated_by) FROM stdin;
    default          postgres    false    199   ��       �          0    46035    role_permission 
   TABLE DATA           �   COPY "default".role_permission (id, role_id, permission_id, created_on, updated_on, author_id, updated_by, scope_id) FROM stdin;
    default          postgres    false    205   L�       �          0    46086    stations 
   TABLE DATA           F   COPY "default".stations (id, organisation_id, name, code) FROM stdin;
    default          postgres    false    208   i�       �          0    103334    user 
   TABLE DATA           ;   COPY "default"."user" (id, username, password) FROM stdin;
    default          postgres    false    219   ��       �          0    46010 	   user_role 
   TABLE DATA           s   COPY "default".user_role (id, user_id, role_id, status, author_id, created_on, updated_on, updated_by) FROM stdin;
    default          postgres    false    202   �       �          0    45990    users 
   TABLE DATA           i   COPY "default".users (id, username, password, author_id, created_on, updated_on, updated_by) FROM stdin;
    default          postgres    false    197   �       �           0    0    account_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('"default".account_id_seq', 2, true);
          default          postgres    false    216            �           0    0    kiosks_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('"default".kiosks_id_seq', 6, true);
          default          postgres    false    218            �           0    0    organisations_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('"default".organisations_id_seq', 35, true);
          default          postgres    false    222            �           0    0    permissions_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('"default".permissions_id_seq', 1, false);
          default          postgres    false    203            �           0    0    profile_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('"default".profile_id_seq', 24, true);
          default          postgres    false    225            �           0    0    resident_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('"default".resident_id_seq', 14, true);
          default          postgres    false    224            �           0    0    role_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('"default".role_id_seq', 2, true);
          default          postgres    false    198            �           0    0    stations_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('"default".stations_id_seq', 2, true);
          default          postgres    false    217            �           0    0    user_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('"default".user_id_seq', 2, true);
          default          postgres    false    220            �           0    0    user_role_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('"default".user_role_id_seq', 4, true);
          default          postgres    false    201            �           0    0    users_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('"default".users_id_seq', 24, true);
          default          postgres    false    200            �
           2606    46070    account account_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY "default".account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);
 A   ALTER TABLE ONLY "default".account DROP CONSTRAINT account_pkey;
       default            postgres    false    207                       2606    136148    resident big integer 
   CONSTRAINT     d   ALTER TABLE ONLY "default".resident
    ADD CONSTRAINT "big integer" PRIMARY KEY (id) INCLUDE (id);
 C   ALTER TABLE ONLY "default".resident DROP CONSTRAINT "big integer";
       default            postgres    false    223    223                        2606    54208    departments departments_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY "default".departments
    ADD CONSTRAINT departments_pkey PRIMARY KEY (id);
 I   ALTER TABLE ONLY "default".departments DROP CONSTRAINT departments_pkey;
       default            postgres    false    212                       2606    54221 &   employee_station employee_station_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY "default".employee_station
    ADD CONSTRAINT employee_station_pkey PRIMARY KEY (id);
 S   ALTER TABLE ONLY "default".employee_station DROP CONSTRAINT employee_station_pkey;
       default            postgres    false    214                       2606    54216    employees employees_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY "default".employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (id);
 E   ALTER TABLE ONLY "default".employees DROP CONSTRAINT employees_pkey;
       default            postgres    false    213                       2606    168916    guest guest_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY "default".guest
    ADD CONSTRAINT guest_pkey PRIMARY KEY (id);
 =   ALTER TABLE ONLY "default".guest DROP CONSTRAINT guest_pkey;
       default            postgres    false    226            
           2606    111543    organisations id 
   CONSTRAINT     Q   ALTER TABLE ONLY "default".organisations
    ADD CONSTRAINT id PRIMARY KEY (id);
 =   ALTER TABLE ONLY "default".organisations DROP CONSTRAINT id;
       default            postgres    false    221            �
           2606    54187    kiosks kiosks_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY "default".kiosks
    ADD CONSTRAINT kiosks_pkey PRIMARY KEY (id);
 ?   ALTER TABLE ONLY "default".kiosks DROP CONSTRAINT kiosks_pkey;
       default            postgres    false    209            �
           2606    54200 "   office_station office_station_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY "default".office_station
    ADD CONSTRAINT office_station_pkey PRIMARY KEY (id);
 O   ALTER TABLE ONLY "default".office_station DROP CONSTRAINT office_station_pkey;
       default            postgres    false    211            �
           2606    54195    offices offices_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY "default".offices
    ADD CONSTRAINT offices_pkey PRIMARY KEY (id);
 A   ALTER TABLE ONLY "default".offices DROP CONSTRAINT offices_pkey;
       default            postgres    false    210                       2606    103338 
   user p_key 
   CONSTRAINT     M   ALTER TABLE ONLY "default"."user"
    ADD CONSTRAINT p_key PRIMARY KEY (id);
 9   ALTER TABLE ONLY "default"."user" DROP CONSTRAINT p_key;
       default            postgres    false    219            �
           2606    46057 &   permission_scope permission_scope_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY "default".permission_scope
    ADD CONSTRAINT permission_scope_pkey PRIMARY KEY (id);
 S   ALTER TABLE ONLY "default".permission_scope DROP CONSTRAINT permission_scope_pkey;
       default            postgres    false    206            �
           2606    46034    permissions permissions_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY "default".permissions
    ADD CONSTRAINT permissions_pkey PRIMARY KEY (id);
 I   ALTER TABLE ONLY "default".permissions DROP CONSTRAINT permissions_pkey;
       default            postgres    false    204                       2606    62380    profile profile_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY "default".profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (id);
 A   ALTER TABLE ONLY "default".profile DROP CONSTRAINT profile_pkey;
       default            postgres    false    215            �
           2606    46039 $   role_permission role_permission_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY "default".role_permission
    ADD CONSTRAINT role_permission_pkey PRIMARY KEY (id);
 Q   ALTER TABLE ONLY "default".role_permission DROP CONSTRAINT role_permission_pkey;
       default            postgres    false    205            �
           2606    46005    role role_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY "default".role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 ;   ALTER TABLE ONLY "default".role DROP CONSTRAINT role_pkey;
       default            postgres    false    199            �
           2606    46093    stations stations_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY "default".stations
    ADD CONSTRAINT stations_pkey PRIMARY KEY (id);
 C   ALTER TABLE ONLY "default".stations DROP CONSTRAINT stations_pkey;
       default            postgres    false    208            �
           2606    46014    user_role user_role_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY "default".user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (id);
 E   ALTER TABLE ONLY "default".user_role DROP CONSTRAINT user_role_pkey;
       default            postgres    false    202            �
           2606    45997    users users_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY "default".users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 =   ALTER TABLE ONLY "default".users DROP CONSTRAINT users_pkey;
       default            postgres    false    197                       2606    160720    resident PROF    FK CONSTRAINT     �   ALTER TABLE ONLY "default".resident
    ADD CONSTRAINT "PROF" FOREIGN KEY (profile_id) REFERENCES "default".profile(id) NOT VALID;
 <   ALTER TABLE ONLY "default".resident DROP CONSTRAINT "PROF";
       default          postgres    false    2822    223    215                       2606    86950    offices account    FK CONSTRAINT     �   ALTER TABLE ONLY "default".offices
    ADD CONSTRAINT account FOREIGN KEY (account_id) REFERENCES "default".account(id) NOT VALID;
 <   ALTER TABLE ONLY "default".offices DROP CONSTRAINT account;
       default          postgres    false    210    207    2806                       2606    95152    employees account    FK CONSTRAINT     �   ALTER TABLE ONLY "default".employees
    ADD CONSTRAINT account FOREIGN KEY (account_id) REFERENCES "default".account(id) NOT VALID;
 >   ALTER TABLE ONLY "default".employees DROP CONSTRAINT account;
       default          postgres    false    2806    207    213                       2606    46076    account author_id    FK CONSTRAINT     �   ALTER TABLE ONLY "default".account
    ADD CONSTRAINT author_id FOREIGN KEY (author_id) REFERENCES "default".users(id) NOT VALID;
 >   ALTER TABLE ONLY "default".account DROP CONSTRAINT author_id;
       default          postgres    false    207    2794    197                       2606    95147    office_station office    FK CONSTRAINT     �   ALTER TABLE ONLY "default".office_station
    ADD CONSTRAINT office FOREIGN KEY (office_id) REFERENCES "default".offices(id) NOT VALID;
 B   ALTER TABLE ONLY "default".office_station DROP CONSTRAINT office;
       default          postgres    false    210    211    2812                       2606    127947    stations organisation_id    FK CONSTRAINT     �   ALTER TABLE ONLY "default".stations
    ADD CONSTRAINT organisation_id FOREIGN KEY (organisation_id) REFERENCES "default".organisations(id) NOT VALID;
 E   ALTER TABLE ONLY "default".stations DROP CONSTRAINT organisation_id;
       default          postgres    false    208    2826    221                       2606    46071    account owner    FK CONSTRAINT     p   ALTER TABLE ONLY "default".account
    ADD CONSTRAINT owner FOREIGN KEY (owner) REFERENCES "default".users(id);
 :   ALTER TABLE ONLY "default".account DROP CONSTRAINT owner;
       default          postgres    false    207    2794    197                       2606    46045    role_permission permission_id    FK CONSTRAINT     �   ALTER TABLE ONLY "default".role_permission
    ADD CONSTRAINT permission_id FOREIGN KEY (permission_id) REFERENCES "default".permissions(id) NOT VALID;
 J   ALTER TABLE ONLY "default".role_permission DROP CONSTRAINT permission_id;
       default          postgres    false    2800    205    204                       2606    46020    user_role role    FK CONSTRAINT     |   ALTER TABLE ONLY "default".user_role
    ADD CONSTRAINT role FOREIGN KEY (role_id) REFERENCES "default".role(id) NOT VALID;
 ;   ALTER TABLE ONLY "default".user_role DROP CONSTRAINT role;
       default          postgres    false    199    202    2796                       2606    46040    role_permission role_id    FK CONSTRAINT     �   ALTER TABLE ONLY "default".role_permission
    ADD CONSTRAINT role_id FOREIGN KEY (role_id) REFERENCES "default".role(id) NOT VALID;
 D   ALTER TABLE ONLY "default".role_permission DROP CONSTRAINT role_id;
       default          postgres    false    205    2796    199                       2606    46058    role_permission scope_id    FK CONSTRAINT     �   ALTER TABLE ONLY "default".role_permission
    ADD CONSTRAINT scope_id FOREIGN KEY (scope_id) REFERENCES "default".permission_scope(id) NOT VALID;
 E   ALTER TABLE ONLY "default".role_permission DROP CONSTRAINT scope_id;
       default          postgres    false    2804    205    206                       2606    95142    office_station station    FK CONSTRAINT     �   ALTER TABLE ONLY "default".office_station
    ADD CONSTRAINT station FOREIGN KEY (station_id) REFERENCES "default".stations(id) NOT VALID;
 C   ALTER TABLE ONLY "default".office_station DROP CONSTRAINT station;
       default          postgres    false    208    211    2808                       2606    46081    account updated_by    FK CONSTRAINT     �   ALTER TABLE ONLY "default".account
    ADD CONSTRAINT updated_by FOREIGN KEY (updated_by) REFERENCES "default".users(id) NOT VALID;
 ?   ALTER TABLE ONLY "default".account DROP CONSTRAINT updated_by;
       default          postgres    false    2794    197    207                       2606    46015    user_role user    FK CONSTRAINT        ALTER TABLE ONLY "default".user_role
    ADD CONSTRAINT "user" FOREIGN KEY (user_id) REFERENCES "default".users(id) NOT VALID;
 =   ALTER TABLE ONLY "default".user_role DROP CONSTRAINT "user";
       default          postgres    false    202    2794    197                       2606    127952    profile user_id    FK CONSTRAINT     ~   ALTER TABLE ONLY "default".profile
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES "default".users(id) NOT VALID;
 <   ALTER TABLE ONLY "default".profile DROP CONSTRAINT user_id;
       default          postgres    false    215    2794    197            �   -   x�3�4���OIU��K��!3\R�������`������ �|	�      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   �   x����j�@�s�+�z�E�[�	�YL�p{2Yl�%D�>Q�A��:��Q�0�i����Z4ib�l�%G��:ja�?U�MYS�r���r=�/=Ch�+�)bk0���p��;z*7ϰ�͋!l���ZqQ��V��Bq�^��͸�/���S*�>��S���5,5����޴�Ah!yp��-������E"�:A
ik���OjY�      �      x������ � �      �      x������ � �      �   �   x���1�0@��9Ev��B)7�,AP���'b�����$�Y��u��X#ڵa��v
��&����U�R�RD�<�y�]�>��֭���_����2������z���3�����a\�h�F4�@�u�(1"��U	�JpU«`�k�X#�!��F�5B�b�k�X#�FBl$�FBl$�FBl$�^vι����      �      x������ � �      �      x������ � �      �   �  x��ӿn�0�Y~
��	ޑ�s3t��d̢�D�[Aз/� ���M����������ޯ}w��0���!�n�`� T�%�^�=��E���_�?<�v6{/�4|�Ʈ9��S�M�
R턱��2���飻u�y9���E�@(P�k�8櫘���֭�{�1�u�yA�ohsThP�\�T@ӆ6G���O�:�S�Ja �roN^c�;^���8���f�]�r����M{¢��n��-�B�d5+��ED�vT'U�:�@eKr�HS�A�@ᐖ$bˢi�$B�t]J��iY�"]�L/�(��0ҵ,Z��~�8��x�t�ˈ�7���y��6��*oޏ��r��#NG4�}��8��KhYTh�UIBTZWs���,*@�-�j� �p�
��ӼYaA��2����v� �HrM      �   �   x�}�˱D!D��	\����2���x���u�F��0.B�͇}�4tD#�}�	ha���,4�gVX/.La�Q�F?h�m�so��PG=���a��ƅ@N�~Ud�$̃�_���U�f٫ppNoٱI���zB�E����Ȼ�N�)���&*K�G�>�2t�;���i���6A� ������M3-�Gka�]�2x�8�l�i.���̞��Ⱦ��Ho��> J���      �   @   x�3���s�ttv�����J8��t�u���L����M�,�L8c� �+F��� �5      �      x������ � �      �   +   x�3�4��N�-H�I����2r=�JR�R�KRS��\1z\\\ 8�v      �   8   x�3���/K-*rH�M���K���,H,..�/J�2�,I-.�ʀ�RSKR�b���� B��      �      x�3�4���̲T�?8����� m��      �   @  x���=o�@��T�ѣ+((������(�ii�8���r�@T4P!AB�
( B��Aޟ��q�M H�H�ƫ}��;�)\?��V7�.ʽC��\�ʺ~�\U��fA@0�Q����5
,3��[�{���_ RD�8��#��h�h�<��ν7>��$�+�[��G���]�+����rT"�w��Bk���E5D�tН(��~��(Q��lDV��{�D}Q^�j��fe�8m:U],�wxl�EV�䰓%��0U�)`&KJ�h�����(��鹜�9��͗��|��6�Jn�sz��g@34��D�sN[�=D(���_JF%�7C$;p]��|��C����^��GNo6r�t=���w'(���"G�ѐb��O�����K9����jn�>�|-7�r�1��9=���8��08��\ޟ��������Y4Ł��ᓌDPV����%�����$LPH�08�~�_��íS�k�]?� wS��Ŷr`o��p?"\�Ӑu�/{c­�����O0��v�4�Ǆ{E�o�1�v���~��Q�a�ܴ���D@��d�u�-5�L~�G�     